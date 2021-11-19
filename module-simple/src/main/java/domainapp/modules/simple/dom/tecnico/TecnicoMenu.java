package domainapp.modules.simple.dom.tecnico;


import org.apache.isis.applib.annotation.*;

import javax.inject.Inject;
import java.util.List;

@DomainService(
        nature = NatureOfService.VIEW_MENU_ONLY,
        objectType = "simple.SimpleTecnicoMenu",
        repositoryFor = Tecnico.class
)
@DomainServiceLayout(
        named = "",
        menuOrder = ""
)
public class TecnicoMenu {

    @Action()
    @ActionLayout(named = "Crear Tecnico")
    @MemberOrder(sequence = "1")
    public Tecnico create(

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

        return tecnicorepository.create(dni,nombre, apellido, direccion, telefono);
    }


    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named = "Buscar Tecnico")
    @MemberOrder(sequence = "2")
    public Tecnico findByDni(
            @Parameter(optionality = Optionality.MANDATORY)
            @ParameterLayout(named = "Por dni: ")
            final Tecnico tecnico) {

        return tecnico;
    }

    public List<Tecnico> choices0FindByDni() {return tecnicorepository.Listar();}

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named = "Listado de Tecnicos")
    @MemberOrder(sequence = "3")
    public List<Tecnico> listAll() {
        List<Tecnico> tecnicos = tecnicorepository.Listar();
        return tecnicos;
    }

    @Inject
    TecnicoRepositorio tecnicorepository;
}
