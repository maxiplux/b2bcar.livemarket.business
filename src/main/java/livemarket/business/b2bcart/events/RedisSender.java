package livemarket.business.b2bcart.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import livemarket.business.b2bcart.models.files.FileStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

@Service
public class RedisSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisSender.class);

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private ChannelTopic topic;

    public void sendDataToRedisQueue(String input) {
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.convertAndSend(topic.getTopic(), input);
        LOGGER.warn("Data - " + input + " sent through Redis Topic - " + topic.getTopic());
    }

    public void sendDataToRedisQueue(FileStorage input) {


        redisTemplate.convertAndSend(topic.getTopic(), input);
        LOGGER.info("Data - " + input + " sent through Redis Topic - " + topic.getTopic());
    }

}
