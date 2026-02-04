package kr.spartaclub.schedulemanagementproject.service;


import kr.spartaclub.schedulemanagementproject.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
