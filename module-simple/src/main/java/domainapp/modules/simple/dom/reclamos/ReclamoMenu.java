package domainapp.modules.simple.dom.reclamos;

import java.io.IOException;

import java.util.List;
import java.util.regex.Pattern;

import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.value.Blob;
import org.datanucleus.query.typesafe.TypesafeQuery;

import org.apache.isis.applib.services.jdosupport.IsisJdoSupport;
import org.apache.isis.applib.services.repository.RepositoryService;

@DomainService(
        nature = NatureOfService.VIEW_MENU_ONLY,
        objectType = "simple.SimpleReclamoMenu",
        repositoryFor = Reclamo.class
)
@DomainServiceLayout(
        named = "Reclamos",
        menuOrder = "10"
)
public class ReclamoMenu {

    @Action()
    @ActionLayout(named = "Alta Reclamo")
    @MemberOrder(sequence = "1")
    public Reclamo create(
/*
            @Parameter(maxLength = 40,
                    regexPattern = "^[0-9]{11}$",
                    regexPatternReplacement = "Solo numeros, sin espacios ni barras (total 11 numeros)"
            )*/

            @Parameter(maxLength = 40,
                    regexPattern = "[A-Za-z\\s]+",
                    regexPatternReplacement = "Debe ser un nombre valido (solo letras)")
            @ParameterLayout(named = "Nombre") final String nombre,

            @Parameter(maxLength = 40,
                    regexPattern = "[A-Za-z\\s]+",
                    regexPatternReplacement = "Debe ser un apellido valido (solo letras)")
            @ParameterLayout(named = "Apellido") final String apellido,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Direccion") final String direccion,

            @Parameter(maxLength = 40,
                    regexPattern = "[0-9]+",
                    regexPatternReplacement = "Solo numeros y sin espacios"
            )
            @ParameterLayout(named = "Telefono") final String telefono) {

        return repositoryReclamo.create(nombre, apellido, direccion, telefono);
    }

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT,named = "Buscar Reclamo")
    @MemberOrder(sequence = "2")
    public List<Reclamo> findByName(
            @Parameter(optionality = Optionality.MANDATORY)
            @ParameterLayout(named = "Nombre del Cliente") final String nombre
    ) {
        TypesafeQuery<Reclamo> q = isisJdoSupport.newTypesafeQuery(Reclamo.class);
        final QReclamo cand = QReclamo.candidate();
        q = q.filter(
                cand.nombre.indexOf(q.stringParameter("Nombre")).ne(-1)
        );
        return q.setParameter("Nombre", nombre)
                .executeList();
    }

    @Programmatic
    public Reclamo findByNameExact(final String nombre) {
        TypesafeQuery<Reclamo> q = isisJdoSupport.newTypesafeQuery(Reclamo.class);
        final QReclamo cand = QReclamo.candidate();
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
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named = "Listado de Clientes Activos")
    @MemberOrder(sequence = "3")
    public List<Reclamo> listAllActive() {
        List<Reclamo> reclamos = repositoryReclamo.ListarActivos();
        return reclamos;
    }




    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named = "Listado de Clientes Inactivos")
    @MemberOrder(sequence = "3")
    public List<Reclamo> listAllInactive() {
        List<Reclamo> reclamos = repositoryReclamo.ListarInactivos();
        return reclamos;
    }


    @javax.inject.Inject
    ReclamoRepositorio repositoryReclamo;

    @javax.inject.Inject
    IsisJdoSupport isisJdoSupport;
}