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
package domainapp.modules.simple.dom.tecnico;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.services.jdosupport.IsisJdoSupport;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.datanucleus.query.typesafe.TypesafeQuery;

import javax.inject.Inject;
import java.util.List;

@DomainService(
        nature = NatureOfService.DOMAIN,
        repositoryFor = Tecnico.class)


public class TecnicoRepositorio {

    @Programmatic
    public Tecnico create(
            final String nombre,
            final String apellido,
            final Integer dni,
            final Integer telefono,
            final Cuadrilla cuadrilla) {

        final Tecnico Tecnico = new Tecnico(nombre, apellido, dni, telefono, cuadrilla);
        repositoryService.persist(Tecnico);
        return Tecnico;
    }

    public List<Tecnico> listAll() {
        return repositoryService.allInstances(Tecnico.class);
    }


    @Programmatic
    public List<Tecnico> findByApellido(
            final String apellido
    ) {
        TypesafeQuery<Tecnico> q = isisJdoSupport.newTypesafeQuery(Tecnico.class);
        final QTecnico cand = QTecnico.candidate();
        q = q.filter(
                cand.apellido.indexOf(q.stringParameter("apellido")).ne(-1)
        );
        return q.setParameter("apellido", apellido.toUpperCase())
                .executeList();
    }

    @Programmatic
    public List<Tecnico> findByDni(
            final Integer dni
    ) {
        TypesafeQuery<Tecnico> q = isisJdoSupport.newTypesafeQuery(Tecnico.class);
        final QTecnico cand = QTecnico.candidate();
        q = q.filter(
                cand.dni.eq(q.integerParameter("dni"))
        );
        return q.setParameter("dni", dni)
                .executeList();
    }

    @Inject
    RepositoryService repositoryService;

    @Inject
    IsisJdoSupport isisJdoSupport;
}
