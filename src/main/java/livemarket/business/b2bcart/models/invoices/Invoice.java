package livemarket.business.b2bcart.models.invoices;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import livemarket.business.b2bcart.models.AuditModel;
import livemarket.business.b2bcart.models.users.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Entity
public class Invoice extends AuditModel {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String comments;

    @JsonIgnoreProperties(value = {"invoices", "hibernateLazyInitializer", "handler"}, allowSetters = true)
    @ManyToOne(fetch = FetchType.LAZY)
    private User customer;


    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "invoice_id")
    private Set<ItemsInvoices> items;

    public void add(List<ItemsInvoices> items) {
        this.items.addAll(items);
    }
    public void add(ItemsInvoices item) {
        this.items.add(item);
    }
}
