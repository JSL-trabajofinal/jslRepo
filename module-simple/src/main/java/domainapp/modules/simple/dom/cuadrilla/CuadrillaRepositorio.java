package domainapp.modules.simple.dom.cuadrilla;
import domainapp.modules.simple.dom.ayudante.Ayudante;
import domainapp.modules.simple.dom.tecnico.Tecnico;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.repository.RepositoryService;

import java.util.List;

@DomainService(
        nature = NatureOfService.DOMAIN,
        repositoryFor = Cuadrilla.class
)
public class CuadrillaRepositorio {

    @Programmatic
    public List<Cuadrilla> Listar() {

        return repositoryService.allMatches(
                new QueryDefault<>(
                        Cuadrilla.class,
                        "find"));
    }

    @Programmatic
    public List<Cuadrilla> Listar(Cuadrilla nombre){

        return repositoryService.allMatches(
                new QueryDefault<>(
                        Cuadrilla.class,
                        "findByCuadrilla",
                        "nombre", nombre));
    }

/*
    @Programmatic
    public List<Cuadrilla> Listar(Tecnico tecnico){

        return repositoryService.allMatches(
                new QueryDefault<>(
                        Cuadrilla.class,
                        "findByTecnico",
                        "tecnico", tecnico));
    }

    @Programmatic
    public List<Cuadrilla> Listar(Ayudante ayudante){

        return repositoryService.allMatches(
                new QueryDefault<>(
                        Cuadrilla.class,
                        "findByAyudante",
                        "ayudante", ayudante));
    }*/

    @Programmatic
    public Cuadrilla findByNombre(final String nombre) {

        return repositoryService.uniqueMatch(
                new QueryDefault<>(
                        Cuadrilla.class,
                        "findByNombre",
                        "nombre", nombre));
    }

    @Programmatic
    public List<Cuadrilla> findByNombreContains(final String nombre) {

        return repositoryService.allMatches(
                new QueryDefault<>(
                        Cuadrilla.class,
                        "findByNombreContains",
                        "nombre", nombre));
    }

    @Programmatic
    public Cuadrilla create(final String nombre, final Tecnico tecnico, final Ayudante ayudante) {

        final Cuadrilla cuadrilla = new Cuadrilla(nombre, tecnico, ayudante);
        repositoryService.persist(cuadrilla);
        return cuadrilla;
    }

    @Programmatic
    public Cuadrilla findOrCreate(final String nombre, final Tecnico tecnico, Ayudante ayudante) {
        Cuadrilla cuadrilla = findByNombre(nombre);
        if (cuadrilla == null) {
            cuadrilla = create(nombre, tecnico, ayudante);
        }
        return cuadrilla;
    }

    @javax.inject.Inject
    RepositoryService repositoryService;
}
