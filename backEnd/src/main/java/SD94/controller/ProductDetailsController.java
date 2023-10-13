package SD94.controller;

import SD94.dto.DetailsDTO;
import SD94.entity.ProductDetails;
import SD94.entity.ProductSize;
import SD94.repository.ProductColorRepository;
import SD94.repository.ProductDetailsRepository;
import SD94.repository.ProductSizeRepository;
import SD94.service.ProductDetailsService;
import SD94.service.ProductService;
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
    ProductDetailsService productDetailsService;

    @Autowired
    ProductService productService;

    @Autowired
    ProductColorRepository productColorRepository;

    @Autowired
    ProductSizeRepository productSizeRepository;

    //Hien thi
    @GetMapping("list")
    public List<ProductDetails> listProductDetails(){
        return productDetailsService.findAllProductDetails();
    }

    //Hien thi theo id
    @GetMapping("edit/prodtuctDetailsID={id}")
    public ProductDetails editProductDetails(@PathVariable("id") Long id){
        return productDetailsRepository.findByID(id);
    }

    //Sửa và lưu
    @PutMapping("saveUpdate")
    public ResponseEntity<ProductDetails> saveUpdate(@RequestBody ProductDetails productDetails){
        return productDetailsService.saveEdit(productDetails);
    }

    //Xóa
    @DeleteMapping("deleteprodtuctDetails/{id}")
    public ResponseEntity<List<ProductDetails>> deleteProductDetails(@PathVariable("id") Long id){
        System.out.println(id);
        return productDetailsService.deleteProductDetails(id);
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
    public ResponseEntity<ProductDetails> saveCreate(@RequestBody DetailsDTO detailsDTO){
//        System.out.println(detailsDTO);
        ProductSize productSize = productSizeRepository.findByID(detailsDTO.getIdSize());
//        System.out.println(productSize);
        ProductDetails productDetails = new ProductDetails();
        productDetails.setProductSize(productSize);
        productDetails.setQuantity(detailsDTO.getQuantity());
        productDetailsRepository.save(productDetails);
        return null;
    }

    //Tìm kiếm
    @RequestMapping("search={search}")
    public List<ProductDetails> searchAll(@PathVariable("search") String search){
        return productDetailsService.searchAllProductDetails(search);
    }

    @RequestMapping("searchDate={searchDate}")
    public List<ProductDetails> searchDate(@PathVariable("searchDate") String search){
        return productDetailsService.searchDateProductDetails(search);
    }

}
