package SD94.entity;

import SD94.entity.bill.Bill;
import SD94.entity.bill.StatusBill;
import SD94.entity.customer.Base;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "invoiceHistory")
public class InvoiceHistory extends Base implements Serializable {

    @Column(name = "operation", columnDefinition = "nvarchar(256) not null unique")
    private String operation;

    @ManyToOne
    @JoinColumn(name = "idStatus", referencedColumnName = "id")
    private StatusBill status;

    @ManyToOne
    @JoinColumn(name = "idNhanVien", referencedColumnName = "id")
    private Staff staff;

    @ManyToOne
    @JoinColumn(name = "idBill", referencedColumnName = "id")
    private Bill bill;

}
