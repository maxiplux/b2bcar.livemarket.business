package livemarket.business.b2bcart.controllers;


import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import livemarket.business.b2bcart.errors.ValidationErrorBuilder;
import livemarket.business.b2bcart.models.users.User;
import livemarket.business.b2bcart.services.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController


@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserServiceImpl service;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/singin")
    @Secured({
            "ROLE_ADMIN",
            "ROLE_USER"
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization ", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer ")
    })
    public ResponseEntity<?> createUser(@Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(errors));
        }

        if (this.service.existsByUsername(user.getUsername())) {

            return ResponseEntity.badRequest().body("Error User Invalid");
        }

        if (this.service.existsByEmail(user.getEmail())) {

            return ResponseEntity.badRequest().body("Error email Invalid");
        }




        return new ResponseEntity<User>(service.save(user), HttpStatus.OK);

    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization ", required = true, dataType = "string", paramType = "header", defaultValue="Bearer ")
    })
    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    public ResponseEntity<?> setNewPassword(@RequestBody User newPasswordHolder, String newPassword) {

        try {
            User user = service.findByUsername(newPasswordHolder.getUsername());


            if (user != null) {
                //todo move to services
                user.setResetToken(null);
                user.setPassword(bCryptPasswordEncoder.encode(newPassword));
                service.save(user);
                return ResponseEntity.status(HttpStatus.OK).body(user);

            } else {
                return new ResponseEntity<String>( "User not found",HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<String>( "User not found",HttpStatus.BAD_REQUEST);
        }


    }
}
