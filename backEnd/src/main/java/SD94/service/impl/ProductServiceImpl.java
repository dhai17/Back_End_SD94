package SD94.service.impl;

import SD94.controller.message.Message;
import SD94.entity.product.Material;
import SD94.entity.product.Manufacturer;
import SD94.entity.product.Product;
import SD94.entity.product.TypeProduct;
import SD94.repository.ProducerRepository;
import SD94.repository.ProductLineRepository;
import SD94.repository.ProductMaterialRepository;
import SD94.repository.ProductRepository;
import SD94.service.service.ProductService;
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
        try {
            Optional<Product> optional = repository.findById(productUpdate.getId());
            if (optional.isPresent()){
                Product product = optional.get();
                product.setName(productUpdate.getName());
                product.setPrice(productUpdate.getPrice());
                product.setOrigin(productUpdate.getOrigin());
                product.setStatus(productUpdate.getStatus());
                product.setManufacturer(productUpdate.getManufacturer());
                product.setTypeProduct(productUpdate.getTypeProduct());
                product.setMaterial(productUpdate.getMaterial());
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

    @Override
    public ResponseEntity<Product> saveCreate(Product productCreate) {
        System.out.println(productCreate);
        try {
            if (productCreate.getMaterial() != null) {
                Optional<Material> optionalProductMaterial = productMaterialRepository.findById(productCreate.getMaterial().getId());
                if (optionalProductMaterial.isPresent()) {
                    Material material = optionalProductMaterial.get();
                    productCreate.setMaterial(material);
                } else {
                    return new ResponseEntity(new Message("Invalid Product Material", TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
                }
            }

            if (productCreate.getTypeProduct() != null) {
                Optional<TypeProduct> optionalProductLine = productLineRepository.findById(productCreate.getTypeProduct().getId());
                if (optionalProductLine.isPresent()) {
                    TypeProduct line = optionalProductLine.get();
                    productCreate.setTypeProduct(line);
                } else {
                    return new ResponseEntity(new Message("Invalid Product Line", TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
                }
            }

            if (productCreate.getManufacturer() != null) {
                Optional<Manufacturer> optionalProducer = producerRepository.findById(productCreate.getManufacturer().getId());
                if (optionalProducer.isPresent()) {
                    Manufacturer manufacturer = optionalProducer.get();
                    productCreate.setManufacturer(manufacturer);
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
