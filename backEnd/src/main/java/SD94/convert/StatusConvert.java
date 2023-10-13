package SD94.convert;

import SD94.dto.StatusDTO;
import SD94.entity.Status;
import org.springframework.stereotype.Component;

@Component
public class StatusConvert {

    public StatusDTO toDto(Status entity) {
        StatusDTO dto = new StatusDTO();
        dto.setId(entity.getId());
        dto.setWaitForConfirmation(entity.getWaitForConfirmation());
        dto.setWaitingForDelivery(entity.getWaitingForDelivery());
        dto.setAreDelivering(entity.getAreDelivering());
        dto.setDelivered(entity.getDelivered());
        dto.setCancelled(entity.getCancelled());
        dto.setSold(entity.getSold());
        dto.setReceivedMoney(entity.getReceivedMoney());
        dto.setCancelOrder(entity.getCancelOrder());
        return dto;
    }

    public Status toEntity(StatusDTO dto) {
        Status entity = new Status();
        entity.setId(dto.getId());
        entity.setWaitForConfirmation(dto.getWaitForConfirmation());
        entity.setWaitingForDelivery(dto.getWaitingForDelivery());
        entity.setAreDelivering(dto.getAreDelivering());
        entity.setDelivered(dto.getDelivered());
        entity.setCancelled(dto.getCancelled());
        entity.setSold(dto.getSold());
        entity.setReceivedMoney(dto.getReceivedMoney());
        entity.setCancelOrder(dto.getCancelOrder());
        return entity;
    }

}
