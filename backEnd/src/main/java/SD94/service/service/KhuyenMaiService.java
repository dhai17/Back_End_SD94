package SD94.service.service;

import SD94.entity.khuyenMai.KhuyenMai;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface KhuyenMaiService {
    List<KhuyenMai> findAllDiscount();

    ResponseEntity<KhuyenMai> saveEdit(KhuyenMai khuyenMaiUpdate);

    ResponseEntity<List<KhuyenMai>> deleteDiscount(Long id);

    ResponseEntity<KhuyenMai> saveCreate(KhuyenMai khuyenMaiCreate);

    List<KhuyenMai> searchAllDiscount(String search);

    List<KhuyenMai> searchDateDiscount(String searchDate);
}
