package SD94.controller.customer.sanPham;

import SD94.dto.KichCoDTO;
import SD94.dto.MauSacDTO;
import SD94.dto.SPCTDTO;
import SD94.entity.sanPham.KichCo;
import SD94.entity.sanPham.MauSac;
import SD94.entity.sanPham.SanPham;
import SD94.repository.sanPham.KichCoRepository;
import SD94.repository.sanPham.MauSacRepository;
import SD94.repository.sanPham.SanPhamChiTietRepository;
import SD94.repository.sanPham.SanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer/sanPham")
public class SanPhamCustomerController {

    @Autowired
    SanPhamRepository sanPhamRepository;

    @Autowired
    SanPhamChiTietRepository sanPhamChiTietRepository;

    @Autowired
    KichCoRepository kichCoRepository;

    @Autowired
    MauSacRepository mauSacRepository;

    //Lay danh sach
    @GetMapping("/danhSach")
    public List<SanPham> getSanPhamCustomer() {
        List<SanPham> SanPham = sanPhamRepository.findAll();
        return SanPham;
    }

    //Loc san pham
    @GetMapping("/loc/gia")
    public List<SanPham> getSanPhamTheoGia(Float gia1, Float gia2) {
        List<SanPham> sanPhams = sanPhamRepository.findTheoGia(gia1, gia2);
        return sanPhams;
    }

    @GetMapping("/loc/loai_san_pham")
    public List<SanPham> getSanPhamTheoLoaiSanPham(@RequestParam long id_loai_san_pham) {
        List<SanPham> sanPhams = sanPhamRepository.findByLoaiSanPham(id_loai_san_pham);
        return sanPhams;
    }

    @GetMapping("/loc/chat_lieu")
    public List<SanPham> getSanPhamTheoChatLieu(@RequestParam long id_chat_lieu) {
        List<SanPham> sanPhams = sanPhamRepository.findByChatLieu(id_chat_lieu);
        return sanPhams;
    }

    @GetMapping("/loc/nha_san_xuat")
    public List<SanPham> getSanPhamTheoNSX(@RequestParam long id_nsx) {
        List<SanPham> sanPhams = sanPhamRepository.findByNSX(id_nsx);
        return sanPhams;
    }

    //Chuc nang
    //Select san pham chi tiet theo san pham
    @GetMapping("/getSanPham/id={id}")
    public SanPham getKichCoVaMauSac(@PathVariable("id") long id_sanPham) {
        SanPham sanPham = sanPhamRepository.findByID(id_sanPham);
        return sanPham;
    }

    @RequestMapping("/api/getSize/{id}")
    public ResponseEntity<List<String>> getKichCo(@PathVariable("id") String id) {
        Long id_product = Long.valueOf(id);
        List<String> productSizes = kichCoRepository.getKichCo(id_product);
        return ResponseEntity.ok().body(productSizes);
    }

    @RequestMapping("/api/getColor/{id}")
    public ResponseEntity<List<String>> getMauSac(@PathVariable("id") String id) {
        Long id_product = Long.valueOf(id);
        List<String> productColor = mauSacRepository.getColor(id_product);
        return ResponseEntity.ok().body(productColor);
    }

    @PostMapping("/api/getSoLuong")
    public ResponseEntity<Integer> getSoLuong(@RequestBody SPCTDTO dto) {
        MauSac mauSac = mauSacRepository.findByMaMauSac(dto.getMaMauSac());
        KichCo kichCo = kichCoRepository.findByKichCo(dto.getKichCo());
        Integer soLuong = sanPhamChiTietRepository.getSoLuongHienCp(mauSac.getId(), kichCo.getId(), dto.getSanPhamId());
        return ResponseEntity.ok().body(soLuong);
    }

    @GetMapping("/getAnhMacDinh")
    public String getAnhMacDinh(@RequestParam long id_sanPham) {
        String tenAnh = sanPhamChiTietRepository.getAnhMacDinh(id_sanPham);
        return tenAnh;
    }
}
