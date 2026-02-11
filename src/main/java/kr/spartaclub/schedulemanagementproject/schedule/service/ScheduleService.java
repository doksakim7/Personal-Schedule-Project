package kr.spartaclub.schedulemanagementproject.schedule.service;


import kr.spartaclub.schedulemanagementproject.schedule.dto.*;
import kr.spartaclub.schedulemanagementproject.schedule.entity.Schedule;
import kr.spartaclub.schedulemanagementproject.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jeongjihun
 * Date: 26. 2. 4.
 * Time: 오후 5:08
 **/

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    // 일정 생성 기능(POST)
    @Transactional
    public CreateScheduleResponse createSchedule(CreateScheduleRequest request) {
        Schedule schedule = new Schedule(
                request.getTitle(),
                request.getContent()
        );
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new CreateScheduleResponse(
                savedSchedule.getScheduleId(),
                savedSchedule.getTitle(),
                savedSchedule.getContent(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getModifiedAt()
        );
    }

    // 일정 전체 조회(name, GET)
    @Transactional(readOnly = true)
    public List<GetScheduleResponse> getAllSchedules(String name) {
        List<GetScheduleResponse> dtos = new ArrayList<>();

        List<Schedule> schedules;
        if (name == null || name.isEmpty()) {
            schedules = scheduleRepository.findAllByOrderByModifiedAtDesc();

            // 전체 조회
            for (Schedule schedule : schedules) {
                GetScheduleResponse dto = new GetScheduleResponse(
                        schedule.getScheduleId(),
                        schedule.getTitle(),
                        schedule.getContent(),
                        schedule.getCreatedAt(),
                        schedule.getModifiedAt()
                );
                dtos.add(dto);
            }
        } else {
            schedules = scheduleRepository.findByNameOrderByModifiedAtDesc(name);
            // name 기준 조회
            for (Schedule schedule : schedules) {

                GetScheduleResponse dtoName = new GetScheduleResponse(
                        schedule.getScheduleId(),
                        schedule.getTitle(),
                        schedule.getContent(),
                        schedule.getCreatedAt(),
                        schedule.getModifiedAt()
                );
                dtos.add(dtoName);
            }
        }
        return dtos;
    }

    // 일정 단건 조회(id, GET)
    @Transactional(readOnly = true)
    public GetScheduleResponse getOneSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다.")
        );
        return new GetScheduleResponse(
                schedule.getScheduleId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    // 일정 수정(PATCH)
    @Transactional
    public UpdateScheduleResponse updateSchedule(Long scheduleId, UpdateScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다.")
        );

        schedule.update(
                request.getTitle()
        );

        return new UpdateScheduleResponse(
                schedule.getScheduleId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    // 일정 삭제(DELETE)
    @Transactional
    public void deleteSchedule(Long scheduleId) {
        boolean existence = scheduleRepository.existsById(scheduleId);
        if (!existence) {
            throw new IllegalStateException("존재하지 않는 일정입니다");
        }
        scheduleRepository.deleteById(scheduleId);
    }
}
