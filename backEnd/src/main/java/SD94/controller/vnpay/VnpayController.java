package SD94.controller.vnpay;

import SD94.config.VnpayConflig;
import SD94.service.service.VnpayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

@RestController
public class VnpayController {
    @Autowired
    VnpayService vnpayService;

    @PostMapping("/payment/create")
    public ResponseEntity<String> createUrl(@RequestParam("amount") long amount,
                                            @RequestParam("id_bill") String id_bill){
        String paymentUrl = vnpayService.createPayment(amount, id_bill);
        return ResponseEntity.status(302).header("Location", paymentUrl).body("");
    }

    @RequestMapping("/payment/return")
    public ResponseEntity<String> returnPayment(HttpServletRequest request){
        int paymentStatus = VnpayConflig.orderReturn(request);
        if(paymentStatus == 1){
            return ResponseEntity.status(200).body("Success");
        }else {
            return ResponseEntity.status(400).body("Error");
        }
    }
}
