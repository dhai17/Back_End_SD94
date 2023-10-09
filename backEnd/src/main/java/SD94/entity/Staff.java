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
@Table(name = "staff")
public class NhanVien extends Base implements Serializable {
    @Column(name="name", columnDefinition = "nvarchar(256) not null unique")
    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name="startedDate", columnDefinition = "Datetime null")
    private Date startedDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "dayUpdate", columnDefinition = "Datetime null")
    private Date dayUpdate;

    @Column(name = "creator", columnDefinition = "navarchar(256) not null unique")
    private String creator;

    @Column(name = "phoneNumber", columnDefinition = "int null")
    private int phoneNumber;

    @Column(name = "email", columnDefinition = "nvarchar(256) not null unique")
    private String email;


    @Column(name = "gender",  columnDefinition = "bit")
    private Boolean gender;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "dateOfBirth", columnDefinition = "Datetime null")
    private Date dateOfBirth;
}
