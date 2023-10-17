package SD94.controller.customer.cart;

import SD94.entity.Cart;
import SD94.entity.DetailedShoppingCart;
import SD94.entity.ProductDetails;
import SD94.repository.CartDetailsRepository;
import SD94.repository.CartRepository;
import SD94.repository.ProductDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Collections;
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
    public List<DetailedShoppingCart> listCart(@RequestParam("customer_id") long customer_id){
        Cart cart = cartRepository.findbyCustomerID(customer_id);
        long idCart = cart.getId();
        List<DetailedShoppingCart> cartList = cartDetailsRepository.findByCartID(idCart);
        return cartList;
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam("product_details_id") long product_details_id,
                            @RequestParam("customer_id") long customer_id,
                            @RequestParam("quantity") Integer quantity){
        Cart cart = cartRepository.findbyCustomerID(customer_id);
        ProductDetails productDetails = productDetailsRepository.findByID(product_details_id);
        Float priceFloat = productDetails.getProduct().getPrice();
        int price = Integer.valueOf(String.valueOf(priceFloat));
        Integer total = price * quantity;
        BigDecimal totalcart = BigDecimal.valueOf(total);

        DetailedShoppingCart cartDetails = new DetailedShoppingCart();
        cartDetails.setCart(cart);
        cartDetails.setDeleted(false);
        cartDetails.setQuanTity(quantity);
        cartDetails.setUnitPrice(price);
        cartDetails.setIntoMoney(totalcart);
        cartDetailsRepository.save(cartDetails);
        return "done";
    }

    @PostMapping("/delete/cartDetails")
    public List<DetailedShoppingCart> deletedCartDetails(@RequestParam("id_cart_details") long id_cart_details,
                                                         @RequestParam("id_cart") long id_cart){

        Optional<DetailedShoppingCart> shoppingCart = cartDetailsRepository.findById(id_cart_details);
        if(shoppingCart.isPresent()){
            DetailedShoppingCart cartDetials = shoppingCart.get();
            cartDetials.setDeleted(true);
            cartDetailsRepository.save(cartDetials);
        }

        List<DetailedShoppingCart> cartList = cartDetailsRepository.findByCartID(id_cart);
        return cartList;
    }

    @PostMapping("/update/quantity/product")
    public ResponseEntity<String> updateSoLuong(@RequestParam("quantity") int quantity,
                                                      @RequestParam("id_cart_details") long id_cart_details) {
        Optional<DetailedShoppingCart> shoppingCart = cartDetailsRepository.findById(id_cart_details);

        if (shoppingCart.isPresent()) {
            DetailedShoppingCart cartDetails = shoppingCart.get();
            int soLuongSanPhamHienCo = cartDetails.getProductDetails().getQuantity();
            Float priceFloat = cartDetails.getProductDetails().getProduct().getPrice();
            int price = Integer.valueOf(String.valueOf(priceFloat));
            Integer total = price * quantity;
            BigDecimal totalcart = BigDecimal.valueOf(total);

            if (quantity > soLuongSanPhamHienCo) {
                return ResponseEntity.badRequest().body(Collections.singletonList("Số lượng nhập vào lớn hơn số lượng hiện có").toString());
            }else {
                cartDetails.setQuanTity(quantity);
                cartDetails.setIntoMoney(totalcart);
                cartDetailsRepository.save(cartDetails);
            }
        }

        return ResponseEntity.ok().body("done");
    }

}
