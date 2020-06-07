package livemarket.business.b2bcart.controllers;


import livemarket.business.b2bcart.models.files.FileItemDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class HomeController {

    @GetMapping("/")
    public String helloWorld(){
        return "Hello World";
    }


    @GetMapping("/protected/")
    public String helloWorldProtected(Principal principal) {
        return "Hello VIP " + principal.getName();
    }


}
