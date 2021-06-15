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

import com.google.common.collect.ComparisonChain;
import domainapp.modules.simple.dom.reclamo.Reclamo;
import domainapp.modules.simple.dom.reclamo.ReclamoRepositorio;
import lombok.AccessLevel;
import lombok.NonNull;
import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.services.message.MessageService;
import org.apache.isis.applib.services.title.TitleService;

import javax.jdo.annotations.*;
import java.math.BigInteger;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.regex.Pattern;

import static org.apache.isis.applib.annotation.CommandReification.ENABLED;

@PersistenceCapable(identityType=IdentityType.DATASTORE, schema = "simple")
@DatastoreIdentity(strategy= IdGeneratorStrategy.IDENTITY, column="id")
@Sequence(name="usuarioseq", datastoreSequence="YOUR_SEQUENCE_NAME", strategy=SequenceStrategy.CONTIGUOUS, initialValue = 10000, allocationSize = 1)
@Version(strategy= VersionStrategy.DATE_TIME, column="version")
@Queries({
        @Query(
                name = "findAllActives", language = "JDOQL",
                value = "SELECT "
                        + "FROM domainapp.modules.simple.dom.usuario.Usuario "
                        + "WHERE activo == true "),
        @Query(
                name = "findAllInactives", language = "JDOQL",
                value = "SELECT "
                        + "FROM domainapp.modules.simple.dom.usuario.Usuario "
                        + "WHERE activo == false "),
})

@Unique(name="Usuario_nombre_UNQ", members = {"dni"})
@DomainObject(auditing = Auditing.ENABLED)
@DomainObjectLayout(cssClassFa="user-circle" )  // causes UI events to be triggered
@lombok.Getter @lombok.Setter
@lombok.RequiredArgsConstructor
public class Usuario implements Comparable<Usuario> {

    @Column(allowsNull = "true", length = 10)
    @Property(editing = Editing.DISABLED)
    @Persistent(valueStrategy=IdGeneratorStrategy.SEQUENCE, sequence="usuarioseq")
    private BigInteger nroUsuario;

    @Column(allowsNull = "true", length = 13)
    @NonNull
    @Property(editing = Editing.ENABLED)
    private String dni;

    @Column(allowsNull = "false", length = 40)
    @NonNull
    @PropertyLayout(named="Nombre")
    @Property(editing = Editing.ENABLED)
    @Title(prepend = "Usuario ")
    private String nombre;

    @Column(allowsNull = "true", length = 40)
    @NonNull
    @Property(editing = Editing.ENABLED,
            regexPattern = "[0-9]+",
            regexPatternReplacement = "Solo numeros y sin espacios"
    )
    private String telefono;

    @Column(allowsNull = "true", length = 40)
    @NonNull
    @Property(editing = Editing.ENABLED,
            regexPattern = "(\\w+\\.)*\\w+@(\\w+\\.)+[A-Za-z]+",
            regexPatternFlags= Pattern.CASE_INSENSITIVE,
            regexPatternReplacement = "Debe ser un email valido (contiene un '@' simbolo)"
    )
    private String email;

    @Column(allowsNull = "true", length = 40)
    @NonNull
    @Property(editing = Editing.ENABLED)
    private String direccion;

    @Column(allowsNull = "true")
    @Property()
    private Boolean activo = true;


    @Persistent(
            mappedBy = "usuario",
            dependentElement = "false"
    )
    @CollectionLayout(defaultView = "table")
    @lombok.Getter @lombok.Setter
    private SortedSet<Reclamo> reclamos = new TreeSet<Reclamo>();

    @Action(
            semantics = SemanticsOf.IDEMPOTENT_ARE_YOU_SURE,
            associateWith = "simple"
    )
    public Usuario nuevoReclamo() {
        if(activo){
            repositoryReclamo.create(this);
        }
        else{
            messageService.warnUser(
                    "El usuario "+ this.getNombre() + " se encuentra Inactivo, no puede crear nuevo reclamo");
        }
        return this;
    }

    @Action(semantics = SemanticsOf.IDEMPOTENT_ARE_YOU_SURE, command = ENABLED, publishing = Publishing.ENABLED, associateWith = "activo")
    public Usuario updateActivo()
    {
        if(getActivo()){ setActivo(false); }
        else{ setActivo(true); }
        return this;
    }

    @Override
    public String toString() {
        return getNombre();
    }

    public int compareTo(final Usuario other) {
        return ComparisonChain.start()
                .compare(this.getNombre(), other.getNombre())
                .result();
    }

    @NotPersistent
    @javax.inject.Inject
    @lombok.Getter(AccessLevel.NONE) @lombok.Setter(AccessLevel.NONE)
    ReclamoRepositorio repositoryReclamo;

    @javax.inject.Inject
    @NotPersistent
    @lombok.Getter(AccessLevel.NONE) @lombok.Setter(AccessLevel.NONE)
    domainapp.modules.simple.dom.usuario.UsuarioRepositorio repositoryUsuario;

    @javax.inject.Inject
    @NotPersistent
    @lombok.Getter(AccessLevel.NONE) @lombok.Setter(AccessLevel.NONE)
    TitleService titleService;

    @javax.inject.Inject
    @NotPersistent
    @lombok.Getter(AccessLevel.NONE) @lombok.Setter(AccessLevel.NONE)
    MessageService messageService;

}