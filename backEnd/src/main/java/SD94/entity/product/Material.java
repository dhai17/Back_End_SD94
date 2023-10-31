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
@Table(name = "productMaterial")
public class Material extends Base implements Serializable {

    @Column(name = "material", columnDefinition = "nvarchar(256) null")
    private String material;

}
