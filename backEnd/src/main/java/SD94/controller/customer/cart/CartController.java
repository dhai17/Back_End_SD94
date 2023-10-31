package SD94.controller.customer.cart;

import SD94.entity.gioHang.GioHang;
import SD94.entity.gioHang.GioHangChiTiet;
import SD94.entity.sanPham.SanPhamChiTiet;
import SD94.repository.CartDetailsRepository;
import SD94.repository.CartRepository;
import SD94.repository.ProductDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductDetailsRepository productDetailsRepository;

    @Autowired
    CartDetailsRepository cartDetailsRepository;

    @RequestMapping("/list")
    public List<GioHangChiTiet> listCart(@RequestParam("customer_id") Long customer_id) {
        GioHang gioHang = cartRepository.findbyCustomerID(customer_id);
        long idCart = gioHang.getId();
        List<GioHangChiTiet> cartList = cartDetailsRepository.findByCartID(idCart);
        return cartList;
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam("product_details_id") long product_details_id,
                            @RequestParam("customer_id") long customer_id,
                            @RequestParam("quantity") Integer quantity) {
        GioHang gioHang = cartRepository.findbyCustomerID(customer_id);
        SanPhamChiTiet sanPhamChiTiet = productDetailsRepository.findByID(product_details_id);
        Float priceFloat = sanPhamChiTiet.getSanPham().getGia();
        int price = Integer.valueOf(String.valueOf(priceFloat));
        Integer total = price * quantity;
        BigDecimal totalcart = BigDecimal.valueOf(total);

        GioHangChiTiet gioHangChiTiet = new GioHangChiTiet();
        gioHangChiTiet.setGioHang(gioHang);
        gioHangChiTiet.setDeleted(false);
        gioHangChiTiet.setSoLuong(quantity);
        gioHangChiTiet.setDonGia(price);
        gioHangChiTiet.setThanhTien(totalcart);
        cartDetailsRepository.save(gioHangChiTiet);
        return "done";
    }

    @PostMapping("/delete/cartDetails")
    public List<GioHangChiTiet> deletedCartDetails(@RequestBody GioHangChiTiet request) {
        Long id_cart_details = request.getId();
//        int total = request.getUnitPrice();

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

    @PostMapping("/update/quantity/product")
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
