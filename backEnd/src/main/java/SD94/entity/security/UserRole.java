package SD94.entity.security;
import SD94.entity.nhanVien.NhanVien;
import lombok.*;

import javax.persistence.*;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userRoleId;

    @ManyToOne(fetch = FetchType.EAGER)
    private NhanVien staff;

    @ManyToOne
    private Role role;
}
