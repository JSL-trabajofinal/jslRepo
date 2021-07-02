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
package domainapp.modules.simple.dom.reclamo;



import domainapp.modules.simple.dom.usuario.Usuario;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.apache.isis.applib.annotation.*;

import org.apache.isis.applib.services.factory.FactoryService;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.schema.utils.jaxbadapters.JodaDateTimeStringAdapter;
import org.joda.time.LocalDate;

import javax.jdo.annotations.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;



@PersistenceCapable(identityType= IdentityType.DATASTORE, schema="simple", table="reclamos")
@javax.jdo.annotations.DatastoreIdentity(strategy=javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column="idReclamo")
@DomainObject(auditing = Auditing.ENABLED)
@DomainObjectLayout()  // causes UI events to be triggered
@lombok.Getter @lombok.Setter
public class Reclamo {

    @javax.jdo.annotations.Column(allowsNull = "false")
    @Property()
    @Getter
    @Setter
    private Usuario usuario;

    @Column(allowsNull = "true")
    @lombok.NonNull
    @Property(editing = Editing.ENABLED)
    @XmlJavaTypeAdapter(JodaDateTimeStringAdapter.ForJaxb.class)
    private LocalDate fecha = LocalDate.now();


    @javax.jdo.annotations.Column(allowsNull = "true")
    @Property()
    @Title(prepend = "Reclamo: ")
    private TipoReclamo tipoReclamo;

    @javax.jdo.annotations.Column(allowsNull = "true", length = 4000)
    @Property(editing = Editing.ENABLED)
    private String notes;


    @javax.jdo.annotations.Column(allowsNull = "false")
    @lombok.NonNull
    @Property()
    private Estado estado;

    public TipoReclamo title() {
        return getTipoReclamo();
    }


    @Action()
    @ActionLayout(named = "Editar")
    public Reclamo update(
            @ParameterLayout(named = "Nombre: ")
            final TipoReclamo tipoReclamo
    ){
        this.setTipoReclamo(tipoReclamo);
        return this;
    }

    public TipoReclamo default0Update() { return getTipoReclamo(); }

    @javax.inject.Inject
    @javax.jdo.annotations.NotPersistent
    @lombok.Getter(AccessLevel.NONE) @lombok.Setter(AccessLevel.NONE)
    FactoryService factoryService;

    @javax.inject.Inject
    @javax.jdo.annotations.NotPersistent
    @lombok.Getter(AccessLevel.NONE) @lombok.Setter(AccessLevel.NONE)
    RepositoryService repositoryService;
}