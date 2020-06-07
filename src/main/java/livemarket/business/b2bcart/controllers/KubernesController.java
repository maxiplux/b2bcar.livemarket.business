package livemarket.business.b2bcart.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class KubernesController {

    @GetMapping("/")
    public String helloWorld(){
        return "Hello World";
    }


    @GetMapping("/protected/")
    public String helloWorldProtected(Principal principal) {
        return "Hello VIP " + principal.getName();
    }
}
