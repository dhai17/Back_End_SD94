package SD94.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
