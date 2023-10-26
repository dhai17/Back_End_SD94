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
@Table(name = "product")
public class Product extends Base implements Serializable {

    @Column(name = "name", columnDefinition = "nvarchar(256) null")
    private String name;

    @Column(name = "price", columnDefinition = "nvarchar(256) null")
    private Float price;

    @Column(name = "origin", columnDefinition = "nvarchar(256) null")
    private String origin;

    @Column(name = "status", columnDefinition = "int null")
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "idMaterial", referencedColumnName = "id")
    private ProductMaterial productMaterial;

    @ManyToOne
    @JoinColumn(name = "idProductLine", referencedColumnName = "id")
    private ProductLine productLine;

    @ManyToOne
    @JoinColumn(name = "idProducer", referencedColumnName = "id")
    private Producer producer;

}
