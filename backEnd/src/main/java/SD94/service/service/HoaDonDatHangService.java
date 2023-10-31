package SD94.service.service;

import SD94.entity.hoaDon.HoaDon;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface HoaDonDatHangService {
    List<HoaDon> findAllBill1();
    List<HoaDon> findAllBill2();
    List<HoaDon> findAllBill3();
    List<HoaDon> findAllBill4();
    List<HoaDon> findAllBill5();

    ResponseEntity<Map<String, Boolean>> updateStatus1(String id_bill);
    ResponseEntity<Map<String, Boolean>> updateStatus2(String id_bill);
    ResponseEntity<Map<String, Boolean>> updateStatus3(String id_bill);
    ResponseEntity<Map<String, Boolean>> updateStatus4(String id_bill);
    ResponseEntity<Map<String, Boolean>> updateStatus5(String id_bill);
    List<HoaDon> searchAllBill1(String search);

    List<HoaDon> searchDateBill1(String searchDate);

    List<HoaDon> searchAllBill2(String search);

    List<HoaDon> searchDateBill2(String searchDate);

    List<HoaDon> searchAllBill3(String search);

    List<HoaDon> searchDateBill3(String searchDate);

    List<HoaDon> searchAllBill4(String search);

    List<HoaDon> searchDateBill4(String searchDate);

    List<HoaDon> searchAllBill5(String search);

    List<HoaDon> searchDateBill5(String searchDate);

    ResponseEntity<Map<String, Boolean>> updateStatusAll2();
    ResponseEntity<Map<String, Boolean>> updateStatusAll3();

}
