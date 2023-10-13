package SD94.service.serviceImplement;

import SD94.controller.Message.Message;
import SD94.entity.Producer;
import SD94.entity.Product;
import SD94.entity.ProductLine;
import SD94.entity.ProductMaterial;
import SD94.repository.ProducerRepository;
import SD94.repository.ProductLineRepository;
import SD94.repository.ProductMaterialRepository;
import SD94.repository.ProductRepository;
import SD94.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository repository;

    @Autowired
    ProductMaterialRepository productMaterialRepository;

    @Autowired
    ProductLineRepository productLineRepository;

    @Autowired
    ProducerRepository producerRepository;


    @Override
    public List<Product> findAllProduct() {
        List<Product> list = repository.findAll();
        return list;
    }

    @Override
    public ResponseEntity<Product> saveEdit(Product productUpdate) {
        String errorMessage;
        Message errorResponse;

        if (productUpdate.getName() == "" || productUpdate.getPrice() == null || productUpdate.getOrigin() == null) {
            errorMessage = "Nhập đầy đủ thông tin";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }

        try {
            Optional<Product> optional = repository.findById(productUpdate.getId());
            if (optional.isPresent()){
                Product product = optional.get();
                product.setName(productUpdate.getName());
                product.setPrice(productUpdate.getPrice());
                product.setOrigin(productUpdate.getOrigin());
                product.setStatus(productUpdate.getStatus());
                product.setProducer(productUpdate.getProducer());
                product.setProductLine(productUpdate.getProductLine());
                product.setProductMaterial(productUpdate.getProductMaterial());
                repository.save(product);
                return ResponseEntity.ok(product);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e){
            return new ResponseEntity(new Message(e.getMessage(), TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<Product>> deleteProduct(Long id) {
        try {
            Optional<Product> optional = repository.findById(id);
            if (optional.isPresent()){
                Product product = optional.get();
                product.setDeleted(true);
                repository.save(product);

                List<Product> list = findAllProduct();
                return ResponseEntity.ok(list);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

//    @Override
//    public ResponseEntity<Product> saveCreate(Product productCreate) {
////        String errorMessage;
////        Message errorResponse;
//
//        try {
//            Optional<ProductMaterial> optionalProductMaterial = productMaterialRepository.findById(productCreate.getId());
//            if(optionalProductMaterial.isPresent()){
//                ProductMaterial material = optionalProductMaterial.get();
//                Product product = new Product();
//                product.setProductMaterial(material);
//                repository.save(product);
//            }
//            Optional<ProductLine> optionalProductLine = productLineRepository.findById(productCreate.getId());
//            if(optionalProductLine.isPresent()){
//                ProductLine line = optionalProductLine.get();
//                Product product = new Product();
//                product.setProductLine(line);
//                repository.save(product);
//            }
//            Optional<Producer> optionalProducer = producerRepository.findById(productCreate.getId());
//            if(optionalProducer.isPresent()){
//                Producer producer = optionalProducer.get();
//                Product product = new Product();
//                product.setProducer(producer);
//                repository.save(product);
//            }
//            Product product = new Product();
//            product.setProductMaterial(productCreate.getProductMaterial());
//            product.setProductLine(productCreate.getProductLine());
//            product.setProducer(productCreate.getProducer());
//            product.setName(productCreate.getName());
//            product.setPrice(productCreate.getPrice());
//            product.setOrigin(productCreate.getOrigin());
//            product.setStatus(productCreate.getStatus());
//            repository.save(product);
//            return ResponseEntity.ok(product);
//        } catch (Exception e){
//            return new ResponseEntity(new Message(e.getMessage(), TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
//        }
//    }

    @Override
    public ResponseEntity<Product> saveCreate(Product productCreate) {
        System.out.println(productCreate);
        try {
            if (productCreate.getProductMaterial() != null) {
                Optional<ProductMaterial> optionalProductMaterial = productMaterialRepository.findById(productCreate.getProductMaterial().getId());
                if (optionalProductMaterial.isPresent()) {
                    ProductMaterial material = optionalProductMaterial.get();
                    productCreate.setProductMaterial(material);
                } else {
                    return new ResponseEntity(new Message("Invalid Product Material", TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
                }
            }

            if (productCreate.getProductLine() != null) {
                Optional<ProductLine> optionalProductLine = productLineRepository.findById(productCreate.getProductLine().getId());
                if (optionalProductLine.isPresent()) {
                    ProductLine line = optionalProductLine.get();
                    productCreate.setProductLine(line);
                } else {
                    return new ResponseEntity(new Message("Invalid Product Line", TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
                }
            }

            if (productCreate.getProducer() != null) {
                Optional<Producer> optionalProducer = producerRepository.findById(productCreate.getProducer().getId());
                if (optionalProducer.isPresent()) {
                    Producer producer = optionalProducer.get();
                    productCreate.setProducer(producer);
                } else {
                    return new ResponseEntity(new Message("Invalid Producer", TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
                }
            }

            repository.save(productCreate);
            return ResponseEntity.ok(productCreate);
        } catch (Exception e) {
            return new ResponseEntity(new Message(e.getMessage(), TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<Product> searchAllProduct(String search) {
        List<Product> list = repository.findByAll(search);
        System.out.println("Search ...");
        return list;
    }

    @Override
    public List<Product> searchDateProduct(String searchDate) {
        LocalDate search = LocalDate.parse(searchDate);
        List<Product> list = repository.findByDate(search);
        return list;
    }
}
