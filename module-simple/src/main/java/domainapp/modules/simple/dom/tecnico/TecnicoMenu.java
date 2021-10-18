package domainapp.modules.simple.dom.tecnico;

import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.services.eventbus.ActionDomainEvent;

import java.util.List;
import java.util.regex.Pattern;


@DomainService(
        nature = NatureOfService.VIEW_MENU_ONLY,
        objectType = "simple.SimpleTecnicoMenu",
        repositoryFor = Tecnico.class
)
@DomainServiceLayout(
        named = "Tecnicos",
        menuOrder = "10"
)
public class TecnicoMenu {

    public static class CreateDomainEvent extends ActionDomainEvent<TecnicoMenu> {}
    @Action(domainEvent = CreateDomainEvent.class)
    @MemberOrder(sequence = "1")
    public Tecnico create(
            @Parameter(
                    regexPattern = "[A-Za-z]+",
                    regexPatternFlags = Pattern.CASE_INSENSITIVE,
                    regexPatternReplacement = "Ingrese solo letras"
            )
            @ParameterLayout(named="Nombre") final String nombre,

            @Parameter(
                    regexPattern = "[A-Za-z]+",
                    regexPatternFlags = Pattern.CASE_INSENSITIVE,
                    regexPatternReplacement = "Ingrese solo letras"
            )
            @ParameterLayout(named="Apellido") final String apellido,
            @ParameterLayout(named="DNI") final Integer dni,
            @ParameterLayout(named="Telefono") final Integer telefono,
            @ParameterLayout(named="Cuadrilla") final Cuadrilla cuadrilla
    ) {     return repositoryTecnico.create(
            nombre.toUpperCase(),
            apellido.toUpperCase(),
            dni,
            telefono,
            cuadrilla);
    }

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    @MemberOrder(sequence = "2")
    public List<Tecnico> listAll() {
        return repositoryTecnico.listAll();
    }

    @Action()
    @ActionLayout(named = "Buscar Tecnico por Apellido")
    @MemberOrder(sequence = "3")

    public List<Tecnico> findByApellido(@ParameterLayout(named = "Apellido") final String apellido) {
        return repositoryTecnico.findByApellido(apellido);
    }

    @Action()
    @ActionLayout(named = "Buscar Tecnico por Dni")
    @MemberOrder(sequence = "4")

    public List<Tecnico> findByDni(@ParameterLayout(named = "Dni") final Integer dni) {
        return repositoryTecnico.findByDni(dni);
    }

    @javax.inject.Inject
    TecnicoRepositorio repositoryTecnico;

}