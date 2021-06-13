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


import com.google.common.collect.ComparisonChain;
import domainapp.modules.simple.dom.usuario.Usuario;
import lombok.AccessLevel;
import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.services.message.MessageService;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.applib.services.title.TitleService;
import org.apache.isis.schema.utils.jaxbadapters.JodaDateTimeStringAdapter;
import org.joda.time.LocalDate;

import javax.jdo.annotations.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigInteger;

import static org.apache.isis.applib.annotation.CommandReification.ENABLED;


@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "simple")
@DatastoreIdentity(strategy = IdGeneratorStrategy.IDENTITY, column = "id")
@Sequence(name = "reclamosqe", datastoreSequence = "YOUR_SEQUENCE_NAME2", strategy = SequenceStrategy.CONTIGUOUS, initialValue = 100, allocationSize = 1)
@Version(strategy = VersionStrategy.DATE_TIME, column = "version")
@Queries({
        @Query(
                name = "find", language = "JDOQL",
                value = "SELECT "),
        @Query(
                name = "findLast", language = "JDOQL",
                value = "SELECT "
                        + "ORDER BY nroReclamo DESC"),
})

@Unique(name = "Reclamo_nombre_UNQ", members = {"nroReclamo"})
@DomainObject(auditing = Auditing.ENABLED)
@DomainObjectLayout(cssClassFa = "file-text-o")
@lombok.Getter
@lombok.Setter
@lombok.RequiredArgsConstructor
public class Reclamo implements Comparable<Reclamo> {

    @Column(allowsNull = "true", length = 10)
    @Property(editing = Editing.DISABLED)
    @Persistent(valueStrategy = IdGeneratorStrategy.SEQUENCE, sequence = "reclamoseq")
    @Title(prepend = "Nro Reclamo: ")
    private BigInteger nroReclamo;

    @Column(allowsNull = "true")
    @lombok.NonNull
    @Property(editing = Editing.ENABLED)
    @XmlJavaTypeAdapter(JodaDateTimeStringAdapter.ForJaxb.class)
    private LocalDate fecha = LocalDate.now();


    @Column(allowsNull = "false")
    @lombok.NonNull
    @lombok.Getter
    @lombok.Setter
    @Property(editing = Editing.DISABLED)
    private TipoReclamo tipoReclamo;


    @Column(allowsNull = "false")
    @lombok.NonNull
    @lombok.Getter
    @lombok.Setter
    @Property(editing = Editing.DISABLED)
    private Usuario usuario;


    @Column(allowsNull = "true")
    @lombok.NonNull
    @Property(editing = Editing.DISABLED)
    private domainapp.modules.simple.dom.reclamo.Estado estado = domainapp.modules.simple.dom.reclamo.Estado.Espera;

    public Reclamo(Usuario usuario) {
    }

    @Action(semantics = SemanticsOf.IDEMPOTENT_ARE_YOU_SURE, command = ENABLED, publishing = Publishing.ENABLED)
    public Reclamo anularReclamo() {
        if (getEstado().equals(domainapp.modules.simple.dom.reclamo.Estado.Espera)) {
            setEstado(domainapp.modules.simple.dom.reclamo.Estado.Anulado);
            messageService.warnUser("Se ha Anulado el reclamo");
        } else if (getEstado().equals(domainapp.modules.simple.dom.reclamo.Estado.Cerrado)) {
            messageService.warnUser("No se puede Anular un Reclamo Cerrado");
        } else {
            messageService.warnUser("El reclamo ya estaba previamente Anulado");
        }
        return this;
    }



    @Override
    public String toString() {
        return getNroReclamo().toString();
    }

    @Override
    public int compareTo(final Reclamo other) {
        return ComparisonChain.start()
                .compare(this.getNroReclamo().toString(), other.getNroReclamo().toString())
                .result();
    }

    @javax.inject.Inject
    @NotPersistent
    @lombok.Getter(AccessLevel.NONE)
    @lombok.Setter(AccessLevel.NONE)
    RepositoryService repositoryService;

    @javax.inject.Inject
    @NotPersistent
    @lombok.Getter(AccessLevel.NONE)
    @lombok.Setter(AccessLevel.NONE)
    ReclamoRepositorio repositoryReclamo;

    @javax.inject.Inject
    @NotPersistent
    @lombok.Getter(AccessLevel.NONE)
    @lombok.Setter(AccessLevel.NONE)
    TitleService titleService;

    @javax.inject.Inject
    @NotPersistent
    @lombok.Getter(AccessLevel.NONE)
    @lombok.Setter(AccessLevel.NONE)
    MessageService messageService;



}
