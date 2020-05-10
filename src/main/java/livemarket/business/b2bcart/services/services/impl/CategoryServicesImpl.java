package livemarket.business.b2bcart.services.services.impl;


import livemarket.business.b2bcart.models.items.Category;
import livemarket.business.b2bcart.repositories.CategoryRepository;
import livemarket.business.b2bcart.services.CategoryServices;
import livemarket.business.b2bcart.services.services.generics.CrudServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class CategoryServicesImpl extends CrudServicesImpl<Category> implements CategoryServices<Category> {

    @Autowired
    private CategoryRepository categoryRepository;


    @PostConstruct
    public  void posContructor() {
        this.setRepository( categoryRepository);
    }


    public Optional<Category> UpdateById(long id, Category element) {
        Optional<Category> optionalCurrentCompany = this.repository.findById(id);
        if (optionalCurrentCompany.isPresent()) {
            Category currentProduct = optionalCurrentCompany.get();

            if (element.getName() != null) {
                currentProduct.setName(element.getName());
            }


            return Optional.of((Category) this.repository.save(currentProduct));
        }
        return Optional.empty();

    }


}
