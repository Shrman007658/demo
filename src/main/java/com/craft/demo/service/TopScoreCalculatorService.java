package com.craft.demo.service;

import com.craft.demo.entities.Player;
import com.craft.demo.model.PlayerData;
import com.craft.demo.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import com.craft.demo.repository.ScoreRepository;
@Service
public class TopScoreCalculatorService implements ScoreService{

    @Autowired
    ScoreRepository scoreRepository;
    @Override
    public List<PlayerData> getTopScores(int count) {
        List<PlayerData> topNPlayers = scoreRepository.getTopScores(count);
        for(PlayerData pl : topNPlayers)
        {
            System.out.println(pl.getName());
        }
        return topNPlayers;
    }
}
