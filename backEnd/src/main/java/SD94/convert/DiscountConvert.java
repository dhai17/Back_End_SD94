package SD94.convert;

import SD94.dto.DiscountDTO;
import SD94.entity.Discount;
import org.springframework.stereotype.Component;

@Component
public class DiscountConvert {

    public DiscountDTO toDto(Discount entity) {
        DiscountDTO dto = new DiscountDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setStartedDate(entity.getStartedDate());
        dto.setEndDate(entity.getEndDate());
        dto.setPercentDiscount(entity.getPercentDiscount());
        dto.setMaximumvalue(entity.getMaximumvalue());
        dto.setStatus(entity.getStatus());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setCreatedby(entity.getCreatedby());
        dto.setLastModifiedDate(entity.getLastModifiedDate());
        dto.setLastModifiedBy(entity.getLastModifiedBy());
        dto.setDeleted(entity.isDeleted());
        return dto;
    }

    public Discount toEntity(DiscountDTO dto) {
        Discount entity = new Discount();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setStartedDate(dto.getStartedDate());
        entity.setEndDate(dto.getEndDate());
        entity.setPercentDiscount(dto.getPercentDiscount());
        entity.setMaximumvalue(dto.getMaximumvalue());
        entity.setStatus(dto.getStatus());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setCreatedby(dto.getCreatedby());
        entity.setLastModifiedDate(dto.getLastModifiedDate());
        entity.setLastModifiedBy(dto.getLastModifiedBy());
        entity.setDeleted(dto.isDeleted());
        return entity;
    }
}
