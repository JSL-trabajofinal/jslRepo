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
import domainapp.modules.simple.dom.persona.Persona;
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
@javax.jdo.annotations.Unique(name="Operador_dni_UNQ", members = {"dni"})
@DomainObject(auditing = Auditing.ENABLED)
@DomainObjectLayout()  // causes UI events to be triggered
@lombok.Getter @lombok.Setter

public class Operador extends Persona {


    public Operador(String nombre, String apellido, Integer dni, Integer telefono) {
        super(nombre, apellido, dni, telefono);
    }


    @javax.jdo.annotations.Column(allowsNull="true")
    @Property()
    private Boolean activo = true;


    @Action(semantics = SemanticsOf.IDEMPOTENT_ARE_YOU_SURE, publishing = Publishing.ENABLED, associateWith = "activo")
    public Operador updateActivo()
    {
        if(getActivo()){ setActivo(false); }
        else{ setActivo(true); }
        return this;
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