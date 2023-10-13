package SD94.service;

import SD94.entity.Producer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProducerService {

    List<Producer> findAllProducer();

    ResponseEntity<Producer> saveEdit(Producer producerUpdate);

    ResponseEntity<List<Producer>> deleteProducer(Long id);

    ResponseEntity<Producer> saveCreate(Producer producerCreate);

    List<Producer> searchAllProducer(String search);

    List<Producer> searchDateProducer(String searchDate);

}
