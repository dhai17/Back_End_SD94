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
@Table(name = "khachHang")
@Entity
public class KhachHang extends Base implements Serializable {
    @Column(name = "hoTen", columnDefinition = "nvarchar(50) not null")
    private String hoTen;

    @Column(name = "soDienThoai", columnDefinition = "nvarchar(10) null")
    private String soDienThoai;

    @Column(name = "email", columnDefinition = "nvarchar(200) null")
    private String email;

    @Temporal(TemporalType.DATE)
    @Column(name = "ngaySinh", columnDefinition = "Date null")
    private Date ngaySinh;

    @Column(name = "diaChi", columnDefinition = "nvarchar(250) null")
    private String diaChi;

    @Column(name = "matKhau", columnDefinition = "nvarchar(20) null")
    private String matKhau;

}
