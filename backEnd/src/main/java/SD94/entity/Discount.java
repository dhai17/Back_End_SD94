package SD94.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "discount")
public class Discount extends Base implements Serializable {
    @Column(name = "name", columnDefinition = "nvarchar(256) not null unique")
    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "startedDate", columnDefinition = "Datetime null")
    private Date startedDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "endDate", columnDefinition = "Datetime null")
    private Date endDate;

    @Column(name = "percentDiscount", columnDefinition = "int null")
    private int percentDiscount;

    @Column(name = "maximumvalue", columnDefinition = "int null")
    private int maximumvalue;

    @Column(name = "status", columnDefinition = "int null")
    private int status;
}
