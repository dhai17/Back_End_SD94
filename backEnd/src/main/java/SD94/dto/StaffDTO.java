package SD94.dto;

import lombok.Data;

import java.util.Date;

@Data
public class StaffDTO {

    private long id;

    private String name;

    private Date dateOfBirth;

    private Boolean gender;

    private String password;

    private String email;

    private String phoneNumber;

    private String address;

    private int status;

    private Date createdDate;

    private String createdby;

    private Date lastModifiedDate;

    private String lastModifiedBy;

    private boolean isDeleted;

}
