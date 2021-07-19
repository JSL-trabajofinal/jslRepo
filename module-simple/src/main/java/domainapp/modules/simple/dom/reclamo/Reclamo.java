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

<<<<<<< HEAD
import domainapp.modules.simple.dom.cuadrilla.Cuadrilla;
import domainapp.modules.simple.dom.cuadrilla.CuadrillaRepositorio;
=======


>>>>>>> develop
import domainapp.modules.simple.dom.usuario.Usuario;

import lombok.AccessLevel;
import lombok.Getter;
<<<<<<< HEAD
import lombok.NonNull;
import lombok.Setter;
import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.services.factory.FactoryService;
import org.apache.isis.applib.services.message.MessageService;
=======
import lombok.Setter;
import org.apache.isis.applib.annotation.*;

import org.apache.isis.applib.services.factory.FactoryService;
>>>>>>> develop
import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.schema.utils.jaxbadapters.JodaDateTimeStringAdapter;
import org.joda.time.LocalDate;

import javax.inject.Inject;
import javax.jdo.annotations.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


<<<<<<< HEAD
@PersistenceCapable(identityType= IdentityType.DATASTORE, schema="simple", table="Reclamo")
@DatastoreIdentity(strategy=IdGeneratorStrategy.IDENTITY, column="Reclamo_ID")
@DomainObject(auditing = Auditing.ENABLED)
@DomainObjectLayout()  // causes UI events to be triggered
@Getter @Setter
public class Reclamo {

    @Column(allowsNull = "false", name = "Usuario_ID")
    @Persistent(mappedBy = "usuario", dependentElement = "true")
    @Property()
    @Getter @Setter
    private Usuario usuario;

    @Column(allowsNull = "false")
    @NonNull
    @PropertyLayout(named="Fecha Creacion del Reclamo: ")
    @Property(editing = Editing.DISABLED)
    @XmlJavaTypeAdapter(JodaDateTimeStringAdapter.ForJaxb.class)
    private LocalDate fecha = LocalDate.now();

    @Column(allowsNull = "true")
    @Property(editing = Editing.ENABLED)
    @Title(prepend = "Reclamo: ")
    private TipoReclamo tipoReclamo;

    @Column(allowsNull = "true", length = 4000)
    @Property(editing = Editing.ENABLED)
    private String notes;

    @Column(allowsNull = "false")
    @NonNull
    @Property()
    private Estado estado;

    public TipoReclamo title() {
        return getTipoReclamo();
    }

    @Programmatic
    public void CambiarEstado(Estado estado) {
        this.estado = estado;
    }

    @Action(semantics = SemanticsOf.IDEMPOTENT_ARE_YOU_SURE)
    public Reclamo Anular() {
        CambiarEstado(Estado.Anulado);
        if (getEstado().equals(Estado.Anulado)) {
            messageService.warnUser("Reclamo Anulado");
        } else {
            CambiarEstado(Estado.Anulado);
            messageService.warnUser("No puede anular dos veces el mismo reclamo");
        }
        return this;
=======

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
>>>>>>> develop
    }

    public Reclamo Cerrar() {
        if (getEstado().equals(Estado.Anulado)) {
            messageService.warnUser("No se puede cerrar un reclamo Anulado");
        } else {
            CambiarEstado(Estado.Cerrado);
            messageService.warnUser("Reclamo Cerrado");
        }
        return this;
    }

<<<<<<< HEAD
    public Reclamo Asignar(){
        CambiarEstado(Estado.En_Proceso);
        return this;
    }

    public Reclamo sinAsignar(){
        CambiarEstado(Estado.Sin_Asignar);
        return this;
    }

=======
>>>>>>> develop
    @Action()
    @ActionLayout(named = "Editar")
    public Reclamo update(
            @ParameterLayout(named = "Nombre: ")
<<<<<<< HEAD
            final TipoReclamo tipoReclamo,
            final Estado estado
    ){
        this.setTipoReclamo(tipoReclamo);
        this.CambiarEstado(estado);
=======
            final TipoReclamo tipoReclamo
    ){
        this.setTipoReclamo(tipoReclamo);
>>>>>>> develop
        return this;
    }

    public TipoReclamo default0Update() { return getTipoReclamo(); }
<<<<<<< HEAD

    /*@Action()
    @ActionLayout(named = "Asignar Cuadrilla")
    public Reclamo AsignarCuadrilla(
            @Parameter(optionality = Optionality.MANDATORY)
            @ParameterLayout(named = "Cuadrilla")
            final Cuadrilla cuadrilla) {

        this.AsignarCuadrilla() = cuadrilla;
        return this;
    }

    public List<Cuadrilla> choices0AsignarCuadrilla() { return CuadrillaRepositorio.ListarActivos(); }*/

    @Inject
    @NotPersistent
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
    MessageService messageService;

    @Inject
    @NotPersistent
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
    FactoryService factoryService;

    @Inject
    @NotPersistent
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
=======

    @javax.inject.Inject
    @javax.jdo.annotations.NotPersistent
    @lombok.Getter(AccessLevel.NONE) @lombok.Setter(AccessLevel.NONE)
    FactoryService factoryService;

    @javax.inject.Inject
    @javax.jdo.annotations.NotPersistent
    @lombok.Getter(AccessLevel.NONE) @lombok.Setter(AccessLevel.NONE)
>>>>>>> develop
    RepositoryService repositoryService;
}