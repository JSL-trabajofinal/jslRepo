package domainapp.modules.simple.dom.reportes;

public class RepoTecnico {

    private String nombre;

    public RepoTecnico(String nombre){
        this.nombre = nombre;
    }

    public RepoTecnico(){}

    public String getNombre(){ return this.nombre; }
}
