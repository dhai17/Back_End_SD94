package SD94.repository.implement;

import SD94.dto.thongKe.ThongKeAll;
import SD94.repository.thongKe.ThongKeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ThongKeRepositoryImpl implements ThongKeRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<ThongKeAll> getThongKe() {
        return jdbcTemplate.query(
                "call thongKeAll()",
                ((rs, rowNum) -> new ThongKeAll(
                        rs.getLong("trangThai_id"),
                        rs.getLong("tong_so_hoadon"),
                        rs.getBigDecimal("tong_tien_hoadon")
                ))
        );
    }
}
