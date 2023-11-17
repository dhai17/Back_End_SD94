package SD94.controller.admin.sanPham;

import SD94.dto.HinhAnhDTO;
import SD94.dto.HoaDonChiTietDTO;
import SD94.dto.SanPhamChiTietDTO;
import SD94.entity.sanPham.HinhAnh;
import SD94.entity.sanPham.KichCo;
import SD94.entity.sanPham.SanPhamChiTiet;
import SD94.repository.sanPham.HinhAnhRepository;
import SD94.repository.sanPham.KichCoRepository;
import SD94.repository.sanPham.MauSacRepository;
import SD94.repository.sanPham.SanPhamChiTietRepository;
import SD94.service.service.SanPhamChiTietService;
import SD94.service.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sanPhamChiTiet")
public class SanPhamChiTietController {

    @Autowired
    SanPhamChiTietRepository productDetailsRepository;

    @Autowired
    SanPhamChiTietService sanPhamChiTietService;

    @Autowired
    SanPhamService sanPhamService;

    @Autowired
    MauSacRepository productColorRepository;

    @Autowired
    KichCoRepository productSizeRepository;

    @Autowired
    SanPhamChiTietRepository sanPhamChiTietRepository;

    @Autowired
    HinhAnhRepository hinhAnhRepository;


    Long spct_id;

    //Hien thi
    @GetMapping("/danhSach")
    public ResponseEntity<List<SanPhamChiTiet>> getColor() {
        List<SanPhamChiTiet> list = productDetailsRepository.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/dsCTSP")
    public ResponseEntity<List<SanPhamChiTiet>> getProduct(@RequestParam("san_pham_id") Long id) {
        List<SanPhamChiTiet> product = sanPhamChiTietRepository.findSpctByIdSp(id);
        return ResponseEntity.ok().body(product);
    }

    @GetMapping("/themAnh/{id}")
    public ResponseEntity<?> getHinhAnh(@PathVariable("id") long id) {
        List<SanPhamChiTiet> product = sanPhamChiTietRepository.findByProductID(id);
        return ResponseEntity.ok().body(product);
    }

    //Hien thi theo id
    @GetMapping("/chinhSua/{id}")
    public SanPhamChiTiet editProductDetails(@PathVariable("id") Long id){
        return productDetailsRepository.findByID(id);
    }

    //Sửa và lưu
    @PutMapping("/luuChinhSua")
    public ResponseEntity<SanPhamChiTiet> saveUpdate(@RequestBody SanPhamChiTietDTO sanPhamChiTiet){
        return sanPhamChiTietService.saveEdit(sanPhamChiTiet);
    }

    //Xóa
    @DeleteMapping("/xoa/{id}")
    public ResponseEntity<List<SanPhamChiTiet>> deleteProductDetails(@PathVariable("id") Long id){
        return sanPhamChiTietService.deleteProductDetails(id);
    }

    @PostMapping("/themMoi")
    public SanPhamChiTiet saveCreate(@RequestBody HoaDonChiTietDTO detailsDTO){
        KichCo size = productSizeRepository.findByID(detailsDTO.getIdSize());
        SanPhamChiTiet sanPhamChiTiet = new SanPhamChiTiet();
        sanPhamChiTiet.setKichCo(size);
        sanPhamChiTiet.setSoLuong(detailsDTO.getQuantity());
        productDetailsRepository.save(sanPhamChiTiet);
        return sanPhamChiTiet;
    }

    //Tìm kiếm
    @RequestMapping("/timKiem={search}")
    public List<SanPhamChiTiet> searchAll(@PathVariable("search") String search){
        return sanPhamChiTietService.searchAllProductDetails(search);
    }

    @RequestMapping("/timKiemNgay={searchDate}")
    public List<SanPhamChiTiet> searchDate(@PathVariable("searchDate") String search){
        return sanPhamChiTietService.searchDateProductDetails(search);
    }

    @GetMapping("/get/SanPhamChiTiet")
    public SanPhamChiTiet getSPCT(@RequestParam long id_SPCT) {
        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietRepository.findByID(id_SPCT);
        return sanPhamChiTiet;
    }

    @PutMapping("/ChinhSuaSoLuongSPCT")
    public ResponseEntity chinhSuaSoLuongSPCT(@RequestBody SanPhamChiTiet sanPhamChiTiet) {
        return sanPhamChiTietService.chinhSuaSoLuongSPCT(sanPhamChiTiet);
    }

    @PostMapping("/themAnhSanPham")
    public ResponseEntity<?> themAnhSanPham(@RequestBody HinhAnhDTO hinhAnhDTO) {
        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietRepository.findByID(hinhAnhDTO.getId_spct());
        spct_id = sanPhamChiTiet.getId();
        HinhAnh hinhAnh = null;
        for(String tenAnh: hinhAnhDTO.getTen_anh()){
            hinhAnh = new HinhAnh();
            hinhAnh.setSanPhamChiTiet(sanPhamChiTiet);
            hinhAnh.setTenAnh(tenAnh);
            hinhAnh.setMauSac(sanPhamChiTiet.getMauSac());
            hinhAnh.setAnhMacDinh(false);
            hinhAnhRepository.save(hinhAnh);
        }
        Map<String, Object> response = new HashMap<>();
        response.put("mess", "Them anh thanh cong");
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/hienThiAnh")
    public ResponseEntity<?> hienThiAnh(){
        List<HinhAnh> hinhAnhs = hinhAnhRepository.findByIDProduct(spct_id);
        return ResponseEntity.ok().body(hinhAnhs);
    }

    @GetMapping("/getdefaultimage")
    public HinhAnh getDefaultImageByIdProduct(@RequestParam("idsp")Long idsp){
        return hinhAnhRepository.getFirstAnhByIdSanPham(idsp);
    }

    @PutMapping("/setAnhMacDinh")
    public ResponseEntity<?> setAnhMacDinh(@RequestBody HinhAnhDTO hinhAnhDTO) {
        HinhAnh check = hinhAnhRepository.findAnhMacDinh(hinhAnhDTO.getId_spct());
        if(check != null){
            check.setAnhMacDinh(false);
            hinhAnhRepository.save(check);
            HinhAnh hinhAnh = hinhAnhRepository.findByID(hinhAnhDTO.getId_hinh_anh());
            hinhAnh.setAnhMacDinh(true);
            hinhAnhRepository.save(hinhAnh);
            Map<String, Object> response = new HashMap<>();
            response.put("mess", "set anh mac dinh thanh cong");
            return ResponseEntity.ok().body(response);
        }else {
            HinhAnh hinhAnh = hinhAnhRepository.findByID(hinhAnhDTO.getId_hinh_anh());
            hinhAnh.setAnhMacDinh(true);
            hinhAnhRepository.save(hinhAnh);
            Map<String, Object> response = new HashMap<>();
            response.put("mess", "set anh mac dinh thanh cong");
            return ResponseEntity.ok().body(response);
        }
    }

    @Transactional
    @PutMapping("/xoaAnh")
    public ResponseEntity<?> xoaAnh(@RequestBody HinhAnhDTO hinhAnhDTO) {
         hinhAnhRepository.xoaAnh(hinhAnhDTO.getId_hinh_anh());
        List<HinhAnh> hinhAnhs = hinhAnhRepository.findByIDProduct(spct_id);
        return ResponseEntity.ok().body(hinhAnhs);
    }
}
