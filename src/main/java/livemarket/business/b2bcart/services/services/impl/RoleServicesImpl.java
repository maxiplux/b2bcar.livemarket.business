package livemarket.business.b2bcart.services.services.impl;

import livemarket.business.b2bcart.models.users.Role;
import livemarket.business.b2bcart.repositories.RoleRepository;
import livemarket.business.b2bcart.services.RoleServices;
import livemarket.business.b2bcart.services.services.generics.CrudServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class RoleServicesImpl extends CrudServicesImpl<Role> implements RoleServices<Role> {

    @Autowired
    private RoleRepository roleRepository;


    @PostConstruct
    public void posContructor() {
        this.setRepository(roleRepository);
    }


    public Optional<Role> UpdateById(long id, Role element) {
        Optional<Role> optionalCurrentCompany = this.repository.findById(id);
        if (optionalCurrentCompany.isPresent()) {
            Role currentProduct = optionalCurrentCompany.get();

            if (element.getName() != null) {
                currentProduct.setName(element.getName());
            }


            return Optional.of((Role) this.repository.save(currentProduct));
        }
        return Optional.empty();

    }


}
