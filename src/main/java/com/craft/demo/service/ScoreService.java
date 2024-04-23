package com.craft.demo.service;

import com.craft.demo.model.PlayerData;
import com.craft.demo.model.ResponseModel;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScoreService {

    ResponseModel getTopScores(int count);
}
