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


import domainapp.modules.simple.dom.reclamo.Reclamo;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.repository.RepositoryService;

import java.util.List;

@DomainService(
        nature = NatureOfService.DOMAIN,
        repositoryFor = Reclamo.class)


public class TecnicoRepositorio {

    @Programmatic
    public Tecnico create(
            final String nombre,
            final String apellido,
            final String usuario,
            final String contraseña) {

        final Tecnico Tecnico = new Tecnico(nombre,apellido,usuario,contraseña);
        repositoryService.persist(Tecnico);
        return Tecnico;
    }

    @Programmatic
    public List<Tecnico> ListarActivos() {
        return repositoryService.allMatches(
                new QueryDefault<>(
                        Tecnico.class,
                        "findAllActives"));
    }

    @Programmatic
    public List<Tecnico> ListarInactivos() {
        return repositoryService.allMatches(
                new QueryDefault<>(
                        Tecnico.class,
                        "findAllInactives"));
    }

    @Programmatic
    public List<Reclamo> listAll(){
        return repositoryService.allInstances(Reclamo.class);
    }


    @javax.inject.Inject
    RepositoryService repositoryService;
}