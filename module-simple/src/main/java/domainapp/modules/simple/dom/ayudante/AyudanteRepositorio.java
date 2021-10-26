package domainapp.modules.simple.dom.ayudante;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.repository.RepositoryService;

import javax.inject.Inject;
import java.util.List;

@DomainService(
        nature = NatureOfService.DOMAIN,
        repositoryFor = Ayudante.class
)
public class AyudanteRepositorio {

    @Programmatic
    public List<Ayudante> Listar() {

        return repositoryService.allMatches(
                new QueryDefault<>(
                        Ayudante.class,
                        "find"));
    }

    @Programmatic
    public Ayudante findByDni(final String dni) {

        return repositoryService.uniqueMatch(
                new QueryDefault<>(
                        Ayudante.class,
                        "findByDni",
                        "dni", dni));
    }

    @Programmatic
    public List<Ayudante> findByNombreContains(final String nombre) {

        return repositoryService.allMatches(
                new QueryDefault<>(
                        Ayudante.class,
                        "findByNombreContains",
                        "nombre", nombre));
    }

    @Programmatic
    public Ayudante create(
            final String dni,
            final String nombre,
            final String apellido,
            final String direccion,
            final String telefono) {

        final Ayudante ayudante = new Ayudante(dni, nombre, apellido, direccion, telefono);
        repositoryService.persist(ayudante);
        return ayudante;
    }

    @Programmatic
    public Ayudante findOrCreate(
            final String dni,
            final String nombre,
            final String apellido,
            final String direccion,
            final String telefono) {

        Ayudante ayudante = findByDni(dni);
        if (ayudante == null) {
            ayudante = create(dni, nombre, apellido, direccion, telefono);
        }
        return ayudante;
    }

    @Inject
    RepositoryService repositoryService;
}
