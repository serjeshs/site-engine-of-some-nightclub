package by.ladyka.club.endpoints;

import by.ladyka.club.dto.EventReportDto;
import by.ladyka.club.service.EventReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/event/report")
public class EventReportController {

	@Autowired
	EventReportService eventReportService;

	@GetMapping
	public ResponseEntity<EventReportDto> getEventReport(@RequestParam(name = "id") Long id) {
		return new ResponseEntity<EventReportDto>(eventReportService.getEventReport(id), HttpStatus.OK);
	}
}
