package SD94.repository.thongKe;

import SD94.dto.thongKe.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public interface ThongKeRepository {

    @Procedure(name = "thongKeAll()")
    List<ThongKeAll> getThongKe();

    @Procedure(name = "thongKeAll_online()")
    List<ThongKeAllOnl> getThongKe_online();

    @Procedure(name = "thongKeAll_offline()")
    List<ThongKeAllOff> getThongKe_offline();

    @Procedure(name = "thongKeTheoNgay()")
    List<TamTinhTongTienDaBanTheoNgay> getThongKeTheoNgay();

    @Procedure(name = "thongKeTheoThang()")
    List<ThongKeTheoThang> getThongKeTheoThang(@Param("thang") int thang);

    @Procedure(name = "thongKeTheoNam()")
    List<ThongKeTheoNam> getThongKeTheoNam(@Param("nam") int nam);

    @Procedure(name = "top5SanPhamBanChay()")
    List<TopSanPhamBanChay> getThongKeSanPhamBanChay();

}