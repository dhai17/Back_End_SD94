package SD94.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ProductLineDTO {

    private Long id;

    private String name;

    private Date createdDate;

    private String createdby;

    private Date lastModifiedDate;

    private String lastModifiedBy;

    private boolean isDeleted;

}
