package SD94.convert;

import SD94.dto.ProducerDTO;
import SD94.entity.sanPham.NhaSanXuat;
import org.springframework.stereotype.Component;

@Component
public class ProducerConvert {

    public ProducerDTO toDto(NhaSanXuat entity) {
        ProducerDTO dto = new ProducerDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setAddress(entity.getDiaChi());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setCreatedby(entity.getCreatedby());
        dto.setLastModifiedDate(entity.getLastModifiedDate());
        dto.setLastModifiedBy(entity.getLastModifiedBy());
        return dto;
    }

    public NhaSanXuat toEntity(ProducerDTO dto) {
        NhaSanXuat entity = new NhaSanXuat();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDiaChi(dto.getAddress());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setCreatedby(dto.getCreatedby());
        entity.setLastModifiedDate(dto.getLastModifiedDate());
        entity.setLastModifiedBy(dto.getLastModifiedBy());
        return entity;
    }

}
