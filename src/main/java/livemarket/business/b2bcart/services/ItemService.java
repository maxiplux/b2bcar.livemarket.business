package livemarket.business.b2bcart.services;


import livemarket.business.b2bcart.models.items.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ItemService {
    Page<Item> findAll(Pageable pageable);

    Item createProduct(Item product);

    Item updateProduct(Item product);

    Optional<Item> updateProductById(long id, Item product);

    Boolean deleteProductById(long id);

    Optional<Item> findById(long id);
}
