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
@Table(name = "position")
public class Position extends Base implements Serializable {

    @Column(name = "name", columnDefinition = "nvarchar(256) not null unique")
    private String name;

    @Column(name = "roles", columnDefinition = "int null")
    private int roles;


}
