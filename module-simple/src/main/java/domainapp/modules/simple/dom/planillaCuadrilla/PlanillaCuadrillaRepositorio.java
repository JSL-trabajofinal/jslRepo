package domainapp.modules.simple.dom.planillaCuadrilla;
import domainapp.modules.simple.dom.cuadrilla.Cuadrilla;
import domainapp.modules.simple.dom.reclamo.Reclamo;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.repository.RepositoryService;

import javax.inject.Inject;
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
            final Reclamo reclamoAsignado,
            final Respuesta seRealizoConexion,
            final Respuesta seCambioConexion,
            final Respuesta seReparoConexion,
            final Respuesta seAnuloConexion,
            final Respuesta seDestapoRed,
            final Respuesta colectoraNivelAlto,
            final Respuesta problemaInterno,
            // final LocalDate fechaPlanilla,
            final String observacion

    ) {
        final PlanillaCuadrilla planillaCuadrilla = new PlanillaCuadrilla(cuadrilla, reclamoAsignado, seRealizoConexion, seCambioConexion, seReparoConexion, seAnuloConexion, seDestapoRed, colectoraNivelAlto, problemaInterno, observacion);
        repositoryService.persist(planillaCuadrilla);
        return planillaCuadrilla;
    }

    @Inject
    RepositoryService repositoryService;

}
