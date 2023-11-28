package SD94.validator;

import SD94.dto.SanPhamDTO;
import SD94.entity.sanPham.*;
import SD94.repository.sanPham.KichCoRepository;
import SD94.repository.sanPham.MauSacRepository;
import SD94.repository.sanPham.SanPhamChiTietRepository;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SanPhamValidate {

    public enum ErrorCode {
        Null_maMauSac,

        Null_kichCoDaChon,

        Null_san_pham_id,

        Null_soLuongDaChon,

        Null_donGia,

        Null_soLuong,

        Null_tongTien,

        Null_id_hoaDon,

        Null_soLuongHienCo

    }

    //Mua ngay
    public static ResponseEntity<?> checkOut(SanPhamDTO sanPhamDTO) {
        Map<String, String> errors = new HashMap<>();
        checkMaMauSac(sanPhamDTO.getMaMauSac(), errors);
        checkKichCoDaChon(sanPhamDTO.getKichCoDaChon(), errors);
        checkSanPhamID(sanPhamDTO.getSan_pham_id(), errors);
        checkSoLuongHienCo(sanPhamDTO.getSoLuongHienCo(), errors);
        checkSoLuongDaChon(sanPhamDTO.getSoLuong(), sanPhamDTO.getSoLuongHienCo(), errors);

        if (errors.isEmpty()) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().body(errors);

    }

    public static void checkSoLuongHienCo(Integer SoLuongHienCO, Map<String, String> errors) {
        if (SoLuongHienCO == null) {
            errors.put(ErrorCode.Null_soLuongHienCo.name(), "Không có số lượng hiện có");
        }
    }

    public static void checkMaMauSac(String maMauSac, Map<String, String> errors) {
        if (maMauSac == null || maMauSac.trim().isEmpty()) {
            errors.put(ErrorCode.Null_maMauSac.name(), "Phải chọn màu sắc và kích cỡ");
        }
    }

    public static void checkKichCoDaChon(String kichCoDaChon, Map<String, String> errors) {
        if (kichCoDaChon == null || kichCoDaChon.trim().isEmpty()) {
            errors.put(ErrorCode.Null_kichCoDaChon.name(), "Phải chọn màu sắc và kích cỡ");
        }
    }

    public static void checkSoLuongDaChon(Integer soLuongDaChon, Integer soLuongDaCo, Map<String, String> errors) {
        if (soLuongDaChon == null) {
            errors.put(ErrorCode.Null_soLuongDaChon.name(), "Số lượng không được để trống");
        }

        if (soLuongDaChon <= 0) {
            errors.put(ErrorCode.Null_soLuongDaChon.name(), "Số lượng phải lớn hơn 0");
        }

        if (soLuongDaCo != null && soLuongDaChon > soLuongDaCo) {
            errors.put(SanPhamValidate.ErrorCode.Null_soLuongDaChon.name(), "Số lượng đã chọn không được lớn hơn số lượng đang có");
        }
    }

    public static void checkDonGia(Integer donGia, Map<String, String> errors) {
        if (donGia == null || donGia == 0) {
            errors.put(SanPhamValidate.ErrorCode.Null_donGia.name(), "Đơn giá không được bỏ trống");
        }
    }

    public static void checkTongTien(Integer tongTien, Map<String, String> errors) {
        if (tongTien == null) {
            errors.put(SanPhamValidate.ErrorCode.Null_tongTien.name(), "Tổng tiền không được bỏ trống");
        }
    }

    public static void checkSanPhamID(Long sanPham_id, Map<String, String> errors) {
        if (sanPham_id == null) {
            errors.put(SanPhamValidate.ErrorCode.Null_san_pham_id.name(), "Không có sản phẩm nào được tìm thấy");
        }
    }

    public static ResponseEntity<?> checkTaoSP(SanPhamDTO sanPhamDTO) {
        Map<String, Object> response = new HashMap<>();

        if (sanPhamDTO.getTenSanPham() == null || sanPhamDTO.getTenSanPham().isEmpty() ||
                sanPhamDTO.getGia() == null ||
                sanPhamDTO.getLoaiSanPham_id() == null || sanPhamDTO.getNhaSanXuat_id() == null ||
                sanPhamDTO.getKichCo() == null || sanPhamDTO.getKichCo().isEmpty() ||
                sanPhamDTO.getMauSac() == null || sanPhamDTO.getMauSac().isEmpty() ||
                sanPhamDTO.getSoLuong() <= 0) {
            response.put("error", "Nhập đầy đủ thông tin");
            return ResponseEntity.badRequest().body(response);
        }

        if (Float.compare(sanPhamDTO.getGia(), 0.0f) <= 0) {
            response.put("error", "Giá tiền sản phẩm phải lớn hơn 0");
            return ResponseEntity.badRequest().body(response);
        }

        if (sanPhamDTO.getSoLuong() <= 0) {
            response.put("error", "Số lượng sản phẩm phải lớn hơn 0");
            return ResponseEntity.badRequest().body(response);
        }

        return ResponseEntity.ok().body(response);
    }

}
