package com.craft.demo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
public class PlayerData {
    private String name;
    private Integer score;

    public PlayerData(String name, Integer score) {
        this.score = score;
        this.name=name;
    }
}