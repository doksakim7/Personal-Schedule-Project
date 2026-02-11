package kr.spartaclub.schedulemanagementproject.user.dto;


import lombok.Getter;

/**
 * Created by IntelliJ IDEA.
 * User: jeongjihun
 * Date: 26. 2. 12.
 * Time: 오전 3:11
 **/

@Getter
public class CreateUserResponse {

    private final Long userId;
    private final String userName;
    private final String email;
    private final String password;

    public CreateUserResponse(Long userId, String userName, String email, String password) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }
}
