package domainapp.modules.simple.dom.planillaCuadrilla;
import domainapp.modules.simple.dom.cuadrilla.Cuadrilla;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.repository.RepositoryService;

import javax.inject.Inject;
import java.math.BigInteger;
import java.util.List;

@DomainService(
        nature = NatureOfService.DOMAIN,
        repositoryFor = PlanillaCuadrilla.class
)
public class PlanillaCuadrillaRepositorio {
    @Programmatic
    public List<PlanillaCuadrilla> Listar() {

        return repositoryService.allMatches(
                new QueryDefault<>(
                        PlanillaCuadrilla.class,
                        "find"));
    }

    @Programmatic
    public PlanillaCuadrilla findByNombre(final Cuadrilla cuadrilla){

        return repositoryService.uniqueMatch(
                new QueryDefault<>(
                        PlanillaCuadrilla.class,
                        "findByNombre",
                        "cuadrilla", cuadrilla));
    }

    @Programmatic
    public PlanillaCuadrilla create(

            final Cuadrilla cuadrilla,
            final boolean seRealizoConexion,
            final boolean seCambioConexion,
            // final LocalDate fechaPlanilla,
            final String observacion

    ) {
        final PlanillaCuadrilla planillaCuadrilla = new PlanillaCuadrilla(cuadrilla, seRealizoConexion, seCambioConexion, observacion);
        repositoryService.persist(planillaCuadrilla);
        return planillaCuadrilla;
    }

    @Inject
    RepositoryService repositoryService;

}
