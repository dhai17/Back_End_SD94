package SD94.controller.admin.customer;

import SD94.entity.AddRess;
import SD94.entity.Customer;
import SD94.repository.AddressRepository;
import SD94.repository.CustomerRepository;
import SD94.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class CustomerContrller {
    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AddressRepository addressRepository;

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

    @RequestMapping("/api/customer/add-address")
    public String  addAddress(@RequestParam("address") String address,
                              @RequestParam("id_customer") long id_customer){
        Optional<Customer> optionalCustomer = customerRepository.findById(id_customer);
        if(optionalCustomer.isPresent()){
            Customer customer = optionalCustomer.get();
            AddRess addRess = new AddRess();
            addRess.setCustomer(customer);
            addRess.setAddRess(address);
            addRess.set_true(false);
            addressRepository.save(addRess);
        }
        return "add address done";
    }

    @PostMapping("/api/customer/address-true")
    public String setAddress(@RequestParam("id_address") long id_address,
                             @RequestParam("id_customer") long id_customer){
        List<AddRess> addResses = addressRepository.findByCustomerID(id_customer);
        for (AddRess addRess: addResses){
            addRess.set_true(false);
            addressRepository.save(addRess);
        }

        AddRess addRess = addressRepository.findbyCustomerAndID(id_customer, id_address);
        addRess.set_true(true);
        addressRepository.save(addRess);
        return "true";
    }
}
