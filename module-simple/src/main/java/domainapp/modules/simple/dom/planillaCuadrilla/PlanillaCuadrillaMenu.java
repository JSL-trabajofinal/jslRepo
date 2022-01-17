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
