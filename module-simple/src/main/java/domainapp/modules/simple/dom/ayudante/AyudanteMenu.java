package domainapp.modules.simple.dom.ayudante;

import domainapp.modules.simple.dom.ayudante.Ayudante;
import domainapp.modules.simple.dom.ayudante.AyudanteRepositorio;
import domainapp.modules.simple.dom.ayudante.QAyudante;
import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.services.jdosupport.IsisJdoSupport;
import org.datanucleus.query.typesafe.TypesafeQuery;

import java.util.List;
import java.util.regex.Pattern;

@DomainService(
        nature = NatureOfService.VIEW_MENU_ONLY,
        objectType = "simple.SimpleAyudanteMenu",
        repositoryFor = Ayudante.class
)
@DomainServiceLayout(
        named = "Ayudantes",
        menuOrder = "10"
)
public class AyudanteMenu {

    @Action()
    @ActionLayout(named = "Alta Ayudante")
    @MemberOrder(sequence = "1")
    public Ayudante create(

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

        return repositoryAyudante.create(nombre, apellido, usuario, contraseña);
    }

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT,named = "Buscar Ayudante")
    @MemberOrder(sequence = "2")
    public List<Ayudante> findByName(
            @Parameter(optionality = Optionality.MANDATORY)
            @ParameterLayout(named = "Nombre del Ayudante") final String nombre
    ) {
        TypesafeQuery<Ayudante> q = isisJdoSupport.newTypesafeQuery(Ayudante.class);
        final QAyudante cand = QAyudante.candidate();
        q = q.filter(
                cand.nombre.indexOf(q.stringParameter("Nombre")).ne(-1)
        );
        return q.setParameter("Nombre", nombre)
                .executeList();
    }

    @Programmatic
    public Ayudante findByNameExact(final String nombre) {
        TypesafeQuery<Ayudante> q = isisJdoSupport.newTypesafeQuery(Ayudante.class);
        final QAyudante cand = QAyudante.candidate();
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
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named = "Listado de Ayudantes Activos")
    @MemberOrder(sequence = "3")
    public List<Ayudante> listAllActive() {
        List<Ayudante> ayudantes = repositoryAyudante.ListarActivos();
        return ayudantes;
    }

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named = "Listado de Ayudantes Inactivos")
    @MemberOrder(sequence = "3")
    public List<Ayudante> listAllInactive() {
        List<Ayudante> ayudantes = repositoryAyudante.ListarInactivos();
        return ayudantes;
    }

    @javax.inject.Inject
    AyudanteRepositorio repositoryAyudante;

    @javax.inject.Inject
    IsisJdoSupport isisJdoSupport;
}