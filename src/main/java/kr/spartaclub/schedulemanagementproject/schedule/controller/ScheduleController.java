package kr.spartaclub.schedulemanagementproject.schedule.controller;


import kr.spartaclub.schedulemanagementproject.schedule.dto.*;
import kr.spartaclub.schedulemanagementproject.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    // 일정 생성 컨트롤러
    @PostMapping("/schedules")
    public ResponseEntity<CreateScheduleResponse> createSchedule(
            @RequestBody CreateScheduleRequest request
        ) {
        CreateScheduleResponse result = scheduleService.createSchedule(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    // 일정 전체 조회 컨트롤러
    @GetMapping("/schedules")
    public ResponseEntity<List<GetScheduleResponse>> getAllSchedules(
            @RequestParam(required = false) String name
    ) {
        List<GetScheduleResponse> result = scheduleService.getAllSchedules(name);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 일정 단건 조회(id) 컨트롤러
    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<GetScheduleResponse> getSchedule(
            @PathVariable Long scheduleId
    ) {
        GetScheduleResponse result = scheduleService.getOneSchedule(scheduleId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 일정 수정 컨트롤러
    @PatchMapping("/schedules/{scheduleId}")
    public ResponseEntity<UpdateScheduleResponse> updateSchedule(
            @PathVariable Long scheduleId,
            @RequestBody UpdateScheduleRequest request
    ) {
        UpdateScheduleResponse result = scheduleService.updateSchedule(scheduleId, request);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 일정 삭제 컨트롤러
    @DeleteMapping("/schedules/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(
            @PathVariable Long scheduleId
    ) {
        scheduleService.deleteSchedule(scheduleId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
