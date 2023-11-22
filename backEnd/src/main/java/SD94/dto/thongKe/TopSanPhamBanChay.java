package SD94.dto.thongKe;

import lombok.Data;

@Data
public class TopSanPhamBanChay {
    private Long sanPhamId;
    private String tenSanPham;
    private Long mauSac;
    private Long kichThuoc;
    private Long soLuongBan;
    private Long doanhThu;
}
