package SD94.dto;

import lombok.Data;

import java.util.Date;

@Data
public class BillDTO {

    private Long id;

    private String code;

    private String note;

    private int status;

    private String email;

    private String phoneNumber;

    private int shippingMoney;

    private int reducedMoney;

    private int totalOrderPrice;

    private int totalInvoiceAmount;

    private int invoiceType;

    private Date createdDate;

    private String createdby;

    private Date lastModifiedDate;

    private String lastModifiedBy;

//    private KhachHang khachHang;;
//
//    private NhanVien nhanVien;
//
//    private KhuyenMai khuyenMai;

}
