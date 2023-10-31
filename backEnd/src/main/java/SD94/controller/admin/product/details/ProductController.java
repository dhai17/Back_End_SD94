package SD94.controller.admin.product.details;

import SD94.entity.product.*;
import SD94.repository.ProducerRepository;
import SD94.repository.ProductColorRepository;
import SD94.repository.ProductDetailsRepository;
import SD94.repository.ProductLineRepository;
import SD94.repository.ProductMaterialRepository;
import SD94.repository.ProductRepository;
import SD94.repository.ProductSizeRepository;
import SD94.service.service.ProductImagesService;
import SD94.service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin("*")
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

    @Autowired
    ProductImagesService productImagesService;


    @GetMapping("list")
    public ResponseEntity<List<Product>> getProduct() {
        List<Product> list = repository.findAll();
        return ResponseEntity.ok().body(list);
    }


    @PostMapping("saveCreate")
    public List<ProductDetails> saveCreate(@RequestBody Map<String, Object> body) {
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
//        Integer statusF = Integer.valueOf(status);

//        System.out.println(color);

        Optional<Material> optionalProductMaterial = productMaterialRepository.findById(id_metarial);
        Optional<TypeProduct> optionalProductLine = productLineRepository.findById(id_line);
        Optional<Manufacturer> optionalProducer = producerRepository.findById(id_producer);

        if (optionalProductMaterial.isPresent() && optionalProductLine.isPresent() && optionalProducer.isPresent()) {
            Material material = optionalProductMaterial.get();
            TypeProduct typeProduct = optionalProductLine.get();
            Manufacturer producerr = optionalProducer.get();

            Product product = new Product();
            product.setName(name);
            product.setPrice(priceFomat);
            product.setOrigin(origin);
            product.setStatus(0);
            product.setTypeProduct(typeProduct);
            product.setManufacturer(producerr);
            product.setMaterial(material);
            repository.save(product);

            List<ProductDetails> productDetailsList = new ArrayList<>();
            for (Object idSize : size) {
                Long id_size = Long.valueOf(String.valueOf(idSize));

                Optional<Size> optionalProductSize = productSizeRepository.findById(id_size);
                for (Object id_colors : color) {
                    Long id_color = Long.valueOf(String.valueOf(id_colors));
                    Optional<Color> optionalProductColor = productColorRepository.findById(id_color);
                    if (optionalProductSize.isPresent() && optionalProductColor.isPresent()) {
                        Size productSize = optionalProductSize.get();
                        Color productColor = optionalProductColor.get();
                        ProductDetails productDetails = new ProductDetails();
                        productDetails.setProduct(product);
                        productDetails.setColor(productColor);
                        productDetails.setSize(productSize);
                        productDetails.setQuantity(quantityF);
                        productDetailsRepository.save(productDetails);
                        productDetailsList.add(productDetails);
                    }
                }
            }
            return productDetailsList;
        } else {
            return null;
        }
    }

    @DeleteMapping("deleteProduct/{id}")
    public ResponseEntity<List<Product>> deleteProduct(@PathVariable("id") Long id) {
        return productService.deleteProduct(id);
    }

    @DeleteMapping("deleteProductDetails/{id}")
    public void deleteProductDetails(@RequestBody Map<String, Object> body) {
        Long productId = Long.valueOf((String) body.get("productId"));
        repository.deleteById(productId);
    }

    @GetMapping("edit/{id}")
    public Product editProduct(@PathVariable("id") Long id) {
        return repository.findByID(id);
    }

    @PutMapping("/saveUpdate")
    public ResponseEntity<Product> saveUpdate(@RequestBody Product product) {
        return productService.saveEdit(product);
    }

    @RequestMapping("search={search}")
    public List<Product> searchAll(@PathVariable("search") String search) {
        return productService.searchAllProduct(search);
    }

    @RequestMapping("searchDate={searchDate}")
    public List<Product> searchDate(@PathVariable("searchDate") String search) {
        return productService.searchDateProduct(search);
    }

//    @PostMapping("/{productId}/uploadImage")
//    public ResponseEntity<String> uploadImage(@PathVariable Long productId, @RequestParam("file") MultipartFile file) {
//        return ResponseEntity.ok("Image uploaded successfully.");
//    }
//
//    @PostMapping("productImg/saveCreate")
//    public ResponseEntity<ProductImages> saveCreate(@RequestBody ProductImages images,
//                                                    @RequestParam("file") MultipartFile file) throws IOException {
//        images.setImages(file.getBytes());
//        return productImagesService.saveCreate(images);
//    }
//
//    @PostMapping("/add")
//    public ResponseEntity<ProductImages> addImage(@RequestPart(value = "file", required = false) MultipartFile file,
//                                       @RequestPart("requestData") BanPhim banPhim) throws IOException {
//        banPhim.setImages(file.getBytes());
//        return ResponseEntity.ok(banPhimService.saveOrUpdate(banPhim));
//    }
}
