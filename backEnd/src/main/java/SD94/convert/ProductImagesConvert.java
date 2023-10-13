package SD94.convert;

import SD94.dto.ProductImagesDTO;
import SD94.entity.ProductImages;
import org.springframework.stereotype.Component;

@Component
public class ProductImagesConvert {

    public ProductImagesDTO toDto(ProductImages entity) {
        ProductImagesDTO dto = new ProductImagesDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setImg(entity.getImg());
        dto.setProductDetails(entity.getProductDetails());
        dto.setColor(entity.getColor());
        return dto;
    }

    public ProductImages toEntity(ProductImagesDTO dto) {
        ProductImages entity = new ProductImages();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setImg(dto.getImg());
        entity.setProductDetails(dto.getProductDetails());
        entity.setColor(dto.getColor());
        return entity;
    }
}
