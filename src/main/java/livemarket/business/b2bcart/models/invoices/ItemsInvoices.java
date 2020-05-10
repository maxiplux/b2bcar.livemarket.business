package livemarket.business.b2bcart.models.invoices;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import livemarket.business.b2bcart.models.AuditModel;
import livemarket.business.b2bcart.models.items.Item;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = false)
public class ItemsInvoices  extends AuditModel {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnoreProperties(value = {"invoice", "hibernateLazyInitializer", "handler"}, allowSetters = true)
    @ManyToOne(fetch = FetchType.LAZY)
    private Invoice invoice;

    @JsonIgnoreProperties(value = {"item", "hibernateLazyInitializer", "handler"}, allowSetters = true)
    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;


    private Double price;


    private Double tax;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemsInvoices)) return false;
        if (!super.equals(o)) return false;
        ItemsInvoices that = (ItemsInvoices) o;
        return getInvoice().equals(that.getInvoice()) &&
                getItem().equals(that.getItem());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getInvoice(), getItem());
    }
}
