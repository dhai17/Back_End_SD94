package SD94.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ProductColorDTO {

    private Long id;

    private String code;

    private String color;

    private Date createdDate;

    private String createdby;

    private Date lastModifiedDate;

    private String lastModifiedBy;

}
