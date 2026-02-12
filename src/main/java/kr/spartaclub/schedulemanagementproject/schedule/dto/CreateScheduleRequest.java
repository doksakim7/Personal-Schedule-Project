package kr.spartaclub.schedulemanagementproject.schedule.dto;


import jakarta.persistence.Column;
import kr.spartaclub.schedulemanagementproject.user.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * Created by IntelliJ IDEA.
 * User: jeongjihun
 * Date: 26. 2. 4.
 * Time: 오후 5:26
 **/

@Getter
public class CreateScheduleRequest {

    private User user;
    private String title;
    private String content;

}
