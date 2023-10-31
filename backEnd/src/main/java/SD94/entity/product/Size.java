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
@Table(name = "size")
public class Size extends Base implements Serializable {

    @Column(name = "shoeSize", columnDefinition = "int null")
    private Integer shoeSize;

}
