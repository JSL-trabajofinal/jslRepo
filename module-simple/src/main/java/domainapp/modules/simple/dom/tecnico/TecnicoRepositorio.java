package domainapp.modules.simple.dom.tecnico;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.repository.RepositoryService;

import java.util.List;

@DomainService(
        nature = NatureOfService.DOMAIN,
        repositoryFor = Tecnico.class
)
public class TecnicoRepositorio {

    @Programmatic
    public List<Tecnico> Listar() {

        return repositoryService.allMatches(
                new QueryDefault<>(
                        Tecnico.class,
                        "find"));
    }

    @Programmatic
    public Tecnico findByDni(final String dni) {

        return repositoryService.uniqueMatch(
                new QueryDefault<>(
                        Tecnico.class,
                        "findByDni",
                        "dni", dni));
    }

    @Programmatic
    public List<Tecnico> findByNombreContains(final String nombre) {

        return repositoryService.allMatches(
                new QueryDefault<>(
                        Tecnico.class,
                        "findByNombreContains",
                        "nombre", nombre));
    }

    @Programmatic
    public Tecnico create(
            final String dni,
            final String nombre,
            final String apellido,
            final String direccion,
            final String telefono) {

        final Tecnico tecnico = new Tecnico(dni, nombre, apellido, direccion, telefono);
        repositoryService.persist(tecnico);
        return tecnico;
    }

    @Programmatic
    public Tecnico findOrCreate(
            final String dni,
            final String nombre,
            final String apellido,
            final String direccion,
            final String telefono) {

        Tecnico tecnico = findByDni(dni);
        if (tecnico == null) {
            tecnico = create(dni, nombre, apellido, direccion, telefono);
        }
        return tecnico;
    }

    @javax.inject.Inject
    RepositoryService repositoryService;
}
