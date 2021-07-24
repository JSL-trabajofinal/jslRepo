/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package domainapp.modules.simple.dom.usuario;

import domainapp.modules.simple.dom.reclamo.Reclamo;
import domainapp.modules.simple.dom.reclamo.TipoReclamo;
import domainapp.modules.simple.dom.reclamo.Estado;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.services.factory.FactoryService;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.applib.services.title.TitleService;
import com.google.common.collect.ComparisonChain;
import org.joda.time.LocalDate;
import javax.inject.Inject;
import javax.jdo.annotations.*;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.regex.Pattern;

@PersistenceCapable(identityType= IdentityType.DATASTORE, schema="simple")
@DatastoreIdentity(strategy=IdGeneratorStrategy.IDENTITY, column="Usuario_ID")
@Sequence(name="usuarioseq",datastoreSequence = "YOUR_SEQUENCE_NAME",strategy=SequenceStrategy.CONTIGUOUS)
@Queries({
        @Query(name = "findByApellido", language = "JDOQL",
                value = "SELECT "
                        + "FROM domainapp.modules.simple.dom.usuario.Usuario "
                        + "WHERE apellido == :apellido "),

        @Query(name = "findByDni", language = "JDOQL",
                value = "SELECT "
                        + "FROM domainapp.modules.simple.dom.usuario.Usuario "
                        + "WHERE dni == :dni ")
})

@DomainObject(auditing = Auditing.ENABLED)
@DomainObjectLayout()  // causes UI events to be triggered
@Unique(name="Usuario_dni_UNQ", members = {"dni"})
@Getter @Setter
public class Usuario {

    @Column(allowsNull = "false")
    @Property()
    private Integer dni;


    @Column(allowsNull = "false")
    @Property()
    @Title(prepend = "Usuario: ")
    private String apellido;

    @Column(allowsNull = "false")
    @Property()
    /*    @Title(prepend = " ")*/
    private String nombre;

    @Column(allowsNull = "true")

    @Property(editing = Editing.ENABLED,
            regexPattern = "[0-9]",
            regexPatternReplacement = "Solo numeros y sin espacios"
    )
    private String telefono;

    @Column(allowsNull = "false")
    @Property(editing = Editing.ENABLED,
            regexPattern = "(\\w+\\.)*\\w+@(\\w+\\.)+[A-Za-z]+",
            regexPatternFlags= Pattern.CASE_INSENSITIVE,
            regexPatternReplacement = "Debe ser un email valido (contiene un '@' simbolo)"
    )
    private String email;

    @Column(allowsNull = "false")
    @Property()
    private String direccion;

    @Column(allowsNull = "false")
    @Property()
    private String barrio;

    @Collection()
    @Persistent(mappedBy = "usuario", dependentElement = "true")
    private List<Reclamo> reclamo = new ArrayList<>();

    @Getter @Setter
    private SortedSet<Usuario> usuario = new TreeSet<>();

    public Usuario(Integer dni, String apellido, String nombre, String telefono, String email, String direccion, String barrio) {
        this.dni = dni;
        this.apellido = apellido;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.barrio = barrio;
    }

    public Usuario(List<Reclamo> reclamo) {
        this.reclamo = reclamo;
    }

    public Usuario() {}

    @Action()
    @ActionLayout(named = "Editar")
    public Usuario update(
            @ParameterLayout(named = "Numero de telefono: ")
            final String telefono,

            @ParameterLayout(named = "Email: ")
            final String email,

            @ParameterLayout(named = "Direccion: ")
            final String direccion
    ){
        this.setTelefono(telefono);
        this.setDireccion(direccion);
        this.setEmail(email);
        return this;
    }

    public String default0Update() { return getTelefono(); }
    public String default1Update()  { return getDireccion(); }
    public String default2Update() { return getEmail(); }


    @Action()
    @ActionLayout(named = "Cargar Reclamo")
    public Usuario addReclamo(
            @ParameterLayout(named="Fecha:") final LocalDate fecha,
            @ParameterLayout(named="Tipo de Reclamo: ") final TipoReclamo tipoReclamo
    ){
        final Reclamo reclamo = factoryService.instantiate(Reclamo.class);
        reclamo.setFecha(fecha);
        reclamo.setTipoReclamo(tipoReclamo);
        reclamo.setNotes("");
        reclamo.setEstado(Estado.Sin_Asignar);
        reclamo.setEstado(Estado.Asignado);
        getReclamo().add(reclamo);
        repositoryService.persist(reclamo);
        return this;
    }


    @Inject @NotPersistent
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
    TitleService titleService;

    @Inject @NotPersistent
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
    FactoryService factoryService;

    @javax.inject.Inject
    @NotPersistent
    @lombok.Getter(AccessLevel.NONE) @lombok.Setter(AccessLevel.NONE)
    RepositoryService repositoryService;

}