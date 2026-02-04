package kr.spartaclub.schedulemanagementproject.controller;


import kr.spartaclub.schedulemanagementproject.dto.*;
import kr.spartaclub.schedulemanagementproject.service.ScheduleService;
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
    @PostMapping("/api/schedules")
    public ResponseEntity<CreateScheduleResponse> createSchedule(
            @RequestBody CreateScheduleRequest request
        ) {
        CreateScheduleResponse result = scheduleService.createSchedule(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    // 일정 전체 조회 컨트롤러
    @GetMapping("/api/schedules")
    public ResponseEntity<List<GetScheduleResponse>> getAll() {
        List<GetScheduleResponse> result = scheduleService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 일정 단건 조회(id) 컨트롤러
    @GetMapping("/api/schedules/{id}")
    public ResponseEntity<GetScheduleResponse> getSchedule(
            @PathVariable Long id
    ) {
        GetScheduleResponse result = scheduleService.getOne(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 일정 수정 컨트롤러
    @PatchMapping("/api/schedules/{id}")
    public ResponseEntity<UpdateScheduleResponse> updateSchedule(
            @PathVariable Long id,
            @RequestBody UpdateScheduleRequest request
    ) {
        UpdateScheduleResponse result = scheduleService.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
