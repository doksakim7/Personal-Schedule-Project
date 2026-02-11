package kr.spartaclub.schedulemanagementproject.user.dto;


import lombok.Getter;

/**
 * Created by IntelliJ IDEA.
 * User: jeongjihun
 * Date: 26. 2. 12.
 * Time: 오전 3:40
 **/

@Getter
public class UpdateUserRequest {

    private String email;
    private String password;
}
