package SD94.dto;

import SD94.entity.product.Manufacturer;
import SD94.entity.product.TypeProduct;
import SD94.entity.product.Material;
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

    private Material idMaterial;

    private TypeProduct idTypeProduct;

    private Manufacturer idManufacturer;

}
