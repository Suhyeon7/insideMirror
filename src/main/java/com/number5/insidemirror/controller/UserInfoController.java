package com.number5.insidemirror.controller;

import com.number5.insidemirror.Repository.ScheduleRepository;
import com.number5.insidemirror.Repository.UserRepository;
import com.number5.insidemirror.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserInfoController {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    @GetMapping("/schedules")
    @ResponseBody
    public ResponseEntity<?> getSchedulesByName(
            @RequestParam String name,
            @RequestParam(required = false) String date // yyyy-MM-dd 형식
    ) {
        Optional<User> userOpt = userRepository.findByName(name);
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(404).body(Map.of("message", "사용자를 찾을 수 없습니다."));
        }

        Integer userId = userOpt.get().getUserId();

        if (date != null) {
            LocalDate localDate = LocalDate.parse(date);
            java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
            return ResponseEntity.ok(scheduleRepository.findByUserIdAndDate(userId, sqlDate));
        } else {
            return ResponseEntity.ok(scheduleRepository.findByUserId(userId));
        }
    }
}
