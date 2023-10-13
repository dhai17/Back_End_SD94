package SD94.dto;

import SD94.entity.Producer;
import SD94.entity.ProductLine;
import SD94.entity.ProductMaterial;
import lombok.Data;

import java.util.Date;

@Data
public class ProductDTO {

    private Long id;

    private String name;

    private Float price;

    private String origin;

    private Integer status;

    private Date createdDate;

    private String createdby;

    private Date lastModifiedDate;

    private String lastModifiedBy;

    private ProductMaterial idProductMaterial;

    private ProductLine idProductLine;

    private Producer idProducer;

}
