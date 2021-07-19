package domainapp.modules.simple.dom.reclamo;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.services.repository.RepositoryService;
import javax.inject.Inject;
import java.util.List;

@DomainService(nature = NatureOfService.DOMAIN, repositoryFor = Reclamo.class)
public class ReclamoRepositorio {

    @Programmatic
    public List<Reclamo> listAll(){
        return repositoryService.allInstances(Reclamo.class);
    }

    @Inject
    RepositoryService repositoryService;

}
