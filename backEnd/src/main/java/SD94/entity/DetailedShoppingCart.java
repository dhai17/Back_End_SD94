package SD94.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "detailedShoppingCart")
public class DetailedShoppingCart extends Base implements Serializable {

    @ManyToOne
    @JoinColumn(name = "Cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "ProductDetails_id")
    private ProductDetails productDetails;

    @Column(name = "quantity", columnDefinition = "int null")
    private int quanTity;

    @Column(name = "unitPrice", columnDefinition = "int ")
    private int unitPrice;

    @Column(name = "intoMoney", columnDefinition = "varchar(50) not null")
    private BigDecimal intoMoney;
}
