package by.ladyka.club.endpoints;

import by.ladyka.club.service.ConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Converter {

    @Autowired
    ConverterService converterService;

    @RequestMapping(value = "api/converter", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity f() {
        return new ResponseEntity<>(converterService.convertDataBase(), HttpStatus.OK);
    }
}
