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
package domainapp.modules.simple.dom.cuadrilla;


import domainapp.modules.simple.dom.operador.Operador;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.repository.RepositoryService;

import java.util.List;

@DomainService(
        nature = NatureOfService.DOMAIN,
        repositoryFor = Operador.class)


public class CuadrillaRepositorio {

    @Programmatic
    public Cuadrilla create(
            final String nombre,
            final String apellido,
            final String usuario ,
            final String contraseña) {

        final Cuadrilla Ayudante = new Cuadrilla(nombre,apellido,usuario,contraseña);
        repositoryService.persist(Ayudante);
        return Ayudante;
    }

    @Programmatic
    public List<Cuadrilla> ListarActivos() {
        return repositoryService.allMatches(
                new QueryDefault<>(
                        Cuadrilla.class,
                        "findAllActives"));
    }

    @Programmatic
    public List<Cuadrilla> ListarInactivos() {
        return repositoryService.allMatches(
                new QueryDefault<>(
                        Cuadrilla.class,
                        "findAllInactives"));
    }

    @javax.inject.Inject
    RepositoryService repositoryService;
}