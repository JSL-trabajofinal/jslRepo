package domainapp.modules.simple.dom.planillaCuadrilla;

import domainapp.modules.simple.dom.cuadrilla.Cuadrilla;

import domainapp.modules.simple.dom.cuadrilla.CuadrillaRepositorio;
import domainapp.modules.simple.dom.reclamo.Reclamo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.apache.isis.applib.annotation.*;

import javax.inject.Inject;
import javax.jdo.annotations.NotPersistent;
import java.math.BigInteger;
import java.util.List;

@DomainService(
        nature = NatureOfService.VIEW_MENU_ONLY,
        objectType = "simple.SimplePlanillaCuadrillaMenu",
        repositoryFor = PlanillaCuadrilla.class)
@DomainServiceLayout(
        named = "",
        menuOrder = ""
)
public class PlanillaCuadrillaMenu {
/*

    @Action()
    @ActionLayout(named = "Crear Planilla Cuadrilla")
    @MemberOrder(sequence = "1")
    public PlanillaCuadrilla create(

            @Parameter(optionality = Optionality.MANDATORY)
            @ParameterLayout(named = "Cuadrilla: ")
            final Cuadrilla cuadrilla,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Se realizo conexión")
            final boolean seRealizoConexion,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Se cambio conexión")
            final boolean seCambioConexion,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Observacion")
            final String observacion)
    {

        return planillaCuadrillaRepository.create(cuadrilla, seRealizoConexion, seCambioConexion, observacion);

    }

*/

    /*public List<Cuadrilla> choices0Create() {return cuadrillaRepository.Listar();}*/

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named = "Listado de Cuadrillas")
    @MemberOrder(sequence = "2")
    public List<PlanillaCuadrilla> listAll() {
        return planillaCuadrillaRepository.Listar();
    }

    @Inject
    @NotPersistent
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
    PlanillaCuadrillaRepositorio planillaCuadrillaRepository;

    @Inject @NotPersistent
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
    CuadrillaRepositorio cuadrillaRepository;

}
