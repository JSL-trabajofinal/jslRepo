package domainapp.modules.simple.dom.reportes;

public class RepoReclamo {

    private String nroReclamo;
    private String fecha;
    private String tipoReclamo;
    private String estado;
    private String cuadrilla;


    public RepoReclamo(String nroReclamo, String fecha, String tipoReclamo, String estado, String cuadrilla){

        this.nroReclamo = nroReclamo;
        this.fecha = fecha;
        this.tipoReclamo = tipoReclamo;
        this.estado = estado;
        this.cuadrilla = cuadrilla;

    }

    public RepoReclamo(){}

    public String getNroReclamo(){ return this.nroReclamo; }
    public String getFecha(){ return this.fecha; }
    public String getTipoReclamo(){ return this.tipoReclamo; }
    public String getEstado(){ return this.estado; }
    public String getCuadrilla(){ return this.cuadrilla; }


}

