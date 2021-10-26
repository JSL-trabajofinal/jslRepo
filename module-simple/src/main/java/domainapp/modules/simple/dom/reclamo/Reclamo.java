package domainapp.modules.simple.dom.reclamo;

import domainapp.modules.simple.dom.cuadrilla.Cuadrilla;
import domainapp.modules.simple.dom.cuadrilla.CuadrillaRepositorio;
import domainapp.modules.simple.dom.usuario.Usuario;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.services.factory.FactoryService;
import org.apache.isis.applib.services.message.MessageService;
import org.apache.isis.schema.utils.jaxbadapters.JodaDateTimeStringAdapter;
import org.joda.time.LocalDate;

import javax.inject.Inject;
import javax.jdo.annotations.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigInteger;
import java.util.List;

@PersistenceCapable(identityType = IdentityType.DATASTORE,schema = "simple",table = "Reclamo")
@DatastoreIdentity(strategy = IdGeneratorStrategy.IDENTITY,column = "id")
@Version(strategy = VersionStrategy.VERSION_NUMBER,column = "version")
@Queries({
        @Query(
                name = "find", language = "JDOQL",
                value = "SELECT "),
        @Query(
                name = "findLast", language = "JDOQL",
                value = "SELECT "
                        + "ORDER BY nroReclamo DESC"),
        @Query(
                name = "findByNroReclamo", language = "JDOQL",
                value = "SELECT "
                        + "FROM domainapp.modules.simple.dom.reclamo.Reclamo "
                        + "WHERE nroReclamo == :nroReclamo "
                        + "ORDER BY nroReclamo ASC")
})
@Unique(name="Reclamo_nroReclamo_UNQ", members = {"nroReclamo"})
@DomainObject(editing = Editing.DISABLED)
@DomainObjectLayout(bookmarking = BookmarkPolicy.AS_ROOT)
@Getter @Setter
public class Reclamo {

    @Column(allowsNull = "true", length = 10)
    @Property(editing = Editing.DISABLED)
    @Persistent(valueStrategy = IdGeneratorStrategy.INCREMENT)
    private BigInteger nroReclamo;

    @Column(allowsNull = "false")
    @NonNull
    @Property()
    @Getter @Setter
    private Usuario usuario;

    @Column(allowsNull = "false")
    @NonNull
    @PropertyLayout(named="Fecha Creacion del Reclamo: ")
    @Property(editing = Editing.DISABLED)
    @XmlJavaTypeAdapter(JodaDateTimeStringAdapter.ForJaxb.class)
    private LocalDate fecha = LocalDate.now();

    @Column(allowsNull = "true")
    @Property(editing = Editing.ENABLED)
    @Title(prepend = "Reclamo: ")
    private TipoReclamo tipoReclamo;

    @Column(allowsNull = "true", length = 2000)
    @Property(editing = Editing.ENABLED)
    private String descripcion;

    @Column(allowsNull = "true", length = 2000)
    @Property(editing = Editing.DISABLED)
    private Estado estado;

    @Column(allowsNull = "true", name = "cuadrilla_asig_id")
    @Property()
    @PropertyLayout(named = "Cuadrilla")
    private Cuadrilla cuadrillaAsignada;

    public Reclamo(){}

    public Reclamo(
            Estado estado,
            Usuario usuario,
            LocalDate fecha,
            TipoReclamo tipoReclamo,
            String descripcion){
        this.estado = estado;
        this.usuario = usuario;
        this.fecha = fecha;
        this.tipoReclamo = tipoReclamo;
        this.descripcion = descripcion;
    }

    public Reclamo(
            BigInteger nroReclamo,
            Estado estado,
            Usuario usuario,
            LocalDate fecha,
            TipoReclamo tipoReclamo,
            String descripcion){
        this.nroReclamo = nroReclamo;
        this.estado = estado;
        this.usuario = usuario;
        this.fecha = fecha;
        this.tipoReclamo = tipoReclamo;
        this.descripcion = descripcion;
    }

    public Usuario getUsuario(){
        return this.usuario;
    }

    @Action()
    @ActionLayout(named = "Editar")
    public Reclamo update(
            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Usuario: ")
            final Usuario usuario,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Tipo Reclamo: ")
            final TipoReclamo tipoReclamo,

            @Parameter(maxLength = 4000)
            @ParameterLayout(named = "Descripcion: ")
            final String descripcion){

        this.usuario = usuario;
        this.tipoReclamo = tipoReclamo;
        this.descripcion = descripcion;
        return this;
    }

    public Usuario default0Update() {return getUsuario();}
    public TipoReclamo default1Update() {return getTipoReclamo();}

    @Programmatic
    public void CambiarEstado(Estado estado) {
        this.estado = estado;
    }

    @Action(semantics = SemanticsOf.IDEMPOTENT_ARE_YOU_SURE)
    public Reclamo Anular() {
        if (getEstado().equals(Estado.Anulado)) {
            messageService.warnUser("El reclamo ya se encuentra anulado!");
        }else if (getEstado().equals(Estado.Cerrado)) {
            messageService.warnUser("No es posible anular un reclamo cerrado!");
        } else {
            CambiarEstado(Estado.Anulado);
            messageService.informUser("Reclamo anulado");
        }
        return this;
    }

    @Action(semantics = SemanticsOf.IDEMPOTENT_ARE_YOU_SURE)
    public Reclamo Cerrar() {
        if (getEstado().equals(Estado.Cerrado)) {
            messageService.warnUser("El reclamo ya se encuentra cerrado!");
        }else if (getEstado().equals(Estado.Anulado)) {
            messageService.warnUser("No es posible cerrar un reclamo anulado!!");
        } else {
            CambiarEstado(Estado.Cerrado);
            messageService.informUser("Reclamo Cerrado");
        }
        return this;
    }

    @Action(semantics = SemanticsOf.IDEMPOTENT_ARE_YOU_SURE)
    @ActionLayout(named = "Asignar Cuadrilla")
    public Reclamo AsignarCuadrilla(
            @Parameter(optionality = Optionality.MANDATORY)
            @ParameterLayout(named = "Cuadrilla")
            final Cuadrilla cuadrilla) {
        if (getEstado().equals(Estado.Anulado)) {
            messageService.warnUser("No se puede asignar un reclamo Anulado!");
        } else if (getEstado().equals(Estado.Cerrado)) {
            messageService.warnUser("No se puede asignar un reclamo Cerrado!");
        }else {
            this.cuadrillaAsignada = cuadrilla;
            CambiarEstado(Estado.En_Proceso);
            messageService.informUser("Reclamo Asignado");
        }
        return this;
    }

    public List<Cuadrilla> choices0AsignarCuadrilla() {
        return cuadrillaRepository.Listar();
    }

    @Override
    public String toString() {
        return org.apache.isis.applib.util.ObjectContracts.toString(this, "dni");
    }
    //endregion

    @Inject @NotPersistent
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
    MessageService messageService;

    @Inject @NotPersistent
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
    FactoryService factoryService;

    @Inject @NotPersistent
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
    CuadrillaRepositorio cuadrillaRepository;

    @Inject @NotPersistent
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
    ReclamoRepositorio reclamoRepository;
}