package domainapp.modules.simple.dom.reportes;

public class RepoReclamo {

    private String nroReclamo;
    private String nombre;
    private String apellido;
    //private String nombreCompleto;
    private String descripcion;
    private String estado;
    private String tipoReclamo;
    //private String cuadrilla;

    public RepoReclamo(String nroReclamo, String nombre,String apellido,String descripcion, String estado, String tipoReclamo){//, String cuadrilla){
        this.nroReclamo = nroReclamo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.descripcion = descripcion;
        this.estado = estado;
        this.tipoReclamo = tipoReclamo;
        //this.cuadrilla = cuadrilla;
    }

    public RepoReclamo(){}

    public String getNroReclamo() {
        return this.nroReclamo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getApellido() {return this.apellido;}

    public String getDescripcion() {
        return this.descripcion;
    }

    public String getEstado() {
        return this.estado;
    }

    public String getTipoReclamo() {
        return this.tipoReclamo;
    }

//    public String getCuadrilla() {
//        return this.cuadrilla;
//    }
}
