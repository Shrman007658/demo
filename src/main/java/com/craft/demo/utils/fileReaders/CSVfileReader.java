package com.craft.demo.utils.fileReaders;

import com.craft.demo.model.PlayerData;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVfileReader implements FileReader{
    @Override
    public List<PlayerData> readFile(String fileLocation) throws IOException {
        CSVParser parser;
        try {
            System.out.println("Reading CSV File at location: " + fileLocation);
            parser = new CSVParser(new BufferedReader(new java.io.FileReader(fileLocation)), CSVFormat.DEFAULT.withHeader("playerId", "playerName", "playerScore"));
            System.out.println("Parsing!! ");
            List<PlayerData> listOfPlayersInCSV = new ArrayList<>();
            parser.stream().forEach((record) ->
            {
                String playerId = record.get("playerId");
                String playerName= record.get("playerName");
                String playerScore= record.get("playerScore");
                System.out.println(playerScore+ " " + playerId);
                listOfPlayersInCSV.add(new PlayerData(Long.valueOf(playerId),playerName,Long.valueOf(playerScore)));
            });
            return listOfPlayersInCSV;
        } catch (Throwable e) {
            System.out.println("Could not read CSV File");
            throw e;//return new arraylist empty which would nto cause nullpointer exceptions
        }
    }

}
