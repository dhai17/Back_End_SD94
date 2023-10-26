package SD94.entity;
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
@Table(name = "productSize")
public class ProductSize extends Base implements Serializable {

    @Column(name = "shoeSize", columnDefinition = "int null")
    private Integer shoeSize;

}
