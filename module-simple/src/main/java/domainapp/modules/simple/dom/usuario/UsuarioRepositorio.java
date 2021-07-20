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

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.services.jdosupport.IsisJdoSupport;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.datanucleus.query.typesafe.TypesafeQuery;
import javax.inject.Inject;
import java.util.List;

<<<<<<< HEAD
=======
import org.apache.isis.applib.services.jdosupport.IsisJdoSupport;

import org.datanucleus.query.typesafe.TypesafeQuery;

import javax.inject.Inject;


>>>>>>> develop
@DomainService(nature = NatureOfService.DOMAIN, repositoryFor = Usuario.class)
public class UsuarioRepositorio {

    public Usuario create(
            final Integer dni,
            final String apellido,
            final String nombre,
            final String telefono,
            final String email,
            final String direccion,
            final String barrio
    ) {
        final Usuario usuario = new Usuario(
                dni,
                apellido.toUpperCase(),
                nombre.toUpperCase(),
                telefono,
                email,
                direccion,
                barrio);
        repositoryService.persist(usuario);
        return usuario;
    }





    @Programmatic
    public List<Usuario> listAll() {
<<<<<<< HEAD
        return repositoryService.allInstances(Usuario.class);
=======
        return repositoryService.allMatches(
                new QueryDefault<>(Usuario.class,
                        "listAll"));
>>>>>>> develop
    }


    @Programmatic
<<<<<<< HEAD
    public List<Usuario> findByApellido( final String apellido
=======
    public List<Usuario> findByApellido(
            //@ParameterLayout(named = "Apellido")
            final String apellido
>>>>>>> develop
    ) {
        TypesafeQuery<Usuario> q = isisJdoSupport.newTypesafeQuery(Usuario.class);
        final QUsuario cand = QUsuario.candidate();
        q = q.filter(
                cand.apellido.indexOf(q.stringParameter("apellido")).ne(-1)
        );
        return q.setParameter("apellido", apellido.toUpperCase())
                .executeList();
<<<<<<< HEAD
    }

    @Programmatic
    public List<Usuario> findByDni( final Integer dni
    ) {
        TypesafeQuery<Usuario> q = isisJdoSupport.newTypesafeQuery(Usuario.class);
        final QUsuario cand = QUsuario.candidate();
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
=======
    }

    @Programmatic
    public List<Usuario> findByDni(
            //@ParameterLayout(named = "Dni")
            final Integer dni
    ) {
        TypesafeQuery<Usuario> q = isisJdoSupport.newTypesafeQuery(Usuario.class);
        final QUsuario cand = QUsuario.candidate();
        q = q.filter(
                cand.dni.eq(q.integerParameter("dni"))
        );
        return q.setParameter("dni", dni)
                .executeList();
    }


    @Inject
    RepositoryService repositoryService;

    @javax.inject.Inject
    IsisJdoSupport isisJdoSupport;

}
>>>>>>> develop
