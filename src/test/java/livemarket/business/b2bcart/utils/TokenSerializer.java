package livemarket.business.b2bcart.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TokenSerializer {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private Integer expires_in;
    private String scope;
    private String info_adicional;
    private String apellido;
    private String nombre;
    private String email;
    private String jti;


}
