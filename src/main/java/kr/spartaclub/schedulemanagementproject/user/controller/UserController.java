package kr.spartaclub.schedulemanagementproject.user.controller;


import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import kr.spartaclub.schedulemanagementproject.user.dto.*;
import kr.spartaclub.schedulemanagementproject.user.entity.User;
import kr.spartaclub.schedulemanagementproject.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jeongjihun
 * Date: 26. 2. 12.
 * Time: 오전 2:42
 **/

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

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


    // 유저 전체 조회 컨트롤러
    @GetMapping("/users")
    public ResponseEntity<List<GetUserResponse>> getAllUsers() {

        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUser());
    }

    // 유저 단건 조회(id) 컨트롤러
    @GetMapping("/users/{userId}")
    public ResponseEntity<GetUserResponse> getOneUser(
            @PathVariable Long userId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getOneUser(userId));
    }

    // 유저 수정 컨트롤러
    @PutMapping("/users/{userId}")
    public ResponseEntity<UpdateUserResponse> updateUser(
            @PathVariable Long userId,
            @Valid @RequestBody UpdateUserRequest request
            ) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(userId, request));
    }

    // 유저 삭제 컨트롤러
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable Long userId
    ) {
        userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
