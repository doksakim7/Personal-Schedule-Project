package kr.spartaclub.schedulemanagementproject.user.controller;


import kr.spartaclub.schedulemanagementproject.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * User: jeongjihun
 * Date: 26. 2. 12.
 * Time: 오전 2:42
 **/

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
}
