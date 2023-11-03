package SD94.controller.admin.sanPham;

import SD94.dto.ThemMoiSanPhamDTO;
import SD94.entity.sanPham.*;
import SD94.repository.sanPham.*;
import SD94.service.service.HinhAnhService;
import SD94.service.service.SanPhamChiTietService;
import SD94.service.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/sanPham")
public class SanPhamController {

    @Autowired
    SanPhamRepository repository;

    @Autowired
    HinhAnhService hinhAnhService;

    @Autowired
    ChatLieuRepository productMaterialRepository;

    @Autowired
    LoaiSanPhamRepository productLineRepository;

    @Autowired
    NhaSanXuatRepository producerRepository;

    @Autowired
    MauSacRepository productColorRepository;

    @Autowired
    KichCoRepository productSizeRepository;

    @Autowired
    SanPhamChiTietRepository productDetailsRepository;

    @Autowired
    SanPhamService sanPhamService;

    @Autowired
    SanPhamChiTietService sanPhamChiTietService;


    @GetMapping("/danhSach")
    public ResponseEntity<List<SanPham>> getProduct() {
        List<SanPham> list = repository.findAll();
        return ResponseEntity.ok().body(list);
    }


    @PostMapping("/themMoi")
    public List<SanPhamChiTiet> saveCreate(@RequestBody ThemMoiSanPhamDTO sp) {
        String tenSanPham = sp.getTenSanPham();
        Float gia = sp.getGia();
        Long chatLieu_id = sp.getChatLieu_id();
        Long loaiSanPham_id = sp.getLoaiSanPham_id();
        Long nhaSanXuat_id = sp.getNhaSanXuat_id();
        List<String> kichCo = sp.getKichCo();
        List<Integer> mauSac = sp.getMauSac();
        int soLuong = sp.getSoLuong();

        Optional<ChatLieu> optionalProductMaterial = productMaterialRepository.findById(chatLieu_id);
        Optional<LoaiSanPham> optionalProductLine = productLineRepository.findById(loaiSanPham_id);
        Optional<NhaSanXuat> optionalProducer = producerRepository.findById(nhaSanXuat_id);

        if (optionalProductMaterial.isPresent() && optionalProductLine.isPresent() && optionalProducer.isPresent()) {
            ChatLieu chatLieu = optionalProductMaterial.get();
            LoaiSanPham loaiSanPham = optionalProductLine.get();
            NhaSanXuat producerr = optionalProducer.get();
            SanPham sanPham = new SanPham();
            sanPham.setTenSanPham(tenSanPham);
            sanPham.setGia(gia);
            sanPham.setTrangThai(0);
            sanPham.setLoaiSanPham(loaiSanPham);
            sanPham.setNhaSanXuat(producerr);
            sanPham.setChatLieu(chatLieu);
            repository.save(sanPham);

            List<SanPhamChiTiet> sanPhamChiTietList = new ArrayList<>();
            for (Object idSize : kichCo) {
                Long id_size = Long.valueOf(String.valueOf(idSize));
                Optional<KichCo> optionalProductSize = productSizeRepository.findById(id_size);
                for (Object id_colors : mauSac) {
                    Long id_color = Long.valueOf(String.valueOf(id_colors));
                    Optional<MauSac> optionalProductColor = productColorRepository.findById(id_color);
                    if (optionalProductSize.isPresent() && optionalProductColor.isPresent()) {
                        KichCo productSize = optionalProductSize.get();
                        MauSac productMauSac = optionalProductColor.get();
                        SanPhamChiTiet sanPhamChiTiet = new SanPhamChiTiet();
                        sanPhamChiTiet.setSanPham(sanPham);
                        sanPhamChiTiet.setMauSac(productMauSac);
                        sanPhamChiTiet.setKichCo(productSize);
                        sanPhamChiTiet.setSoLuong(soLuong);
                        productDetailsRepository.save(sanPhamChiTiet);
                        sanPhamChiTietList.add(sanPhamChiTiet);
                    }
                }
            }
            return sanPhamChiTietList;
        } else {
            return null;
        }
    }

    @DeleteMapping("/xoa/{id}")
    public ResponseEntity<List<SanPham>> deleteProduct(@PathVariable("id") Long id) {
        return sanPhamService.deleteProduct(id);
    }

    @DeleteMapping("/xoa-san-pham-chi-tiet/{id}")
    public ResponseEntity<List<SanPhamChiTiet>> deleteProductDetails(@PathVariable("id") Long id) {
        return sanPhamChiTietService.deleteProductDetails(id);
    }

    @GetMapping("/chinhSua/{id}")
    public SanPham editProduct(@PathVariable("id") Long id) {
        return repository.findByID(id);
    }

    @PutMapping("/luuChinhSua")
    public ResponseEntity<SanPham> saveUpdate(@RequestBody SanPham sanPham) {
        return sanPhamService.saveEdit(sanPham);
    }

    @RequestMapping("timKiem={search}")
    public List<SanPham> searchAll(@PathVariable("search") String search) {
        return sanPhamService.searchAllProduct(search);
    }

    @RequestMapping("timKiemNgay={searchDate}")
    public List<SanPham> searchDate(@PathVariable("searchDate") String search) {
        return sanPhamService.searchDateProduct(search);
    }
}
