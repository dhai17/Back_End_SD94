package SD94.controller.admin.sanPham;

import SD94.entity.sanPham.*;
import SD94.repository.sanPham.*;
import SD94.service.service.HinhAnhService;
import SD94.service.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/danhSach")
    public ResponseEntity<List<SanPham>> getProduct() {
        List<SanPham> list = repository.findAll();
        return ResponseEntity.ok().body(list);
    }


    @PostMapping("themMoi")
    public List<SanPhamChiTiet> saveCreate(@RequestBody Map<String, Object> body) {
        String name = (String) body.get("name");
        String price = (String) body.get("price");
        String origin = (String) body.get("origin");
        Long id_metarial = Long.valueOf((String) body.get("id_metarial"));
        Long id_line = Long.valueOf((String) body.get("id_line"));
        String producer = (String) body.get("producer");
        ArrayList<String> size = (ArrayList<String>) body.get("size");
        ArrayList<String> color = (ArrayList<String>) body.get("color");
        String quantity = (String) body.get("quantity");
        String status = (String) body.get("status");

        Float priceFomat = Float.valueOf(price);
        Long id_producer = Long.valueOf(producer);
        Integer quantityF = Integer.valueOf(quantity);


        Optional<ChatLieu> optionalProductMaterial = productMaterialRepository.findById(id_metarial);
        Optional<LoaiSanPham> optionalProductLine = productLineRepository.findById(id_line);
        Optional<NhaSanXuat> optionalProducer = producerRepository.findById(id_producer);

        if (optionalProductMaterial.isPresent() && optionalProductLine.isPresent() && optionalProducer.isPresent()) {
            ChatLieu chatLieu = optionalProductMaterial.get();
            LoaiSanPham loaiSanPham = optionalProductLine.get();
            NhaSanXuat producerr = optionalProducer.get();

            SanPham sanPham = new SanPham();
            sanPham.setTenSanPham(name);
            sanPham.setGia(priceFomat);
            sanPham.setTrangThai(0);
            sanPham.setLoaiSanPham(loaiSanPham);
            sanPham.setNhaSanXuat(producerr);
            sanPham.setChatLieu(chatLieu);
            repository.save(sanPham);

            List<SanPhamChiTiet> sanPhamChiTietList = new ArrayList<>();
            for (Object idSize : size) {
                Long id_size = Long.valueOf(String.valueOf(idSize));

                Optional<KichCo> optionalProductSize = productSizeRepository.findById(id_size);
                for (Object id_colors : color) {
                    Long id_color = Long.valueOf(String.valueOf(id_colors));
                    Optional<MauSac> optionalProductColor = productColorRepository.findById(id_color);
                    if (optionalProductSize.isPresent() && optionalProductColor.isPresent()) {
                        KichCo productSize = optionalProductSize.get();
                        MauSac productMauSac = optionalProductColor.get();
                        SanPhamChiTiet sanPhamChiTiet = new SanPhamChiTiet();
                        sanPhamChiTiet.setSanPham(sanPham);
                        sanPhamChiTiet.setMauSac(productMauSac);
                        sanPhamChiTiet.setKichCo(productSize);
                        sanPhamChiTiet.setSoLuong(quantityF);
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
    public void deleteProductDetails(@RequestBody Map<String, Object> body) {
        Long productId = Long.valueOf((String) body.get("productId"));
        repository.deleteById(productId);
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
