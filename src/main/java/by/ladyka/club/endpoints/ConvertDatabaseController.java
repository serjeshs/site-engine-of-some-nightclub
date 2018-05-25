package by.ladyka.club.endpoints;

import by.ladyka.club.service.ConvertDatabaseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ConvertDatabaseController {

    private static final Logger logger = LogManager.getLogger(ConvertDatabaseController.class);
    private final ConvertDatabaseService convertDatabaseService;

    @Autowired
    public ConvertDatabaseController(ConvertDatabaseService convertDatabaseService) {
        this.convertDatabaseService = convertDatabaseService;
    }

    @RequestMapping(value = "api/converter", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity f() {
        return new ResponseEntity<>(convertDatabaseService.convertDataBase(), HttpStatus.OK);
    }

    @RequestMapping(value = "api/logger", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity logger() {
        logger.trace("TRACE_TEST_TRACE");
        logger.debug("LOG " + System.currentTimeMillis());
        logger.info("IMAGINATION " + System.currentTimeMillis());
        logger.warn("OLOLO");
        logger.error("О БОЖЕ ТЫ МОЙ!!!");
        logger.debug("Debugging log");
        logger.info("Info log");
        logger.warn("Hey, This is a warning!");
        logger.error("Oops! We have an Error. OK");
        logger.fatal("Damn! Fatal error. Please fix me.");
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
