package SD94.service.serviceImplement;

import SD94.controller.Message.Message;
import SD94.entity.Discount;
import SD94.repository.DiscountRepository;
import SD94.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService {
    @Autowired
    DiscountRepository discountRepository;

    //Lấy danh sách
    @Override
    public List<Discount> findAllDiscount() {
        List<Discount> discounts = discountRepository.findAllDiscount();
        return discounts;
    }

    //Lưu thông tin chỉnh sửa
    @Override
    public ResponseEntity<Discount> saveEdit(Discount discountUpdate) {
        String errorMessage;
        Message errorResponse;

        if (discountUpdate.getName() == "" || discountUpdate.getStartedDate() == null || discountUpdate.getEndDate() == null || discountUpdate.getPercentDiscount() == 0 || discountUpdate.getMaximumvalue() == 0) {
            errorMessage = "Nhập đầy đủ thông tin";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }

        if (discountUpdate.getPercentDiscount() < 0 || discountUpdate.getPercentDiscount() > 100) {
            errorMessage = "Phần trăm giảm phải là số và lớn hơn 0 và nhỏ hơn 100";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }

        if (discountUpdate.getMaximumvalue() < 0 || discountUpdate.getMaximumvalue() > 10000000) {
            errorMessage = "Tiền giảm tối đa phải là số và lớn hơn 0 và nhỏ hơn 10 triệu";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }

        // Định dạng của ngày tháng khi trả về
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
        sdf.setTimeZone(TimeZone.getTimeZone("ICT"));

        Date startDate = null;
        Date endDate = null;

        try {
            startDate = sdf.parse(String.valueOf(discountUpdate.getStartedDate()));
            endDate = sdf.parse(String.valueOf(discountUpdate.getEndDate()));
        } catch (ParseException e) {
            errorMessage = "Lỗi xử lý ngày tháng";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }

        if (endDate.compareTo(startDate) < 0) {
            errorMessage = "Ngày kết thúc phải sau ngày bắt đầu";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }

        try {
            Optional<Discount> optionalDiscount = discountRepository.findById(discountUpdate.getId());
            if (optionalDiscount.isPresent()) {
                Discount discount = optionalDiscount.get();
                discount.setName(discountUpdate.getName());
                discount.setStartedDate(discountUpdate.getStartedDate());
                discount.setEndDate(discountUpdate.getEndDate());
                discount.setPercentDiscount(discountUpdate.getPercentDiscount());
                discount.setMaximumvalue(discountUpdate.getMaximumvalue());
                discountRepository.save(discount);
                return ResponseEntity.ok(discount);

            } else {
                return ResponseEntity.notFound().build();
            }

        } catch (Exception e) {
            return new ResponseEntity(new Message(e.getMessage(), TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
        }
    }

    //Xóa
    @Override
    public ResponseEntity<List<Discount>> deleteDiscount(Long id) {
        try {
            Optional<Discount> optionalDiscount = discountRepository.findById(id);
            if (optionalDiscount.isPresent()) {
                Discount discount = optionalDiscount.get();
                discount.setDeleted(true);
                discountRepository.save(discount);

                List<Discount> discountList = findAllDiscount();
                return ResponseEntity.ok(discountList);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    //Thêm mới
    @Override
    public ResponseEntity<Discount> saveCreate(Discount discountCreate) {
        Optional<Discount> optionalDiscount = discountRepository.findByName(discountCreate.getName());
        String errorMessage;
        Message errorResponse;
        if (optionalDiscount.isPresent()) {
            errorMessage = "Trùng mã khuyến mại";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }

        if (discountCreate.getName() == null || discountCreate.getStartedDate() == null || discountCreate.getEndDate() == null || discountCreate.getPercentDiscount() == 0 || discountCreate.getMaximumvalue() == 0) {
            errorMessage = "Nhập đầy đủ thông tin";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }

        if (discountCreate.getPercentDiscount() < 0 || discountCreate.getPercentDiscount() > 100) {
            errorMessage = "Phần trăm giảm phải là số và lớn hơn 0 và nhỏ hơn 100";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }

        if (discountCreate.getMaximumvalue() < 0 || discountCreate.getMaximumvalue() > 10000000) {
            errorMessage = "Tiền giảm tối đa phải là số và lớn hơn 0 và nhỏ hơn 10 triệu";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }

        // Định dạng của ngày tháng khi trả về
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
        sdf.setTimeZone(TimeZone.getTimeZone("ICT"));

        Date startDate = null;
        Date endDate = null;

        try {
            startDate = sdf.parse(String.valueOf(discountCreate.getStartedDate()));
            endDate = sdf.parse(String.valueOf(discountCreate.getEndDate()));
        } catch (ParseException e) {
            errorMessage = "Lỗi xử lý ngày tháng";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }

        if (endDate.compareTo(startDate) < 0) {
            errorMessage = "Ngày kết thúc phải sau ngày bắt đầu";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }

        try {
            Discount discount = new Discount();
            discount.setName(discountCreate.getName());
            discount.setStartedDate(discountCreate.getStartedDate());
            discount.setEndDate(discountCreate.getEndDate());
            discount.setPercentDiscount(discountCreate.getPercentDiscount());
            discount.setMaximumvalue(discountCreate.getMaximumvalue());
            discountRepository.save(discount);
            return ResponseEntity.ok(discount);

        } catch (Exception e) {
            return new ResponseEntity(new Message(e.getMessage(), TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
        }
    }

    //Tìm kiếm
    @Override
    public List<Discount> searchAllDiscount(String search) {
        List<Discount> discountList = discountRepository.findDiscountByAll(search);
        System.out.println("Hải đần");
        return discountList;
    }

    @Override
    public List<Discount> searchDateDiscount(String searchDate) {
        LocalDate search = LocalDate.parse(searchDate);
        List<Discount> discountList = discountRepository.findDiscountByDate(search);
        return discountList;
    }
}
