package kr.spartaclub.schedulemanagementproject.user.dto;


import kr.spartaclub.schedulemanagementproject.user.entity.User;
import lombok.Getter;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: jeongjihun
 * Date: 26. 2. 13.
 * Time: 오전 6:18
 **/

@Getter
public class SessionUser implements Serializable {

    private final Long userId;
    private final String userName;
    private final String email;

    public SessionUser(User user) {
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.email = user.getEmail();
    }
}
