package SD94.controller;

import SD94.entity.Producer;
import SD94.entity.Product;
import SD94.entity.ProductColor;
import SD94.entity.ProductDetails;
import SD94.entity.ProductLine;
import SD94.entity.ProductMaterial;
import SD94.entity.ProductSize;
import SD94.repository.ProducerRepository;
import SD94.repository.ProductColorRepository;
import SD94.repository.ProductDetailsRepository;
import SD94.repository.ProductLineRepository;
import SD94.repository.ProductMaterialRepository;
import SD94.repository.ProductRepository;
import SD94.repository.ProductSizeRepository;
import SD94.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/product/")
public class ProductController {

    @Autowired
    ProductRepository repository;

    @Autowired
    ProductService productService;

    @Autowired
    ProductMaterialRepository productMaterialRepository;

    @Autowired
    ProductLineRepository productLineRepository;

    @Autowired
    ProducerRepository producerRepository;

    @Autowired
    ProductColorRepository productColorRepository;

    @Autowired
    ProductSizeRepository productSizeRepository;

    @Autowired
    ProductDetailsRepository productDetailsRepository;


    @GetMapping("list")
    public ResponseEntity<List<Product>> getProduct() {
        List<Product> list = repository.findAll();
        return ResponseEntity.ok().body(list);
    }


    @PostMapping("saveCreate")
    public ResponseEntity<List<ProductDetails>> saveCreate(@RequestBody Map<String, Object> body) {
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
        Integer statusF = Integer.valueOf(status);

        System.out.println(color);

        Optional<ProductMaterial> optionalProductMaterial = productMaterialRepository.findById(id_metarial);
        Optional<ProductLine> optionalProductLine = productLineRepository.findById(id_line);
        Optional<Producer> optionalProducer = producerRepository.findById(id_producer);

        if (optionalProductMaterial.isPresent() && optionalProductLine.isPresent() && optionalProducer.isPresent()) {
            ProductMaterial productMaterial = optionalProductMaterial.get();
            ProductLine productLine = optionalProductLine.get();
            Producer producerr = optionalProducer.get();

            Product product = new Product();
            product.setName(name);
            product.setPrice(priceFomat);
            product.setOrigin(origin);
            product.setProductLine(productLine);
            product.setProducer(producerr);
            product.setProductMaterial(productMaterial);
            repository.save(product);

            List<ProductDetails> productDetailsList = new ArrayList<>();
            for (Object idSize : size) {
                Long id_size = Long.valueOf(String.valueOf(idSize));

                Optional<ProductSize> optionalProductSize = productSizeRepository.findById(id_size);
                for (Object id_colors : color) {
                    Long id_color = Long.valueOf(String.valueOf(id_colors));
                    Optional<ProductColor> optionalProductColor = productColorRepository.findById(id_color);
                    if (optionalProductSize.isPresent() && optionalProductColor.isPresent()) {
                        ProductSize productSize = optionalProductSize.get();
                        ProductColor productColor = optionalProductColor.get();
                        ProductDetails productDetails = new ProductDetails();
                        productDetails.setProduct(product);
                        productDetails.setProductColor(productColor);
                        productDetails.setProductSize(productSize);
                        productDetails.setQuantity(quantityF);
                        productDetails.setStatus(statusF);
                        productDetailsRepository.save(productDetails);
                        productDetailsList.add(productDetails);
                    }
                }
            }
            return ResponseEntity.ok().body(productDetailsList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("deleteProduct/{id}")
    public ResponseEntity<List<Product>> deleteProduct(@PathVariable("id") Long id) {
        return productService.deleteProduct(id);
    }

    @GetMapping("edit/{id}")
    public Product editProduct(@PathVariable("id") Long id) {
        return repository.findByID(id);
    }

    @RequestMapping("search={search}")
    public List<Product> searchAll(@PathVariable("search") String search) {
        return productService.searchAllProduct(search);
    }

    @RequestMapping("searchDate={searchDate}")
    public List<Product> searchDate(@PathVariable("searchDate") String search) {
        return productService.searchDateProduct(search);
    }

    @RequestMapping("/select-product-details")
    public ResponseEntity<ProductDetails> productDetails(@RequestParam("id_product") long id_product,
                                                         @RequestParam("id_color") long id_color,
                                                         @RequestParam("id_size") long id_size) {
        System.out.println(id_product + id_color + id_size);
        ProductDetails productDetails = productDetailsRepository.findByColorAndSize(id_product, id_color, id_size);
        return ResponseEntity.ok().body(productDetails);
    }
}
