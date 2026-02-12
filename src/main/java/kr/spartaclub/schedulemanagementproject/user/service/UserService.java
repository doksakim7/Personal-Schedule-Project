package kr.spartaclub.schedulemanagementproject.user.service;


import kr.spartaclub.schedulemanagementproject.user.dto.*;
import kr.spartaclub.schedulemanagementproject.user.entity.User;
import kr.spartaclub.schedulemanagementproject.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    // 회원가입 기능(POST)
    @Transactional
    public Long register(RegisterRequest request) {

        // 이메일 중복 체크
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalStateException("이미 존재하는 이메일입니다.");
        }

        User user = new User(
                request.getUserName(),
                request.getEmail(),
                request.getPassword()
        );

        User saved = userRepository.save(user);
        return saved.getUserId();
    }

    // 세션 로그인/로그아웃 기능(POST)
    @Transactional(readOnly = true)
    public User login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalStateException("이메일 또는 비밀번호가 올바르지 않습니다."));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new IllegalStateException("이메일 또는 비밀번호가 올바르지 않습니다.");
        }

        return user;
    }

    // 유저 전체 조회(GET)
    @Transactional(readOnly = true)
    public List<GetUserResponse> getAllUser() {
        List<GetUserResponse> dtos = new ArrayList<>();
        List<User> users = userRepository.findAll();
        for (User user : users) {
            GetUserResponse dto = new GetUserResponse(
                    user.getUserId(),
                    user.getUserName(),
                    user.getEmail(),
                    user.getCreatedAt(),
                    user.getModifiedAt()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    // 유저 단건 조회(id, GET)
    @Transactional(readOnly = true)
    public GetUserResponse getOneUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 유저입니다.")
        );
        return new GetUserResponse(
                user.getUserId(),
                user.getUserName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }

    // 유저 수정(PUT)
    @Transactional
    public UpdateUserResponse updateUser(Long userId, UpdateUserRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 유저입니다."));

        // 비밀번호 검증 (본인 확인)
        if (!user.getPassword().equals(request.getPassword())) {
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }

        // 이메일 변경 시 중복 체크
        if (!user.getEmail().equals(request.getEmail())
                && userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalStateException("이미 존재하는 이메일입니다.");
        }

        // 이메일만 수정
        user.update(request.getEmail());

        return new UpdateUserResponse(
                user.getUserId(),
                user.getEmail(),
                user.getUserName(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }

    // 유저 삭제(DELETE)
    @Transactional
    public void deleteUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 유저입니다."));

        userRepository.delete(user);
    }
}
