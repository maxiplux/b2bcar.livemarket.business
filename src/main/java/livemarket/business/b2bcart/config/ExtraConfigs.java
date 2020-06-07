package livemarket.business.b2bcart.config;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExtraConfigs {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
