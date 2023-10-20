package SD94.controller.banHang.banHangOnline;

import SD94.entity.*;
import SD94.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/banHang/online")
public class BanHangOnlineController {
    @Autowired
    CartDetailsRepository cartDetailsRepository;

    @Autowired
    BillDetailsRepository billDetailsRepository;

    @Autowired
    BillRepository billRepository;

    @Autowired
    ProductDetailsRepository productDetailsRepository;

    @Autowired
    DiscountRepository discountRepository;

    private Long idBill;

    @PostMapping("/datHang")
    public ResponseEntity<Long> checkout(@RequestParam("id_cart_details") long[] id_cart_details,
                                         @RequestParam("total") int total) {
        Bill bill = new Bill();
        bill.setCreatedDate(new Date());
        bill.setCreatedby("abc");
        billRepository.save(bill);

        for (long id : id_cart_details) {
            Optional<DetailedShoppingCart> optionalcart = cartDetailsRepository.findById(id);
            if (optionalcart.isPresent()) {
                DetailedShoppingCart cartDetails = optionalcart.get();
                BillDetails billDetails = new BillDetails();
                billDetails.setProductDetails(cartDetails.getProductDetails());
                billDetails.setQuantity(cartDetails.getQuanTity());
                billDetails.setUnitPrice(cartDetails.getUnitPrice());
                billDetails.setTotalPayment(total);
                billDetails.setBill(bill);
                billDetailsRepository.save(billDetails);
            }
        }

        idBill = bill.getId();
        return ResponseEntity.ok(idBill);
    }

    @GetMapping("/check-out")
    public ResponseEntity<Bill> getBill() {
        if (idBill != null) {
            Optional<Bill> optionalBill = billRepository.findById(idBill);
            if (optionalBill.isPresent()) {
                Bill bill = optionalBill.get();
                return ResponseEntity.ok(bill);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/add/discount")
    public ResponseEntity<String> addDiscount(@RequestParam("id_bill") long id_bill,
                              @RequestParam("id_discount") long id_discount,
                              @RequestParam("total") int total) {
        Discount discount = discountRepository.findByID(id_discount);
        Optional<Bill> optionalBill = billRepository.findById(id_bill);
        if (optionalBill.isPresent()){
            Bill bill = optionalBill.get();
            int phanTramGiam = discount.getPercentDiscount();
            int tienGiamToiDa = discount.getMaximumvalue();
            int tongTienBill = total;

            int tongTienSauGiam = (tongTienBill * phanTramGiam)/100;
            if(tongTienSauGiam > tienGiamToiDa){
                tongTienSauGiam = tongTienBill - tienGiamToiDa;
                bill.setTotalInvoiceAmount(tongTienSauGiam);
                bill.setDiscount(discount);
                billRepository.save(bill);
                return ResponseEntity.badRequest().body(Collections.singletonList("Tien giam lon hon tien giam toi da").toString());
            }else {
                bill.setTotalInvoiceAmount(tongTienSauGiam);
                bill.setDiscount(discount);
                billRepository.save(bill);
            }
        }

        return ResponseEntity.ok().body("done");
    }

    //nút đặt hàng ship cod
    @PostMapping("/shipCode")
    public String shipCode(@RequestParam("id_bill") long id_bill,
                           @RequestParam("total") int total,
                           @RequestParam("money_ship") int money_ship,
                           @RequestParam("diaChiGiaohang") String diaChiGiaohang,
                           @RequestParam("SoDienThoaiNguoiNhan") String SoDienThoaiNguoiNhan,
                           @RequestParam("emailNguoiNhan") String emailNguoiNhan,
                           @RequestParam("ghiChu") String ghiChu) {
        Optional<Bill> optionalBill = billRepository.findById(id_bill);
        if (optionalBill.isPresent()) {
            StatusBill status = new StatusBill();
            status.setId(1L);
            Bill bill = optionalBill.get();
            bill.setAddress(diaChiGiaohang);
            bill.setEmail(emailNguoiNhan);
            bill.setNote(ghiChu);
            bill.setPhoneNumber(SoDienThoaiNguoiNhan);
            bill.setShippingMoney(money_ship);
            bill.setTotalInvoiceAmount(total);
            bill.setStatus(status);
            billRepository.save(bill);

            List<BillDetails> listBillDetails = billDetailsRepository.findByIDBill(id_bill);
            for (BillDetails billDtails : listBillDetails) {
                int soLuongDangCo = billDtails.getQuantity();
                int soLuongDangCoSanPham = billDtails.getProductDetails().getQuantity();
                int soLuongCapNhat = soLuongDangCoSanPham - soLuongDangCo;
                long idProductDetails = billDtails.getProductDetails().getId();
                ProductDetails productDetails = productDetailsRepository.findByID(idProductDetails);
                productDetails.setQuantity(soLuongCapNhat);
                productDetailsRepository.save(productDetails);

                DetailedShoppingCart cart = cartDetailsRepository.findByProductDetailsID(idProductDetails);
                cart.setDeleted(true);
                cartDetailsRepository.save(cart);
            }
        }
        return "done";
    }

}
