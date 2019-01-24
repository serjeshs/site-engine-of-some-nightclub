package by.ladyka.club.service.order.report;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.stereotype.Component;

import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class SimpleReportExporter {

	public void exportToPdf(JasperPrint jasperPrint, OutputStream outputStream) {
		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
		try {
			exporter.exportReport();
		} catch (JRException ex) {
			Logger.getLogger(SimpleReportExporter.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
