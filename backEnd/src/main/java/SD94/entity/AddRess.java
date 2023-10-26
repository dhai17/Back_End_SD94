package SD94.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "addRess")
@Entity
public class AddRess extends Base implements Serializable {
    @Column(name = "addRess", columnDefinition = "nvarchar(500) null")
    private String addRess;

    @ManyToOne
    @JoinColumn(name = "Costomer_id")
    private Customer customer;

    @Column(name = "is_true")
    private boolean is_true = false;

}
