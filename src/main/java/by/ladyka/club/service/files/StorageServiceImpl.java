package by.ladyka.club.service.files;

import by.ladyka.club.config.CustomSettings;
import by.ladyka.club.entity.FileEntity;
import by.ladyka.club.repository.FilesRepository;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.time.LocalDateTime;

@Service
public class StorageServiceImpl implements StorageService {
    private final CustomSettings customSettings;
    private final FilesRepository filesRepository;

    @Autowired
    public StorageServiceImpl(CustomSettings customSettings, FilesRepository filesRepository) {
        this.customSettings = customSettings;
        this.filesRepository = filesRepository;
    }

    @Override
    public String store(MultipartFile file) throws IOException {
        LocalDateTime date = LocalDateTime.now();
        String directoryOut = date.getYear() + File.separator + date.getMonthValue();
        new File(customSettings.getFilesDirectory() + File.separator + directoryOut).mkdirs();
        String fN = file.getOriginalFilename();
        String fileName = (int) (Math.random() * 10000) + fN.substring(fN.length() - 5);
        File outFile = new File(customSettings.getFilesDirectory() + File.separator + directoryOut + File.separator + fileName);
        OutputStream outStream = new FileOutputStream(outFile);
        IOUtils.copy(file.getInputStream(), outStream);
        outStream.close();
        FileEntity fileEntity = new FileEntity();
        fileEntity.setFilePath(directoryOut + fileName);
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