package SD94.entity.cart;

import SD94.entity.customer.Base;
import SD94.entity.customer.Customer;
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
@Table(name = "cart")
public class Cart extends Base implements Serializable {
    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "status", columnDefinition = "int null")
    private int status;
}
