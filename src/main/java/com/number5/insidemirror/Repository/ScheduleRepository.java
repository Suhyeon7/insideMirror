package com.number5.insidemirror.Repository;

import com.number5.insidemirror.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import java.sql.Date;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    List<Schedule> findByUserId(Integer userId);
    List<Schedule> findByUserIdAndDate(Integer userId, Date date);
}
