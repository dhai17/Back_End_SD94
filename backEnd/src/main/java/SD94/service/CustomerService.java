package SD94.service;

import SD94.entity.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface CustomerService {
    List<Customer> findAllCustomer();
    
    ResponseEntity<Customer> createCustomer(Customer customerCreate);
    ResponseEntity<Customer> editCustomer(Customer customerEdit);
    ResponseEntity<List<Customer>> deleteCustomer(Long id);
    List<Customer> searchAllCustomer(String search);
    List<Customer> searchDateCustomer(String searchDate);


}
