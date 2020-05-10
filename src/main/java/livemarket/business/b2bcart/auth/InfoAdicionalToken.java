package livemarket.business.b2bcart.auth;


import livemarket.business.b2bcart.models.users.User;
import livemarket.business.b2bcart.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class InfoAdicionalToken implements TokenEnhancer {

    @Autowired
    private IUserService userService;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        User usuario = userService.findByUsername(authentication.getName());
        Map<String, Object> info = new HashMap<>();
        //info.put("info_adicional", "Hola que tal!: ".concat(authentication.getName()));

        info.put("firtsName", usuario.getFirstName());
        info.put("lastName", usuario.getLastName());
        info.put("email", usuario.getEmail());

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);

        return accessToken;
    }

}
