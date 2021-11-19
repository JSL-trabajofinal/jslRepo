package domainapp.modules.simple.dom.usuario;


import org.apache.isis.applib.annotation.*;

import javax.inject.Inject;
import java.util.List;
import java.util.regex.Pattern;

@DomainService(nature = NatureOfService.VIEW_MENU_ONLY,objectType = "simple.SimpleUsuarioMenu",repositoryFor = Usuario.class)
@DomainServiceLayout(
        named = "",
        menuOrder = ""
)
public class UsuarioMenu {

    @Action()
    @ActionLayout(named = "Cargar Usuario")
    @MemberOrder(sequence = "1")
    public Usuario create(

            @Parameter(maxLength = 8,regexPatternReplacement = "Solo se admiten hasta 8 dígitos, sin puntos")
            @ParameterLayout(named = "DNI: ")
            final String dni,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Nombre: ")
            final String nombre,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Apellido: ")
            final String apellido,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Direccion: ")
            final String direccion,

            @Parameter(
                    maxLength=40,
                    regexPattern = "(\\w+\\.)*\\w+@(\\w+\\.)+[A-Za-z]+",
                    regexPatternFlags = Pattern.CASE_INSENSITIVE,
                    regexPatternReplacement = "Debe ser una dirección de correo válida (que contener @)")
            @ParameterLayout(named = "Email: ")
            final String email,

            @Parameter(
                    maxLength = 19,
                    regexPattern = "[+]?[0-9 ]+",
                    regexPatternReplacement =
                            "Solo puede especificar números, espacios y opcionalmente el prefijo '+'. " +
                                    "Por ejemplo:, '+54 299 4484857', o '2994484857'")
            @ParameterLayout(named = "Telefono: ")
            final String telefono) {

        return usuariorepository.create(dni,nombre, apellido, direccion, email, telefono);
    }

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named = "Buscar Usuario")
    @MemberOrder(sequence = "2")
    public Usuario findByDni(
            @Parameter(optionality = Optionality.MANDATORY)
            @ParameterLayout(named = "Por dni: ")
            final Usuario usuario) {

        return usuario;
    }

    public List<Usuario> choices0FindByDni() {return usuariorepository.Listar();}

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named = "Listado de Usuarios")
    @MemberOrder(sequence = "3")
    public List<Usuario> listAll() {
        List<Usuario> usuarios = usuariorepository.Listar();
        return usuarios;
    }

    @Inject
    UsuarioRepositorio usuariorepository;
}
