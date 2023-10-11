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
@Table(name = "bill")
public class Bill extends Base implements Serializable {

    @Column(name = "code", columnDefinition = "nvarchar(256) not null unique")
    private String code;

    @Column(name = "note", columnDefinition = "nvarchar(50) null")
    private String note;

    @Column(name = "name", columnDefinition = "nvarchar(256) null")
    private String name;

    @Column(name = "email", columnDefinition = "nvarchar(256) null")
    private String email;

    @Column(name = "phoneNumber", columnDefinition = "nvarchar(50) null")
    private String phoneNumber;

    @Column(name = "address", columnDefinition = "nvarchar(256) null")
    private String address;

    @Column(name = "shippingMoney", columnDefinition = "int null")
    private int shippingMoney;

    @Column(name = "reducedMoney", columnDefinition = "int null")
    private int reducedMoney;

    @Column(name = "totalOrderPrice", columnDefinition = "int null")
    private int totalOrderPrice;

    @Column(name = "totalInvoiceAmount", columnDefinition = "int null")
    private int totalInvoiceAmount;

    @Column(name = "invoiceType", columnDefinition = "int null")
    private int invoiceType;

    @ManyToOne
    @JoinColumn(name = "idStatus", referencedColumnName = "id")
    private StatusBill status;

//    @ManyToOne
//    @JoinColumn(name = "idCustomer", referencedColumnName = "id")
//    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "idStaff", referencedColumnName = "id")
    private Staff staff;

    @ManyToOne
    @JoinColumn(name = "idDiscount", referencedColumnName = "id")
    private Discount discount;

}
