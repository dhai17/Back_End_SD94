package SD94.service.serviceImplement;

import SD94.entity.Customer;
import SD94.repository.CustomerRepository;
import SD94.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public List<Customer> findAllCustomer() {
        List<Customer> customers = customerRepository.findAllCustomer();
        return customers;
    }

    @Override
    public ResponseEntity<Customer> createCustomer(Customer customerCreate) {
        return null;
    }

    @Override
    public ResponseEntity<Customer> editCustomer(Customer customerEdit) {
        return null;
    }

    @Override
    public ResponseEntity<List<Customer>> deleteCustomer(Long id) {
        try {
            Optional<Customer> optionalCustomer = customerRepository.findById(id);
            if (optionalCustomer.isPresent()){
                Customer customer = optionalCustomer.get();
                customer.setDeleted(true);
                customerRepository.save(customer);

                List<Customer> customerList = findAllCustomer();
                return ResponseEntity.ok(customerList);
            }else {
                return ResponseEntity.notFound().build();
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Override
    public List<Customer> searchAllCustomer(String search) {
        return null;
    }

    @Override
    public List<Customer> searchDateCustomer(String searchDate) {
        return null;
    }
}
