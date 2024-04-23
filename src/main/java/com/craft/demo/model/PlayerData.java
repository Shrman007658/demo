package com.craft.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
public class PlayerData {
    private Long playerId;
    private String playerName;
    private Long playerScore;

    public PlayerData(Long playerId, String playerName, Long score) {
        this.playerScore = score;
        this.playerName=playerName;
        this.playerId =playerId;
    }
}