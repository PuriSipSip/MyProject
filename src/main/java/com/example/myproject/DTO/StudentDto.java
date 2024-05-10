package com.example.myproject.DTO;

import com.example.myproject.Model.Activity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class StudentDto {
    @Id
    // รหัสนักศึกษา
    private String studentId;
    // ชื่อ
    private String firstName;
    // นามสกุล
    private String lastName;
    // กิจกรรมที่เข้าร่วม
    private List<Activity> joinedActivities;
}
