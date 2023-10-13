package SD94.convert;

import SD94.dto.ProductDTO;
import SD94.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConvert {

    public ProductDTO toDto(Product entity) {
        ProductDTO dto = new ProductDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setOrigin(entity.getOrigin());
        dto.setStatus(entity.getStatus());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setCreatedby(entity.getCreatedby());
        dto.setLastModifiedDate(entity.getLastModifiedDate());
        dto.setLastModifiedBy(entity.getLastModifiedBy());
        dto.setIdProductMaterial(entity.getProductMaterial());
        dto.setIdProductLine(entity.getProductLine());
        dto.setIdProducer(entity.getProducer());
        return dto;
    }

    public Product toEntity(ProductDTO dto) {
        Product entity = new Product();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setOrigin(dto.getOrigin());
        entity.setStatus(dto.getStatus());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setCreatedby(dto.getCreatedby());
        entity.setLastModifiedDate(dto.getLastModifiedDate());
        entity.setLastModifiedBy(dto.getLastModifiedBy());
        entity.setProductMaterial(dto.getIdProductMaterial());
        entity.setProductLine(dto.getIdProductLine());
        entity.setProducer(dto.getIdProducer());
        return entity;
    }
}
