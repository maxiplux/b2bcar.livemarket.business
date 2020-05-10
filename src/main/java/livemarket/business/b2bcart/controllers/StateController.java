package livemarket.business.b2bcart.controllers;


import livemarket.business.b2bcart.controllers.generics.CrudController;
import livemarket.business.b2bcart.models.rules.State;
import livemarket.business.b2bcart.services.StateServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/v1/states")
public class StateController extends CrudController<State> {
    @Autowired
    StateServices stateServices;

    @PostConstruct
    private void postConstruct() {
        this.setService( stateServices);
    }

}
