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
import org.apache.isis.applib.services.eventbus.ActionDomainEvent;
import org.apache.isis.applib.services.jdosupport.IsisJdoSupport;
import org.datanucleus.query.typesafe.TypesafeQuery;
import org.joda.time.LocalDate;
import javax.inject.Inject;
import javax.jdo.annotations.Column;
import java.util.List;
import java.util.regex.Pattern;

@DomainService(
        nature = NatureOfService.VIEW_MENU_ONLY,
        objectType = "simple.SimpleUsuarioMenu",
        repositoryFor = Usuario.class
)
@DomainServiceLayout(
        named = "Usuarios",
        menuOrder = ""

)
public class UsuarioMenu {

    public static class CreateDomainEvent extends ActionDomainEvent<UsuarioMenu> {}
    @Action(domainEvent = CreateDomainEvent.class)
    @ActionLayout(named = "Alta Usuario")
    @MemberOrder(sequence = "1")
    public Usuario create(
            @Parameter(
                    regexPattern = "[0-9]+",
                    regexPatternReplacement = "Solo numeros y sin espacios"
            )
            @ParameterLayout(named="DNI") final Integer dni,

            @Parameter(
                    regexPattern = "[A-Za-z]+",
                    regexPatternFlags = Pattern.CASE_INSENSITIVE,
                    regexPatternReplacement = "Ingrese solo letras"
            )
            @ParameterLayout(named="Apellido") final String apellido,
            @ParameterLayout(named="Nombre") final String nombre,
            @ParameterLayout(named="Telefono") final String telefono,
            @Parameter(
                    regexPattern = "(\\w+\\.)*\\w+@(\\w+\\.)+[A-Za-z]+",
                    regexPatternFlags= Pattern.CASE_INSENSITIVE,
                    regexPatternReplacement = "Debe ser un email valido (contiene un '@' simbolo)"
            )
            @ParameterLayout(named="Email") final String email,

            @ParameterLayout(named="Direccion") final String direccion,
            @ParameterLayout(named="Barrio") final String barrio
    ) {

        return usuarioRepository.create(
                dni,
                apellido.toUpperCase(),
                nombre.toUpperCase(),
                telefono,
                email,
                direccion,
                barrio);
    }

    @Action()
    @ActionLayout(named = "Buscar Usuario por Apellido")
    @MemberOrder(sequence = "3")
    public List<Usuario> findByApellido(@ParameterLayout(named = "Apellido") final String apellido) {
        return usuarioRepository.findByApellido(apellido);
    }

    @Action()
    @ActionLayout(named = "Buscar Usuario por Dni")
    @MemberOrder(sequence = "4")
    public List<Usuario> findByDni(@ParameterLayout(named = "Dni") final Integer dni) {
        return usuarioRepository.findByDni(dni);
    }

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named = "Listar Usuarios")
    @MemberOrder(sequence = "2")
    public List<Usuario> listAll() {
        return usuarioRepository.listAll();
    }


    @Inject
    UsuarioRepositorio usuarioRepository;
}
