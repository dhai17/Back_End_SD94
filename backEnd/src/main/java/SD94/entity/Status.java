package SD94.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "status")
public class Status extends Base implements Serializable {

    @Column(name = "waitForConfirmation", columnDefinition = "nvarchar(256) null")
    private String waitForConfirmation;

    @Column(name = "waitingForDelivery", columnDefinition = "nvarchar(256) null")
    private String waitingForDelivery;

    @Column(name = "areDelivering", columnDefinition = "nvarchar(256) null")
    private String areDelivering;

    @Column(name = "delivered", columnDefinition = "nvarchar(256) null")
    private String delivered;

    @Column(name = "cancelled", columnDefinition = "nvarchar(256) null")
    private String cancelled;

    @Column(name = "sold", columnDefinition = "nvarchar(256) null")
    private String sold;

    @Column(name = "receivedMoney", columnDefinition = "nvarchar(256) null")
    private String receivedMoney;

    @Column(name = "cancelOrder", columnDefinition = "nvarchar(256) null")
    private String cancelOrder;

}
