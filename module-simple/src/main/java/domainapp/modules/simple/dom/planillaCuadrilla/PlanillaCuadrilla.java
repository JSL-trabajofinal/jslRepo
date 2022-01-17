package domainapp.modules.simple.dom.planillaCuadrilla;

import domainapp.modules.simple.dom.cuadrilla.Cuadrilla;
import domainapp.modules.simple.dom.cuadrilla.CuadrillaRepositorio;

import domainapp.modules.simple.dom.reclamo.Reclamo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.apache.isis.applib.annotation.*;

import javax.inject.Inject;
import javax.jdo.annotations.*;
import java.math.BigInteger;


@PersistenceCapable(
        identityType= IdentityType.DATASTORE,
        schema = "simple",
        table = "PlanillaCuadrilla")

@DatastoreIdentity(
        strategy= IdGeneratorStrategy.IDENTITY,
        column = "id")

@Version(
        strategy = VersionStrategy.VERSION_NUMBER,
        column = "version")

@Queries({
        @Query(
                name = "find", language = "JDOQL",
                value = "SELECT "
                        + "FROM domainapp.modules.simple.dom.planillaCuadrilla.PlanillaCuadrilla  "
                        + "ORDER BY idPlanillaCuadrilla ASC")

})
@Unique(name="PlanillaCuadrilla_idPlanillaCuadrilla_UNQ", members = { "idPlanillaCuadrilla" })
@DomainObject(editing = Editing.DISABLED)
@DomainObjectLayout(bookmarking = BookmarkPolicy.AS_ROOT)

public class PlanillaCuadrilla implements Comparable<PlanillaCuadrilla> {

    @Column(allowsNull = "true", length = 10)
    @NonNull
    @Property(editing = Editing.DISABLED)
    @Persistent(valueStrategy = IdGeneratorStrategy.INCREMENT)
    @Getter @Setter
    private BigInteger idPlanillaCuadrilla;



    @Property()
    @NonNull
    @Column(allowsNull = "false")
    @Getter @Setter
    private Cuadrilla cuadrilla;

    @Property(editing = Editing.ENABLED)
    @NonNull
    @Column(allowsNull = "true")
    @Getter @Setter
    private Respuesta seRealizoConexion;

    @Property(editing = Editing.ENABLED)
    @NonNull
    @Column(allowsNull = "true")
    @Getter @Setter
    private Respuesta seCambioConexion;


    @Property(editing = Editing.ENABLED)
    @NonNull
    @Column(allowsNull = "true")
    @Getter @Setter
    private Respuesta seReparoConexion;


    @Property(editing = Editing.ENABLED)
    @NonNull
    @Column(allowsNull = "true")
    @Getter @Setter
    private Respuesta seAnuloConexion;


    @Property(editing = Editing.ENABLED)
    @NonNull
    @Column(allowsNull = "true")
    @Getter @Setter
    private Respuesta seDestapoRed;


    @Property(editing = Editing.ENABLED)
    @NonNull
    @Column(allowsNull = "true")
    @Getter @Setter
    private Respuesta colectoraNivelAlto;



    @Property(editing = Editing.ENABLED)
    @NonNull
    @Column(allowsNull = "true")
    @Getter @Setter
    private Respuesta problemaInterno;



    @Column(allowsNull = "true")
    @NonNull
    @Property()
    @Getter @Setter
    private Reclamo reclamoAsignado;



    @Property()
    @NonNull
    @Column(allowsNull = "true")
    @Getter @Setter
    private String observacion;

    public String title(){ return "Planilla de Servicio"; }




    public PlanillaCuadrilla(){}

    public PlanillaCuadrilla(
            final Cuadrilla cuadrilla,
            final Reclamo reclamoAsignado,
            final Respuesta seRealizoConexion,
            final Respuesta seCambioConexion,
            final Respuesta seReparoConexion,
            final Respuesta seAnuloConexion,
            final Respuesta seDestapoRed,
            final Respuesta colectoraNivelAlto,
            final Respuesta problemaInterno,
            final String observacion){
        this.cuadrilla = cuadrilla;
        this.reclamoAsignado = reclamoAsignado;
        this.seRealizoConexion = seRealizoConexion;
        this.seCambioConexion = seCambioConexion;
        this.seReparoConexion = seReparoConexion;
        this.seAnuloConexion = seAnuloConexion;
        this.seDestapoRed = seDestapoRed;
        this.colectoraNivelAlto = colectoraNivelAlto;
        this.problemaInterno = problemaInterno;
        this.observacion = observacion;
    }



    @Action()
    @ActionLayout(named = "Editar")
    public PlanillaCuadrilla updatePlanillaCuadrilla(


            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Se realizo conexión")
            final Respuesta seRealizoConexion,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Se cambio conexión")
            final Respuesta seCambioConexion,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Se reparo conexión")
            final Respuesta seReparoConexion,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Se anulo conexión")
            final Respuesta seAnuloConexion,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Se destapo red")
            final Respuesta seDestapoRed,


            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Colectora nivel alto")
            final Respuesta colectoraNivelAlto,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Problema interno")
            final Respuesta problemaInterno,


            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Observacion")
            final String observacion)
    {

        this.seRealizoConexion = seRealizoConexion;
        this.seCambioConexion = seCambioConexion;
        this.seReparoConexion = seReparoConexion;
        this.seAnuloConexion = seAnuloConexion;
        this.seDestapoRed = seDestapoRed;
        this.colectoraNivelAlto = colectoraNivelAlto;
        this.problemaInterno = problemaInterno;
        this.observacion = observacion;

        return this;
    }



    //region > compareTo, toString
    @Override
    public int compareTo(final PlanillaCuadrilla other) {
        return org.apache.isis.applib.util.ObjectContracts.compare(this, other, "idPlanillaCuadrilla");
    }



    @Override
    public String toString() {
        return org.apache.isis.applib.util.ObjectContracts.toString(this, "idPlanillaCuadrilla");
    }

    @Inject
    @NotPersistent
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
    PlanillaCuadrillaRepositorio planillaCuadrillaRepository;

    @Inject @NotPersistent
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
    CuadrillaRepositorio cuadrillaRepository;


}