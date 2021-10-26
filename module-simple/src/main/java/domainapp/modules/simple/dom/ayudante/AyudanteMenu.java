package domainapp.modules.simple.dom.ayudante;


import org.apache.isis.applib.annotation.*;

import javax.inject.Inject;
import java.util.List;

@DomainService(
        nature = NatureOfService.VIEW_MENU_ONLY,
        objectType = "simple.SimpleAyudanteMenu",
        repositoryFor = Ayudante.class
)
@DomainServiceLayout(
        named = "",
        menuOrder = ""
)
public class AyudanteMenu {

    @Action()
    @ActionLayout(named = "Crear Ayudante")
    @MemberOrder(sequence = "1")
    public Ayudante create(

            @Parameter(maxLength = 40)
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

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Telefono: ")
            final String telefono) {

        return ayudanterepository.create(dni,nombre, apellido, direccion, telefono);
    }

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named = "Buscar Ayudante")
    @MemberOrder(sequence = "2")
    public Ayudante findByDni(
            @Parameter(optionality = Optionality.MANDATORY)
            @ParameterLayout(named = "Por dni: ")
            final Ayudante ayudante) {

        return ayudante;
    }

    public List<Ayudante> choices0FindByDni() {return ayudanterepository.Listar();}

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named = "Listado de Ayudantes")
    @MemberOrder(sequence = "3")
    public List<Ayudante> listAll() {
        List<Ayudante> ayudantes = ayudanterepository.Listar();
        return ayudantes;
    }

    @Inject
    AyudanteRepositorio ayudanterepository;
}
