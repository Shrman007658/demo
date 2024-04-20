package com.craft.demo.repository;

import com.craft.demo.entities.Player;
import com.craft.demo.entities.TimeStampEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TimeCheckpointRepository extends JpaRepository<TimeStampEntity,String>  {

    @Query("SELECT t.timestamp from TimeStampEntity t ")
    Long getLatestCheckpoint();

    @Modifying
    @Query("UPDATE TimeStampEntity t SET t.timestamp = COALESCE(:timeStamp, t.timestamp)")
    int updateTimestamp(@Param("timeStamp") long timeStamp);

}
