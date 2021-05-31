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
package domainapp.modules.simple.dom.reclamos;

import org.apache.isis.applib.query.QueryDefault;

import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.value.Blob;


import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@DomainService(
        nature = NatureOfService.DOMAIN,
        repositoryFor = Reclamo.class)


public class ReclamoRepositorio {

    @Programmatic
    public Reclamo create(
            final String nombre,
            final String apellido,
            final String direccion,
            final String telefono) {

        final Reclamo Reclamo = new Reclamo(nombre,apellido,direccion,telefono);
        repositoryService.persist(Reclamo);
        return Reclamo;
    }

    @Programmatic
    public List<Reclamo> ListarActivos() {
        return repositoryService.allMatches(
                new QueryDefault<>(
                        Reclamo.class,
                        "findAllActives"));
    }

    @Programmatic
    public List<Reclamo> ListarInactivos() {
        return repositoryService.allMatches(
                new QueryDefault<>(
                        Reclamo.class,
                        "findAllInactives"));
    }

    @Programmatic
    public Blob generarReporteReclamos()throws  IOException {

        List<Reclamo> Reclamos = new ArrayList<Reclamo>();

        //EjecutarReportes ejecutarReportes=new EjecutarReportes();

        Reclamos = repositoryService.allInstances(Reclamo.class);

        //return ejecutarReportes.ListadoReclamosPDF(Reclamos);
        return null;
    }

    /*
    @javax.inject.Inject
    EjecutarReportes ejecutarReportes;
*/
    @javax.inject.Inject
    RepositoryService repositoryService;



}