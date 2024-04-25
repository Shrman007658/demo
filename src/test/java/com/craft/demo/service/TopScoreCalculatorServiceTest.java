package com.craft.demo.service;

import com.craft.demo.entities.Player;
import com.craft.demo.model.PlayerData;
import com.craft.demo.model.ResponseModel;
import com.craft.demo.repository.ScoreRepository;
import com.craft.demo.repository.TimeCheckpointRepository;
import org.apache.coyote.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class TopScoreCalculatorServiceTest {

    @Mock
    ScoreRepository scoreRepository;

    @Mock
    TimeCheckpointRepository timeCheckpointRepository;

    TopScoreCalculatorService topScoreCalculatorService;


    ResponseModel responseModel;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        responseModel = new ResponseModel(new ArrayList<>(),"");
        topScoreCalculatorService = new TopScoreCalculatorService(scoreRepository,responseModel,timeCheckpointRepository);
    }

    @Test
    void getTopScores() {
        List<PlayerData> playerDataList = new ArrayList<>();
        playerDataList.add(new PlayerData(1L, "John", 100L));
        playerDataList.add(new PlayerData(2L, "Alice", 90L));

        // Mocked values for dependencies
        when(scoreRepository.getTopScores(2)).thenReturn(playerDataList);
        when(timeCheckpointRepository.getLatestCheckpoint()).thenReturn(1619185811000L); // Example timestamp in milliseconds
        ResponseModel actualResponseModel = topScoreCalculatorService.getTopScores(2);
        assertEquals(actualResponseModel.getPlayers(),playerDataList);

    }

    @Test
    void verifyMethodCalls()
    {
        List<PlayerData> playerDataList = new ArrayList<>();
        playerDataList.add(new PlayerData(1L, "John", 100L));
        playerDataList.add(new PlayerData(2L, "Alice", 90L));

        // Mocked values for dependencies
        when(scoreRepository.getTopScores(2)).thenReturn(playerDataList);
        when(timeCheckpointRepository.getLatestCheckpoint()).thenReturn(1619185811000L);
        topScoreCalculatorService.getTopScores(2);
        verify(scoreRepository).getTopScores(2);
        verify(timeCheckpointRepository).getLatestCheckpoint();

    }
}
