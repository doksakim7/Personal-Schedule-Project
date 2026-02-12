package kr.spartaclub.schedulemanagementproject.user.controller;


import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import kr.spartaclub.schedulemanagementproject.user.dto.*;
import kr.spartaclub.schedulemanagementproject.user.entity.User;
import kr.spartaclub.schedulemanagementproject.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * User: jeongjihun
 * Date: 26. 2. 13.
 * Time: 오전 7:42
 **/

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;


    // 회원가입(유저 생성) 컨트롤러
    @PostMapping("/auth/register")
    public ResponseEntity<CreateUserResponse> register(
            @Valid @RequestBody CreateUserRequest request
    ) {
        Long userId = userService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CreateUserResponse(userId));
    }

    // 세션 로그인 컨트롤러
    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request,
            HttpSession session
    ) {
        User user = userService.login(request);

        session.setAttribute("loginUser", new SessionUser(user));

        return ResponseEntity.status(HttpStatus.OK)
                .body(new LoginResponse(user.getUserId(), user.getEmail()));
    }

    // 세션 로그아웃 컨트롤러
    @PostMapping("/auth/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
