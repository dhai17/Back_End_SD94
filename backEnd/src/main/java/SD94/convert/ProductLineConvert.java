package SD94.convert;

import SD94.dto.ProductLineDTO;
import SD94.entity.product.TypeProduct;
import org.springframework.stereotype.Component;

@Component
public class ProductLineConvert {

    public ProductLineDTO toDto(TypeProduct entity) {
        ProductLineDTO dto = new ProductLineDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setCreatedby(entity.getCreatedby());
        dto.setLastModifiedDate(entity.getLastModifiedDate());
        dto.setLastModifiedBy(entity.getLastModifiedBy());
        dto.setDeleted(entity.isDeleted());
        return dto;
    }

    public TypeProduct toEntity(ProductLineDTO dto) {
        TypeProduct entity = new TypeProduct();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setCreatedby(dto.getCreatedby());
        entity.setLastModifiedDate(dto.getLastModifiedDate());
        entity.setLastModifiedBy(dto.getLastModifiedBy());
        entity.setDeleted(dto.isDeleted());
        return entity;
    }
}
