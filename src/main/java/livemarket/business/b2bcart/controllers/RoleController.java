package livemarket.business.b2bcart.controllers;


import livemarket.business.b2bcart.controllers.generics.CrudController;
import livemarket.business.b2bcart.models.users.Role;
import livemarket.business.b2bcart.services.RoleServices;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/v1/roles")
@Data
public class RoleController extends CrudController<Role> {

    @Autowired
    private RoleServices roleServices;

    @PostConstruct
    public void posContructor() {
        this.service = roleServices;
    }

}
