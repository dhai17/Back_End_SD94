package SD94.service.service;

import SD94.entity.product.Manufacturer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProducerService {

    List<Manufacturer> findAllProducer();

    ResponseEntity<Manufacturer> saveEdit(Manufacturer manufacturerUpdate);

    ResponseEntity<List<Manufacturer>> deleteProducer(Long id);

    ResponseEntity<Manufacturer> saveCreate(Manufacturer manufacturerCreate);

    List<Manufacturer> searchAllProducer(String search);

    List<Manufacturer> searchDateProducer(String searchDate);

}
