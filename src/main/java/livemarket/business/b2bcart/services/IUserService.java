package livemarket.business.b2bcart.services;


import livemarket.business.b2bcart.models.users.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IUserService {

    User findByUsername(String username);

    boolean existsByEmail(String username);

    Boolean existsByUsername(String username);

    User save(User user);

    UserDetails loadUserByUsername(String var1) throws UsernameNotFoundException;
}
