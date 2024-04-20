package com.craft.demo.entities;
import jakarta.persistence.*;

@Entity
@Table(name="timestamp_table")
public class TimeStampEntity {
    @Id
    @Column(name = "timestamp")
    private Long timestamp;
}

