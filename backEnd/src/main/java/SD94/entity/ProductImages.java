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
@Table(name = "productImages")
public class ProductImages extends Base implements Serializable {

    @ManyToOne
    @JoinColumn(name = "idColor", referencedColumnName = "id")
    private ProductColor color;

    @ManyToOne
    @JoinColumn(name = "idProduct", referencedColumnName = "id")
    private ProductDetails productDetails;

    @Column(name = "image", columnDefinition = "nvarchar(256) null")
    private String img;

    @Column(name = "name", columnDefinition = "nvarchar(256) null")
    private String name;

    private byte[] images;

}
