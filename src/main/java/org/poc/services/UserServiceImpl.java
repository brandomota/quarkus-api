package org.poc.services;

import io.smallrye.config.common.utils.StringUtil;
import lombok.NonNull;
import org.dom4j.util.StringUtils;
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
import javax.ws.rs.BadRequestException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

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
    public List<User> findUsers(Map<String,String> filters) {
        Map<String, Object> queryValues = new HashMap<>();
        final AtomicReference<String> query = new AtomicReference<>();

        filters.forEach((key, value) -> {
            switch (key){
                case "name":
                    if (query.get() != null) {
                        query.set(query.get().concat(" AND name LIKE :name"));
                    } else {
                        query.set("name LIKE :name");
                    }
                    queryValues.put("name", "%"+ value + "%");
                    break;
                case "email":
                    if (query.get() != null) {
                        query.set(query.get().concat(" AND email LIKE :email"));
                    } else {
                        query.set("email LIKE :email");
                    }
                    queryValues.put("email", "%"+ value + "%");
                    break;
                default:
                    throw new BadRequestException(String.format("invalid filter: %s", key));
            }
        });

        return this.userRepository.list(query.get(),queryValues);
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
