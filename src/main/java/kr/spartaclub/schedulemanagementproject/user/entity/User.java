package kr.spartaclub.schedulemanagementproject.user.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 * User: jeongjihun
 * Date: 26. 2. 12.
 * Time: 오전 2:43
 **/
@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(length = 20, nullable = false)
    @NotBlank(message = "userName은 필수입니다.")
    @Size(max = 20, message = "userName은 20자 이하여야 합니다.")
    private String userName;

    @Column(length = 30, nullable = false)
    @NotBlank(message = "email은 필수입니다.")
    @Email(message = "email 형식이 올바르지 않습니다.")
    @Size(max = 30, message = "email은 30자 이하여야 합니다.")
    private String email;

    @Column(length = 20, nullable = false)
    @NotBlank(message = "password는 필수입니다.")
    @Size(min = 8, max = 20, message = "password는 8자 이상 20자 이하여야 합니다.")
    private String password;

    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public void update(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
