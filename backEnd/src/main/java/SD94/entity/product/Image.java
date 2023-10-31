package SD94.entity.product;

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
@Table(name = "image")
public class Image extends Base implements Serializable {

    @ManyToOne
    @JoinColumn(name = "idColor", referencedColumnName = "id")
    private Color color;

    @ManyToOne
    @JoinColumn(name = "idProduct", referencedColumnName = "id")
    private ProductDetails productDetails;

    @Column(name = "name", columnDefinition = "nvarchar(256) null")
    private String name;
}
