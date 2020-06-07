package livemarket.business.b2bcart.repositories;


import livemarket.business.b2bcart.models.files.FileStorage;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface FileStorageRepository extends MongoRepository<FileStorage, String> {

}

