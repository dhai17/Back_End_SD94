package SD94.dto;

import SD94.entity.product.Color;
import SD94.entity.product.ProductDetails;
import lombok.Data;

@Data
public class ProductImagesDTO {

    private long id;

    private String img;

    private String name;

    private Color color;

    private ProductDetails productDetails;

}
