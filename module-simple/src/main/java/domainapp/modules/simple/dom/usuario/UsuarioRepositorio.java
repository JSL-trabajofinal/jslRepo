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


import domainapp.modules.simple.dom.impl.*;
import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.services.eventbus.ActionDomainEvent;
import org.apache.isis.applib.services.jdosupport.IsisJdoSupport;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.datanucleus.query.typesafe.TypesafeQuery;

import java.util.List;

@DomainService(
        nature = NatureOfService.VIEW_MENU_ONLY,
        objectType = "simple.UsuarioMenu",
        repositoryFor = Usuario.class
)
@DomainServiceLayout(
        named = "Simple Objects",
        menuOrder = "10"
)
public class UsuarioRepositorio {

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    @MemberOrder(sequence = "1")
    public List<Usuario> listAll() {
        return repositoryService.allInstances(Usuario.class);
    }


    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    @MemberOrder(sequence = "2")
    public List<Usuario> findByName(
            @ParameterLayout(named="Name")
            final String name
    ) {
        TypesafeQuery<Usuario> q = isisJdoSupport.newTypesafeQuery(Usuario.class);
        final QUsuario cand = QUsuario.candidate();
        q = q.filter(
                cand.nombre.indexOf(q.stringParameter("name")).ne(-1)
        );
        return q.setParameter("name", name)
                .executeList();
    }

    @Programmatic
    public Usuario findByNameExact(final String name) {
        TypesafeQuery<Usuario> q = isisJdoSupport.newTypesafeQuery(Usuario.class);
        final QUsuario cand = QUsuario.candidate();
        q = q.filter(
                cand.nombre.eq(q.stringParameter("nombre"))
        );
        return null; //q.setParameter()
                //.executeUnique();
    }

    @Programmatic
    public void ping() {
        TypesafeQuery<Usuario> q = isisJdoSupport.newTypesafeQuery(Usuario.class);
        final QUsuario candidate = QUsuario.candidate();
        q.range(0,2);
        q.orderBy(candidate.nombre.asc());
        q.executeList();
    }

    public static class CreateDomainEvent extends ActionDomainEvent<UsuarioRepositorio> {}
    @Action(domainEvent = CreateDomainEvent.class)
    @MemberOrder(sequence = "3")
    public Usuario create(
            @ParameterLayout(named="Dni") final String dni,
            @ParameterLayout(named="Apellido") final String apellido,
            @ParameterLayout(named="Nombre") final String nombre) {
        return repositoryService.persist(new Usuario(dni, apellido, nombre));
    }

    @javax.inject.Inject
    RepositoryService repositoryService;

    @javax.inject.Inject
    IsisJdoSupport isisJdoSupport;

}
