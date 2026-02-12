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

    public CreateUserResponse(Long userId) {
        this.userId = userId;
    }
}
