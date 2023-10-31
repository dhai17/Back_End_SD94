package SD94.controller.customer.cart;

import SD94.entity.cart.Cart;
import SD94.entity.cart.CartDetails;
import SD94.entity.product.ProductDetails;
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
    public List<CartDetails> listCart(@RequestParam("customer_id") Long customer_id) {
        Cart cart = cartRepository.findbyCustomerID(customer_id);
        long idCart = cart.getId();
        List<CartDetails> cartList = cartDetailsRepository.findByCartID(idCart);
        return cartList;
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam("product_details_id") long product_details_id,
                            @RequestParam("customer_id") long customer_id,
                            @RequestParam("quantity") Integer quantity) {
        Cart cart = cartRepository.findbyCustomerID(customer_id);
        ProductDetails productDetails = productDetailsRepository.findByID(product_details_id);
        Float priceFloat = productDetails.getProduct().getPrice();
        int price = Integer.valueOf(String.valueOf(priceFloat));
        Integer total = price * quantity;
        BigDecimal totalcart = BigDecimal.valueOf(total);

        CartDetails cartDetails = new CartDetails();
        cartDetails.setCart(cart);
        cartDetails.setDeleted(false);
        cartDetails.setQuanTity(quantity);
        cartDetails.setUnitPrice(price);
        cartDetails.setIntoMoney(totalcart);
        cartDetailsRepository.save(cartDetails);
        return "done";
    }

    @PostMapping("/delete/cartDetails")
    public List<CartDetails> deletedCartDetails(@RequestBody CartDetails request) {
        Long id_cart_details = request.getId();
//        int total = request.getUnitPrice();

        Optional<CartDetails> shoppingCart = cartDetailsRepository.findById(id_cart_details);
        long id_cart = 0;
        if (shoppingCart.isPresent()) {
            CartDetails cartDetials = shoppingCart.get();
            id_cart = cartDetials.getCart().getId();
            cartDetials.setDeleted(true);
            cartDetailsRepository.save(cartDetials);
        }

        List<CartDetails> cartList = cartDetailsRepository.findByCartID(id_cart);
        return cartList;
    }

    @PostMapping("/update/quantity/product")
    public List<CartDetails> updateSoLuong(@RequestBody CartDetails request) {
        Long id_cart_details = request.getId();
        int quantity = request.getQuanTity();
        Optional<CartDetails> shoppingCart = cartDetailsRepository.findById(id_cart_details);
        if (shoppingCart.isPresent()) {
            CartDetails cartDetails = shoppingCart.get();
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
            cartDetails.setQuanTity(quantity);
            cartDetails.setIntoMoney(BigDecimal.valueOf(2502));
            cartDetailsRepository.save(cartDetails);
        }

        long idCart = shoppingCart.get().getCart().getId();
        List<CartDetails> cartList = cartDetailsRepository.findByCartID(idCart);

        return cartList;
    }

}
