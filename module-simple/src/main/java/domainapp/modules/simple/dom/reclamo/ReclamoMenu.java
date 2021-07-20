package domainapp.modules.simple.dom.reclamo;

import java.util.List;
import org.apache.isis.applib.annotation.*;
import javax.inject.Inject;

@DomainService(
        nature = NatureOfService.VIEW_MENU_ONLY,
        objectType = "simple.SimpleReclamoMenu",
        repositoryFor = Reclamo.class
)
@DomainServiceLayout(
        named = "Reclamo",
        menuOrder = "10"
)
public class ReclamoMenu {

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named = "Listar Reclamos")
    public List<Reclamo> listAll(){
        return reclamoRepository.listAll();
    }

    @Inject
    ReclamoRepositorio reclamoRepository;

}