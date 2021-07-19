package domainapp.modules.simple.dom.operador;

import java.io.IOException;

import java.util.List;
import java.util.regex.Pattern;

import domainapp.modules.simple.dom.operador.Operador;
import domainapp.modules.simple.dom.operador.OperadorRepositorio;
import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.value.Blob;
import org.datanucleus.query.typesafe.TypesafeQuery;

import org.apache.isis.applib.services.jdosupport.IsisJdoSupport;
import org.apache.isis.applib.services.repository.RepositoryService;

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

    @Action()
    @ActionLayout(named = "Alta Operador")
    @MemberOrder(sequence = "1")
    public Operador create(

            @Parameter(maxLength = 40,
                    regexPattern = "[A-Za-z\\s]+",
                    regexPatternReplacement = "Debe ser un nombre valido (solo letras)")
            @ParameterLayout(named = "Nombre") final String nombre,

            @Parameter(maxLength = 40,
                    regexPattern = "[A-Za-z\\s]+",
                    regexPatternReplacement = "Debe ser un apellido valido (solo letras)")
            @ParameterLayout(named = "Apellido") final String apellido,

            @Parameter(maxLength = 40,
                    regexPattern = "[A-Za-z\\s]+",
                    regexPatternReplacement = "Debe ser un usuario  valido (solo letras)")
            @ParameterLayout(named = "Usuario") final String usuario,

            @Parameter(maxLength = 40,
                    regexPattern = "[A-Za-z\\s]+",
                    regexPatternFlags= Pattern.CASE_INSENSITIVE,
                    regexPatternReplacement = "Debe ser una contraseña valida (solo letras)")
            @ParameterLayout(named = "Contraseña") final String contraseña) {

        return repositoryOperador.create(nombre, apellido, usuario, contraseña);
    }

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT,named = "Buscar Operador")
    @MemberOrder(sequence = "2")
    public List<Operador> findByName(
            @Parameter(optionality = Optionality.MANDATORY)
            @ParameterLayout(named = "Nombre del Operador") final String nombre
    ) {
        TypesafeQuery<Operador> q = isisJdoSupport.newTypesafeQuery(Operador.class);
        final QOperador cand = QOperador.candidate();
        q = q.filter(
                cand.nombre.indexOf(q.stringParameter("Nombre")).ne(-1)
        );
        return q.setParameter("Nombre", nombre)
                .executeList();
    }

    /*@Programmatic
    public Operador findByNameExact(final String nombre) {
        TypesafeQuery<Operador> q = isisJdoSupport.newTypesafeQuery(Operador.class);
        final QOperador cand = QOperador.candidate();
        q = q.filter(
                cand.nombre.eq(q.stringParameter("Nombre"))
        );
        return q.setParameter("nombre", nombre)
                .executeUnique();
    }*/

    /*@Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named = "Busqueda por CUIT/CUIL")
    @MemberOrder(sequence = "3")
    public Reclamo findByCuilExact(
            @Parameter(optionality = Optionality.MANDATORY)
            @ParameterLayout(named = "Nro CUIL/CUIT") final String cuil) {
        TypesafeQuery<Reclamo> q = isisJdoSupport.newTypesafeQuery(Reclamo.class);
        final QReclamo cand = QReclamo.candidate();
        q = q.filter(
                cand.cuil.eq(q.stringParameter("cuil"))
        );
        return q.setParameter("cuil", cuil)
                .executeUnique();
    }
*/

    /*@Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named = "Listado de Operadores Activos")
    @MemberOrder(sequence = "3")
    public List<Operador> listAllActive() {
        List<Operador> operadores = repositoryOperador.ListarActivos();
        return operadores;
    }

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named = "Listado de Operadores Inactivos")
    @MemberOrder(sequence = "3")
    public List<Operador> listAllInactive() {
        List<Operador> operadores = repositoryOperador.ListarInactivos();
        return operadores;
    }*/

    @javax.inject.Inject
    OperadorRepositorio repositoryOperador;

    @javax.inject.Inject
    IsisJdoSupport isisJdoSupport;
}