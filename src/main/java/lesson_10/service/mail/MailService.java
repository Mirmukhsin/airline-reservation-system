package lesson_10.service.mail;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lesson_10.entity.Flight;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class MailService {
    public static final String EMAIL = "air@gmail.com";
    private final JavaMailSender javaMailSender;
    private final Configuration configuration;
    @Value("${server.port}")
    String PORT;

    public void sendActivationMail(Map<String, String> model) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(EMAIL);
            mimeMessageHelper.setTo(model.get("to"));
            mimeMessageHelper.setSubject("Activation Email");
            Template template = configuration.getTemplate("activation.ftlh");
            String url = "http://localhost:" + PORT + "/api/activate/" + model.get("code");
            Map<String, String> objectModel = Map.of("url", url);
            String htmlMailContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, objectModel);
            mimeMessageHelper.setText(htmlMailContent, true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException | IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

    public void sendMail(Map<String, Object> model) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setFrom(EMAIL);
            messageHelper.setTo("to@gmail.com");
            messageHelper.setSubject("Notification");
            Template template = configuration.getTemplate("notification.ftlh");
            String htmlMailContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            messageHelper.setText(htmlMailContent, true);

            javaMailSender.send(mimeMessage);

        } catch (MessagingException | IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

    public void sendNewFlights(Map<String, List<Flight>> model) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setFrom(EMAIL);
            messageHelper.setTo("to@gmail.com");
            messageHelper.setSubject("New Flights");
            Template template = configuration.getTemplate("newFlight.ftlh");
            String htmlMailContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            messageHelper.setText(htmlMailContent, true);

            javaMailSender.send(mimeMessage);

        } catch (MessagingException | IOException | TemplateException e) {
            e.printStackTrace();
        }
    }
}
