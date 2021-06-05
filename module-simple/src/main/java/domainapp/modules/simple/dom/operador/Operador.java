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
package domainapp.modules.simple.dom.operador;

import com.google.common.collect.ComparisonChain;
import jdk.jfr.Enabled;
import lombok.AccessLevel;
import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.services.message.MessageService;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.applib.services.title.TitleService;

import javax.jdo.annotations.*;

@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE, schema = "simple")
@javax.jdo.annotations.DatastoreIdentity(strategy= IdGeneratorStrategy.IDENTITY, column="id")
@javax.jdo.annotations.Version(strategy= VersionStrategy.DATE_TIME, column="version")
@Sequence(name="operador", datastoreSequence="YOUR_SEQUENCE_NAME",strategy=SequenceStrategy.CONTIGUOUS,allocationSize=1)
@Queries({
        @Query(
                name="findAllActives", language="JDOQL",
                value="SELECT "
                + "FROM domainapp.modules.simple.dom.operador.Operador "
                + "WHERE activo == true "),
        @Query(
                name="findAllInactives", language="JDOQL",
                value="SELECT "
                + "FROM domainapp.modules.simple.dom.operador.Operador "
                + "WHERE activo == false "),
})
@javax.jdo.annotations.Unique(name="Operador_usuario_UNQ", members = {"usuario"})
@DomainObject(auditing = Auditing.ENABLED)
@DomainObjectLayout()  // causes UI events to be triggered
@lombok.Getter @lombok.Setter
@lombok.RequiredArgsConstructor
public class Operador implements Comparable<Operador> {

    @javax.jdo.annotations.Column(allowsNull = "true", length = 40)
    @lombok.NonNull
    @Property(editing = Editing.ENABLED) // editing disabled by default, see isis.properties
    @Title(prepend = "Nombre: ")
    @MemberOrder(sequence = "1")
    private String nombre;


    @javax.jdo.annotations.Column(allowsNull = "true", length = 40)
    @lombok.NonNull
    @Property(editing = Editing.ENABLED) // editing disabled by default, see isis.properties
    @Title(prepend = ". apellido ")
    @MemberOrder(sequence = "2")
    private String apellido;


    @javax.jdo.annotations.Column(allowsNull = "false", length = 40)
    @lombok.NonNull
    @Property(editing = Editing.ENABLED) // editing disabled by default, see isis.properties
    @Title(prepend = ". usuario: ")
    @MemberOrder(sequence = "3")
    private String usuario;


    @javax.jdo.annotations.Column(allowsNull = "false", length = 40)
    @lombok.NonNull
    @Property(editing = Editing.ENABLED) // editing disabled by default, see isis.properties
    @Title(prepend = ". contraseña:  ")
    @MemberOrder(sequence = "4")
    private String contraseña;

    @javax.jdo.annotations.Column(allowsNull="true")
    @Property()
    private Boolean activo = true;


    public String ReporNombre(){ return this.nombre; }
    public String ReporApellido(){ return this.apellido; }
    public String ReporUsuario(){ return this.usuario; }
    public String ReporContraseña(){ return this.contraseña; }
    public String ReporActivo(){ return this.activo.toString(); }


    @Action(semantics = SemanticsOf.IDEMPOTENT_ARE_YOU_SURE, publishing = Publishing.ENABLED, associateWith = "activo")
    public Operador updateActivo()
    {
        if(getActivo()){ setActivo(false); }
        else{ setActivo(true); }
        return this;
    }

/*
    @Action(semantics = IDEMPOTENT, command = ENABLED, publishing = Publishing.ENABLED, associateWith = "name")
    public Reclamo updateName(
            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Name")
            final String name) {
        setName(name);
        return this;
    }
ç
    @Action(semantics = NON_IDEMPOTENT_ARE_YOU_SURE)
    public void delete() {
        final String title = titleService.titleOf(this);
        messageService.informUser(String.format("'%s' deleted", title));
        repositoryService.remove(this);
    }

*/
    @Override
    public String toString() {
        return getNombre()+" "+getApellido()+ " "+getUsuario()+" "+getContraseña();
    }

    public int compareTo(final Operador other) {
        return ComparisonChain.start()
                .compare(this.getNombre(), other.getNombre())
                .result();
    }

    @javax.inject.Inject
    @javax.jdo.annotations.NotPersistent
    @lombok.Getter(AccessLevel.NONE) @lombok.Setter(AccessLevel.NONE)
    RepositoryService repositoryService;

    @javax.inject.Inject
    @javax.jdo.annotations.NotPersistent
    @lombok.Getter(AccessLevel.NONE) @lombok.Setter(AccessLevel.NONE)
    TitleService titleService;

    @javax.inject.Inject
    @javax.jdo.annotations.NotPersistent
    @lombok.Getter(AccessLevel.NONE) @lombok.Setter(AccessLevel.NONE)
    MessageService messageService;

}