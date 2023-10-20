package SD94.convert;

import SD94.dto.StaffDTO;
import SD94.entity.Staff;
import org.springframework.stereotype.Component;

@Component
public class StaffConvert {


    public StaffDTO toDto(Staff entity){
        StaffDTO dto = new StaffDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setAddress(entity.getAddress());
        dto.setEmail(entity.getEmail());
        dto.setGender(Boolean.valueOf(entity.getGender()));
        dto.setPhoneNumber((entity.getPhoneNumber()));
        dto.setPassword(entity.getPassword());
        dto.setStatus(entity.getStatus());
        dto.setCreatedby(entity.getCreatedby());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setLastModifiedBy(entity.getLastModifiedBy());
        dto.setLastModifiedDate(entity.getLastModifiedDate());
        dto.setDeleted(entity.isDeleted());
        dto.setDateOfBirth(entity.getDateOfBirth());
        return dto;

    }


    public Staff toEntity(StaffDTO dto){
        Staff entity = new Staff();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setAddress(dto.getAddress());
        entity.setEmail(dto.getEmail());
        entity.setGender(Boolean.valueOf(dto.getGender()));
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setPassword(dto.getPassword());
        entity.setStatus(dto.getStatus());
        entity.setCreatedby(dto.getCreatedby());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setLastModifiedBy(dto.getLastModifiedBy());
        entity.setLastModifiedDate(dto.getLastModifiedDate());
        entity.setDeleted(dto.isDeleted());
        entity.setDateOfBirth(dto.getDateOfBirth());
        return entity;
    }
}
