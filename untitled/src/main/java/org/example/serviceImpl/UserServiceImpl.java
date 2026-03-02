package org.example.serviceImpl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.Base64Codec;
import org.example.Service.UserService;
import org.example.model.User.User;
import org.example.repository.UserRepository;
import org.example.request.UserRequestDto;
import org.example.response.DataResponse;
import org.example.response.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.*;

public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public RestResponse register(UserRequestDto userRequestDto) {
        Optional<User> userCheck =  userRepository.findByEmail(userRequestDto.getEmail());

        if (userCheck.isPresent()){
            throw new RuntimeException("Email Already Exist"); // will change to customize exception for user already exist
        }

        User user = new User();
        user.setEmail(userRequestDto.getEmail());
        user.setRole(userRequestDto.getRole());
        user.setName(userRequestDto.getName());
        user.setPassword(bCryptPasswordEncoder.encode(userRequestDto.getPassword()));

        User user1 = userRepository.save(user);

        DataResponse ds = new DataResponse(201,"Success", getToken(user1)); // will change it into auth service

        return ds;

    }

    // will change it into authentication service
    @Override
    public RestResponse login(UserRequestDto userRequestDto) {

        Optional<User> userCheck = userRepository.findByEmail(userRequestDto.getEmail());

        if (!userCheck.isPresent()){
            return new DataResponse(401, "Invalid user id and password",null);
        }

        User user = userCheck.get();

        if (!bCryptPasswordEncoder.matches(userRequestDto.getPassword(), user.getPassword())){
            return new DataResponse(401, "Invalid user id and password",null);
        }
        else {
            return new DataResponse(201, "Success", this.getToken(user));
        }

    }

    @Override
    public RestResponse getUserDeatils(long userId) {
        return new DataResponse(200, "Success", userRepository.findById(userId).get());
    }

    private String getToken(User user){
        final Map<String, Object> claims = new HashMap<>();
        claims.put("ROLES",user.getRole().getDatabaseColumnValue());
        claims.put("USERID", (user.getId()));
        return Jwts.builder().setClaims(claims).setExpiration(new Date(System.currentTimeMillis() + 864000000l))
                .signWith(SignatureAlgorithm.RS256, this.getJWTSigningKey()).compact();

    }

    public PrivateKey getJWTSigningKey() {
        ClassPathResource resource = new ClassPathResource("keystore/jwt_private_key.pem");
        String temp = null;
        try {
            temp = new String(StreamUtils.copyToByteArray(resource.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String privKeyPEM = temp.replace("-----BEGIN PRIVATE KEY-----\n", "");
        privKeyPEM = privKeyPEM.replace("-----END PRIVATE KEY-----", "");
        Base64Codec b64 = new Base64Codec();
        byte[] decoded = b64.decode(privKeyPEM);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decoded);
        KeyFactory kf = null;
        try {
            kf = KeyFactory.getInstance("RSA");
            return kf.generatePrivate(spec);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }

    }


}
