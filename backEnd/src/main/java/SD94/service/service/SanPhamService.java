package SD94.service.service;

import SD94.entity.sanPham.SanPham;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SanPhamService {

    List<SanPham> findAllProduct();

    ResponseEntity<SanPham> saveEdit(SanPham sanPhamUpdate);

    ResponseEntity<List<SanPham>> deleteProduct(Long id);

    ResponseEntity<SanPham> saveCreate(SanPham sanPhamCreate);

    List<SanPham> searchAllProduct(String search);

    List<SanPham> searchDateProduct(String searchDate);

}
