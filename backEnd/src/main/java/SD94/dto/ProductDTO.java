package SD94.dto;

import SD94.entity.sanPham.NhaSanXuat;
import SD94.entity.sanPham.LoaiSanPham;
import SD94.entity.sanPham.ChatLieu;
import lombok.Data;

import java.util.Date;

@Data
public class ProductDTO {

    private Long id;

    private String name;

    private Float price;

    private String origin;

    private Integer status;

    private Date createdDate;

    private String createdby;

    private Date lastModifiedDate;

    private String lastModifiedBy;

    private ChatLieu idChatLieu;

    private LoaiSanPham idLoaiSanPham;

    private NhaSanXuat idNhaSanXuat;

}
