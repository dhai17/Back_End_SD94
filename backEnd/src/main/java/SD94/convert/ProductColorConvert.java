package SD94.convert;

import SD94.dto.MauSacDTO;
import SD94.entity.sanPham.MauSac;
import org.springframework.stereotype.Component;

@Component
public class ProductColorConvert {

    public MauSacDTO toDto(MauSac entity) {
        MauSacDTO dto = new MauSacDTO();
        dto.setId(entity.getId());
        dto.setCode(entity.getMaMauSac());
        dto.setColor(entity.getTenMauSac());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setCreatedby(entity.getCreatedby());
        dto.setLastModifiedDate(entity.getLastModifiedDate());
        dto.setLastModifiedBy(entity.getLastModifiedBy());
        return dto;
    }

    public MauSac toEntity(MauSacDTO dto) {
        MauSac entity = new MauSac();
        entity.setId(dto.getId());
        entity.setMaMauSac(dto.getCode());
        entity.setTenMauSac(dto.getColor());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setCreatedby(dto.getCreatedby());
        entity.setLastModifiedDate(dto.getLastModifiedDate());
        entity.setLastModifiedBy(dto.getLastModifiedBy());
        return entity;
    }
}
