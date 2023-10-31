package SD94.convert;

import SD94.dto.ProductImagesDTO;
import SD94.entity.product.Image;
import org.springframework.stereotype.Component;

@Component
public class ProductImagesConvert {

    public ProductImagesDTO toDto(Image entity) {
        ProductImagesDTO dto = new ProductImagesDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
//        dto.setImg(entity.getImg());
        dto.setProductDetails(entity.getProductDetails());
        dto.setColor(entity.getColor());
        return dto;
    }

    public Image toEntity(ProductImagesDTO dto) {
        Image entity = new Image();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
//        entity.setImg(dto.getImg());
        entity.setProductDetails(dto.getProductDetails());
        entity.setColor(dto.getColor());
        return entity;
    }
}
