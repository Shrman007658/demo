package com.craft.demo.service;

import com.craft.demo.model.PlayerData;
import com.craft.demo.model.ResponseModel;
import com.craft.demo.repository.ScoreRepository;
import com.craft.demo.repository.TimeCheckpointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
@Service
public class TopScoreCalculatorService implements ScoreService{

    ScoreRepository scoreRepository;

    ResponseModel responseModel;
    TimeCheckpointRepository timeCheckpointRepository;

    public TopScoreCalculatorService(ScoreRepository scoreRepository, ResponseModel responseModel, TimeCheckpointRepository timeCheckpointRepository) {
        this.scoreRepository = scoreRepository;
        this.responseModel = responseModel;
        this.timeCheckpointRepository = timeCheckpointRepository;
    }
    @Override
    public ResponseModel getTopScores(int count) {
        if(count<1)
            count=1;
        List<PlayerData> topNPlayers = scoreRepository.getTopScores(count);
        // Ensure that responseModel is properly initialized and populated
        responseModel.setPlayers(topNPlayers);
        Instant instant = Instant.ofEpochMilli(timeCheckpointRepository.getLatestCheckpoint());        String timestamp= DateTimeFormatter
                .ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.of("UTC"))
                .format(instant);
        responseModel.setReportUpdatedTimestamp(timestamp);
        return responseModel;
    }
}
