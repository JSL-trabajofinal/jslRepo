package domainapp.modules.simple.dom.reportes;

import domainapp.modules.simple.dom.cuadrilla.CuadrillaRepositorio;
import domainapp.modules.simple.dom.reclamo.Reclamo;
import domainapp.modules.simple.dom.reclamo.ReclamoRepositorio;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import net.sf.jasperreports.engine.JRException;
import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.services.jdosupport.IsisJdoSupport;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.applib.value.Blob;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
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

    @Action
    @ActionLayout(named = "Reporte de Reclamo")
    // reporte de Reclamo
    public Blob generarReporte() throws JRException, IOException {
        EjecutarReportes ejecutar = new EjecutarReportes();
        //List<Reclamo> reclamo = repositoryReclamo.Listar();
        return ejecutar.ListadoReclamoPDF(repositoryReclamo.Listar());
    }
    /*public Blob generarReporte() throws JRException, IOException{

        return repositoryReclamo.generarReporte();
    }*/

    @Inject @NotPersistent
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
    ReclamoRepositorio repositoryReclamo;

    @Inject @NotPersistent
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
    CuadrillaRepositorio repositoryCuadrilla;
}