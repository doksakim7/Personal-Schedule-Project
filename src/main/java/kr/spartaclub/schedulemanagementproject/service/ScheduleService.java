package kr.spartaclub.schedulemanagementproject.service;


import kr.spartaclub.schedulemanagementproject.dto.*;
import kr.spartaclub.schedulemanagementproject.entity.Schedule;
import kr.spartaclub.schedulemanagementproject.repository.ScheduleRepository;
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
                request.getContent(),
                request.getName(),
                request.getPassword()
        );
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new CreateScheduleResponse(
                savedSchedule.getId(),
                savedSchedule.getTitle(),
                savedSchedule.getContent(),
                savedSchedule.getName(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getModifiedAt()
        );
    }

    // 일정 전체 조회(name, GET)
    @Transactional(readOnly = true)
    public List<GetScheduleResponse> getAllSchedules(String name) {
        List<GetScheduleResponse> dtos = new ArrayList<>();

        if (name == null) {
            List<Schedule> schedules = scheduleRepository.findAllOrderByModifiedAtDesc();

            // 전체 조회
            for (Schedule schedule : schedules) {
                GetScheduleResponse dto = new GetScheduleResponse(
                        schedule.getId(),
                        schedule.getTitle(),
                        schedule.getContent(),
                        schedule.getName(),
                        schedule.getCreatedAt(),
                        schedule.getModifiedAt()
                );
                dtos.add(dto);
            }
            return dtos;
        } else {
            List<Schedule> schedules = scheduleRepository.findByNameOrderByModifiedAtDesc(name);
            // name 기준 조회
            for (Schedule schedule : schedules) {

                GetScheduleResponse dtoName = new GetScheduleResponse(
                        schedule.getId(),
                        schedule.getTitle(),
                        schedule.getContent(),
                        schedule.getName(),
                        schedule.getCreatedAt(),
                        schedule.getModifiedAt()
                );
                dtos.add(dtoName);

            }
            return dtos;
        }
    }

    // 일정 단건 조회(id, GET)
    @Transactional(readOnly = true)
    public GetScheduleResponse getOneSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다.")
        );
        return new GetScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getName(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    // 일정 수정(PATCH)
    @Transactional
    public UpdateScheduleResponse updateSchedule(Long id, UpdateScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다.")
        );

        // 비밀번호 검증
        if (schedule.getPassword().equals(request.getPassword())) {
            schedule.update(
                    request.getTitle(),
                    request.getName()
            );
        } else {
            throw new IllegalStateException("비밀번호가 틀렸습니다.");
        }
        return new UpdateScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getName(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    // 일정 삭제(DELETE)
    @Transactional
    public void deleteSchedule(Long id, String password) {
        boolean existence = scheduleRepository.existsById(id);
        if (!existence) {
            throw new IllegalStateException("존재하지 않는 일정입니다");
        }
        if(password.equals(scheduleRepository.getReferenceById(id).getPassword())) {
            scheduleRepository.deleteById(id);
        } else {
            throw new IllegalStateException("비밀번호가 틀렸습니다.");
        }
    }
}
