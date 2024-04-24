package com.craft.demo.utils.fileGetters;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class FileGetterImplLocalStorage implements FileGetter {
    @Override
    public File getFile(String fileLocation) {
        try
        {
            return FileUtils.getFile(fileLocation);

        }
        catch (Exception e)
        {
            return null;
        }

    }
}
