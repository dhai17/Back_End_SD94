package SD94.entity.khachHang;

import javax.persistence.*;

import SD94.entity.Base;
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
public class KhachHang extends Base implements Serializable {
    @Column(name = "hoTen", columnDefinition = "nvarchar(50) not null")
    private String hoTen;

    @Column(name = "soDienThoai", columnDefinition = "nvarchar(10) null")
    private String soDienThoai;

//    @Pattern(regexp = "^[\\w\\.-]+@[\\w\\.-]+$\n", message = "loi email ", payload = ErrorPayload.class)
    @Column(name = "email", columnDefinition = "nvarchar(200) null")
    private String email;

//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Ho_Chi_Minh")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    @Temporal(TemporalType.DATE)
    @Column(name = "ngaySinh", columnDefinition = "Date null")
    private Date ngaySinh;

    @Column(name = "diaChi", columnDefinition = "nvarchar(250) null")
    private String diaChi;

    @Column(name = "matKhau", columnDefinition = "nvarchar(20) null")
    private String matKhau;

}
