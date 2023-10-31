package SD94.convert;

import SD94.dto.ProductMaterialDTO;
import SD94.entity.sanPham.ChatLieu;
import org.springframework.stereotype.Component;

@Component
public class ProductMaterialConvert {

    public ProductMaterialDTO toDto(ChatLieu entity) {
        ProductMaterialDTO dto = new ProductMaterialDTO();
        dto.setId(entity.getId());
        dto.setMaterial(entity.getChatLieu());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setCreatedby(entity.getCreatedby());
        dto.setLastModifiedDate(entity.getLastModifiedDate());
        dto.setLastModifiedBy(entity.getLastModifiedBy());
        dto.setDeleted(entity.isDeleted());
        return dto;
    }

    public ChatLieu toEntity(ProductMaterialDTO dto) {
        ChatLieu entity = new ChatLieu();
        entity.setId(dto.getId());
        entity.setChatLieu(dto.getMaterial());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setCreatedby(dto.getCreatedby());
        entity.setLastModifiedDate(dto.getLastModifiedDate());
        entity.setLastModifiedBy(dto.getLastModifiedBy());
        entity.setDeleted(dto.isDeleted());
        return entity;
    }

}
