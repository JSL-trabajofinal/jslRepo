package domainapp.modules.simple.dom.reclamo;

import domainapp.modules.simple.dom.usuario.Usuario;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.joda.time.LocalDate;

import javax.inject.Inject;
import java.math.BigInteger;
import java.util.List;

@DomainService( nature = NatureOfService.DOMAIN, repositoryFor = Reclamo.class)
public class ReclamoRepositorio {

    @Programmatic
    public List<Reclamo> Listar() {
        return repositoryService.allMatches(
                new QueryDefault<>(
                        Reclamo.class,
                        "find"));
    }

    @Programmatic
    public List<Reclamo> ListarUltimos() {
        return repositoryService.allMatches(
                new QueryDefault<>(
                        Reclamo.class,
                        "findLast"));
    }

    @Programmatic
    public Reclamo findByNroReclamo(final BigInteger nroReclamo) {
        return repositoryService.uniqueMatch(
                new QueryDefault<>(
                        Reclamo.class,
                        "findByNroReclamo",
                        "nroReclamo", nroReclamo));
    }

    @Programmatic
    public List<Reclamo> findByUsuarioContains(final Usuario usuario) {
        return repositoryService.allMatches(
                new QueryDefault<>(
                        Reclamo.class,
                        "findByUsuarioContains",
                        "usuario", usuario));
    }

    @Programmatic
    public Reclamo create(
            final Estado estado,
            final Usuario usuario,
            final LocalDate fecha,
            final TipoReclamo tipoReclamo,
            final String descripcion) {

        final Reclamo reclamo = new Reclamo(estado,usuario,fecha,tipoReclamo,descripcion);
        repositoryService.persist(reclamo);
        return reclamo;
    }

    @Programmatic
    public Reclamo findOrCreate(
            final BigInteger nroReclamo,
            final Estado estado,
            final Usuario usuario,
            final LocalDate fecha,
            final TipoReclamo tipoReclamo,
            final String descripcion) {

        Reclamo reclamo = findByNroReclamo(nroReclamo);
        if (reclamo == null) {
            reclamo = create(estado, usuario, fecha, tipoReclamo,descripcion);
        }
        return reclamo;
    }

    @Inject
    RepositoryService repositoryService;

}
