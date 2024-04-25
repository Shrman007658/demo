package com.craft.demo.utils.fileUtils;

import com.craft.demo.repository.ScoreRepository;
import com.craft.demo.utils.fileGetters.FileGetterImplLocalStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;


@Component
public class FileUtilities {
    ScoreRepository scoreRepository;
    FileGetterImplLocalStorage fileGetter;
    String fileLocation;

    @Autowired
    public FileUtilities(ScoreRepository scoreRepository, FileGetterImplLocalStorage fileGetter, @Value("${scoreReader.file.location}") String fileLocation)
    {
        this.scoreRepository=scoreRepository;
        this.fileGetter = fileGetter;
        this.fileLocation=fileLocation;
    }
    public long getLastAlteredTimestamp()
    {
        try {
            System.out.println("Getting file at " + fileLocation);
            if(fileGetter.getFile(fileLocation).exists())
            return fileGetter.getFile(fileLocation).lastModified();
            else
                throw new FileNotFoundException("File not found!");
        } catch (Exception e) {
            System.err.println("File not found!");
            throw new RuntimeException(e);
        }
    }
}
