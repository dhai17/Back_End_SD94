package SD94.convert;

import SD94.dto.ProducerDTO;
import SD94.entity.product.Manufacturer;
import org.springframework.stereotype.Component;

@Component
public class ProducerConvert {

    public ProducerDTO toDto(Manufacturer entity) {
        ProducerDTO dto = new ProducerDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setAddress(entity.getAddress());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setCreatedby(entity.getCreatedby());
        dto.setLastModifiedDate(entity.getLastModifiedDate());
        dto.setLastModifiedBy(entity.getLastModifiedBy());
        return dto;
    }

    public Manufacturer toEntity(ProducerDTO dto) {
        Manufacturer entity = new Manufacturer();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setAddress(dto.getAddress());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setCreatedby(dto.getCreatedby());
        entity.setLastModifiedDate(dto.getLastModifiedDate());
        entity.setLastModifiedBy(dto.getLastModifiedBy());
        return entity;
    }

}
