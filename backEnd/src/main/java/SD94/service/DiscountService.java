package SD94.service;

import SD94.entity.Discount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DiscountService {
    List<Discount> findAllDiscount();

    ResponseEntity<Discount> saveEdit(Discount discountUpdate);

    ResponseEntity<List<Discount>> deleteDiscount(Long id);

    ResponseEntity<Discount> saveCreate(Discount discountCreate);

    List<Discount> searchAllDiscount(String search);

    List<Discount> searchDateDiscount(String searchDate);
}
