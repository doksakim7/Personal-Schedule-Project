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

    // 유저 생성 기능(POST)
    @Transactional
    public CreateUserResponse saveUser(CreateUserRequest request) {
        User user = new User(
                request.getUserName(),
                request.getEmail(),
                request.getPassword()
        );

        User savedUser = userRepository.save(user);

        return new CreateUserResponse(
                savedUser.getUserId(),
                savedUser.getUserName(),
                savedUser.getEmail(),
                savedUser.getPassword()
        );
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
                    user.getPassword(),
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
                user.getPassword(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }

    // 유저 수정(PUT)
    @Transactional
    public UpdateUserResponse updateUser(Long userId, UpdateUserRequest request) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 유저입니다.")
        );
        user.update(
                request.getEmail(),
                request.getPassword()
        );

        return new UpdateUserResponse(
                user.getUserId(),
                user.getEmail(),
                user.getUserName(),
                user.getPassword(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }

    // 유저 삭제(DELETE)
    @Transactional
    public void deleteUser(Long userId) {
        boolean existence = userRepository.existsById(userId);
        if (!existence) {
            throw new IllegalStateException("존재하지 않는 유저입니다");
        }
        userRepository.deleteById(userId);
    }
}
