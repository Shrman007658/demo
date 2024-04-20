package com.craft.demo.scheduler;

import com.craft.demo.repository.ScoreRepository;
import com.craft.demo.repository.TimeCheckpointRepository;
import com.craft.demo.utils.FileUtilities;
import jakarta.transaction.Transactional;
import org.apache.commons.csv.CSVParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScoreLoaderCron {

    Long lastUpdatedTimestamp=0L;
    @Autowired
    FileUtilities fileUtilities;
    @Autowired
    ScoreRepository repository;
    @Autowired
    TimeCheckpointRepository timeCheckpointRepository;
    @Scheduled(cron = "${scoreReader.cron.cronTime}")
    @Transactional
    public void loadScoresToDb()
    {
        try {
            //Read from File and load to DB, update the checkpoint somewhere.
            //While reading the file, check the checkpoint and then read all lines and keep firing queries to update
            System.out.println("Scheduler is working! ");
            lastUpdatedTimestamp = timeCheckpointRepository.getLatestCheckpoint();
            if(lastUpdatedTimestamp==null)
                lastUpdatedTimestamp=0L;
            System.out.println(lastUpdatedTimestamp);
            if(lastUpdatedTimestamp < fileUtilities.getLastAlteredTimestamp())
            {
                CSVParser parser = null;
                parser = fileUtilities.readCSVFile();
                if (parser != null)
                {
                    System.out.println("Parsing!! ");
                    parser.stream().forEach((record) ->
                    {
                        String playerId = record.get("playerId");
                        String playerName= record.get("playerName");
                        String playerScore=record.get("playerScore");
                        System.out.println(playerScore+ " " + playerId);
                        int numOfUpdated=repository.updateScore(playerId,playerScore);
                        if(numOfUpdated==0)
                            repository.insertNewPlayer(playerName,Long.parseLong(playerScore));

                    });
                    //Put the values in the DB
                    lastUpdatedTimestamp= fileUtilities.getLastAlteredTimestamp();
                    timeCheckpointRepository.updateTimestamp(lastUpdatedTimestamp);
                }
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally {

        }
    }
}
