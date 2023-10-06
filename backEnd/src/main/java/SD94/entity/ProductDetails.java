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
@Table(name = "productDetails")
public class ProductDetails extends Base implements Serializable {

    @Column(name = "quantity", columnDefinition = "int null")
    private Integer quantity;

    @Column(name = "status", columnDefinition = "int null")
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "idProduct", referencedColumnName = "id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "idColor", referencedColumnName = "id")
    private ProductColor productColor;

    @ManyToOne
    @JoinColumn(name = "idSize", referencedColumnName = "id")
    private ProductSize productSize;

}
