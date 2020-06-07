package livemarket.business.b2bcart.services.services.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import livemarket.business.b2bcart.events.RedisSender;
import livemarket.business.b2bcart.models.items.Item;
import livemarket.business.b2bcart.repositories.FileStorageRepository;
import livemarket.business.b2bcart.repositories.ItemRepositoryMongo;
import livemarket.business.b2bcart.util.EntityDtoConverter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import com.mongodb.client.gridfs.model.GridFSFile;
import livemarket.business.b2bcart.models.files.FileStorage;
import livemarket.business.b2bcart.services.FileStorageServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileStorageServicesImpl implements FileStorageServices {

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Autowired
    private FileStorageRepository fileStorageRepository;

    @Autowired
    private RedisSender redisSender;


    @Autowired
    private EntityDtoConverter entityDtoConverter;


    @Autowired
    private ItemRepositoryMongo itemRepositoryMongo;

    @Override
    public FileStorage getFileById(String id) throws IOException {
        GridFSFile file = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
        FileStorage fileStorage = new FileStorage();
        fileStorage.setName(file.getMetadata().get("name").toString());
        fileStorage.setStream(gridFsTemplate.getResource(file).getInputStream());
        return fileStorage;

    }


    @Override
    public Boolean handleCSV(String id) throws IOException {
        GridFSFile file = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
        FileStorage fileStorage = new FileStorage();
        fileStorage.setName(file.getMetadata().get("name").toString());
        fileStorage.setStream(gridFsTemplate.getResource(file).getInputStream());

        entityDtoConverter.csvToItems(fileStorage.getStream()).forEach(item -> {
            itemRepositoryMongo.save(item);
        });
        return  true;
    }

    @Override
    public FileStorage addFileToStorage(String name, MultipartFile file) throws IOException {



        DBObject metaData = new BasicDBObject();

        metaData.put("type", "binary");
        metaData.put("name", name);
        ObjectId id = gridFsTemplate.store(file.getInputStream(), file.getName(), file.getContentType(), metaData);
        FileStorage fileStorage = new FileStorage();
        fileStorage.setId(id.toString());
        fileStorage.setName(name);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, false);


        this.fileStorageRepository.save(fileStorage);


        this.redisSender.sendDataToRedisQueue(entityDtoConverter.convertEntityToJson(fileStorage));


        return fileStorage;

    }
}
