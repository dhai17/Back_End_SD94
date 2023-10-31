package SD94.entity.customer;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "customer")
@Entity
public class Customer extends Base implements Serializable {
    @Column(name = "name", columnDefinition = "nvarchar(50) not null")
    private String name;

    @Column(name = "phone_number", columnDefinition = "nvarchar(10) null")
    private String phoneNumber;

//    @Pattern(regexp = "^[\\w\\.-]+@[\\w\\.-]+$\n", message = "loi email ", payload = ErrorPayload.class)
    @Column(name = "email", columnDefinition = "nvarchar(200) null")
    private String email;

//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Ho_Chi_Minh")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    @Temporal(TemporalType.DATE)
    @Column(name = "date_birth", columnDefinition = "Date null")
    private Date dateBirth;

    @Column(name = "add_ress", columnDefinition = "nvarchar(250) null")
    private String addRess;

    @Column(name = "password", columnDefinition = "nvarchar(20) null")
    private String passWord;

}
