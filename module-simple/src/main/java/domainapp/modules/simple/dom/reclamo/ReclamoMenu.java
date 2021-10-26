package domainapp.modules.simple.dom.reclamo;


import org.apache.isis.applib.annotation.*;

import javax.inject.Inject;
import java.util.List;

@DomainService(nature = NatureOfService.VIEW_MENU_ONLY,objectType = "simple.SimpleReclamoMenu",repositoryFor = Reclamo.class)
@DomainServiceLayout(
        named = "",
        menuOrder = ""
)
public class ReclamoMenu {

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named = "Buscar por Nro de Reclamo")
    @MemberOrder(sequence = "1")
    public Reclamo findByNroReclamo(
            @Parameter(optionality = Optionality.MANDATORY)
            @ParameterLayout(named = "Por Nro de Reclamo: ")
            final Reclamo reclamo) {

        return reclamo;
    }

    public List<Reclamo> choices0FindByNroReclamo() {return reclamorepository.Listar();}

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named = "Listado de Reclamos")
    @MemberOrder(sequence = "2")
    public List<Reclamo> listAll() {
        List<Reclamo> reclamos = reclamorepository.Listar();
        return reclamos;
    }

    @Inject
    ReclamoRepositorio reclamorepository;
}
