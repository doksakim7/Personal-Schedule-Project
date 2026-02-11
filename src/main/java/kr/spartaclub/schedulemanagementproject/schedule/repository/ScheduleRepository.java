package kr.spartaclub.schedulemanagementproject.schedule.repository;

import kr.spartaclub.schedulemanagementproject.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    // 수정시간 기준으로 내림차순 정렬
    public List<Schedule> findAllByOrderByModifiedAtDesc();

    // 해당 이름으로 찾고 수정시간 기준으로 내림차순 정렬
    public List<Schedule> findByNameOrderByModifiedAtDesc(String name);
}
