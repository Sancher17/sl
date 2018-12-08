package com.cafe.controllers.login;

import com.cafe.security.domain.DetailedProfile;
import com.cafe.security.domain.MinimalProfile;
import com.cafe.security.domain.Profile;
import com.cafe.security.exceptions.ProfileNotFoundException;
import com.cafe.security.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private final ProfileService profileService;

    @SuppressWarnings("unused")
    public ProfileController() {
        this(null);
    }

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

//    @GetMapping
//    public MinimalProfile minimal(@RequestParam(value = "username") String username) {
//        return profileService.minimal(username)
//                .orElseThrow(() -> new ProfileNotFoundException(username));
//    }

//     custom
    @GetMapping
    public MinimalProfile minimal(@RequestParam(value = "username") String username) {
        System.out.println("profile minimal controller - " + LocalDateTime.now());
        return profileService.minimal(username).orElse(new MinimalProfile(username));
  }

    @GetMapping("/details/")
    public DetailedProfile details(@RequestParam(value = "username") String username) {
        System.out.println("profile details controller");
        return profileService.detailed(username)
                .orElseThrow(() -> new ProfileNotFoundException(username));
    }
}
