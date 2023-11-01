package SD94.convert;

import SD94.dto.KichCoDTO;
import SD94.entity.sanPham.KichCo;
import org.springframework.stereotype.Component;

@Component
public class ProductSizeConvert {

    public KichCoDTO toDto(KichCo entity) {
        KichCoDTO dto = new KichCoDTO();
        dto.setId(entity.getId());
        dto.setShoeSize(entity.getKichCo());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setCreatedby(entity.getCreatedby());
        dto.setLastModifiedDate(entity.getLastModifiedDate());
        dto.setLastModifiedBy(entity.getLastModifiedBy());
        dto.setDeleted(entity.isDeleted());
        return dto;
    }

    public KichCo toEntity(KichCoDTO dto) {
        KichCo entity = new KichCo();
        entity.setId(dto.getId());
        entity.setKichCo(dto.getShoeSize());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setCreatedby(dto.getCreatedby());
        entity.setLastModifiedDate(dto.getLastModifiedDate());
        entity.setLastModifiedBy(dto.getLastModifiedBy());
        entity.setDeleted(dto.isDeleted());
        return entity;
    }
}
