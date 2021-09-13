package domainapp.modules.simple.dom.ayudante;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.NatureOfService;


import java.util.List;
import java.util.regex.Pattern;


import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.services.eventbus.ActionDomainEvent;


@DomainService(
        nature = NatureOfService.VIEW_MENU_ONLY,
        objectType = "simple.SimpleAyudanteMenu",
        repositoryFor = Ayudante.class
)
@DomainServiceLayout(
        named = "Ayudantees",
        menuOrder = "10"
)
public class AyudanteMenu {

    public static class CreateDomainEvent extends ActionDomainEvent<AyudanteMenu> {}
    @Action(domainEvent = CreateDomainEvent.class)
    @MemberOrder(sequence = "1")
    public Ayudante create(
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
            @ParameterLayout(named="Telefono") final Integer telefono
    ) {     return repositoryAyudante.create(
            nombre.toUpperCase(),
            apellido.toUpperCase(),
            dni,
            telefono);
    }

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    @MemberOrder(sequence = "2")
    public List<Ayudante> listAll() {
        return repositoryAyudante.listAll();
    }

    @Action()
    @ActionLayout(named = "Buscar Ayudante por Apellido")
    @MemberOrder(sequence = "3")

    public List<Ayudante> findByApellido(@ParameterLayout(named = "Apellido") final String apellido) {
        return repositoryAyudante.findByApellido(apellido);
    }

    @Action()
    @ActionLayout(named = "Buscar Ayudante por Dni")
    @MemberOrder(sequence = "4")

    public List<Ayudante> findByDni(@ParameterLayout(named = "Dni") final Integer dni) {
        return repositoryAyudante.findByDni(dni);
    }

    @javax.inject.Inject
    AyudanteRepositorio repositoryAyudante;

}