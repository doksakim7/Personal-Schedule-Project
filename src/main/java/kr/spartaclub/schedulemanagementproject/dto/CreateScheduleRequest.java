package kr.spartaclub.schedulemanagementproject.dto;


import jakarta.persistence.Column;
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

    private String title;
    private String content;
    private String name;
    private String password;

}
