package com.example.myproject.DTO;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter

public class ActivityDto {
    private String id;
    private String name;
    private String description;
    private String location;
    private LocalDate date;
    private MultipartFile photos;

}
