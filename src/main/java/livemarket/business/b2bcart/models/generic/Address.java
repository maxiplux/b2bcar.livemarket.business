package livemarket.business.b2bcart.models.generic;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@NoArgsConstructor
public class Address implements Serializable {
    private static final long serialversionUID = 79234897L;

    private String name;
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;

}
