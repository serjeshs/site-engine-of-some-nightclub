package by.havefun.utils.file;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import by.havefun.GlobalSettings;
import by.havefun.dao.AppUserDAO;
import by.havefun.dao.BaseDAO;
import by.havefun.entity.AppUser;
import by.havefun.entity.FileTable;
import by.havefun.security.KeccakUtil;

@Service
@Transactional
public class LocalFile {

    @Autowired
    AppUserDAO appUserDAO;

    @Autowired
    BaseDAO baseDAO;

    public String fileAdd(MultipartFile file, String email) {
        return fileAdd(file, email, false);
    }
    
    public String fileAdd(MultipartFile file, String email,boolean hash) {
        

        long fileDiskName = new GregorianCalendar().getTimeInMillis();
        String fileOriginName = file.getOriginalFilename();
        
        int fileOriginNameLength = fileOriginName.length();
        if (fileOriginNameLength  > 40) {
            fileOriginName = fileOriginName.substring(fileOriginNameLength - 39, fileOriginNameLength);
        }

        
        LocalDateTime a = LocalDateTime.now();
        String folderFiles = GlobalSettings.folderFiles
                + GlobalSettings.fileSeparator
                + a.getYear()
                + GlobalSettings.fileSeparator
                + a.getMonth().getValue()
                + GlobalSettings.fileSeparator
                + a.getDayOfMonth()
                + GlobalSettings.fileSeparator;
        
        String fileDiskPath = folderFiles + fileDiskName;
        
        if (fileOriginNameLength > 3) {
                fileDiskPath += fileOriginName;
        }

        

        if (!file.isEmpty()) {
            try {
                File f = new File(fileDiskPath);
                f.mkdirs();
                file.transferTo(f);
                return addFileBase(fileOriginName, fileDiskPath, String.valueOf(fileDiskName), email);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
                return e.getLocalizedMessage();
            }
        } else {
            return "FILE IS EMPTY";
        }
    }

    @Transactional
    private String addFileBase(String fileOriginName, String fileDiskPath,
            String fileDiskName, String email) {
        AppUser appUser = appUserDAO.getAppUserFromEmail(email);
        FileTable fileTable = new FileTable();
        fileTable.setAppUser(appUser);
        fileTable.setFilename(fileOriginName);
        fileTable.setFilepath(fileDiskPath);
        fileTable.setTimeAdd();
        String hashcode = String.valueOf(KeccakUtil.getHash(String.valueOf(fileOriginName) + System.currentTimeMillis()));
        hashcode += fileOriginName.substring(fileOriginName.length() - 4, fileOriginName.length()).toLowerCase();
        fileTable.setHashcode(hashcode);
        baseDAO.saveOrUpdate(fileTable);
        //return hashcode;
        String returnValue = fileDiskPath.substring(GlobalSettings.folderFiles.length()-7);
        return returnValue;
    }

    public FileTable getFile(String filehash) {
        List<FileTable> files = baseDAO.getListEntity(FileTable.class, FileTable.COL_HASH_CODE, filehash);
        return baseDAO.getOneValue(files);
    }

}
