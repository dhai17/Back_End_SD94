package SD94.repository.thongKe;

import SD94.dto.thongKe.ThongKeAll;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.Procedure;
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

}
