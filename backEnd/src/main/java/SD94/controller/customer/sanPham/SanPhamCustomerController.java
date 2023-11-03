package SD94.controller.customer.sanPham;

import SD94.entity.sanPham.SanPham;
import SD94.repository.sanPham.SanPhamChiTietRepository;
import SD94.repository.sanPham.SanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer/sanPham")
public class SanPhamCustomerController {

    @Autowired
    SanPhamRepository sanPhamRepository;

    @Autowired
    SanPhamChiTietRepository sanPhamChiTietRepository;

    //Lay danh sach
    @GetMapping("/danhSach")
    public List<SanPham> getSanPhamCustomer(){
        List<SanPham> SanPham = sanPhamRepository.findAll();
        return SanPham;
    }

    //Loc san pham
    @GetMapping("/loc/gia")
    public List<SanPham> getSanPhamTheoGia(Float gia1, Float gia2){
        List<SanPham> sanPhams = sanPhamRepository.findTheoGia(gia1, gia2);
        return sanPhams;
    }

    @GetMapping("/loc/loai_san_pham")
    public List<SanPham> getSanPhamTheoLoaiSanPham(@RequestParam long id_loai_san_pham){
        List<SanPham> sanPhams = sanPhamRepository.findByLoaiSanPham(id_loai_san_pham);
        return sanPhams;
    }

    @GetMapping("/loc/chat_lieu")
    public List<SanPham> getSanPhamTheoChatLieu(@RequestParam long id_chat_lieu){
        List<SanPham> sanPhams = sanPhamRepository.findByChatLieu(id_chat_lieu);
        return sanPhams;
    }

    @GetMapping("/loc/nha_san_xuat")
    public List<SanPham> getSanPhamTheoNSX(@RequestParam long id_nsx){
        List<SanPham> sanPhams = sanPhamRepository.findByNSX(id_nsx);
        return sanPhams;
    }

    //Chuc nang
    //Select san pham chi tiet theo san pham
    @GetMapping("/sanPhamChiTiet/getKichCoVaMauSac")
    public List<String> getKichCoVaMauSac(@RequestParam long id_sanPham){
        List<String> list = sanPhamChiTietRepository.getProduct(id_sanPham);
        return list;
    }

//    @GetMapping("/getAnhMacDinh")


}
