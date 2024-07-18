package site.metacoding.blogv3._core.utils;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class EmailUtil {

    private final JavaMailSender sender;

    // 메일 전송하는 메소드
    public void sendEmail(String toAddress, String subject, String body) {

        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            // 어디로 메일 보낼거냐
            helper.setTo(toAddress);
            // 제목이 무엇이냐
            helper.setSubject(subject);
            // true로 하면 html 디자인 되어있는 콘텐트를 넣어준다
            helper.setText(body, true);

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        // 내가 설정한 이메일로 날아감
        sender.send(message);
    }

    //html 꾸미기
    public String getCertificationMessage(String body) {

        String certificationMessage = "";
        certificationMessage += "<h1 style='text-align: center;'>[Jstory 인증메일]</h1>";
        certificationMessage += "<h3 style='text-align: center;'>" +
                "인증코드 : <strong style='font-size: 32px; letter-spacing: 8px;'>" + body + "</strong></h3>";

        return certificationMessage;
    }
}
