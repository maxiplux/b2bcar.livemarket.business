package livemarket.business.b2bcart.repositories;

import livemarket.business.b2bcart.models.files.FileItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.io.File;
import java.util.List;
import java.util.Optional;


public interface FileItemRepository extends MongoRepository<FileItem, String> {

    @Query("{ 'itemPk' : ?0 }")
    public Optional<List<FileItem>> findByItemPkEquals(Integer itemPk);

}

