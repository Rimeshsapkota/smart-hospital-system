package com.smarthospital.common_lib.shared.image;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@AllArgsConstructor
public class Image {
    public static HttpHeaders headers=new HttpHeaders();
    public static  String screenshotId;
    public static Byte[] uploadImage(MultipartFile screenshot) throws IOException {
        // Generate a unique screenshot ID or use any other logic as needed
//        screenshotId = UUID.randomUUID().toString();

        // Convert the screenshot content to a byte array
        byte[] screenshotData = screenshot.getBytes();

        // Convert the byte array to Byte array
        Byte[] screenshotBytes = new Byte[screenshotData.length];
        for (int i = 0; i < screenshotData.length; i++) {
            screenshotBytes[i] = screenshotData[i];
        }
        return screenshotBytes;
    }

    public static byte[] getImage(byte[] screenshotBytes){
        // Convert the Byte array to byte array
        byte[] screenshotData = new byte[screenshotBytes.length];
        for (int i = 0; i < screenshotBytes.length; i++) {
            screenshotData[i] = screenshotBytes[i];
        }
        // Set the response headers
        headers.setContentType(MediaType.IMAGE_JPEG);
        return screenshotData;
    }


}
