package livemarket.business.b2bcart.services;


import livemarket.business.b2bcart.models.files.FileItemDto;
import livemarket.business.b2bcart.models.items.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ItemService {

    List<FileItemDto> getFileByItemPk(String id);

    FileItemDto addFileItem(String title, Integer order, Integer itemPk, Boolean isCovert, MultipartFile file) throws IOException;

    Page<Item> findAll(Pageable pageable);

    Item createProduct(Item product);

    Item updateProduct(Item product);

    Optional<Item> updateProductById(long id, Item product);

    Boolean deleteProductById(long id);

    Optional<Item> findById(long id);
}
