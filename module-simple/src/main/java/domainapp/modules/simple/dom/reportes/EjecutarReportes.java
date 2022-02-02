package domainapp.modules.simple.dom.reportes;

import domainapp.modules.simple.dom.cuadrilla.Cuadrilla;
import domainapp.modules.simple.dom.reclamo.Reclamo;
import domainapp.modules.simple.dom.tecnico.Tecnico;
import domainapp.modules.simple.dom.usuario.Usuario;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.apache.isis.applib.value.Blob;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EjecutarReportes {

    public Blob ListadoTecnicosPDF(List<Tecnico> tecnicos) throws JRException, IOException{

        List<RepoTecnico> repoTecnicos = new ArrayList<>();
        repoTecnicos.add(new RepoTecnico());

        for (Tecnico tecnico : tecnicos) {
            RepoTecnico repoTecnico = new RepoTecnico(tecnico.RepoDni(), tecnico.RepoApellido(), tecnico.RepoNombre(),tecnico.RepoTelefono(), tecnico.RepoDireccion());
            repoTecnicos.add(repoTecnico);
        }

        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(repoTecnicos);
        return GenerarArchivoPDF("ListadoTecnicosDesing.jrxml", "Listado de Tecnicos.pdf", ds);
    }

    public Blob ListadoCuadrillasPDF(List<Cuadrilla> cuadrillas) throws JRException, IOException{

        List<RepoCuadrilla> repoCuadrillas = new ArrayList<>();
        repoCuadrillas.add(new RepoCuadrilla());

        for (Cuadrilla cuadrilla : cuadrillas) {
            RepoCuadrilla repoCuadrilla = new RepoCuadrilla(cuadrilla.RepoCuadrilla(), cuadrilla.RepoTecnico(), cuadrilla.RepoAyudante());//,tecnico.RepoTelefono(), tecnico.RepoDireccion());
            repoCuadrillas.add(repoCuadrilla);
        }

        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(repoCuadrillas);
        return GenerarArchivoPDF("ListadoCuadrillasDesing.jrxml", "Listado de Cuadrillas.pdf", ds);
    }

    public Blob ListadoReclamosPDF(List<Reclamo> reclamos) throws JRException, IOException{

        List<RepoReclamo> repoReclamos = new ArrayList<>();
        repoReclamos.add(new RepoReclamo());

        for (Reclamo reclamo : reclamos) {
            RepoReclamo repoReclamo = new RepoReclamo(reclamo.RepoNroReclamo(), reclamo.RepoFecha().toString("dd-MM-yyyy"), reclamo.RepoTipoReclamo(), reclamo.RepoEstado(), reclamo.RepoCuadrilla());
            repoReclamos.add(repoReclamo);
        }

        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(repoReclamos);
        return GenerarArchivoPDF("ListadoReclamosDesing.jrxml", "Listado de Reclamos.pdf", ds);
    }

    public Blob ListadoUsuariosPDF(List<Usuario> usuarios) throws JRException, IOException{

        List<RepoUsuario> repoUsuarios = new ArrayList<>();
        repoUsuarios.add(new RepoUsuario());

        for (Usuario usuario : usuarios) {
            RepoUsuario repoUsuario = new RepoUsuario(usuario.RepoDni(), usuario.RepoApellido(), usuario.RepoNombre(), usuario.RepoDireccion(), usuario.RepoTelefono());
            repoUsuarios.add(repoUsuario);
        }

        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(repoUsuarios);
        return GenerarArchivoPDF("ListadoUsuariosDesing.jrxml", "Listado de Usuarios.pdf", ds);
    }

    private Blob GenerarArchivoPDF(String archivoDesing, String nombreSalida, JRBeanCollectionDataSource ds) throws JRException, IOException{

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(archivoDesing);
        JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("ds", ds);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, ds);
        byte[] contentBytes = JasperExportManager.exportReportToPdf(jasperPrint);

        return new Blob(nombreSalida, "application/pdf", contentBytes);

    }
}
