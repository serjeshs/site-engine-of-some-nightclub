package by.ladyka.club.endpoints;

import by.ladyka.club.dto.menu.TicketOrderDto;
import by.ladyka.club.service.MenuService;
import by.ladyka.club.service.order.OrderTicketsService;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping(value = "/api/private/orders")
@AllArgsConstructor
public class PrivateOrderController {

	private MenuService menuService;
	private OrderTicketsService orderTicketsService;

	@GetMapping(value = "/{uuid}")
	public TicketOrderDto order(@PathVariable String uuid) {
		try {
			return orderTicketsService.getOrder(uuid);
		} catch (RuntimeException ex) {
			return menuService.getOrder(uuid);
		}
	}

	@GetMapping(value = "/{uuid}/pdf")
	public void orderPdf(@PathVariable String uuid, HttpServletResponse httpServletResponse) throws IOException, JRException {
		JRPdfExporter exporter = new JRPdfExporter();

		String fileLocationInClasspath = "reports/user-private-tickets.jrxml";
//		Resource resource = new ClassPathResource(fileLocationInClasspath);
//		InputStream resourceInputStream = resource.getInputStream();


		InputStream resourceInputStream
				= getClass().getResourceAsStream(fileLocationInClasspath);
		JRDataSource ds = new JREmptyDataSource();
		JasperPrint jasperPrint = JasperFillManager.fillReport(
				resourceInputStream, null, ds);

		exporter.setExporterInput(new SimpleExporterInput(resourceInputStream));
		exporter.setExporterOutput(
				new SimpleOutputStreamExporterOutput(httpServletResponse.getOutputStream()));

		SimplePdfReportConfiguration reportConfig
				= new SimplePdfReportConfiguration();
		reportConfig.setSizePageToContent(true);
		reportConfig.setForceLineBreakPolicy(false);

		SimplePdfExporterConfiguration exportConfig
				= new SimplePdfExporterConfiguration();
		exportConfig.setMetadataAuthor("baeldung");
		exportConfig.setEncrypted(true);
		exportConfig.setAllowedPermissionsHint("PRINTING");
		exporter.setConfiguration(reportConfig);
		exporter.setConfiguration(exportConfig);

		exporter.exportReport();

//		try {
//			return orderTicketsService.getOrder(uuid);
//		} catch (RuntimeException ex) {
//
//		}
	}

}
