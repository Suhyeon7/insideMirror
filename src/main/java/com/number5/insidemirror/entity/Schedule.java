package com.number5.insidemirror.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "schedules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer scheduleId;

    @Column(nullable = false)
    private Integer userId;

    @Column(nullable = false)
    private java.sql.Date date;

    @Column(nullable = false)
    private String title;
}
