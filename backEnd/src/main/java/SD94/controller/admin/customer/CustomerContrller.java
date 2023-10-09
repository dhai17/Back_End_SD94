package SD94.controller.admin.customer;

import SD94.entity.Customer;
import SD94.repository.CustomerRepository;
import SD94.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerContrller {
    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/api/customer/list")
    public List<Customer> listCustomer(){
        return customerService.findAllCustomer();
    }

    @PutMapping("/api/customer/deleteCustomer={id}")
    public ResponseEntity<List<Customer>> deleteCustomer(@PathVariable("id") Long id){
        return customerService.deleteCustomer(id);
    }
}
