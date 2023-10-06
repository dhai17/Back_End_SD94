package SD94.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CustomerDTO {
    private long id;
    private String name;
    private Date phoneNumber;
    private String email;
    private Date dateBirth;
    private String addRess;
    private String passWord;
    private Date createdDate;
    private String createdby;
    private Date lastModifiedDate;
    private String lastModifiedBy;
    private boolean isDeleted;
}
