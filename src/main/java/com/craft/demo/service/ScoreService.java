package com.craft.demo.service;

import com.craft.demo.model.PlayerData;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScoreService {

    List<PlayerData> getTopScores(int count);
}
