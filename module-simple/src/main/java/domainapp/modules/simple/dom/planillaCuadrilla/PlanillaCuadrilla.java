package domainapp.modules.simple.dom.planillaCuadrilla;

import domainapp.modules.simple.dom.ayudante.Ayudante;
import domainapp.modules.simple.dom.cuadrilla.Cuadrilla;
import domainapp.modules.simple.dom.cuadrilla.CuadrillaRepositorio;

import domainapp.modules.simple.dom.reclamo.Reclamo;
import domainapp.modules.simple.dom.tecnico.Tecnico;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.apache.isis.applib.annotation.*;
import org.joda.time.LocalDate;

import javax.inject.Inject;
import javax.jdo.annotations.*;
import java.math.BigInteger;
import java.util.List;


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

    @Property(
            editing = Editing.DISABLED,
            editingDisabledReason = "tilde"
    )
    @Column(allowsNull = "false")
    private boolean seRealizoConexion;
    public boolean isSeRealizoConexion() { return seRealizoConexion; }
    public void setSeRealizoConexion(final boolean seRealizoConexion) { this.seRealizoConexion = seRealizoConexion; }


    @Property(
            editing = Editing.DISABLED,
            editingDisabledReason = "tilde"
    )
    @Column(allowsNull = "false")
    private boolean seCambioConexion;
    public boolean isSeCambioConexion() { return seCambioConexion; }
    public void setSeCambioConexion(final boolean seCambioConexion) { this.seCambioConexion = seCambioConexion; }

/*    @Action()
    @MemberOrder(name = "seRealizoConexion", sequence = "1")
    public PlanillaCuadrilla marcar() {
        setSeRealizoConexion(isSeRealizoConexion());
        return this;
    }*/

 /*   @Property()
    @Column(allowsNull = "false")
    private Estado tareaDos;

    @Property()
    @Column(allowsNull = "false")
    private Estado tareaTres;

    @Property()
    @Column(allowsNull = "false")
    private Estado tareaCuatro;

    @Property()
    @Column(allowsNull = "false")
    private Estado tareaCinco;

    @Property()
    @Column(allowsNull = "false")
    private Estado tareaSeis;

    @Property()
    @Column(allowsNull = "false")
    private Estado tareaSiete;*/
    @Property()
    @NonNull
    @Column(allowsNull = "false")
    @Getter @Setter
    private String observacion;

    public String title(){ return "Planilla de Servicio"; }

    public PlanillaCuadrilla(){}

    public PlanillaCuadrilla(
            final Cuadrilla cuadrilla,
            final boolean seRealizoConexion,
            final boolean seCambioConexion,
            final String observacion){
        this.cuadrilla = cuadrilla;
        this.seRealizoConexion = seRealizoConexion;
        this.seCambioConexion = seCambioConexion;
        this.observacion = observacion;
    }

/*    public Cuadrilla default0Update() {return getCuadrilla();}
    public String default1Update() {return getObservacion();}*/


    @Action()
    @ActionLayout(named = "Editar")
    public PlanillaCuadrilla updatePlanillaCuadrilla(


            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Se realizo conexión")
            final boolean seRealizoConexion,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Se cambio conexión")
            final boolean seCambioConexion,
/*

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Se reparo conexión")
            final Estado tareaTres,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Se anulo conexión")
            final Estado tareaCuatro,


            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Se destapo red")
            final Estado tareaCinco,


            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Colectora nivel alto")
            final Estado tareaSeis,


            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Problema interno")
            final Estado tareaSiete,*/


           @Parameter(maxLength = 40)
           @ParameterLayout(named = "Observacion")
           final String observacion)
    {

        this.seRealizoConexion = seRealizoConexion;
        this.seCambioConexion = seCambioConexion;
/*        this.tareaDos = tareaDos;
        this.tareaTres = tareaTres;
        this.tareaCuatro = tareaCuatro;
        this.tareaCinco = tareaCinco;
        this.tareaSeis = tareaSeis;
        this.tareaSiete = tareaSiete;*/
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