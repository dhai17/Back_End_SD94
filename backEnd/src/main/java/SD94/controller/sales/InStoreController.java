package SD94.controller.sales;

import SD94.entity.Bill;
import SD94.entity.ProductColor;
import SD94.entity.ProductDetails;
import SD94.entity.ProductSize;
import SD94.repository.BillRepository;
import SD94.repository.ProductColorRepository;
import SD94.repository.ProductDetailsRepository;
import SD94.repository.ProductSizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class InStoreController {
    @Autowired
    ProductColorRepository productColorRepository;

    @Autowired
    ProductSizeRepository productSizeRepository;

    @Autowired
    ProductDetailsRepository productDetailsRepository;

    @Autowired
    BillRepository billRepository;

    @RequestMapping("/api/getSize")
    public ResponseEntity<List<String>> getSize(@RequestParam("product_id") String id) {
        Long id_product = Long.valueOf(id);
        List<String> productSizes = productSizeRepository.getSize(id_product);
        return ResponseEntity.ok().body(productSizes);
    }

    @RequestMapping("/api/getColor")
    public ResponseEntity<List<String>> getColor(@RequestParam("product_id") String id) {
        Long id_product = Long.valueOf(id);
        List<String> productColor = productColorRepository.getColor(id_product);
        String a[];
        return ResponseEntity.ok().body(productColor);
    }

    @RequestMapping("/api/getProduct")
    public ResponseEntity<List<String>> getProduct(@RequestParam("product_id") String id) {
        Long id_product = Long.valueOf(id);
        List<String> product = productDetailsRepository.getProduct(id_product);
        return ResponseEntity.ok().body(product);
    }

    @RequestMapping("/select-product-details")
    public ResponseEntity<ProductDetails> productDetails(@RequestParam("id_product") long id_product,
                                                         @RequestParam("id_color") long id_color,
                                                         @RequestParam("id_size") long id_size) {
        ProductDetails productDetails = productDetailsRepository.findByColorAndSize(id_product, id_color, id_size);
        Optional<Bill> optionalBill = billRepository.findById(1L);
        if(optionalBill.isPresent()){
            Bill bill = optionalBill.get();

        }
        return ResponseEntity.ok().body(productDetails);
    }
}
