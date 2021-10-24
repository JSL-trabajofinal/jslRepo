package domainapp.modules.simple.dom.cuadrilla;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.apache.isis.applib.annotation.*;

import javax.jdo.annotations.*;
import java.util.List;

@PersistenceCapable(
        identityType=IdentityType.DATASTORE,
        schema = "simple",
        table = "Cuadrilla"
)

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
                        + "FROM domainapp.modules.simple.dom.cuadrilla.Cuadrilla  "
                        + "ORDER BY nombre ASC"),

        @Query(
                name = "findByNombre", language = "JDOQL",
                value = "SELECT "
                        + "FROM domainapp.modules.simple.dom.cuadrilla.Cuadrilla "
                        + "WHERE nombre == :nombre "),
         @Query(
                name = "findByTecnico", language = "JDOQL",
                value = "SELECT "
                        + "FROM domainapp.modules.simple.dom.cuadrilla.Cuadrilla "
                        + "WHERE tecnico == :tecnico "
                        + "ORDER BY nombre ASC"),

        @Query(
                name = "findByAyudante", language = "JDOQL",
                value = "SELECT "
                        + "FROM domainapp.modules.simple.dom.cuadrilla.Cuadrilla "
                        + "WHERE ayudante == :ayudante "
                        + "ORDER BY nombre ASC"),

})

@Unique(name = "Cuadrilla_nombre_UNQ", members = { "nombre" })
@DomainObject(
        editing = Editing.DISABLED
)
@DomainObjectLayout(
        bookmarking = BookmarkPolicy.AS_ROOT
)
@Getter @Setter
public class Cuadrilla implements Comparable<Cuadrilla> {

    @Column(allowsNull = "false", length = 40)
    @Property()
    private String nombre;

    @Column(allowsNull = "false")
    @Property()
    private Tecnico tecnico;

    @Column(allowsNull = "false")
    @Property()
    private Ayudante ayudante;

/*    @Persistent(mappedBy = "cuadrilla", defaultFetchGroup = "true")
    @Column(allowsNull = "false")
    @Property()
    private List<Reclamo> reclamos;*/


    @Persistent(mappedBy = "cuadrilla", defaultFetchGroup = "true")
    @Column(allowsNull = "false")
    @Property()
    private List<Reclamo> reclamos;

    public String title(){
        return getNombre();
    }

/*
    public String RepoNombre(){ return this.nombre; }
    public String RepoTecnico(){ return this.tecnico.toString(); }
    public String RepoAyudante() {return this.ayudante.toString(); }*/

    public Cuadrilla(){}

    public Cuadrilla(
            final String nombre,
            final Tecnico tecnico,
            final Ayudante ayudante){

        this.nombre = nombre;
        this.tecnico = tecnico;
        this.ayudante = ayudante;
    }

    public Cuadrilla(
            final String nombre,
            final Tecnico tecnico,
            final Ayudante ayudante,
            final List<Reclamo> reclamos){

        this.nombre = nombre;
        this.tecnico = tecnico;
        this.ayudante = ayudante;
        this.reclamos = reclamos;
    }

    @Action()
    @ActionLayout(named = "Editar")
    public Cuadrilla update(

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Nombre: ")
            final String nombre,

            @Parameter(optionality = Optionality.MANDATORY)
            @ParameterLayout(named = "Tecnico: ")
            final Tecnico tecnico,

            @Parameter(optionality = Optionality.MANDATORY)
            @ParameterLayout(named = "Ayudante: ")
            final Ayudante ayudante){

        this.nombre = nombre;
        this.tecnico = tecnico;
        this.ayudante = ayudante;

        return this;
    }

    public String default0Update() {return getNombre();}

    public Tecnico default1Update() {return getTecnico();}
    public List<Tecnico> choices1Update() { return tecnicoRepository.Listar();}

    public Ayudante default2Update() {return getAyudante();}
    public List<Ayudante> choices2Update() {
        return ayudanteRepository.Listar();
    }



    //region > compareTo, toString
    @Override
    public int compareTo(final Cuadrilla other) {
        return org.apache.isis.applib.util.ObjectContracts.compare(this, other, "nombre");
    }

    @Override
    public String toString() {
        return org.apache.isis.applib.util.ObjectContracts.toString(this, "nombre");
    }
    //endregion

    @javax.inject.Inject
    @NotPersistent
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
    TecnicoRepositorio tecnicoRepository;

    @javax.inject.Inject
    @NotPersistent
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
    AyudanteRepositorio ayudanteRepository;

    @javax.inject.Inject
    @NotPersistent
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
    CuadrillaRepositorio cuadrillaRepository;

}
