package livemarket.business.b2bcart.models.files;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import livemarket.business.b2bcart.models.AuditModel;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.InputStream;
import java.io.Serializable;

@Document
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({ "stream" })
public class FileStorage implements Serializable {
    @Id
    private String id;

    private String name;

    private InputStream stream;


}
