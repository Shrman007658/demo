package com.craft.demo.utils;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class FileOperationsImpl implements FileOperations{
    @Override
    public File getFile(String fileLocation) {
        try
        {
            System.out.println("Wont print in test. Will print while running");//this is not printed in the test because this method is mocked.
            return FileUtils.getFile(fileLocation);

        }
        catch (Exception e)
        {
            return null;
        }

    }
}
