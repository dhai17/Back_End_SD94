package SD94.service.service;

import SD94.entity.hoaDon.HoaDon;
import SD94.entity.hoaDon.HoaDonChiTiet;
import SD94.repository.hoaDon.HoaDonChiTietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;

@Service
public class MailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    HoaDonChiTietRepository hoaDonChiTietRepository;

    public void sendOrderConfirmationEmail(String recipientEmail,
                                           HoaDon hoaDon) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("linhnkph22810@fpt.edu.vn");
        helper.setTo(recipientEmail);
        helper.setSubject("Đơn hàng của bạn đã được đặt thành công");

        long idHoaDon = hoaDon.getId();

        // Lấy danh sách ảnh chính của tất cả sản phẩm và lưu vào List
        List<HoaDonChiTiet> hoaDonChiTiet = hoaDonChiTietRepository.findByIDBill(idHoaDon);
        List<String> tenAnhChinhList = new ArrayList<>();

        // Tạo context và thêm thông tin đơn hàng vào mẫu email
        Context context = new Context();
        context.setVariable("id", hoaDon.getId());
        context.setVariable("nguoiNhan", hoaDon.getNguoiNhan());
        context.setVariable("tongTien", hoaDon.getTongTienHoaDon());
        context.setVariable("hoaDonChiTiet", hoaDonChiTiet);
        context.setVariable("hoaDon", hoaDon);
        context.setVariable("trangThai", hoaDon.getTrangThai().getName());
        context.setVariable("tenAnhChinhList", tenAnhChinhList);
        // Thêm các thông tin khác của đơn hàng vào context nếu cần

        String emailContent = templateEngine.process("mail/GuiMail", context);

        helper.setText(emailContent, true);

        javaMailSender.send(message);
    }

}
