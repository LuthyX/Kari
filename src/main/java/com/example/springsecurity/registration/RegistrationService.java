//package com.example.springsecurity.registration;
//
//import com.example.springsecurity.models.AppUser;
//import com.example.springsecurity.appuser.AppUserRole;
//import com.example.springsecurity.services.AppUserService;
//import com.example.springsecurity.registration.token.ConfirmationToken;
//import com.example.springsecurity.registration.token.ConfirmationTokenService;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDateTime;
//
//@Service
//@AllArgsConstructor
//public class RegistrationService {
//    private EmailValidator emailValidator;
//    private final AppUserService appUserService;
//    private final ConfirmationTokenService confirmationTokenService;
//    public String register(RegistrationRequest request){
//    boolean isValidEmail = emailValidator.test(request.getEmail());
//    if (
//            !isValidEmail
//    ) {
//        throw new IllegalStateException("Email is not Valid");
//    }
//    return appUserService.signUpUser(
//            new AppUser(
//                    request.getFirstName(),
//                    request.getLastName(),
//                    request.getEmail(),
//                    request.getPassword()
//            )
//    );
//    }
//
//    @Transactional
//    public String confirmToken(String token){
//        ConfirmationToken confirmationToken = confirmationTokenService.getToken(token).orElseThrow(() -> new IllegalStateException("token not found"));
//        if (confirmationToken.getConfirmedAt() != null) {
//            throw new IllegalStateException("Email Already Confirmed");
//        }
//        LocalDateTime expiredAt = confirmationToken.getExpiresAt();
//        if (expiredAt.isBefore(LocalDateTime.now())){
//            throw new IllegalStateException("Token Expired");
//        }
//        confirmationTokenService.setConfirmedAt(token);
//        appUserService.enableAppUser(confirmationToken.getAppUser().getEmail());
//        return "confirmed";
//    }
//
//}
