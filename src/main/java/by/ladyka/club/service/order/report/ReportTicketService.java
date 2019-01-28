package by.ladyka.club.service.order.report;

import by.ladyka.club.dto.report.TicketOrderReportDto;
import by.ladyka.club.dto.tikets.TablePlaceDto;
import by.ladyka.club.service.order.OrderTicketsService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReportTicketService {

	private final String defaultPdfStyle = "defaultPdf";
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
		order.setPayStatus(translatePayStatus(order.getPayStatus()));
		parameters.put("order", order);

		//TODO rewrite this part of report  !!!!11111 FFFUUUUUCCCCKKKKKK

		if (order.getTables().size() > 0) {
			StringBuilder tablePlaces = new StringBuilder();
			for (TablePlaceDto tablePlaceDto : order.getTables()) {
				tablePlaces.append(String.format("Столик №%d. Место №%d\n", tablePlaceDto.getTable(), tablePlaceDto.getPlace()));
			}
			parameters.put("tablePlaces", tablePlaces.toString());
		} else {
			parameters.put("tablePlaces", "Нет");
		}



		final JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
		JRDesignStyle defaultStyle = new JRDesignStyle();
		defaultStyle.setName(defaultPdfStyle);
		defaultStyle.setPdfEmbedded(true);
		defaultStyle.setPdfEncoding("Identity-H");
		defaultStyle.setPdfFontName("fonts/arial.ttf");
		defaultStyle.setDefault(true);

		jasperPrint.setDefaultStyle(defaultStyle);
		simpleExporter.exportToPdf(jasperPrint, outputStream);
	}

	private String translatePayStatus(String payStatus) {
		return "successful".equals(payStatus) ? "Оплачено" : "Не оплачено";
	}
}
