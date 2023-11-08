package SD94.controller.customer.gioHang;

import SD94.entity.gioHang.GioHang;
import SD94.entity.gioHang.GioHangChiTiet;
import SD94.entity.khachHang.KhachHang;
import SD94.entity.sanPham.SanPhamChiTiet;
import SD94.repository.gioHang.GioHangChiTietRepository;
import SD94.repository.gioHang.GioHangRepository;
import SD94.repository.khachHang.KhachHangRepository;
import SD94.repository.sanPham.SanPhamChiTietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gioHang")
public class GioHangController {
    @Autowired
    GioHangRepository cartRepository;

    @Autowired
    SanPhamChiTietRepository productDetailsRepository;

    @Autowired
    GioHangChiTietRepository cartDetailsRepository;

    @Autowired
    KhachHangRepository khachHangRepository;

    @RequestMapping("/danhSach/{email}")
    public List<GioHangChiTiet> listCart(@PathVariable("email") String email) {
        KhachHang khachHang = khachHangRepository.findByEmail(email);
        GioHang gioHang = cartRepository.findbyCustomerID(khachHang.getId());
        long idCart = gioHang.getId();
        List<GioHangChiTiet> cartList = cartDetailsRepository.findByCartID(idCart);
        return cartList;
    }

    @PostMapping("/xoa/gioHangChiTiet")
    public List<GioHangChiTiet> deletedCartDetails(@RequestBody GioHangChiTiet request) {
        Long id_cart_details = request.getId();

        Optional<GioHangChiTiet> shoppingCart = cartDetailsRepository.findById(id_cart_details);
        long id_cart = 0;
        if (shoppingCart.isPresent()) {
            GioHangChiTiet cartDetials = shoppingCart.get();
            id_cart = cartDetials.getGioHang().getId();
            cartDetials.setDeleted(true);
            cartDetailsRepository.save(cartDetials);
        }

        List<GioHangChiTiet> cartList = cartDetailsRepository.findByCartID(id_cart);
        return cartList;
    }

    @PostMapping("/sua/soLuong/sanPham")
    public List<GioHangChiTiet> updateSoLuong(@RequestBody GioHangChiTiet request) {
        Long id_cart_details = request.getId();
        int quantity = request.getSoLuong();
        Optional<GioHangChiTiet> shoppingCart = cartDetailsRepository.findById(id_cart_details);
        if (shoppingCart.isPresent()) {
            GioHangChiTiet gioHangChiTiet = shoppingCart.get();
//            int soLuongSanPhamHienCo = cartDetails.getProductDetails().getQuantity();
//            Float priceFloat = cartDetails.getProductDetails().getProduct().getPrice();
//            int price = Integer.valueOf(String.valueOf(priceFloat));
//            Integer total = price * quantity;
//            BigDecimal totalcart = BigDecimal.valueOf(total);

//            if (quantity > soLuongSanPhamHienCo) {
//                return (List<DetailedShoppingCart>) ResponseEntity.badRequest().body(Collections.singletonList("Số lượng nhập vào lớn hơn số lượng hiện có").toString());
//            } else {
//                cartDetails.setQuanTity(quantity);
//                cartDetails.setIntoMoney(totalcart);
//                cartDetailsRepository.save(cartDetails);
//            }
            gioHangChiTiet.setSoLuong(quantity);
            gioHangChiTiet.setThanhTien(BigDecimal.valueOf(2502));
            cartDetailsRepository.save(gioHangChiTiet);
        }

        long idCart = shoppingCart.get().getGioHang().getId();
        List<GioHangChiTiet> cartList = cartDetailsRepository.findByCartID(idCart);

        return cartList;
    }

}
