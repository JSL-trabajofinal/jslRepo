package domainapp.modules.simple.dom.reportes;

import domainapp.modules.simple.dom.reclamo.Reclamo;
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

    public Blob reporteReclamoPDF(List<Reclamo> reclamos)throws JRException, IOException {

        List<RepoReclamo> reclamosDatasource = new ArrayList<RepoReclamo>();

        reclamosDatasource.add(new RepoReclamo());

        for (Reclamo recla : reclamos) {

            RepoReclamo repoReclamos = new RepoReclamo(recla.ReporNumeroReclamo(), recla.ReporNombreCompleto(), recla.ReporTelefono(), recla.ReporDescripcion(), recla.ReporDireccion(), recla.ReporTipoReclamo(), recla.ReporCuadrillla());

            reclamosDatasource.add(repoReclamos);

        }

        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(reclamosDatasource);
        return GenerarArchivoPDF("ReporteReclamo.jrxml","ReporteReclamo.pdf", ds);
    }

    /*public Blob  ListadoPresupuestosPDF(List<Presupuesto> presupuestos)throws JRException, IOException{

        List<RepoPresupuestos> presupuestosDatasource = new ArrayList<RepoPresupuestos>();

        presupuestosDatasource.add(new RepoPresupuestos());


        for (Presupuesto pre : presupuestos) {

            RepoPresupuestos repoPresupuestos = new RepoPresupuestos(pre.ReporNro(),pre.ReporFecha().toString(),pre.ReporCliente(),pre.ReporPrecio(),pre.ReporEstado());

            presupuestosDatasource.add(repoPresupuestos);

        }

        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(presupuestosDatasource);
        return GenerarArchivoPDF("listadoPresupuestos.jrxml","ListadoPresupuestos.pdf", ds);
    }*/

    private Blob GenerarArchivoPDF(String archivoDesing, String nombreSalida, JRBeanCollectionDataSource ds) throws JRException, IOException{

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(archivoDesing);
        JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("ds", ds);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, ds);
        byte[] contentBytes = JasperExportManager.exportReportToPdf(jasperPrint);

        return new Blob(nombreSalida, "application/pdf", contentBytes);
    }
}

