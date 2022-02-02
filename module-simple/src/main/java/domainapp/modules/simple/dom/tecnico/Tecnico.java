package domainapp.modules.simple.dom.tecnico;

import domainapp.modules.simple.dom.cuadrilla.Cuadrilla;
import domainapp.modules.simple.dom.cuadrilla.CuadrillaRepositorio;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.apache.isis.applib.annotation.*;

import javax.inject.Inject;
import javax.jdo.annotations.*;
import java.util.Arrays;
import java.util.List;

@PersistenceCapable(identityType = IdentityType.DATASTORE,schema = "simple",table = "Tecnico")
@DatastoreIdentity(strategy = IdGeneratorStrategy.IDENTITY,column = "id")
@Version(strategy = VersionStrategy.VERSION_NUMBER,column = "version")
@Queries({
        @Query(
                name = "find", language = "JDOQL",
                value = "SELECT "
                        + "FROM domainapp.modules.simple.dom.tecnico.Tecnico "
                        + "ORDER BY nombre ASC"),
/*        @Query(
                name = "findByNombreContains", language = "JDOQL",
                value = "SELECT "
                        + "FROM domainapp.modules.simple.dom.tecnico.Tecnico"
                        + "WHERE nombre.indexOf(:nombre) >= 0 "),*/

        @Query(
                name = "findByDni", language = "JDOQL",
                value = "SELECT "
                        + "FROM domainapp.modules.simple.dom.tecnico.Tecnico "
                        + "WHERE dni == :dni "
                        + "ORDER BY dni ASC")
})
@Unique(name="Tecnico_dni_UNQ", members = {"dni"})
@DomainObject(editing = Editing.DISABLED)
@DomainObjectLayout(bookmarking = BookmarkPolicy.AS_ROOT)
@Getter @Setter
public class Tecnico implements Comparable<Tecnico>{

    @Column(allowsNull = "false", length = 40)
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
    private String telefono;

    @Persistent(mappedBy = "tecnico", defaultFetchGroup = "true")
    @Column(allowsNull = "true")
    @Property()
    private List<Cuadrilla> cuadrillaTecnico;

    public String RepoDni() { return this.dni; }
    public String RepoApellido() { return this.apellido; }
    public String RepoNombre() { return this.nombre; }
    public String RepoTelefono() { return this.telefono; }
    public String RepoDireccion() { return this.direccion; }

    public Tecnico(){}

    public Tecnico(
            String dni,
            String nombre,
            String apellido,
            String direccion,
            String telefono){

        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Tecnico(
            String dni,
            String nombre,
            String apellido,
            String direccion,
            String telefono,
             List<Cuadrilla> cuadrillaTecnico){

        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.cuadrillaTecnico = cuadrillaTecnico;
    }

    public String getNombre(){
        return this.nombre;
    }

    @Action()
    @ActionLayout(named = "Editar")
    public Tecnico update(
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
    public String default4Update() {return getTelefono();}

    //region > compareTo, toString
    @Override
    public int compareTo(final Tecnico other) {
        return org.apache.isis.applib.util.ObjectContracts.compare(this, other, "dni");
    }

    @Override
    public String toString() {
        return org.apache.isis.applib.util.ObjectContracts.toString(this, "dni");
    }
    //endregion

    @Inject @NotPersistent
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
    CuadrillaRepositorio cuadrillaRepository;

    @Inject @NotPersistent
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
    TecnicoRepositorio tecnicoRepository;
}
