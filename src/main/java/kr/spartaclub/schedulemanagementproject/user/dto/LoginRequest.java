package kr.spartaclub.schedulemanagementproject.user.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 * User: jeongjihun
 * Date: 26. 2. 13.
 * Time: 오전 6:15
 **/

@Getter
@NoArgsConstructor
public class LoginRequest {

    @NotBlank(message = "email은 필수입니다.")
    @Email(message = "email 형식이 올바르지 않습니다.")
    @Size(max = 30, message = "email은 30자 이하여야 합니다.")
    private String email;

    @NotBlank(message = "password는 필수입니다.")
    @Size(min = 8, max = 20, message = "password는 8자 이상 20자 이하여야 합니다.")
    private String password;
}
