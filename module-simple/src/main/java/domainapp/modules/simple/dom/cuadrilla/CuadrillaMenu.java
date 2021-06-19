package domainapp.modules.simple.dom.cuadrilla;

import domainapp.modules.simple.dom.cuadrilla.QCuadrilla;
import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.services.jdosupport.IsisJdoSupport;
import org.datanucleus.query.typesafe.TypesafeQuery;

import java.util.List;
import java.util.regex.Pattern;

@DomainService(
        nature = NatureOfService.VIEW_MENU_ONLY,
        objectType = "simple.SimpleCuadrillaMenu",
        repositoryFor = Cuadrilla.class
)
@DomainServiceLayout(
        named = "Cuadrillas",
        menuOrder = "10"
)
public class CuadrillaMenu {

    @Action()
    @ActionLayout(named = "Alta Cuadrilla")
    @MemberOrder(sequence = "1")
    public Cuadrilla create(

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

        return repositoryCuadrilla.create(nombre, apellido, usuario, contraseña);
    }

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT,named = "Buscar Cuadrilla")
    @MemberOrder(sequence = "2")
    public List<Cuadrilla> findByName(
            @Parameter(optionality = Optionality.MANDATORY)
            @ParameterLayout(named = "Nombre del Ayudante") final String nombre
    ) {
        TypesafeQuery<Cuadrilla> q = isisJdoSupport.newTypesafeQuery(Cuadrilla.class);
        final QCuadrilla cand = QCuadrilla.candidate();
        q = q.filter(
                cand.nombre.indexOf(q.stringParameter("Nombre")).ne(-1)
        );
        return q.setParameter("Nombre", nombre)
                .executeList();
    }

    @Programmatic
    public Cuadrilla findByNameExact(final String nombre) {
        TypesafeQuery<Cuadrilla> q = isisJdoSupport.newTypesafeQuery(Cuadrilla.class);
        final QCuadrilla cand = QCuadrilla.candidate();
        q = q.filter(
                cand.nombre.eq(q.stringParameter("Nombre"))
        );
        return q.setParameter("nombre", nombre)
                .executeUnique();
    }

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

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named = "Listado de Cuadrillas Activos")
    @MemberOrder(sequence = "3")
    public List<Cuadrilla> listAllActive() {
        List<Cuadrilla> cuadrillas = repositoryCuadrilla.ListarActivos();
        return cuadrillas;
    }

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named = "Listado de Cuadrillas Inactivos")
    @MemberOrder(sequence = "3")
    public List<Cuadrilla> listAllInactive() {
        List<Cuadrilla> cuadrillas = repositoryCuadrilla.ListarInactivos();
        return cuadrillas;
    }

    @javax.inject.Inject
    CuadrillaRepositorio repositoryCuadrilla;

    @javax.inject.Inject
    IsisJdoSupport isisJdoSupport;
}