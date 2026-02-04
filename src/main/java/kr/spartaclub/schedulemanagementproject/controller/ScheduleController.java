package kr.spartaclub.schedulemanagementproject.controller;


import kr.spartaclub.schedulemanagementproject.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * User: jeongjihun
 * Date: 26. 2. 4.
 * Time: 오후 5:08
 **/

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;
}
