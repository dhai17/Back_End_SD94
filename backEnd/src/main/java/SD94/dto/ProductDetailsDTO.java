package SD94.dto;

import SD94.entity.Product;
import SD94.entity.ProductColor;
import SD94.entity.ProductSize;
import lombok.Data;

import java.util.Date;

@Data
public class ProductDetailsDTO {

    private Long id;

    private Integer quantity;

    private Integer status;

    private Date createdDate;

    private String createdby;

    private Date lastModifiedDate;

    private String lastModifiedBy;

    private Product product;

    private ProductColor productColor;

    private ProductSize productSize;

}
