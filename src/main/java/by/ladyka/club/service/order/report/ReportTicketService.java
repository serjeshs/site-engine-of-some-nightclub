package by.ladyka.club.service.order.report;

import by.ladyka.club.dto.menu.TicketOrderDto;
import by.ladyka.club.dto.report.TicketOrderReportDto;
import by.ladyka.club.service.order.OrderTicketsService;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReportTicketService {

	@Autowired
	OrderTicketsService orderTicketsService;

	@Autowired
	SimpleReportExporter simpleExporter;

	public void ticketPrivate(String uuid, ServletOutputStream outputStream) throws JRException {
		final TicketOrderReportDto order = orderTicketsService.getOrderReport(uuid);
		String fileLocationInClasspath = "/reports/user-private-tickets.jrxml";
		InputStream resourceInputStream = getClass().getResourceAsStream(fileLocationInClasspath);

		JasperReport jasperReport = JasperCompileManager.compileReport(resourceInputStream);

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("title", "Билет № " + order.getId());
		parameters.put("order", order);

		JRDataSource ds = new JREmptyDataSource();
		final JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, ds);
		simpleExporter.exportToPdf(jasperPrint, "republic", outputStream);
	}
}
