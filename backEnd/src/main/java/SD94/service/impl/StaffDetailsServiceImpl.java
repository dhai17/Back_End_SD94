package SD94.service.impl;
import SD94.entity.nhanVien.NhanVien;
import SD94.repository.nhanVien.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class StaffDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private NhanVienRepository staffRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        NhanVien user = this.staffRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user found !!");
        }
        return user;
    }
}
