package com.verma.RedditCloneUIWebService.service;

import com.verma.RedditCloneUIWebService.dto.RegisterRequest;
import com.verma.RedditCloneUIWebService.exception.SpringRedditException;
import com.verma.RedditCloneUIWebService.model.NotificationEmail;
import com.verma.RedditCloneUIWebService.model.User;
import com.verma.RedditCloneUIWebService.model.VerificationToken;
import com.verma.RedditCloneUIWebService.repository.UserRepository;
import com.verma.RedditCloneUIWebService.repository.VerificationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {


//    @Autowired
//    private PasswordEncoder passwordEncoder;
    private final PasswordEncoder passwordEncoder;

//    @Autowired
//    private UserRepository userRepository;
    private final UserRepository userRepository;

//    @Autowired
//    private VerificationTokenRepository verificationTokenRepository;
    private final VerificationTokenRepository verificationTokenRepository;

//    @Autowired
//    private MailService mailService;
    private final MailService mailService;

    @Transactional
    public void signup(RegisterRequest registerRequest){
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreated(Instant.now());
        user.setEnabled(false);
        userRepository.save(user);

        String token = generateVerificationToken(user);
        mailService.sendMail(new NotificationEmail("PLease Activate Your Account", user.getEmail(), "Thank You for signing up to spring Reddit!!!, " +
                "\n\n Please click on the below URL to activate your account : \n"
                + "http://localhost:8080/api/auth/accountVerification/" + token));
    }

    private String generateVerificationToken(User user){
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationTokenRepository.save(verificationToken);
        return token;
    }

    public void verifyAccount(String token){
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        verificationToken.orElseThrow(() -> new SpringRedditException("Invalid Token!!"));
        fetchUserAndEnable(verificationToken.get());
    }

    @Transactional
    private void fetchUserAndEnable(VerificationToken verificationToken){
        String username = verificationToken.getUser().getUsername();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new SpringRedditException("User not found with name : " + username));
        user.setEnabled(true);
        userRepository.save(user);
    }

}
