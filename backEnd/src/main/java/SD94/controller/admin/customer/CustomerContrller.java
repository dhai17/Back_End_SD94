package SD94.controller.admin.customer;

import SD94.entity.Customer;
import SD94.repository.CustomerRepository;
import SD94.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @PostMapping(value = "/api/customer/createCustomer")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customerCreate){
        return customerService.createCustomer(customerCreate);
    }

    @PutMapping("/api/customer/deleteCustomer={id}")
    public ResponseEntity<List<Customer>> deleteCustomer(@PathVariable("id") Long id){
        return customerService.deleteCustomer(id);
    }

    @GetMapping("/api/customer/edit/customerID={id}")
    public Customer customerEdit(@PathVariable("id") Long id){
        return customerRepository.findByID(id);
    }

    @PutMapping("/api/customer/saveUpdate")
    public ResponseEntity<Customer> saveUpdate(@RequestBody Customer customerUpdate){
        return customerService.editCustomer(customerUpdate);
    }

    @RequestMapping("/api/customer/search={search}")
    public List<Customer> searchAllCustomer(@PathVariable("search") String search){
        return customerService.searchAllCustomer(search);
    }

    @RequestMapping("/api/customer/searchDate={searchDate}")
    public List<Customer> searchDateCustomer(@PathVariable("searchDate") String searchDate){
        return customerService.searchDateCustomer(searchDate);
    }
}
