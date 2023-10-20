//package SD94.entity;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.EntityListeners;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//import java.io.Serializable;
//import java.util.Date;
//
//@Entity
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@EntityListeners(AuditingEntityListener.class)
//@Table(name = "staff")
//public class Staff extends Base implements Serializable {
//
//    @Column(name="name", columnDefinition = "nvarchar(256) not null unique")
//    private String name;
//
//    @Column(name = "phoneNumber", columnDefinition = "int null")
//    private int phoneNumber;
//
//    @Column(name = "email", columnDefinition = "nvarchar(256) not null unique")
//    private String email;
//    @Column(name = "pass_word",columnDefinition = "nvarchar(256) null ")
//    private String password;
//
//    @Column(name = "gender",  columnDefinition = "bit")
//    private Boolean gender;
//
//    @Column(name = "address", columnDefinition = "nvarchar(256) not null unique")
//    private String address;
//
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
//    @Column(name = "dateOfBirth", columnDefinition = "Datetime null")
//    private Date dateOfBirth;
//
//    @Column(name = "status", columnDefinition = "int null")
//    private int status;
//
//    @ManyToOne
//    @JoinColumn(name = "idPosition", referencedColumnName = "id")
//    private Position position;
//
//}
