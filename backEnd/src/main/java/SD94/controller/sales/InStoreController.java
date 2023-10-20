package SD94.controller.sales;

import SD94.entity.*;
import SD94.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class InStoreController {
    @Autowired
    ProductColorRepository productColorRepository;

    @Autowired
    ProductSizeRepository productSizeRepository;

    @Autowired
    ProductDetailsRepository productDetailsRepository;

    @Autowired
    BillRepository billRepository;

    @Autowired
    BillDetailsRepository billDetailsRepository;

    @RequestMapping("/api/getSize")
    public ResponseEntity<List<String>> getSize(@RequestParam("product_id") String id) {
        Long id_product = Long.valueOf(id);
        List<String> productSizes = productSizeRepository.getSize(id_product);
        return ResponseEntity.ok().body(productSizes);
    }

    @RequestMapping("/api/getColor")
    public ResponseEntity<List<String>> getColor(@RequestParam("product_id") String id) {
        Long id_product = Long.valueOf(id);
        List<String> productColor = productColorRepository.getColor(id_product);
        String a[];
        return ResponseEntity.ok().body(productColor);
    }

    @RequestMapping("/api/getProduct")
    public ResponseEntity<List<String>> getProduct(@RequestParam("product_id") String id) {
        Long id_product = Long.valueOf(id);
        List<String> product = productDetailsRepository.getProduct(id_product);
        return ResponseEntity.ok().body(product);
    }

    @RequestMapping("/new-bill")
    public String newBill(){
        Bill bill = new Bill();
        bill.setCode("HD034");
        bill.setCreatedby("hduong");
        bill.setCreatedDate(new Date());
        billRepository.save(bill);
        return "create bill done";
    }

    @RequestMapping("/cancel")
    public String huyDon(@RequestParam("id_bill") long id_bill) {
        Optional<Bill> optionalBill = billRepository.findById(1L);
        if (optionalBill.isPresent()) {
            Bill bill = optionalBill.get();
            StatusBill statusBill = new StatusBill();
            statusBill.setId(8L);
            bill.setStatus(statusBill);
            billRepository.save(bill);
        }

        return "Payment bill done";
    }

    @RequestMapping("/select-product-details")
    public String productDetails(@RequestParam("id_product") long id_product,
                                 @RequestParam("id_color") long id_color,
                                 @RequestParam("id_size") long id_size) {
        ProductDetails productDetails = productDetailsRepository.findByColorAndSize(id_product, id_color, id_size);
        Optional<Bill> optionalBill = billRepository.findById(1L);
        if (optionalBill.isPresent()) {
            Bill bill = optionalBill.get();
            BillDetails bill_details = new BillDetails();
            bill_details.setQuantity(2);
            bill_details.setUnitPrice(100);
            bill_details.setTotalPayment(200);
            bill_details.setBill(bill);
            bill_details.setProductDetails(productDetails);
            billDetailsRepository.save(bill_details);
        }
        return "save product to bill done";
    }

    @RequestMapping("/payment")
    public String thanhtoanHoaDonTaiQuay(@RequestParam("id_bill") long id_bill) {
        Optional<Bill> optionalBill = billRepository.findById(id_bill);
        if (optionalBill.isPresent()) {
            Bill bill = optionalBill.get();
            StatusBill statusBill = new StatusBill();
            statusBill.setId(7L);
            bill.setStatus(statusBill);
            billRepository.save(bill);
        }

        return "Payment bill done";
    }

    @PostMapping("/xoa-san-pham")
    public String xoaSanPham(@RequestParam("id_bill") long id_bill,
                             @RequestParam("id_bill_details") long id_bill_details){
        Optional<BillDetails> detailedInvoice = billDetailsRepository.findById(id_bill_details);
        Optional<Bill> optionalBill = billRepository.findById(id_bill);
        if(detailedInvoice.isPresent() && optionalBill.isPresent()){
            BillDetails details = detailedInvoice.get();
            Bill bill = optionalBill.get();

            details.setDeleted(true);
            billDetailsRepository.save(details);

            int tongTien = details.getTotalPayment();
            int tongTienBill = bill.getTotalOrderPrice();
            int capNhatTongTien = tongTienBill - tongTien;
            bill.setTotalOrderPrice(capNhatTongTien);

            long id_product_details = details.getProductDetails().getId();
            ProductDetails productDetails = productDetailsRepository.findByID(id_product_details);
            int soLuong = details.getQuantity();
            int soLuongBanDau = productDetails.getQuantity();
            productDetails.setQuantity(soLuongBanDau + soLuong);
            productDetailsRepository.save(productDetails);
        }
        return "deleted san pham thanh cong";
    }
}
