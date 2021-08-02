package domainapp.modules.simple.dom.tecnico;

import java.util.List;


import domainapp.modules.simple.dom.reclamo.Reclamo;
import org.apache.isis.applib.annotation.*;
import javax.inject.Inject;

@DomainService(
        nature = NatureOfService.VIEW_MENU_ONLY,
        objectType = "simple.SimpleTecnicoMenu",
        repositoryFor = Tecnico.class
)
@DomainServiceLayout(
        named = "Tecnico",
        menuOrder = "10"
)
public class TecnicoMenu {

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named = "Listar Tecnicos")
    public List<Reclamo> listAll(){
        return tecnicoRepository.listAll();
    }

    @Inject
    TecnicoRepositorio tecnicoRepository;

}