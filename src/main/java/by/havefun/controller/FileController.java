package by.havefun.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import by.havefun.entity.FileTable;

@Controller
public class FileController extends AbstractController {

    @RequestMapping(value = "api/file/add")
    public @ResponseBody String fileAdd(MultipartFile file, HttpServletRequest request, Principal principal) throws IOException,
            URISyntaxException {
        return  "api/file/get/" + localFile.fileAdd(file, principal.getName(),true);
    }

    @RequestMapping(value = "api/file/get/*", method = {
            RequestMethod.GET, RequestMethod.HEAD
    })
    public void fileGet(HttpServletResponse response, HttpServletRequest request) {
        String filePath = "";
        InputStream is = null;
        try {

            String[] split = request.getRequestURI().split("/");
            String filehash = split[split.length - 1];

            FileTable fileTable = localFile.getFile(filehash);
            String fileName = fileTable.getFilename();
            response.setHeader("Content-Disposition", "filename=\"" + fileName + "\"");
            String s = fileName.substring(fileName.length() - 3, fileName.length()).toLowerCase();
            if (s.compareTo("jpg") == 0) {
                response.setHeader("Content-Type", "image/jpeg");
            }
            filePath = fileTable.getFilepath();
            is = new FileInputStream(filePath);

            IOUtils.copy(is, response.getOutputStream());

            response.flushBuffer();

        } catch (FileNotFoundException ex) {
            Logger logger = LoggerFactory.getLogger(getClass());
            logger.error("Файла на этом компьютере больше нет. " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }
    
}
