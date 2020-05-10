package livemarket.business.b2bcart.models.items;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import livemarket.business.b2bcart.models.AuditModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Entity
public class Item extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;


    @JsonIgnoreProperties(value = {"categories", "hibernateLazyInitializer", "handler"}, allowSetters = true)
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;




    @Min(value = 0)
    private Double quality;

    @Min(value = 1)
    private Double price;


    private String picture;

    public Double getPriceWithTaxes() {
        return this.price;
    }
}
