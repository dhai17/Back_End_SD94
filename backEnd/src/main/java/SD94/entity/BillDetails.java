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
@Table(name = "detailedInvoice")
public class BillDetails extends Base implements Serializable {

    @Column(name = "quantity", columnDefinition = "int null")
    private int quantity;

    @Column(name = "unitPrice", columnDefinition = "int null")
    private int unitPrice;

    @Column(name = "totalPayment", columnDefinition = "int null")
    private int totalPayment;

    @ManyToOne
    @JoinColumn(name = "idBill", referencedColumnName = "id")
    private Bill bill;

    @ManyToOne
    @JoinColumn(name = "idProductDetails", referencedColumnName = "id")
    private ProductDetails productDetails;

}
