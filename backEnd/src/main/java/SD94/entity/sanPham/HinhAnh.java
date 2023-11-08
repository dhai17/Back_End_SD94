package SD94.entity.sanPham;

import SD94.entity.Base;
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
@Table(name = "HinhAnh")
public class HinhAnh extends Base implements Serializable {

    @ManyToOne
    @JoinColumn(name = "idColor", referencedColumnName = "id")
    private MauSac mauSac;

    @ManyToOne
    @JoinColumn(name = "idProduct", referencedColumnName = "id")
    private SanPhamChiTiet sanPhamChiTiet;

    @Column(name = "name", columnDefinition = "nvarchar(256) null")
    private String tenAnh;

    @Column(name = "anh_mac_dinh", columnDefinition = "bit")
    private Boolean anhMacDinh;
}
