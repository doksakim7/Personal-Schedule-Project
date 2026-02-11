package kr.spartaclub.schedulemanagementproject.user.service;


import kr.spartaclub.schedulemanagementproject.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * User: jeongjihun
 * Date: 26. 2. 12.
 * Time: 오전 2:43
 **/

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


}
