package by.ladyka.club.service.files;

import by.ladyka.club.config.CustomSettings;
import by.ladyka.club.entity.FileEntity;
import by.ladyka.club.repository.FilesRepository;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Path;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class StorageServiceImpl implements StorageService {
    private final CustomSettings customSettings;
    private final FilesRepository filesRepository;

    @Override
    public String store(MultipartFile multipartFile) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();
        originalFilename = (originalFilename != null)? originalFilename : "null";
        InputStream inputStream = multipartFile.getInputStream();
        return store(originalFilename, inputStream);
    }

    @Override
    public String store(String originalFilename, InputStream inputStream) throws IOException {
        LocalDateTime date = LocalDateTime.now();
        String directoryOut = date.getYear() + File.separator + date.getMonthValue();
        new File(customSettings.getFilesDirectory() + File.separator + directoryOut).mkdirs();
        String fileName = (int) (Math.random() * 10000) + originalFilename.substring(0, originalFilename.length() - 4);
        File outFile = new File(customSettings.getFilesDirectory() + File.separator + directoryOut + File.separator + fileName);
        OutputStream outStream = new FileOutputStream(outFile);
        IOUtils.copy(inputStream, outStream);
        outStream.close();
        FileEntity fileEntity = new FileEntity();
        fileEntity.setFilePath(directoryOut + File.separator + fileName);
        fileEntity = filesRepository.save(fileEntity);
        return fileEntity.getFilePath();
    }

    @Override
    public Path load(String filename) {
        return null;
    }

    @Override
    public Resource loadAsResource(String filename) {
        return null;
    }
}