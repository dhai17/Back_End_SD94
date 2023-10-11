//package SD94.controller.security;
//
//
//import SD94.secutity.StaffUserDetails;
//import SD94.service.serviceImplement.UserServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/users")
//public class UserController {
//    private final UserServiceImpl userService;
//
//    @Autowired
//    public UserController(UserServiceImpl userService) {
//        this.userService = userService;
//    }
//
//    @PostMapping("/signup")
//    public ResponseEntity<?> signUp(@RequestBody StaffUserDetails newUserDTO) {
//        // Kiểm tra xem người dùng đã tồn tại chưa
////        if (userService.existsByUsername(newUserDTO.getUsername())) {
////            return ResponseEntity.badRequest().body("Username is already taken!");
////        }
////
////        // Gọi service để đăng ký người dùng và nhận kết quả dưới dạng UserDTO
////        UserDTO registeredUserDTO = userService.signup(newUserDTO);
////
////        if (registeredUserDTO == null) {
////            return ResponseEntity.badRequest().body("Failed to register user!");
////        }
//
//        return ResponseEntity.ok("User registered successfully!");
//    }
//}
