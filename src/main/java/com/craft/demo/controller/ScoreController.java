package com.craft.demo.controller;

import com.craft.demo.model.PlayerData;
import com.craft.demo.model.ResponseModel;
import com.craft.demo.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ScoreController {

    @Autowired
    ScoreService scoreService;
    @GetMapping("/getTopScores")
    public ResponseModel getTopNScores(@RequestParam(name="count" , defaultValue = "5") int count)

    {
        return  scoreService.getTopScores(count);
    }
}
