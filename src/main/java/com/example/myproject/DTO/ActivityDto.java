package com.example.myproject.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ActivityDto {
    private long id;
    private String name;
    private String description;
    private String location;
    private LocalDate date;
    private MultipartFile photos;
}
