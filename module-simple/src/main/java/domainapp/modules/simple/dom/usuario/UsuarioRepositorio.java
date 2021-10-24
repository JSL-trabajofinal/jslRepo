package domainapp.modules.simple.dom.usuario;


import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.repository.RepositoryService;

import java.util.List;

@DomainService(
        nature = NatureOfService.DOMAIN,
        repositoryFor = Usuario.class
)
public class UsuarioRepositorio {

    @Programmatic
    public List<Usuario> Listar() {

        return repositoryService.allMatches(
                new QueryDefault<>(
                        Usuario.class,
                        "find"));
    }

    @Programmatic
    public Usuario findByDni(final String dni) {

        return repositoryService.uniqueMatch(
                new QueryDefault<>(
                        Usuario.class,
                        "findByDni",
                        "dni", dni));
    }

    @Programmatic
    public List<Usuario> findByNombreContains(final String nombre) {

        return repositoryService.allMatches(
                new QueryDefault<>(
                        Usuario.class,
                        "findByNombreContains",
                        "nombre", nombre));
    }

    @Programmatic
    public Usuario create(
            final String dni,
            final String nombre,
            final String apellido,
            final String direccion,
            final String email,
            final String telefono) {

        final Usuario usuario = new Usuario(dni, nombre, apellido, direccion, email, telefono);
        repositoryService.persist(usuario);
        return usuario;
    }

    @Programmatic
    public Usuario findOrCreate(
            final String dni,
            final String nombre,
            final String apellido,
            final String direccion,
            final String email,
            final String telefono) {

        Usuario usuario = findByDni(dni);
        if (usuario == null) {
            usuario = create(dni, nombre, apellido, direccion, email, telefono);
        }
        return usuario;
    }

    @javax.inject.Inject
    RepositoryService repositoryService;
}
