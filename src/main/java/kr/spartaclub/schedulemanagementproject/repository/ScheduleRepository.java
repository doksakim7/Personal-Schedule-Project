package kr.spartaclub.schedulemanagementproject.repository;

import kr.spartaclub.schedulemanagementproject.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

}
