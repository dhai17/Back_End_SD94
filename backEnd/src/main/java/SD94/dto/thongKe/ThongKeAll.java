package SD94.dto.thongKe;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ThongKeAll {
    private Long trangthai_id;
    private Long tong_so_hoadon;
    private BigDecimal tong_tien_hoadon;

    public ThongKeAll(long trangthai_id, long tong_so_hoadon, BigDecimal tong_tien_hoadon) {
        this.trangthai_id = trangthai_id;
        this.tong_so_hoadon = tong_so_hoadon;
        this.tong_tien_hoadon = tong_tien_hoadon;
    }
}
