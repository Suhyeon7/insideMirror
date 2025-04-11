package com.number5.insidemirror.DTO;

import lombok.Data;

@Data
public class ScheduleRequestDto {
    private Integer userId;
    private String date;  // "yyyy-MM-dd"
    private String title;
}
