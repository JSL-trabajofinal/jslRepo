package domainapp.modules.simple.dom.cuadrilla;

import org.apache.isis.applib.annotation.*;

import java.util.List;

@DomainService(
        nature = NatureOfService.VIEW_MENU_ONLY,
        objectType = "simple.SimpleCuadrillaMenu",
        repositoryFor = Cuadrilla.class
)
@DomainServiceLayout(
        named = "",
        menuOrder = ""
)
public class CuadrillaMenu {

    @Action()
    @ActionLayout(named = "Crear Cuadrilla")
    @MemberOrder(sequence = "1")
    public Cuadrilla create(

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Nombre: ")
            final String nombre,

            @Parameter(optionality = Optionality.MANDATORY)
            @ParameterLayout(named = "Tecnico: ")
            final Tecnico tecnico,

            @Parameter(optionality = Optionality.MANDATORY)
            @ParameterLayout(named = "Ayudante: ")
            final Ayudante ayudante) {

        return cuadrillarepository.create(nombre, tecnico, ayudante);
    }

    public List<Tecnico> choices1Create() {return tecnicoRepository.Listar();}
    public List<Ayudante> choices2Create() {return ayudanteRepository.Listar();}

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named = "Buscar Cuadrilla")
    @MemberOrder(sequence = "2")
    public Cuadrilla findByNombre(
            @Parameter(optionality = Optionality.MANDATORY)
            @ParameterLayout(named = "Por Nombre: ")
            final Cuadrilla cuadrilla) {

        return cuadrilla;
    }

    public List<Cuadrilla> choices0FindByNombre() {return cuadrillarepository.Listar();}


    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named = "Listado de Cuadrillas")
    @MemberOrder(sequence = "2")
    public List<Cuadrilla> listAll() {
        return cuadrillarepository.Listar();
    }


    @javax.inject.Inject
    CuadrillaRepositorio cuadrillarepository;

    @javax.inject.Inject
    TecnicoRepositorio tecnicoRepository;

    @javax.inject.Inject
    AyudanteRepositorio ayudanteRepository;
}
