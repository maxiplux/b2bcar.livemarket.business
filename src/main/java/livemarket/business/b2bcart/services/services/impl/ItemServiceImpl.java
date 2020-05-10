package livemarket.business.b2bcart.services.services.impl;



import livemarket.business.b2bcart.models.items.Item;
import livemarket.business.b2bcart.repositories.ItemRepository;
import livemarket.business.b2bcart.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepository productRepository;

    @Override
    public Page<Item> findAll(Pageable pageable) {

        return productRepository.findAll(pageable);
    }

    @Override
    public Item createProduct(Item product) {
        return this.productRepository.save(product);
    }

    @Override
    public Item updateProduct(Item product) {
        return this.productRepository.save(product);
    }


    @Override
    public Optional<Item> updateProductById(long id, Item item) {

        int resultDummyOperation = this.suma(id, id * 2);

        if (resultDummyOperation == 9) {
            System.out.println("Operation only to understand PowerMockito");
        } else {
            System.out.println("!!!!!  Code Coverage : Operation only to understand PowerMockito");

        }

        Optional<Item> optionalCurrentProduct = this.productRepository.findById(id);
        if (optionalCurrentProduct.isPresent()) {
            Item currentItem = optionalCurrentProduct.get();

            if (item.getName() != null) {
                currentItem.setName(item.getName());
            }

            if (item.getPrice() != null) {
                currentItem.setPrice(item.getPrice());
            }

            if (item.getCategory() != null) {
                currentItem.setCategory(item.getCategory());
            }



            if (item.getPicture() != null) {
                currentItem.setPicture(item.getPicture());
            }

            if (item.getQuality() != null) {
                currentItem.setQuality(item.getQuality());
            }


            return Optional.of(this.productRepository.save(currentItem));
        }
        return Optional.empty();


    }

    private int suma(Long id, Long l) {
        return (int) (id + l);
    }


    @Override
    public Boolean deleteProductById(long id) {
        return this.productRepository.deleteById(id);
    }

    @Override
    public Optional<Item> findById(long id) {
        return this.productRepository.findById(id);
    }
}
