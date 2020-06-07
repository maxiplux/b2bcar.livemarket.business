package livemarket.business.b2bcart.repositories;


import livemarket.business.b2bcart.models.files.FileStorage;
import livemarket.business.b2bcart.models.items.Item;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ItemRepositoryMongo extends MongoRepository<Item, Integer> {

}

