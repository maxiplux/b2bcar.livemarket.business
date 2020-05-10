package livemarket.business.b2bcart.controllers;

import livemarket.business.b2bcart.controllers.generics.CrudController;
import livemarket.business.b2bcart.models.items.Category;
import livemarket.business.b2bcart.services.CategoryServices;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/v1/categories")
@Data
public class CategoryController extends CrudController<Category> {

    @Autowired
    CategoryServices categoryServices;

    @PostConstruct
    public  void posContructor() {
        this.service = categoryServices;
    }

}
