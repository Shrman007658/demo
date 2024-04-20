package com.craft.demo.repository;

import com.craft.demo.entities.Player;
import com.craft.demo.model.PlayerData;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Repository
public interface ScoreRepository extends JpaRepository<Player,String> {

    @Query("SELECT u.playerName, u.playerScore from Player u ")
    List<String> getAllPlayerInfo();
    @Modifying
    @Query("INSERT into Player(playerName,playerScore) values (:playerName,:playerScore)")
    void insertNewPlayer(@Param("playerName") String playerName, @Param("playerScore") Long playerScore);

    @Modifying
    @Query("UPDATE Player p SET p.playerScore = p.playerScore + :playerScore WHERE p.playerId = :playerId")
    int updateScore(@Param("playerId") String playerId, @Param("playerScore") String playerScore);

    @Query("SELECT new com.craft.demo.model.PlayerData(u.playerName, u.playerScore)  from Player u order by playerScore DESC LIMIT :count")
    List<PlayerData> getTopScores(@Param("count") int count);

}
