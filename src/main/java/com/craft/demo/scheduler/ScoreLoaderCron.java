package com.craft.demo.scheduler;

import com.craft.demo.model.PlayerData;
import com.craft.demo.repository.ScoreRepository;
import com.craft.demo.repository.TimeCheckpointRepository;
import com.craft.demo.utils.fileUtils.FileUtilities;
import com.craft.demo.utils.fileReaders.FileReaderFactory;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.util.List;

@Component
public class ScoreLoaderCron {

    Long lastUpdatedTimestamp=0L;

    FileUtilities fileUtilities;
    FileReaderFactory fileReaderFactory;
    ScoreRepository repository;
    TimeCheckpointRepository timeCheckpointRepository;
    String fileLocation;

    public ScoreLoaderCron(FileReaderFactory fileReaderFactory,FileUtilities fileUtilities, ScoreRepository repository, TimeCheckpointRepository timeCheckpointRepository, @Value("${scoreReader.file.location}")String fileLocation) {
        this.fileReaderFactory=fileReaderFactory;
        this.lastUpdatedTimestamp = 0L;
        this.fileUtilities = fileUtilities;
        this.repository = repository;
        this.timeCheckpointRepository = timeCheckpointRepository;
        this.fileLocation = fileLocation;
    }

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
            if(lastUpdatedTimestamp < fileUtilities.getLastAlteredTimestamp())
            {
                    System.out.println("Parsing!! ");
                    List<PlayerData> playerDataList= fileReaderFactory.getFileReader("csv").readFile(fileLocation);
                    playerDataList.forEach(player ->
                    {
                        Long playerId = player.getPlayerId();
                        String playerName=player.getPlayerName();
                        Long playerScore = player.getPlayerScore();
                        int numOfUpdated=repository.updateScore(playerId,playerScore);
                        if(numOfUpdated==0)
                            repository.insertNewPlayer(playerName,playerScore);

                    });
                    lastUpdatedTimestamp= fileUtilities.getLastAlteredTimestamp();
                    timeCheckpointRepository.updateTimestamp(lastUpdatedTimestamp);
            }
            else
            {
                System.out.println("The file has not been updated yet. Going to sleep...");
            }


        }
        catch(NumberFormatException num)
        {
            System.err.println("Incorrect Number format");
            num.printStackTrace();
        }
        catch(FileNotFoundException file)
        {
            System.err.println("File does not exist");
            file.printStackTrace();
        }
        catch(Exception e)
        {
            System.err.println("Exception while running cron job");
            e.printStackTrace();
        }
    }
}
