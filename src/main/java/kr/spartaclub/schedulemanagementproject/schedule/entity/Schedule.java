package kr.spartaclub.schedulemanagementproject.schedule.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 * User: jeongjihun
 * Date: 26. 2. 4.
 * Time: 오후 4:51
 **/

@Getter
@Entity
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;

    @Column(length = 30, nullable = false)
    private String title;
    @Column(length = 100, nullable = false)
    private String content;

    public Schedule(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void update(String title) {
        this.title = title;
    }

}
