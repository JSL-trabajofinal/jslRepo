package domainapp.modules.simple.dom.planillaCuadrilla;

import domainapp.modules.simple.dom.ayudante.Ayudante;
import domainapp.modules.simple.dom.cuadrilla.Cuadrilla;
import domainapp.modules.simple.dom.cuadrilla.CuadrillaRepositorio;

import domainapp.modules.simple.dom.reclamo.Reclamo;
import domainapp.modules.simple.dom.tecnico.Tecnico;
import domainapp.modules.simple.dom.usuario.Usuario;
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
            editing = Editing.DISABLED)
    @Column(allowsNull = "false")
    private boolean seRealizoConexion;
    public boolean isSeRealizoConexion() { return seRealizoConexion; }
    public void setSeRealizoConexion(final boolean seRealizoConexion) { this.seRealizoConexion = seRealizoConexion; }


    @Property(
            editing = Editing.DISABLED)
    @Column(allowsNull = "false")
    private boolean seCambioConexion;
    public boolean isSeCambioConexion() { return seCambioConexion; }
    public void setSeCambioConexion(final boolean seCambioConexion) { this.seCambioConexion = seCambioConexion; }

    @Property(
            editing = Editing.DISABLED)
    @Column(allowsNull = "false")
    private boolean seReparoConexion;
    public boolean isSeReparoConexion() { return seReparoConexion; }
    public void setSeReparoConexion(final boolean seReparoConexion) { this.seReparoConexion = seReparoConexion; }

    @Property(
            editing = Editing.DISABLED)
    @Column(allowsNull = "false")
    private boolean seAnuloConexion;
    public boolean isSeAnuloConexion() { return seAnuloConexion; }
    public void setSeAnuloConexion(final boolean seAnuloConexion) { this.seAnuloConexion = seAnuloConexion; }

    @Property(
            editing = Editing.DISABLED)
    @Column(allowsNull = "false")
    private boolean seDestapoRed;
    public boolean isSeDestapoRed() { return seDestapoRed; }
    public void setSeDestapoRed(final boolean seDestapoRed) { this.seDestapoRed = seDestapoRed; }


    @Property(
            editing = Editing.DISABLED)
    @Column(allowsNull = "false")
    private boolean colectoraNivelAlto;
    public boolean isColectoraNivelAlto() { return colectoraNivelAlto; }
    public void setColectoraNivelAlto(final boolean colectoraNivelAlto) { this.colectoraNivelAlto = colectoraNivelAlto; }

    @Property(
            editing = Editing.DISABLED)
    @Column(allowsNull = "false")
    private boolean problemaInterno;
    public boolean isProblemaInterno() { return problemaInterno; }
    public void setProblemaInterno(final boolean problemaInterno) { this.problemaInterno = problemaInterno; }

    @Column(allowsNull = "true")
    @NonNull
    @Property()
    @Getter @Setter
    private Reclamo reclamoAsignado;

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
    @Column(allowsNull = "true")
    @Getter @Setter
    private String observacion;

    public String title(){ return "Planilla de Servicio"; }

    public PlanillaCuadrilla(){}

    public PlanillaCuadrilla(
            final Cuadrilla cuadrilla,
            final Reclamo reclamoAsignado,
            final boolean seRealizoConexion,
            final boolean seCambioConexion,
            final boolean seReparoConexion,
            final boolean seAnuloConexion,
            final boolean seDestapoRed,
            final boolean colectoraNivelAlto,
            final boolean problemaInterno,
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

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Se reparo conexión")
            final boolean seReparoConexion,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Se anulo conexión")
            final boolean seAnuloConexion,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Se destapo red")
            final boolean seDestapoRed,


            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Colectora nivel alto")
            final boolean colectoraNivelAlto,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Problema interno")
            final boolean problemaInterno,


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