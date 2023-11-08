package SD94.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class HoaDonDTO {

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

    private List<Long> id_hoaDon;
    private String checkOut_email;

}
