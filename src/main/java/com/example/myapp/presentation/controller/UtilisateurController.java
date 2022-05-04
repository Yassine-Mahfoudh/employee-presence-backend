package com.example.myapp.presentation.controller;

import com.example.myapp.business.service.ILogDataService;
import com.example.myapp.business.service.IUtilisateurService;
import com.example.myapp.business.service.impl.UtilisateurService;
import com.example.myapp.persistence.model.*;
import com.example.myapp.presentation.Utils.Utility;
import lombok.AllArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import org.springframework.core.env.Environment;


@RestController
@RequestMapping(path = "/utilisateur")
@AllArgsConstructor
public class UtilisateurController {


    @Autowired
    private final IUtilisateurService iUtilisateurService;
    @Autowired
    private final ILogDataService iLogDataService;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private MessageSource messages;
    @Autowired
    private Environment env;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_RH')")
    @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Utilisateur> getUtilisateurById (@PathVariable("id") Long id) {
       try{ Utilisateur utilisateur = iUtilisateurService.getUtilisateurById(id);
        return new ResponseEntity<>(utilisateur, HttpStatus.OK);}
       catch (Exception e){
           throw new IllegalStateException("Error UtilisateurController in method getUtilisateurById:" +e.toString());
       }
    }

    @GetMapping(value = "/find/name/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Utilisateur> getUtilisateurByuserName (@PathVariable("userName") String userName) {
        try{ Utilisateur utilisateur = iUtilisateurService.getUtilisateurByuserName(userName);
            return new ResponseEntity<>(utilisateur, HttpStatus.OK);}
        catch (Exception e){
            throw new IllegalStateException("Error UtilisateurController in method getUtilisateurByuserName:" +e.toString());
        }
    }



    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_RH')")
    @GetMapping
    public List<Utilisateur> getListUtilisateur() {
        try {
            return iUtilisateurService.getListUtilisateur();
        }catch(Exception e){
            throw new IllegalStateException("Error UtilisateurController in method getListUtilisateur:" +e.toString());
        }

    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Utilisateur addUtilisateur(@RequestBody Utilisateur obj){
       try{
          return iUtilisateurService.addUtilisateur(obj);
       }catch(Exception e){
           throw new IllegalStateException("Error UtilisateurController in method addUtilisateur:" +e.toString());
       }

    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping(value="/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteUtilisateur(@PathVariable("id") Long id) {
        try{
            iUtilisateurService.deleteUtilisateur(id);
        return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            throw new IllegalStateException("Error UtilisateurController in method deleteUtilisateur:" +e.toString());
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Utilisateur> updateUtilisateur(@RequestBody Utilisateur obj,  @PathVariable("id")  Long id) {
        try {
            Utilisateur updateUtilisateur = iUtilisateurService.updateUtilisateur(obj, id);
        return new ResponseEntity<>(updateUtilisateur, HttpStatus.OK);
    }
        catch(Exception e){
        throw new IllegalStateException("Error UtilisateurController in method updateUtilisateur:" +e.toString());
        }
    }

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName() {
        try {
            return iUtilisateurService.currentUserName();
        }catch (Exception e){
            e.printStackTrace();
        }
        return new String(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
    }

@PostMapping("/forgotPassword")
public  ResponseEntity<String> processForgotPassword(HttpServletRequest request,@RequestBody Map<String,String> requestMap) {
    String email = requestMap.get("email");
    String token = RandomString.make(30);

    try {
        iUtilisateurService.updateResetPasswordToken(token, email);
        String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;
        sendEmail(email, resetPasswordLink);
        return new ResponseEntity<>(HttpStatus.OK);


    } catch (Exception ex) {
        ex.printStackTrace();
    }

    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
}

    public void sendEmail(String recipientEmail, String link)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("ymahfoudh55@gmail.com", "ST2i managment");
        helper.setTo(recipientEmail);

        String subject = "Here's the link to reset your password";

        String content = "<p>Hello,</p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=\"" + link + "\">Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";

        helper.setSubject(subject);

        helper.setText(content, true);

        mailSender.send(message);
    }


    @PostMapping(path = "/changePassword")
    ResponseEntity<String> changePassword(@RequestBody Map<String,String> requestMap){
        try {

            return iUtilisateurService.changePassword(requestMap);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
