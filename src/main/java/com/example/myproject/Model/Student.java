package com.example.myproject.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
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
