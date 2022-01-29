package domainapp.modules.simple.dom.usuario;

import domainapp.modules.simple.dom.cuadrilla.CuadrillaRepositorio;
import domainapp.modules.simple.dom.reclamo.Estado;
import domainapp.modules.simple.dom.reclamo.Reclamo;
import domainapp.modules.simple.dom.reclamo.TipoReclamo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.services.factory.FactoryService;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.joda.time.LocalDate;

import javax.inject.Inject;
import javax.jdo.annotations.*;
import java.util.ArrayList;
import java.util.List;

@PersistenceCapable(identityType = IdentityType.DATASTORE,schema = "simple",table = "Usuario")
@DatastoreIdentity(strategy = IdGeneratorStrategy.IDENTITY,column = "id")
@Version(strategy = VersionStrategy.VERSION_NUMBER,column = "version")
@Queries({
        @Query(
                name = "find", language = "JDOQL",
                value = "SELECT "
                        + "FROM domainapp.modules.simple.dom.usuario.Usuario "
                        + "ORDER BY nombre ASC"),
        @Query(
                name = "findByNroReclamo", language = "JDOQL",
                value = "SELECT "
                        + "FROM domainapp.modules.simple.dom.usuario.Usuario "
                        + "WHERE dni == :dni "
                        + "ORDER BY dni ASC")
})
@Unique(name="Usuario_dni_UNQ", members = {"dni"})
@DomainObject(editing = Editing.DISABLED)
@DomainObjectLayout(bookmarking = BookmarkPolicy.AS_ROOT)
@Getter @Setter
public class Usuario implements Comparable<Usuario>{

    @Column(allowsNull = "false", length = 8)
    @Property()
    @Title()
    private String dni;

    @Column(allowsNull = "false", length = 40)
    @Property()
    @Title()
    private String nombre;

    @Column(allowsNull = "false", length = 40)
    @Property()
    private String apellido;

    @Column(allowsNull = "false", length = 40)
    @Property()
    private String direccion;

    @Column(allowsNull = "false", length = 40)
    @Property()
    private String email;

    @Column(allowsNull = "false", length = 19)
    @Property()
    private String telefono;

    @Persistent(mappedBy = "usuario", dependentElement = "true")
    @Collection()
    private List<Reclamo> reclamos = new ArrayList<Reclamo>();

    public String RepoDni(){ return this.dni;}
    public String RepoApellido(){ return this.apellido;}
    public String RepoNombre(){ return this.nombre;}
    public String RepoDireccion() { return this.direccion;}
    public String RepoTelefono() {return this.telefono;}


    public Usuario(){}

    public Usuario(
            String dni,
            String nombre,
            String apellido,
            String direccion,
            String email,
            String telefono){

        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.email = email;
        this.telefono = telefono;
    }

    public Usuario(
            String dni,
            String nombre,
            String apellido,
            String direccion,
            String telefono,
            List<Reclamo> reclamos){

        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.reclamos = reclamos;
    }

    public String getNombre(){
        return this.nombre;
    }

    @Action()
    @ActionLayout(named = "Editar")
    public Usuario update(
            @Parameter(maxLength = 40)
            @ParameterLayout(named = "DNI: ")
            final String dni,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Nombre: ")
            final String nombre,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Apellido: ")
            final String apellido,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Direccion: ")
            final String direccion,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Email: ")
            final String email,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Telefono: ")
            final String telefono){

        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        return this;
    }

    public String default0Update() {return getDni();}
    public String default1Update() {return getNombre();}
    public String default2Update() {return getApellido();}
    public String default3Update() {return getDireccion();}
    public String default4Update() {return getEmail();}
    public String default5Update() {return getTelefono();}

    @Action()
    @ActionLayout(named = "Cargar Reclamo")
    public Usuario addReclamo(
            //@ParameterLayout(named="Fecha: ") final LocalDate fecha,
            @ParameterLayout(named="Tipo de Reclamo") final TipoReclamo tipoReclamo
    ){

        final Reclamo reclamo = factoryService.instantiate(Reclamo.class);
        reclamo.setUsuario(this);
        reclamo.setFecha(LocalDate.now());
        reclamo.setTipoReclamo(tipoReclamo);
        reclamo.setEstado(Estado.Sin_Asignar);
        getReclamos().add(reclamo);
        repositoryService.persist(reclamo);
        return this;
    }

    //region > compareTo, toString
    @Override
    public int compareTo(final Usuario other) {
        return org.apache.isis.applib.util.ObjectContracts.compare(this, other, "dni");
    }

    @Override
    public String toString() {
        return org.apache.isis.applib.util.ObjectContracts.toString(this, "dni");
    }
    //endregion

    @Inject @NotPersistent
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
    FactoryService factoryService;

    @Inject @NotPersistent
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
    RepositoryService repositoryService;

    @Inject @NotPersistent
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
    CuadrillaRepositorio cuadrillaRepository;

    @Inject @NotPersistent
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
    UsuarioRepositorio usuarioRepository;
}
