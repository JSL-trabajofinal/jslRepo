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

import domainapp.modules.simple.dom.reclamos.QReclamo;
import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.services.eventbus.ActionDomainEvent;
import org.apache.isis.applib.services.jdosupport.IsisJdoSupport;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.datanucleus.query.typesafe.TypesafeQuery;

import java.util.List;

@DomainService(
        nature = NatureOfService.VIEW_MENU_ONLY,
        objectType = "simple.ReclamoMenu",
        repositoryFor = Operador.class
)
@DomainServiceLayout(
        named = "Operador",
        menuOrder = "10"
)
public class OperadorRepositorio {

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    @MemberOrder(sequence = "1")
    public List<Operador> listAll() {
        return repositoryService.allInstances(Operador.class);
    }


    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    @MemberOrder(sequence = "2")
    public List<Operador> findByName(
            @ParameterLayout(named="Operador: ")final String operador
    ) {
        TypesafeQuery<Operador> q = isisJdoSupport.newTypesafeQuery(Operador.class);
        final QOperador cand = QOperador.candidate();
        q = q.filter(
                cand.nombre.indexOf(q.stringParameter("operador")).ne(-1).or(
                cand.apellido.indexOf(q.stringParameter("operador")).ne(-1)
                )
        );
        return q.setParameter("operador", operador)
                .executeList();
    }

    @Programmatic
    public Operador findByNombreYApellido(
            final String nombre,
            final String apellido
    ) {
        TypesafeQuery<Operador> q = isisJdoSupport.newTypesafeQuery(Operador.class);
        final QOperador cand = QOperador.candidate();
        q = q.filter(
                cand.nombre.eq(q.stringParameter("nombre")).and(
                cand.apellido.eq(q.stringParameter("apellido"))
                )
        );
        return q.setParameter("nombre", nombre)
                .setParameter("apellido", apellido)
                .executeUnique();
    }

    @Programmatic
    public void ping() {
        TypesafeQuery<Operador> q = isisJdoSupport.newTypesafeQuery(Operador.class);
        final QOperador candidate = QOperador.candidate();
        q.range(0,2);
        q.orderBy(candidate.nombre.asc());
        q.executeList();
    }

    public static class CreateDomainEvent extends ActionDomainEvent<OperadorRepositorio> {}
    @Action(domainEvent = CreateDomainEvent.class)
    @MemberOrder(sequence = "3")
    public Operador create(
            @ParameterLayout(named="Nombre")final String nombre,
            @ParameterLayout(named="Apellido")final String apellido,
            @ParameterLayout(named="Usuario")final String usuario,
            @ParameterLayout(named="Contraseña")final String contraseña
            ){
        return repositoryService.persist(new Operador(nombre, apellido, usuario, contraseña));
    }

    @javax.inject.Inject
    RepositoryService repositoryService;

    @javax.inject.Inject
    IsisJdoSupport isisJdoSupport;

}
