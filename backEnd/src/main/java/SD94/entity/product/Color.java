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
@Table(name = "color")
public class Color extends Base implements Serializable {

    @Column(name = "code", columnDefinition = "nvarchar(256) not null unique")
    private String code;

    @Column(name = "color", columnDefinition = "nvarchar(256) null")
    private String color;

}
