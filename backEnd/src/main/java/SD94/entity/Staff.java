
package SD94.entity;

import SD94.entity.customer.Base;
import SD94.entity.security.Authority;
import SD94.entity.security.UserRole;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.*;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "staff")
public class Staff extends Base implements UserDetails {

    @Column(name = "name", columnDefinition = "nvarchar(256) not null unique")
    private String name;

    @Column(name = "phoneNumber", columnDefinition = "nvarchar(50) null")
    private String phoneNumber;

    @Column(name = "email", columnDefinition = "nvarchar(256) not null unique")
    private String email;


    @Column(name = "pass_word", columnDefinition = "nvarchar(256) null ")
    private String password;

    @Column(name = "gender", columnDefinition = "bit")
    private Boolean gender;

    @Column(name = "address", columnDefinition = "nvarchar(256) not null ")
    private String address;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "dateOfBirth", columnDefinition = "Datetime null")
    private Date dateOfBirth;

    @Column(name = "status", columnDefinition = "int null")
    private int status;

    @ManyToOne
    @JoinColumn(name = "idPosition", referencedColumnName = "id")
    private Position position;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "staff")
    @JsonIgnore
    private Set<UserRole> userRoles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Authority> set = new HashSet<>();
        this.userRoles.forEach(userRole -> {
            set.add(new Authority(userRole.getRole().getRoleName()));
        });
        return set;
    }

    @Override
    public String getUsername() {
        return this.email;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

