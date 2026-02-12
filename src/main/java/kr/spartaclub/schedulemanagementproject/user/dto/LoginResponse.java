package kr.spartaclub.schedulemanagementproject.user.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by IntelliJ IDEA.
 * User: jeongjihun
 * Date: 26. 2. 13.
 * Time: 오전 6:16
 **/

@Getter
@AllArgsConstructor
public class LoginResponse {

    private Long userId;
    private String email;
}
