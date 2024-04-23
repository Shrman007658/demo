package com.craft.demo.utils;

import com.craft.demo.repository.ScoreRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.util.List;


@Component
public class FileUtilities {
    ScoreRepository scoreRepository;
    FileOperationsImpl fileOperations;
    String fileLocation;

    @Autowired
    public FileUtilities(ScoreRepository scoreRepository,FileOperationsImpl fileOperations, @Value("${scoreReader.file.location}") String fileLocation)
    {
        this.scoreRepository=scoreRepository;
        this.fileOperations=fileOperations;
        this.fileLocation=fileLocation;
    }
    public long getLastAlteredTimestamp()
    {
        return fileOperations.getFile(fileLocation).lastModified();
    }
    public CSVParser readCSVFile() {
        try {
            System.out.println("Reading CSV File at location: " + fileLocation);
            CSVParser parser = new CSVParser(new BufferedReader(new java.io.FileReader(fileLocation)),CSVFormat.DEFAULT.withHeader("playerId", "playerName", "playerScore"));
            return parser;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
