package SD94.controller.admin.customer;

import SD94.entity.khachHang.DiaChi;
import SD94.entity.khachHang.KhachHang;
import SD94.repository.AddressRepository;
import SD94.repository.CustomerRepository;
import SD94.service.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class CustomerController {
    @Autowired
    KhachHangService khachHangService;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AddressRepository addressRepository;

    @GetMapping("/api/customer/list")
    public List<KhachHang> listCustomer(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String username = authentication .getName();
//        System.out.println(username);
//        Staff staff = staffRepository.findByEmail(username);
//        System.out.println(staff + "----------------------------------");
        return khachHangService.findAllCustomer();
    }

    @PostMapping(value = "/api/customer/createCustomer")
    public ResponseEntity<KhachHang> createCustomer(@RequestBody KhachHang khachHangCreate){
        return khachHangService.createCustomer(khachHangCreate);
    }

    @PutMapping("/api/customer/deleteCustomer={id}")
    public ResponseEntity<List<KhachHang>> deleteCustomer(@PathVariable("id") Long id){
        return khachHangService.deleteCustomer(id);
    }

    @GetMapping("/api/customer/edit/customerID={id}")
    public KhachHang customerEdit(@PathVariable("id") Long id){
        return customerRepository.findByID(id);
    }

    @PutMapping("/api/customer/saveUpdate")
    public ResponseEntity<KhachHang> saveUpdate(@RequestBody KhachHang khachHangUpdate){
        return khachHangService.editCustomer(khachHangUpdate);
    }

    @RequestMapping("/api/customer/search={search}")
    public List<KhachHang> searchAllCustomer(@PathVariable("search") String search){
        return khachHangService.searchAllCustomer(search);
    }

    @RequestMapping("/api/customer/searchDate={searchDate}")
    public List<KhachHang> searchDateCustomer(@PathVariable("searchDate") String searchDate){
        return khachHangService.searchDateCustomer(searchDate);
    }

    @RequestMapping("/api/customer/add-address")
    public String  addAddress(@RequestParam("address") String address,
                              @RequestParam("id_customer") long id_customer){
        Optional<KhachHang> optionalCustomer = customerRepository.findById(id_customer);
        if(optionalCustomer.isPresent()){
            KhachHang khachHang = optionalCustomer.get();
            DiaChi diaChi = new DiaChi();
            diaChi.setKhachHang(khachHang);
            diaChi.setDiaChi(address);
            diaChi.setDiaChiMacDinh(false);
            addressRepository.save(diaChi);
        }
        return "add address done";
    }

    @PostMapping("/api/customer/address-true")
    public String setAddress(@RequestParam("id_address") long id_address,
                             @RequestParam("id_customer") long id_customer){
        List<DiaChi> diaChis = addressRepository.findByCustomerID(id_customer);
        for (DiaChi diaChi : diaChis){
            diaChi.setDiaChiMacDinh(false);
            addressRepository.save(diaChi);
        }

        DiaChi diaChi = addressRepository.findbyCustomerAndID(id_customer, id_address);
        diaChi.setDiaChiMacDinh(true);
        addressRepository.save(diaChi);
        return "true";
    }
}
