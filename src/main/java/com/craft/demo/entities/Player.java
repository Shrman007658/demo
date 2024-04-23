package com.craft.demo.entities;

import jakarta.persistence.*;

@Entity
@Table(name="players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="playerid")
    private Long playerId;

    @Column(name="playername") // Specify custom column name
    private String playerName;

    @Column(name="playerscore") // Specify custom column name
    private Long playerScore;

    // Constructors, getters, and setters
}
