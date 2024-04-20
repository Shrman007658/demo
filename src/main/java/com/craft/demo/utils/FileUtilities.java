package com.craft.demo.utils;

import com.craft.demo.repository.ScoreRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.util.List;


@Component
public class FileUtilities {
    @Autowired
    ScoreRepository scoreRepository;
    @Value("${scoreReader.file.location}")
    String fileLocation;

    public long getLastAlteredTimestamp()
    {
        long lastModifiedTimeMillis = org.apache.commons.io.FileUtils.getFile(fileLocation).lastModified();
        System.out.println(lastModifiedTimeMillis);
        return lastModifiedTimeMillis;
    }
    public CSVParser readCSVFile() {
        try {
            System.out.println("Reading CSV File at location: " + fileLocation);
            List<String> result = scoreRepository.getAllPlayerInfo();
            for (String res : result) {
                System.out.println(res);
            }
            CSVParser parser = new CSVParser(new BufferedReader(new java.io.FileReader(fileLocation)),CSVFormat.DEFAULT.withHeader("playerId", "playerName", "playerScore"));
            return parser;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
