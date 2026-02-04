package kr.spartaclub.schedulemanagementproject.service;


import kr.spartaclub.schedulemanagementproject.dto.CreateScheduleRequest;
import kr.spartaclub.schedulemanagementproject.dto.CreateScheduleResponse;
import kr.spartaclub.schedulemanagementproject.entity.Schedule;
import kr.spartaclub.schedulemanagementproject.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Schedule savedschedule = scheduleRepository.save(schedule);
        return new CreateScheduleResponse(
                savedschedule.getId(),
                savedschedule.getTitle(),
                savedschedule.getContent(),
                savedschedule.getName(),
                savedschedule.getPassword(),
                savedschedule.getCreatedAt(),
                savedschedule.getModifiedAt()
        );
    }
}
