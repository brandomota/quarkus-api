package org.poc.services;

import lombok.NonNull;
import org.eclipse.microprofile.config.ConfigProvider;
import org.hibernate.cfg.NotYetImplementedException;
import org.poc.DTOs.UserDTO;
import org.poc.models.User;
import org.poc.repositories.interfaces.UserRepository;
import org.poc.services.interfaces.UserService;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    @Inject
    UserRepository userRepository;

    private static String secretKey;

    public UserServiceImpl() {
        secretKey = ConfigProvider.getConfig().getValue("app.secret-key", String.class);
    }


    @Override
    public List<User> findAll() {
        return this.userRepository.listAll();
    }

    @Override
    public List<User> findUsers(Map<String,String> query) {
        throw new NotYetImplementedException("estudar melhor aplicação de algo similar ao uso do criteria API");
    }

    @Override
    public User createUser(UserDTO dto) throws Exception {
        try {
            var user = User.builder()
                    .email(dto.getEmail())
                    .name(dto.getName())
                    .build();

            user.setPassword(this.generatePasswordHash(dto.getPassword()));

            this.userRepository.persist(user);

            return user;

        }catch (Exception e) {
            throw new Exception(e.toString());
        }

    }

    private String generatePasswordHash(@NonNull final String password)
            throws InvalidKeyException, NoSuchAlgorithmException {

        final byte[] byteKey = secretKey.getBytes(StandardCharsets.UTF_8);
        var sha512Hmac = Mac.getInstance("HmacSHA512");
        SecretKeySpec keySpec = new SecretKeySpec(byteKey, "HmacSHA512");
        sha512Hmac.init(keySpec);
        byte[] macData = sha512Hmac.doFinal(password.getBytes(StandardCharsets.UTF_8));

        return Base64.getEncoder().encodeToString(macData);

    }
}
