package livemarket.business.b2bcart.models.files;

import livemarket.business.b2bcart.models.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "FileItem")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileItem extends AuditModel {
    @Id
    private String id;

    private String title;
    private Boolean isCovert;

    private Integer order;
    private Integer itemPk;

    private Binary file;

}
