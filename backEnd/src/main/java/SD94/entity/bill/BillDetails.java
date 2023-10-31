package SD94.entity.bill;

import SD94.entity.customer.Base;
import SD94.entity.product.ProductDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import javax.persistence.*;

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
