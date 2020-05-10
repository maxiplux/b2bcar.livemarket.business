package livemarket.business.b2bcart.services.services.impl;


import livemarket.business.b2bcart.models.invoices.Invoice;
import livemarket.business.b2bcart.repositories.InvoiceRepository;
import livemarket.business.b2bcart.services.InvoiceServices;
import livemarket.business.b2bcart.services.services.generics.CrudServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class InvoiceServicesImpl extends CrudServicesImpl<Invoice> implements InvoiceServices<Invoice> {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @PostConstruct
    public  void posContructor() {
        this.setRepository( invoiceRepository);
    }


    public Optional<Invoice> UpdateById(long id, Invoice element) {
        Optional<Invoice> optionalCurrentCompany = this.repository.findById(id);
        if (optionalCurrentCompany.isPresent()) {
            Invoice currentProduct = optionalCurrentCompany.get();

            if (element.getCustomer() != null) {
                currentProduct.setCustomer(element.getCustomer());
            }

            if (element.getComments() != null) {
                currentProduct.setComments(element.getComments());
            }

            if (element.getDescription() != null ) {
                currentProduct.setDescription(element.getDescription());
            }

            if (element.getItems() != null ) {
                currentProduct.setItems(element.getItems());
            }

            return Optional.of((Invoice) this.repository.save(currentProduct));
        }
        return Optional.empty();

    }


}
