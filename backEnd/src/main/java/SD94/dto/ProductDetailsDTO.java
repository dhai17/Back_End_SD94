package SD94.dto;

import SD94.entity.product.Product;
import SD94.entity.product.Color;
import SD94.entity.product.Size;
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

    private Color color;

    private Size size;

}
