package SD94.convert;

import SD94.dto.DiscountDTO;
import SD94.entity.khuyenMai.KhuyenMai;
import org.springframework.stereotype.Component;

@Component
public class DiscountConvert {

    public DiscountDTO toDto(KhuyenMai entity) {
        DiscountDTO dto = new DiscountDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getTenKhuyenMai());
        dto.setStartedDate(entity.getNgayBatDau());
        dto.setEndDate(entity.getNgayKetThuc());
        dto.setPercentDiscount(entity.getPhanTramGiam());
        dto.setMaximumvalue(entity.getTienGiamToiDa());
        dto.setStatus(entity.getTrangThai());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setCreatedby(entity.getCreatedby());
        dto.setLastModifiedDate(entity.getLastModifiedDate());
        dto.setLastModifiedBy(entity.getLastModifiedBy());
        dto.setDeleted(entity.isDeleted());
        return dto;
    }

    public KhuyenMai toEntity(DiscountDTO dto) {
        KhuyenMai entity = new KhuyenMai();
        entity.setId(dto.getId());
        entity.setTenKhuyenMai(dto.getName());
        entity.setNgayBatDau(dto.getStartedDate());
        entity.setNgayKetThuc(dto.getEndDate());
        entity.setPhanTramGiam(dto.getPercentDiscount());
        entity.setTienGiamToiDa(dto.getMaximumvalue());
        entity.setTrangThai(dto.getStatus());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setCreatedby(dto.getCreatedby());
        entity.setLastModifiedDate(dto.getLastModifiedDate());
        entity.setLastModifiedBy(dto.getLastModifiedBy());
        entity.setDeleted(dto.isDeleted());
        return entity;
    }
}
