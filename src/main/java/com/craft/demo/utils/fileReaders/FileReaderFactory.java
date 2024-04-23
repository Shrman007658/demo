package com.craft.demo.utils.fileReaders;


import org.springframework.stereotype.Component;

@Component
public class FileReaderFactory
{

    public FileReader getFileReader(String fileType)
    {
        if(fileType.equals("csv"))
            return new CSVfileReader();
        //add new implementations of FileReader Interface here
        return null;
    }
}
