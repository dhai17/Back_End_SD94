package SD94.service.impl;

import SD94.controller.message.Message;
import SD94.entity.khuyenMai.KhuyenMai;
import SD94.repository.DiscountRepository;
import SD94.service.service.KhuyenMaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

@Service
public class KhuyenMaiServiceImpl implements KhuyenMaiService {
    @Autowired
    DiscountRepository discountRepository;

    //Lấy danh sách
    @Transactional
    @Override
    public List<KhuyenMai> findAllDiscount() {
        List<KhuyenMai> khuyenMais = discountRepository.findAllDiscount();
        long homNay = new Date().getTime();
        for(KhuyenMai khuyenMai : khuyenMais){
            long startDate = khuyenMai.getNgayBatDau().getTime();
            long endDate = khuyenMai.getNgayKetThuc().getTime();
            long idDiscount = khuyenMai.getId();

            if (endDate < homNay && khuyenMai.getTrangThai() != 2){
                discountRepository.updateStatusDiscount(2, idDiscount);
            }

            if(startDate > homNay && khuyenMai.getTrangThai() != 1){
                discountRepository.updateStatusDiscount(1, idDiscount);
            }
            System.out.println("Update thành công");
        }

        List<KhuyenMai> listReturn = discountRepository.findAllDiscount();

        return listReturn;
    }

    //Lưu thông tin chỉnh sửa
    @Override
    public ResponseEntity<KhuyenMai> saveEdit(KhuyenMai khuyenMaiUpdate) {
        String errorMessage;
        Message errorResponse;

        if (khuyenMaiUpdate.getTenKhuyenMai() == "" || khuyenMaiUpdate.getNgayBatDau() == null || khuyenMaiUpdate.getNgayKetThuc() == null || khuyenMaiUpdate.getPhanTramGiam() == 0 || khuyenMaiUpdate.getTienGiamToiDa() == 0) {
            errorMessage = "Nhập đầy đủ thông tin";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }

        if (khuyenMaiUpdate.getPhanTramGiam() < 0 || khuyenMaiUpdate.getPhanTramGiam() > 100) {
            errorMessage = "Phần trăm giảm phải là số và lớn hơn 0 và nhỏ hơn 100";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }

        if (khuyenMaiUpdate.getTienGiamToiDa() < 0 || khuyenMaiUpdate.getTienGiamToiDa() > 10000000) {
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
            startDate = sdf.parse(String.valueOf(khuyenMaiUpdate.getNgayBatDau()));
            endDate = sdf.parse(String.valueOf(khuyenMaiUpdate.getNgayKetThuc()));
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
            Optional<KhuyenMai> optionalDiscount = discountRepository.findById(khuyenMaiUpdate.getId());
            if (optionalDiscount.isPresent()) {
                KhuyenMai khuyenMai = optionalDiscount.get();
                khuyenMai.setTenKhuyenMai(khuyenMaiUpdate.getTenKhuyenMai());
                khuyenMai.setNgayBatDau(khuyenMaiUpdate.getNgayBatDau());
                khuyenMai.setNgayKetThuc(khuyenMaiUpdate.getNgayKetThuc());
                khuyenMai.setPhanTramGiam(khuyenMaiUpdate.getPhanTramGiam());
                khuyenMai.setTienGiamToiDa(khuyenMaiUpdate.getTienGiamToiDa());
                discountRepository.save(khuyenMai);
                return ResponseEntity.ok(khuyenMai);

            } else {
                return ResponseEntity.notFound().build();
            }

        } catch (Exception e) {
            return new ResponseEntity(new Message(e.getMessage(), TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
        }
    }

    //Xóa
    @Override
    public ResponseEntity<List<KhuyenMai>> deleteDiscount(Long id) {
        try {
            Optional<KhuyenMai> optionalDiscount = discountRepository.findById(id);
            if (optionalDiscount.isPresent()) {
                KhuyenMai khuyenMai = optionalDiscount.get();
                khuyenMai.setDeleted(true);
                discountRepository.save(khuyenMai);

                List<KhuyenMai> khuyenMaiList = findAllDiscount();
                return ResponseEntity.ok(khuyenMaiList);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    //Thêm mới
    @Override
    public ResponseEntity<KhuyenMai> saveCreate(KhuyenMai khuyenMaiCreate) {
        Optional<KhuyenMai> optionalDiscount = discountRepository.findByName(khuyenMaiCreate.getTenKhuyenMai());
        String errorMessage;
        Message errorResponse;
        if (optionalDiscount.isPresent()) {
            errorMessage = "Trùng mã khuyến mại";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }

        if (khuyenMaiCreate.getTenKhuyenMai() == null || khuyenMaiCreate.getNgayBatDau() == null || khuyenMaiCreate.getNgayKetThuc() == null || khuyenMaiCreate.getPhanTramGiam() == 0 || khuyenMaiCreate.getTienGiamToiDa() == 0) {
            errorMessage = "Nhập đầy đủ thông tin";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }

        if (khuyenMaiCreate.getPhanTramGiam() < 0 || khuyenMaiCreate.getPhanTramGiam() > 100) {
            errorMessage = "Phần trăm giảm phải là số và lớn hơn 0 và nhỏ hơn 100";
            errorResponse = new Message(errorMessage, TrayIcon.MessageType.ERROR);
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        }

        if (khuyenMaiCreate.getTienGiamToiDa() < 0 || khuyenMaiCreate.getTienGiamToiDa() > 10000000) {
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
            startDate = sdf.parse(String.valueOf(khuyenMaiCreate.getNgayBatDau()));
            endDate = sdf.parse(String.valueOf(khuyenMaiCreate.getNgayKetThuc()));
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
            KhuyenMai khuyenMai = new KhuyenMai();
            khuyenMai.setTenKhuyenMai(khuyenMaiCreate.getTenKhuyenMai());
            khuyenMai.setNgayBatDau(khuyenMaiCreate.getNgayBatDau());
            khuyenMai.setNgayKetThuc(khuyenMaiCreate.getNgayKetThuc());
            khuyenMai.setPhanTramGiam(khuyenMaiCreate.getPhanTramGiam());
            khuyenMai.setTienGiamToiDa(khuyenMaiCreate.getTienGiamToiDa());
            discountRepository.save(khuyenMai);
            return ResponseEntity.ok(khuyenMai);

        } catch (Exception e) {
            return new ResponseEntity(new Message(e.getMessage(), TrayIcon.MessageType.ERROR), HttpStatus.BAD_REQUEST);
        }
    }

    //Tìm kiếm
    @Override
    public List<KhuyenMai> searchAllDiscount(String search) {
        List<KhuyenMai> khuyenMaiList = discountRepository.findDiscountByAll(search);
        return khuyenMaiList;
    }

    @Override
    public List<KhuyenMai> searchDateDiscount(String searchDate) {
        LocalDate search = LocalDate.parse(searchDate);
        List<KhuyenMai> khuyenMaiList = discountRepository.findDiscountByDate(search);
        return khuyenMaiList;
    }
}
