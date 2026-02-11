package kr.spartaclub.schedulemanagementproject.schedule.dto;


import lombok.Getter;

import java.time.LocalDateTime;

/**
 * Created by IntelliJ IDEA.
 * User: jeongjihun
 * Date: 26. 2. 4.
 * Time: 오후 5:29
 **/

@Getter
public class CreateScheduleResponse {

    private final Long scheduleId;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public CreateScheduleResponse(Long scheduleId, String title, String content, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.scheduleId = scheduleId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
