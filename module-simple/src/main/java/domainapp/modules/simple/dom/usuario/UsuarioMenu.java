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


import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.services.jdosupport.IsisJdoSupport;
import org.datanucleus.query.typesafe.TypesafeQuery;

import java.util.List;
import java.util.regex.Pattern;

@DomainService(
        nature = NatureOfService.VIEW_MENU_ONLY,
        objectType = "simple.SimpleUsuarioMenu",
        repositoryFor = domainapp.modules.simple.dom.usuario.Usuario.class
)
@DomainServiceLayout(
        named = "Reclamos",
        menuOrder = "10"
)
public class UsuarioMenu {


    @Action()
    @ActionLayout(named = "Alta de Reclamo")
    @MemberOrder(sequence = "1")
    public domainapp.modules.simple.dom.usuario.Usuario create(

            @Parameter(maxLength = 40,
                    regexPattern = "^[0-9]{8}$",
                    regexPatternReplacement = "Solo numeros, sin puntos (total 8 numeros)"
            )
            @ParameterLayout(named = "Dni") final String dni,

            @Parameter(maxLength = 40,
                    regexPattern = "[A-Za-z\\s]+",
                    regexPatternFlags= Pattern.CASE_INSENSITIVE,
                    regexPatternReplacement = "Debe ser un nombre valido (solo letras)")
            @ParameterLayout(named = "Nombre") final String nombre,

            @Parameter(maxLength = 40,
                    regexPattern = "[0-9]+",
                    regexPatternReplacement = "Solo numeros y sin espacios"
            )
            @ParameterLayout(named = "Telefono") final String telefono,

            @Parameter(maxLength = 40,
                    regexPattern = "(\\w+\\.)*\\w+@(\\w+\\.)+[A-Za-z]+",
                    regexPatternFlags= Pattern.CASE_INSENSITIVE,
                    regexPatternReplacement = "Debe ser un email valido (contiene un '@' simbolo)")
            @ParameterLayout(named = "Email") final String email,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Direccion") final String direccion) {

        return repositoryUsuario.create(dni, nombre.toUpperCase(), telefono, email, direccion);
    }

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT,named = "Buscar usuario")
    @MemberOrder(sequence = "2")
    public List<domainapp.modules.simple.dom.usuario.Usuario> findByName(
            @Parameter(optionality = Optionality.MANDATORY)
            @ParameterLayout(named = "Nombre") final String nombre
    ) {
        TypesafeQuery<domainapp.modules.simple.dom.usuario.Usuario> q = isisJdoSupport.newTypesafeQuery(domainapp.modules.simple.dom.usuario.Usuario.class);
        final QUsuario cand = QUsuario.candidate();
        q = q.filter(
                cand.nombre.indexOf(q.stringParameter("name")).ne(-1)
        );
        return q.setParameter("name", nombre)
                .executeList();
    }

    @Programmatic
    public domainapp.modules.simple.dom.usuario.Usuario findByNameExact(final String nombre) {
        TypesafeQuery<domainapp.modules.simple.dom.usuario.Usuario> q = isisJdoSupport.newTypesafeQuery(domainapp.modules.simple.dom.usuario.Usuario.class);
        final QUsuario cand = QUsuario.candidate();
        q = q.filter(
                cand.nombre.eq(q.stringParameter("name"))
        );
        return q.setParameter("name", nombre)
                .executeUnique();
    }

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named = "Busqueda por DNI")
    @MemberOrder(sequence = "3")
    public domainapp.modules.simple.dom.usuario.Usuario findByCuilExact(
            @Parameter(optionality = Optionality.MANDATORY)
            @ParameterLayout(named = "Nro Dni") final String dni) {
        TypesafeQuery<domainapp.modules.simple.dom.usuario.Usuario> q = isisJdoSupport.newTypesafeQuery(domainapp.modules.simple.dom.usuario.Usuario.class);
        final QUsuario cand = QUsuario.candidate();
        q = q.filter(
                cand.dni.eq(q.stringParameter("dni"))
        );
        return q.setParameter("dni", dni)
                .executeUnique();
    }


    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named = "Listado de Usuarios Activos")
    @MemberOrder(sequence = "3")
    public List<domainapp.modules.simple.dom.usuario.Usuario> listAllActive() {
        List<domainapp.modules.simple.dom.usuario.Usuario> usuarios = repositoryUsuario.ListarActivos();
        return usuarios;
    }




    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named = "Listado de Clientes Inactivos")
    @MemberOrder(sequence = "3")
    public List<domainapp.modules.simple.dom.usuario.Usuario> listAllInactive() {
        List<domainapp.modules.simple.dom.usuario.Usuario> usuarios = repositoryUsuario.ListarInactivos();
        return usuarios;
    }


    @javax.inject.Inject
    domainapp.modules.simple.dom.usuario.UsuarioRepositorio repositoryUsuario;

    @javax.inject.Inject
    IsisJdoSupport isisJdoSupport;
}




