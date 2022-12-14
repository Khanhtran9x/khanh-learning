package internet.com.controller;

import internet.com.dto.reponse_body.ReponseBody;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@RestController
@RequestMapping("/mail")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MailSenderController {
    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Created: LuanND
     * Date: 17/08/2022
     * Description: send bill for customer when payment successfully
     * @param reponseBody : response from Paypal about information of customer when payment successfully
     * @return notify sent email
     * @throws MessagingException
     */
    @RequestMapping("/send")
    public ResponseEntity<String> sendSimpleEmail (@RequestBody ReponseBody reponseBody) throws MessagingException {
        ReponseBody reponseBodyNew = modelMapper.map(reponseBody , ReponseBody.class);
        MimeMessage message = emailSender.createMimeMessage();
        boolean multipart = true;
        MimeMessageHelper helper = new MimeMessageHelper(message , multipart , "utf-8");
        String htmlMsg = "<div style=\"background: url('https://images2.alphacoders.com/401/thumb-1920-40102.jpg') white; background-size: 100% 100%; background-position:right; min-height: 800px; background-repeat: no-repeat;\">" +
                "<p>Dear <b>" + reponseBodyNew.getFullName() + "</b>,</p>\n" +
                "<p>C???m ??n qu?? kh??ch ???? s??? d???ng d???ch v??? c???a ch??ng t??i. C02G1 g???i b???n ho?? ????n ???? thanh to??n.</p>\n" +
                "<p>Xin vui l??ng ki???m tra th??ng tin b??n d?????i. M???i th???c m???c vui l??ng li??n h??? s??? ??i???n tho???i: 0969.307.886 (Mr. Luan - K??? to??n tr?????ng).</p>\n" +
                "<table class=\"table\" style=\"border: 1px solid; padding: 10px 10px; border-collapse: collapse;\">\n" +
                "  <thead>\n" +
                "  <tr style=\"background-color: antiquewhite;\">\n" +
                "    <th style=\"text-align: center !important; border: 1px solid; padding: 5px 5px;\">M?? ho?? ????n</th>\n" +
                "    <th style=\"text-align: center !important; border: 1px solid; padding: 5px 5px;\">Tr???ng th??i</th>\n" +
                "    <th style=\"text-align: center !important; border: 1px solid; padding: 5px 5px;\">T???ng ti???n</th>\n" +
                "    <th style=\"text-align: center !important; border: 1px solid; padding: 5px 5px;\">Th?? ??i???n t???</th>\n" +
                "    <th style=\"text-align: center !important; border: 1px solid; padding: 5px 5px;\">H??? v?? t??n</th>\n" +
                "    <th style=\"text-align: center !important; border: 1px solid; padding: 5px 5px;\">?????a ch???</th>\n" +
                "    <th style=\"text-align: center !important; border: 1px solid; padding: 5px 5px;\">Th??nh ph???</th>\n" +
                "    <th style=\"text-align: center !important; border: 1px solid; padding: 5px 5px;\">T???nh</th>\n" +
                "    <th style=\"text-align: center !important; border: 1px solid; padding: 5px 5px;\">M?? b??u ch??nh</th>\n" +
                "    <th style=\"text-align: center !important; border: 1px solid; padding: 5px 5px;\">Qu???c gia</th>\n" +
                "  </tr>\n" +
                "  </thead>\n" +
                "  <tbody>\n" +
                "  <tr style=\"background-color: aliceblue;\n\">\n" +
                "    <td style=\"text-align: center !important; border: 1px solid; padding: 5px 5px;\">" + reponseBodyNew.getId() + "</td>\n" +
                "    <td style=\"text-align: center !important; border: 1px solid; padding: 5px 5px;\">Ho??n th??nh</td>\n" +
                "    <td style=\"text-align: center !important; border: 1px solid; padding: 5px 5px;\">" + reponseBodyNew.getValue() + "</td>\n" +
                "    <td style=\"text-align: center !important; border: 1px solid; padding: 5px 5px;\">" + reponseBodyNew.getEmail() + "</td>\n" +
                "    <td style=\"text-align: center !important; border: 1px solid; padding: 5px 5px;\">" + reponseBodyNew.getFullName() + "</td>\n" +
                "    <td style=\"text-align: center !important; border: 1px solid; padding: 5px 5px;\">" + reponseBodyNew.getAddress() + "</td>\n" +
                "    <td style=\"text-align: center !important; border: 1px solid; padding: 5px 5px;\">" + reponseBodyNew.getArea1() + "</td>\n" +
                "    <td style=\"text-align: center !important; border: 1px solid; padding: 5px 5px;\">" + reponseBodyNew.getArea2() + "</td>\n" +
                "    <td style=\"text-align: center !important; border: 1px solid; padding: 5px 5px;\">" + reponseBodyNew.getPostalCode() + "</td>\n" +
                "    <td style=\"text-align: center !important; border: 1px solid; padding: 5px 5px;\">" + reponseBodyNew.getCountryCode() + "</td>\n" +
                "  </tr>\n" +
                "  </tbody>\n" +
                "</table>" +
                "<p style=\"font-size: 11px; color: blue;\"><i>Thanks and best regards</i><br>\n" +
                "_____________________________<br>\n" +
                "<i>Nguyen Dinh Luan ( Mr.) - Accounting.</i><br>\n" +
                "<i>Phone: 0969.307.886.</i><br>\n" +
                "<i>Email: luanqn20@gmail.com.</i></p>\n" +
                "</div>";
        message.setContent(htmlMsg , "text/html; charset=UTF-8");
        helper.setTo("luanqn20@gmail.com");
        helper.setSubject("Thanh to??n th??nh c??ng!");
        this.emailSender.send(message);

        return new ResponseEntity<>("Email Sent!" , HttpStatus.OK);
    }
}
