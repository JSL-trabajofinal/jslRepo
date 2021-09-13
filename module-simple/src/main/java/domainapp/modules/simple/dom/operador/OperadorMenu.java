package domainapp.modules.simple.dom.operador;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.NatureOfService;


import java.util.List;
import java.util.regex.Pattern;


import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.services.eventbus.ActionDomainEvent;


@DomainService(
        nature = NatureOfService.VIEW_MENU_ONLY,
        objectType = "simple.SimpleOperadorMenu",
        repositoryFor = Operador.class
)
@DomainServiceLayout(
        named = "Operadores",
        menuOrder = "10"
)
public class OperadorMenu {

    public static class CreateDomainEvent extends ActionDomainEvent<OperadorMenu> {}
    @Action(domainEvent = CreateDomainEvent.class)
    @MemberOrder(sequence = "1")
    public Operador create(
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
    ) {     return repositoryOperador.create(
            nombre.toUpperCase(),
            apellido.toUpperCase(),
            dni,
            telefono);
    }

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    @MemberOrder(sequence = "2")
    public List<Operador> listAll() {
        return repositoryOperador.listAll();
    }

    @Action()
    @ActionLayout(named = "Buscar Operador por Apellido")
    @MemberOrder(sequence = "3")

    public List<Operador> findByApellido(@ParameterLayout(named = "Apellido") final String apellido) {
        return repositoryOperador.findByApellido(apellido);
    }

    @Action()
    @ActionLayout(named = "Buscar Operador por Dni")
    @MemberOrder(sequence = "4")

    public List<Operador> findByDni(@ParameterLayout(named = "Dni") final Integer dni) {
        return repositoryOperador.findByDni(dni);
    }

    @javax.inject.Inject
    OperadorRepositorio repositoryOperador;

}