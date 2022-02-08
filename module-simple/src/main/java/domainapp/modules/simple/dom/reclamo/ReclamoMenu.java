package domainapp.modules.simple.dom.reclamo;

import domainapp.modules.simple.dom.reportes.EjecutarReportes;
import net.sf.jasperreports.engine.JRException;
import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.value.Blob;

import javax.inject.Inject;
import java.io.IOException;
import java.util.List;

@DomainService(nature = NatureOfService.VIEW_MENU_ONLY,objectType = "simple.SimpleReclamoMenu",repositoryFor = Reclamo.class)
@DomainServiceLayout(
        named = "",
        menuOrder = ""
)
public class ReclamoMenu {

/*    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named = "Buscar por Nro de Reclamo")
    @MemberOrder(sequence = "1")
    public Reclamo findByNroReclamo(
            @Parameter(optionality = Optionality.MANDATORY)
            @ParameterLayout(named = "Por Nro de Reclamo: ")
            final Reclamo reclamo) {

        return reclamo;
    }

    public List<Reclamo> choices0FindByNroReclamo() {return reclamorepository.Listar();}*/

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named = "Listado de Reclamos")
    @MemberOrder(sequence = "2")
    public List<Reclamo> listAll() {
        List<Reclamo> reclamos = reclamorepository.Listar();
        return reclamos;
    }

    @Action()
    @ActionLayout(named = "Listado Exportado")
    public Blob ExportarListado() throws JRException, IOException {
        EjecutarReportes ejecutarReportes = new EjecutarReportes();
        return ejecutarReportes.ListadoReclamosPDF(reclamorepository.Listar());
    }

    @Inject
    ReclamoRepositorio reclamorepository;
}