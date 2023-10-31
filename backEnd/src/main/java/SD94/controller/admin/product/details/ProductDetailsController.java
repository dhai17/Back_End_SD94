package SD94.controller.admin.product.details;

import SD94.dto.DetailsDTO;
import SD94.entity.sanPham.KichCo;
import SD94.entity.sanPham.SanPhamChiTiet;
import SD94.repository.ProductColorRepository;
import SD94.repository.ProductDetailsRepository;
import SD94.repository.sanPham.KichCoRepository;
import SD94.service.service.SanPhamChiTietService;
import SD94.service.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/productDetails/")
public class ProductDetailsController {

    @Autowired
    ProductDetailsRepository productDetailsRepository;

    @Autowired
    SanPhamChiTietService sanPhamChiTietService;

    @Autowired
    SanPhamService sanPhamService;

    @Autowired
    ProductColorRepository productColorRepository;

    @Autowired
    KichCoRepository productSizeRepository;

    //Hien thi
    @GetMapping("list")
    public ResponseEntity<List<SanPhamChiTiet>> getColor() {
        List<SanPhamChiTiet> list = productDetailsRepository.findAll();
        return ResponseEntity.ok().body(list);
    }

    //Hien thi theo id
    @GetMapping("edit/prodtuctDetailsID={id}")
    public SanPhamChiTiet editProductDetails(@PathVariable("id") Long id){
        return productDetailsRepository.findByID(id);
    }

    //Sửa và lưu
    @PutMapping("saveUpdate")
    public ResponseEntity<SanPhamChiTiet> saveUpdate(@RequestBody SanPhamChiTiet sanPhamChiTiet){
        return sanPhamChiTietService.saveEdit(sanPhamChiTiet);
    }

    //Xóa
    @DeleteMapping("delete/{id}")
    public ResponseEntity<List<SanPhamChiTiet>> deleteProductDetails(@PathVariable("id") Long id){
//        System.out.println(id);
        return sanPhamChiTietService.deleteProductDetails(id);
    }

    //Thêm sp
//    @PostMapping("saveCreate/{color_id}")
//    public ResponseEntity<ProductDetails> saveCreate(@PathVariable("color_id") long idColor){
//        Optional<ProductColor> optionalProductColor = productColorRepository.findById(idColor);
//        if(optionalProductColor.isPresent()){
//            ProductColor color = optionalProductColor.get();
//            ProductDetails productDetails = new ProductDetails();
//            productDetails.setProductColor(color);
//            productDetailsRepository.save(productDetails);
//        }
////        return productDetailsService.saveCreate(productDetails);
//        return null;
//    }

    @PostMapping("saveCreate")
    public ResponseEntity<SanPhamChiTiet> saveCreate(@RequestBody DetailsDTO detailsDTO){
//        System.out.println(detailsDTO);
        KichCo size = productSizeRepository.findByID(detailsDTO.getIdSize());
//        System.out.println(productSize);
        SanPhamChiTiet sanPhamChiTiet = new SanPhamChiTiet();
        sanPhamChiTiet.setKichCo(size);
        sanPhamChiTiet.setSoLuong(detailsDTO.getQuantity());
        productDetailsRepository.save(sanPhamChiTiet);
        return null;
    }

    //Tìm kiếm
    @RequestMapping("search={search}")
    public List<SanPhamChiTiet> searchAll(@PathVariable("search") String search){
        return sanPhamChiTietService.searchAllProductDetails(search);
    }

    @RequestMapping("searchDate={searchDate}")
    public List<SanPhamChiTiet> searchDate(@PathVariable("searchDate") String search){
        return sanPhamChiTietService.searchDateProductDetails(search);
    }

}
