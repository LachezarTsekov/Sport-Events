package com.example.softunifinalproject.service.impl;

import com.cloudinary.Cloudinary;
import com.example.softunifinalproject.service.CloudinaryService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {

    private static final String TEMP_FILE = "temp-file";
    private static final String URL = "url";
    private static final String PUBLIC_ID = "public_id";

    private final Cloudinary cloudinary;

    public CloudinaryServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public CloudinaryImage upload(MultipartFile file) throws IOException {


        File tempFile = File.createTempFile(TEMP_FILE, file.getOriginalFilename());
        file.transferTo(tempFile);


        try {
            @SuppressWarnings("unchecked")
            Map<String, String> result = cloudinary
                    .uploader()
                    .upload(tempFile, Map.of());

            String url = result.getOrDefault(URL, "https://bitsofco.de/content/images/2018/12/broken-1.png");
            String publicId = result.getOrDefault(PUBLIC_ID, "");

            return new CloudinaryImage()
                    .setPublicId(publicId)
                    .setUrl(url);
        }finally {
            tempFile.delete();
        }

    }

    @Override
    public boolean delete(String publicId) {
        try {
            this.cloudinary.uploader().destroy(publicId, Map.of());
        }catch (IOException exception){
            return false;
        }

        return true;
    }
}
