package com.cafe.controllers.json;


import com.cafe.api.services.IUsersService;
import com.cafe.model.Profile;
import com.cafe.model.User;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/json")
public class JsonController {

    @Autowired
    private IUsersService usersService;

    @JsonView(Profile.PublicView.class)
    @RequestMapping(value= "/jsonTest", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAllPublicProfile() {
        return usersService.getAll();
    }


}
