
package SD94.repository;

import SD94.entity.hoaDon.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<HoaDon, Long> {

}

//package SD94.repository;
//
//import SD94.entity.Staff;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public interface UserRepository extends JpaRepository<Staff, Long> {
//    Staff finByUsername( String username);
//}

