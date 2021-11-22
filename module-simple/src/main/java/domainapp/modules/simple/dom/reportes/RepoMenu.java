package domainapp.modules.simple.dom.reportes;

import domainapp.modules.simple.dom.cuadrilla.CuadrillaRepositorio;
import domainapp.modules.simple.dom.reclamo.ReclamoRepositorio;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import net.sf.jasperreports.engine.JRException;
import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.services.jdosupport.IsisJdoSupport;
import org.apache.isis.applib.value.Blob;

import javax.inject.Inject;
import javax.jdo.annotations.NotPersistent;
import java.io.IOException;

@DomainService(
        nature = NatureOfService.VIEW_MENU_ONLY,
        objectType = "simple.ReporteMenu"
)
@DomainServiceLayout(
        named = "Reportes",
        menuOrder = "10"
)
public class RepoMenu {

    @Action(
            semantics = SemanticsOf.SAFE,
            restrictTo = RestrictTo.PROTOTYPING
    )
    @ActionLayout(
            bookmarking = BookmarkPolicy.AS_ROOT,
            named = "Reporte Reclamo"
    )
    @MemberOrder(sequence = "1")
    // reporte de Reclamo
    public Blob generarReporteReclamo() throws JRException, IOException {
        return repositoryReclamo.generarReporteReclamo();
    }

    /*@Action(
            semantics = SemanticsOf.SAFE,
            restrictTo = RestrictTo.PROTOTYPING
    )
    @ActionLayout(
            bookmarking = BookmarkPolicy.AS_ROOT,
            named = "Reporte de Presupuestos"
    )
    @MemberOrder(sequence = "2")
    public Blob generarReportePresupuestos(
    ) throws JRException, IOException {
        return repositoryCuadrilla.generarReportePresupuestos();
    }*/


    @Inject
    IsisJdoSupport isisJdoSupport;

    @Inject @NotPersistent
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
    ReclamoRepositorio repositoryReclamo;

    @Inject @NotPersistent
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
    CuadrillaRepositorio repositoryCuadrilla;
}