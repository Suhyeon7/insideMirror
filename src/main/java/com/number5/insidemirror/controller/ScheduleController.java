package com.number5.insidemirror.controller;

import com.number5.insidemirror.DTO.ScheduleRequestDto;
import com.number5.insidemirror.Repository.ScheduleRepository;
import com.number5.insidemirror.entity.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Map;

@Controller
@RequestMapping("/api/schedules")
public class ScheduleController {

    @Autowired
    private ScheduleRepository scheduleRepository;

    // 일정 등록
    @PostMapping
    public ResponseEntity<?> addSchedule(@RequestBody ScheduleRequestDto dto) {
        Schedule schedule = Schedule.builder()
                .userId(dto.getUserId())
                .date(Date.valueOf(dto.getDate()))
                .title(dto.getTitle())
                .build();

        Schedule savedSchedule = scheduleRepository.save(schedule);
        return ResponseEntity.ok().body(savedSchedule); // 저장된 일정 전체 반환 (JSON)
    }

    // 일정 삭제
    @DeleteMapping("/{scheduleId}")
    @ResponseBody
    public ResponseEntity<?> deleteSchedule(@PathVariable Integer scheduleId) {
        if (!scheduleRepository.existsById(scheduleId)) {
            return ResponseEntity.status(404).body(Map.of("message", "일정을 찾을 수 없습니다."));
        }

        scheduleRepository.deleteById(scheduleId);
        return ResponseEntity.ok().body(Map.of("message", "일정이 삭제되었습니다."));
    }
}
