package SD94.convert;

import SD94.dto.ProductDetailsDTO;
import SD94.entity.ProductDetails;
import org.springframework.stereotype.Component;

@Component
public class ProductDetailsConvert {

    public ProductDetailsDTO toDto(ProductDetails entity) {
        ProductDetailsDTO dto = new ProductDetailsDTO();
        dto.setId(entity.getId());
        dto.setQuantity(entity.getQuantity());
        dto.setStatus(entity.getStatus());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setCreatedby(entity.getCreatedby());
        dto.setLastModifiedDate(entity.getLastModifiedDate());
        dto.setLastModifiedBy(entity.getLastModifiedBy());
        dto.setProduct(entity.getProduct());
        dto.setProductColor(entity.getProductColor());
        dto.setProductSize(entity.getProductSize());
        return dto;
    }

    public ProductDetails toEntity(ProductDetailsDTO dto) {
        ProductDetails entity = new ProductDetails();
        entity.setId(dto.getId());
        entity.setQuantity(dto.getQuantity());
        entity.setStatus(dto.getStatus());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setCreatedby(dto.getCreatedby());
        entity.setLastModifiedDate(dto.getLastModifiedDate());
        entity.setLastModifiedBy(dto.getLastModifiedBy());
        entity.setProduct(dto.getProduct());
        entity.setProductColor(dto.getProductColor());
        entity.setProductSize(dto.getProductSize());
        return entity;
    }

}
