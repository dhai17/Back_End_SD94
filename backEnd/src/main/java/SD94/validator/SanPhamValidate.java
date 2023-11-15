package SD94.validator;

import SD94.dto.SanPhamDTO;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
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
        System.out.println(sanPhamDTO);
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
            errors.put(ErrorCode.Null_soLuongHienCo.name(), "Khong co so luong hien co");
        }
    }

    public static void checkMaMauSac(String maMauSac, Map<String, String> errors) {
        if (maMauSac == null || maMauSac.trim().isEmpty()) {
            errors.put(ErrorCode.Null_maMauSac.name(), "Phai chon mau sac va kich co");
        }
    }

    public static void checkKichCoDaChon(String kichCoDaChon, Map<String, String> errors) {
        if (kichCoDaChon == null || kichCoDaChon.trim().isEmpty()) {
            errors.put(ErrorCode.Null_kichCoDaChon.name(), "Phai chon mau sac va kich co");
        }
    }

    public static void checkSoLuongDaChon(Integer soLuongDaChon, Integer soLuongDaCo, Map<String, String> errors) {
        if (soLuongDaChon == null) {
            errors.put(ErrorCode.Null_soLuongDaChon.name(), "So luong khong duoc bo trong");
        }

        if (soLuongDaChon <= 0) {
            errors.put(ErrorCode.Null_soLuongDaChon.name(), "So luong phai lon hon 0");
        }

        if (soLuongDaCo != null && soLuongDaChon > soLuongDaCo) {
            errors.put(ErrorCode.Null_soLuongDaChon.name(), "So luong chon khong duoc lon hon so luong dang co");
        }
    }

    public static void checkDonGia(Integer donGia, Map<String, String> errors) {
        if (donGia == null) {
            errors.put(ErrorCode.Null_donGia.name(), "Don gia khong duoc bo trong");
        }
    }

    public static void checkTongTien(Integer tongTien, Map<String, String> errors) {
        if (tongTien == null) {
            errors.put(ErrorCode.Null_tongTien.name(), "Tong tien khong duoc bo trong");
        }
    }

    public static void checkSanPhamID(Long sanPham_id, Map<String, String> errors) {
        if (sanPham_id == null) {
            errors.put(ErrorCode.Null_san_pham_id.name(), "Khong co san pham nao duoc tim thay");
        }
    }
}
