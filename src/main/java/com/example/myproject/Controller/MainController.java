package com.example.myproject.Controller;

import com.example.myproject.Model.Activity;
import com.example.myproject.Repository.ActivityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Controller
public class MainController {

    @Autowired
    ActivityRepo activityRepo;

    @PostMapping("/addActivity")
    public void addActivity(@RequestBody Activity activity){
        activityRepo.save(activity);
    }

    @GetMapping("/activities")
    public String getAllActivities(Model model){
        List<Activity> activities = activityRepo.findAll();
        model.addAttribute("activities", activities);
        return "activities";
    }
}
