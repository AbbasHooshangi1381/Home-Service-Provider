package com.example.springbootfinal.email.service;
import com.example.springbootfinal.dto.Expert.ExpertSaveDto;
import com.example.springbootfinal.email.email.EmailSender;
import com.example.springbootfinal.email.security.token.ConfirmationToken;
import com.example.springbootfinal.email.security.token.ConfirmationTokenService;
import com.example.springbootfinal.exception.DuplicateException;
import com.example.springbootfinal.exception.NotFoundException;
import com.example.springbootfinal.dto.registeration.RegistrationRequest;
import com.example.springbootfinal.exception.NotValidException;
import com.example.springbootfinal.service.RegistrationServices;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@SuppressWarnings("unused")
public class BeforeRegistration {

    private final ConfirmationTokenService confirmTokenService;
    private final EmailSender emailSender;
    private final RegistrationServices registrationService;

    public BeforeRegistration( ConfirmationTokenService confirmTokenService,
                              EmailSender emailSender,RegistrationServices registrationService) {
        this.confirmTokenService = confirmTokenService;
        this.emailSender = emailSender;
        this.registrationService=registrationService;
    }

    public String registerForAdmin(RegistrationRequest request) {
             String registrations = registrationService.saveAdmin(request.getFirstName(),
                    request.getLastName(),
                    request.getEmail(),
                    request.getUserName(),
                    request.getPassword());
            String link = "http://localhost:8090/registration/confirm?token=" + registrations;
            emailSender.sendEmail(request.getEmail(), buildEmail(request.getFirstName(), link));
            return registrations;
        }


    public String registerForCustomer(RegistrationRequest request) {
            String registrations = registrationService.saveCustomer(request.getFirstName(),
                    request.getLastName(),
                    request.getEmail(),
                    request.getUserName(),
                    request.getPassword());
            String link = "http://localhost:8090/registration/confirm?token=" + registrations;
            emailSender.sendEmail(request.getEmail(), buildEmail(request.getFirstName(), link));
            return registrations;
    }

    public String registerForExpert(ExpertSaveDto expertSaveDto) throws IOException {
            String registrations = registrationService.saveExpert(expertSaveDto.getFirstName(),
                    expertSaveDto.getLastName(),
                    expertSaveDto.getEmail(),
                    expertSaveDto.getUserName(),
                   expertSaveDto.getFilePath(),
                    expertSaveDto.getPassword());
            String link = "http://localhost:8090/registration/confirm?token=" + registrations;
            emailSender.sendEmail(expertSaveDto.getEmail(), buildEmail(expertSaveDto.getFirstName(), link));
            return registrations;
    }

    @Transactional
    public String confirmToken(String token) {
        Optional<ConfirmationToken> confirmToken = confirmTokenService.getToken(token);
        if (confirmToken.isEmpty()) {
            throw new NotFoundException("Token not found!");
        }
        LocalDateTime expiresAt = confirmToken.get().getExpiresAt();
        if (expiresAt.isBefore(LocalDateTime.now())) {
            throw new NotValidException("Token is already expired!");
        }
        if (confirmToken.get().getConfirmedAt() != null) {
            throw new DuplicateException("Email is already confirmed");
        }
        confirmTokenService.setConfirmedAt(token);
        registrationService.enableAppUser(confirmToken.get().getBaseUser().getEmail());
        return "Your email is confirmed. Thank you for using our service!";
    }















    private String buildEmail(String name, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }
}
