package com.example.softunifinalproject.config;

import com.cloudinary.Cloudinary;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import java.util.Map;

@Configuration
public class ApplicationConfiguration {

    private final CloudinaryConfig cloudConfig;

    public ApplicationConfiguration(CloudinaryConfig cloudConfig) {
        this.cloudConfig = cloudConfig;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Pbkdf2PasswordEncoder();
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }


    @Bean
    public Cloudinary cloudinary(){
       return new Cloudinary(
               Map.of(
                       "cloud_name", cloudConfig.getCloudName(),
                       "api_key", cloudConfig.getApiKey(),
                       "api_secret", cloudConfig.getApiSecret()
               )
       );
    }
}
