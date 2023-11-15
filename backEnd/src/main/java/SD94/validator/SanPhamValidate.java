package SD94.validator;

import SD94.dto.SanPhamDTO;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class SanPhamValidate {
    // Mua ngay
    public static ResponseEntity<?> checkOut(SanPhamDTO sanPhamDTO) {
        if (sanPhamDTO.getMaMauSac() == null || sanPhamDTO.getMaMauSac().trim().isEmpty()) {
            Map<String, String> respone = new HashMap<>();
            respone.put("Mess", "Phai chon mau sac va kich co");
            return ResponseEntity.badRequest().body(respone);
        }

        if (sanPhamDTO.getKichCoDaChon() == null || sanPhamDTO.getKichCoDaChon().trim().isEmpty()) {
            Map<String, String> respone = new HashMap<>();
            respone.put("Mess", "Phai chon mau sac va kich co");
            return ResponseEntity.badRequest().body(respone);
        }

        if (sanPhamDTO.getSan_pham_id() == null) {
            Map<String, String> respone = new HashMap<>();
            respone.put("Mess", "Khong co san pham nao duoc tim thay");
            return ResponseEntity.badRequest().body(respone);
        }

        if (sanPhamDTO.getSoLuongHienCo() == null) {
            Map<String, String> respone = new HashMap<>();
            respone.put("Mess", "Khong co so luong hien co");
            return ResponseEntity.badRequest().body(respone);
        }

        if (sanPhamDTO.getSoLuong() == 0) {
            Map<String, String> respone = new HashMap<>();
            respone.put("Mess", "So luong khong duoc bo trong");
            return ResponseEntity.badRequest().body(respone);
        }

        if (sanPhamDTO.getSoLuong() <= 0) {
            Map<String, String> respone = new HashMap<>();
            respone.put("Mess", "So luong phai lon hon 0");
            return ResponseEntity.badRequest().body(respone);
        }

        if (sanPhamDTO.getSoLuongHienCo() != null && sanPhamDTO.getSoLuong() > sanPhamDTO.getSoLuongHienCo()) {
            Map<String, String> respone = new HashMap<>();
            respone.put("Mess", "So luong chon khong duoc lon hon so luong dang co");
            return ResponseEntity.badRequest().body(respone);
        }

        return ResponseEntity.ok().build();
    }




}
