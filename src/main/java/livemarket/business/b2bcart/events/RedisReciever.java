package livemarket.business.b2bcart.events;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.gridfs.model.GridFSFile;
import livemarket.business.b2bcart.models.files.FileStorage;
import livemarket.business.b2bcart.repositories.FileStorageRepository;
import livemarket.business.b2bcart.repositories.ItemRepositoryMongo;
import livemarket.business.b2bcart.services.FileStorageServices;
import livemarket.business.b2bcart.util.EntityDtoConverter;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

@Service
public class RedisReciever implements MessageListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisReciever.class);

    @Autowired
    private FileStorageServices fileStorageServices;





    @SneakyThrows
    @Override
    public void onMessage(Message message, byte[] pattern) {
        ObjectMapper objectMapper = new ObjectMapper();
        // improve loop and coupling , the goal about it is learn how to create apps usins redis as a messages broker.
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, false);
        FileStorage fileStorage = objectMapper.readValue(message.toString(), FileStorage.class);
        fileStorageServices.handleCSV(fileStorage.getId());
        LOGGER.info("Example CSV propcesor Received data - " + message.toString() + " from Topic - " + new String(pattern));
    }
}
