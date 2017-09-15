package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.internet.MimeMessage;
import java.util.Objects;

@Controller
public class EmailController {


    @Autowired
    private JavaMailSender sender;



    @RequestMapping("/simpleemail")
    @ResponseBody
    public String home(@RequestParam String name) {
        try {
            sendEmail(name);
            return "Email Sent!";
        } catch (Exception ex) {
            return "Error in sending email: " + ex;
        }
    }

    private void sendEmail(String name) throws Exception {
        MimeMessage message = sender.createMimeMessage();

        // Enable the multipart flag!
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        ClassPathResource file = new ClassPathResource("car.jpg");

        helper.setTo("dennis.lookin@gmail.com");
        helper.setText("" +
                "<html>" +
                    "<body>" +
                        "<div style='display: flex; flex-direction: row;'>" +
                        "<p style='color: green'>Here is a car picture!</p>" +
                        " <img src='cid:id101'/>" +
                        "</div>" +
                    "<body>" +
                "</html>" +
                "", true);
        helper.setSubject("Hi, " + (Objects.equals(name, "") ? "unnamed" : name) + "!");
        helper.addInline("id101", file);

        sender.send(message);
    }
}
