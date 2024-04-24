package com.craft.demo.utils.fileUtils;

import com.craft.demo.repository.ScoreRepository;
import com.craft.demo.utils.fileGetters.FileGetterImplLocalStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


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
        return fileGetter.getFile(fileLocation).lastModified();
    }
}
