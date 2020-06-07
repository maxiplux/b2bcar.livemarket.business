package livemarket.business.b2bcart.util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import livemarket.business.b2bcart.models.files.FileItem;
import livemarket.business.b2bcart.models.files.FileItemDto;
import livemarket.business.b2bcart.models.files.FileStorage;
import livemarket.business.b2bcart.models.items.Category;
import livemarket.business.b2bcart.models.items.Item;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EntityDtoConverter {

    @Autowired
    private ModelMapper modelMapper;

    public FileItemDto convertEntityToDto(FileItem goal) {
        String  base64= Base64.getEncoder().encodeToString(goal.getFile().getData());
        goal.setFile(null);
        FileItemDto finalFile=modelMapper.map(goal, FileItemDto.class);
        finalFile.setFile(base64);
        return finalFile;
    }

    public List<FileItemDto> convertEntityToDto(List<FileItem> goals) {
        return goals.stream()
                .map(goal -> convertEntityToDto(goal))
                .collect(Collectors.toList());
    }

    public FileItem convertDtoToEntity(FileItemDto goal) {
        return modelMapper.map(goal, FileItem.class);
    }

    public String convertEntityToJson(FileStorage goal) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        return  objectMapper.writeValueAsString(goal);
    }

    public   List<Item> csvToItems(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<Item> items = new ArrayList<Item>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Item tutorial =Item.builder()
                                .id(Long.parseLong(csvRecord.get("id")))
                                .category(Category.builder().id(Long.parseLong(csvRecord.get("category_id"))).build() )
                                .picture( csvRecord.get("picture"))
                                .price(Double.parseDouble( csvRecord.get("price")))
                                .quality(Double.parseDouble(csvRecord.get("quality")))
                                .name(csvRecord.get("name"))
                            .build();
                items.add(tutorial);
            }

            return items;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }


}
