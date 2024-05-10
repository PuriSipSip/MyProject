package com.example.myproject.Controller;

import com.example.myproject.DTO.ActivityDto;
import com.example.myproject.Model.Activity;
import com.example.myproject.Repository.ActivityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;


@Controller
public class MainController {

    @Autowired
    ActivityRepo activityRepo;

    @PostMapping("/addActivity")
    public void addActivity(@RequestBody Activity activity) {
        activityRepo.save(activity);
    }

    // เมธอดสำหรับแสดงหน้าเพิ่มกิจกรรมใหม่
    @GetMapping("/addActivity")
    public String showAddActivityForm(Model model) {
        model.addAttribute("activity", new ActivityDto());
        return "addactivity"; // ชื่อไฟล์ HTML ของหน้าที่ใช้สร้างฟอร์มเพิ่มกิจกรรม
    }

    @GetMapping("/profile")
    public String getActivitiesStd(Model model) {
        List<Activity> activities = activityRepo.findAll();
        model.addAttribute("activities", activities);
        return "profile";
    }

    @GetMapping("/activities")
    public String getAllActivities(Model model) {
        List<Activity> activities = activityRepo.findAll();
        model.addAttribute("activities", activities);
        return "activities";
    }

    @GetMapping("/adminpage")
    public String getAllAdminActivities(Model model) {
        List<Activity> activities = activityRepo.findAll();
        model.addAttribute("activities", activities);
        return "adminpage";
    }


    // เมธอดสำหรับลบกิจกรรมโดยใช้ @DeleteMapping
    @DeleteMapping("/deleteActivity/{id}")
    public String deleteActivity(@PathVariable String id) {
        activityRepo.deleteById(id);
        // ส่งกลับไปยังหน้าที่ต้องการหลังจากการลบ
        return "redirect:/adminpage";
    }

    @PostMapping("/saveActivity")
    public String saveActivity(@ModelAttribute("activityDto") @Validated ActivityDto activityDto, BindingResult result) {
        if (result.hasErrors()) {
            // กรณีเกิดข้อผิดพลาดในการตรวจสอบความถูกต้องของข้อมูล ให้กลับไปยังหน้าเพิ่มกิจกรรมอีกครั้ง
            return "adminpage";
        }

        MultipartFile photos = activityDto.getPhotos();
        // ทำการบันทึกรูปภาพลงในระบบ
        Date createdAt = new Date();
        String imagefilename = createdAt.getTime() + "_" + photos.getOriginalFilename();
        try {
            // ทำการบันทึกรูปภาพ
            String uploadDir = "public/images/";
            Path uploadDirPath = Paths.get(uploadDir);
            if (!Files.exists(uploadDirPath)) {
                Files.createDirectory(uploadDirPath);
            }
            try (InputStream inputStream = photos.getInputStream()) {
                Files.copy(inputStream, Paths.get(uploadDir + imagefilename), StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // ทำการบันทึกกิจกรรมลงในฐานข้อมูล
        Activity activity = new Activity();
        activity.setName(activityDto.getName());
        activity.setDescription(activityDto.getDescription());
        activity.setLocation(activityDto.getLocation());
        activity.setDate(activityDto.getDate());
        activity.setPhotos(imagefilename); // ใช้ชื่อไฟล์ที่ได้จากฟอร์ม

        activityRepo.save(activity);

        // หลังจากบันทึกเสร็จสิ้น ให้เปลี่ยนเส้นทางไปยังหน้าเพิ่มกิจกรรม
        return "redirect:/adminpage";
    }


}
