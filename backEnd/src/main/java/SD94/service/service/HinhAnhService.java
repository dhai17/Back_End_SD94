package SD94.service.service;

import SD94.entity.sanPham.HinhAnh;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HinhAnhService {

    List<HinhAnh> findAllProductImages();

    ResponseEntity<HinhAnh> saveEdit(HinhAnh hinhAnhUpdate);

    ResponseEntity<List<HinhAnh>> deleteProductImages(Long id);

    ResponseEntity<HinhAnh> saveCreate(HinhAnh hinhAnhCreate);

    List<HinhAnh> searchAllProductImages(String search);

    List<HinhAnh> searchDateProductImages(String searchDate);

    HinhAnh saveOrUpdate(HinhAnh images);

    HinhAnh findByMaSanPham(Long maSanPham);

}
