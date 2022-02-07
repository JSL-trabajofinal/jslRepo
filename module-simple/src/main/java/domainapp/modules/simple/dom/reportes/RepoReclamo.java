package domainapp.modules.simple.dom.reportes;

public class RepoReclamo {

    private String nroReclamo;
    private String fecha;
    private String tipoReclamo;
    private String estado;
    private String usuario;



    public RepoReclamo(String nroReclamo, String fecha, String tipoReclamo, String estado, String usuario){

        this.nroReclamo = nroReclamo;
        this.fecha = fecha;
        this.tipoReclamo = tipoReclamo;
        this.estado = estado;
        this.usuario = usuario;


    }

    public RepoReclamo(){}

    public String getNroReclamo(){ return this.nroReclamo; }
    public String getFecha(){ return this.fecha; }
    public String getTipoReclamo(){ return this.tipoReclamo; }
    public String getEstado(){ return this.estado; }
    public String getUsuario(){ return this.usuario; }


}

