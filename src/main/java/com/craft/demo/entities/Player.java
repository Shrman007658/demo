package com.craft.demo.entities;

import jakarta.persistence.*;

@Entity
@Table(name="players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="playerid")
    private Long playerId;

    @Column(name="playername")
    private String playerName;

    @Column(name="playerscore")
    private Long playerScore;


}
