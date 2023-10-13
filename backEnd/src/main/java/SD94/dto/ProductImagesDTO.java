package SD94.dto;

import SD94.entity.ProductColor;
import SD94.entity.ProductDetails;
import lombok.Data;

@Data
public class ProductImagesDTO {

    private long id;

    private String img;

    private String name;

    private ProductColor color;

    private ProductDetails productDetails;

}
