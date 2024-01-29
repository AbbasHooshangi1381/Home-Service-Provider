package com.example.springbootfinal.image;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
public class ImageInput {
    public static byte[] uploadProfilePicture(String filePath) throws IOException {
        File file = new File(filePath);

        long fileSize = file.length();
        if (fileSize > 300 * 1024) {
            System.out.println("The size of the your photo is more than 300 KB.");
        }

        String extension = FilenameUtils.getExtension(filePath);
        if (!extension.equalsIgnoreCase("jpg")) {
            System.out.println("Please upload a photo with JPG extension.");
        }

        return Files.readAllBytes(file.toPath());
    }

    public static boolean isImageExists(String filePath) {
        File file = new File(filePath);
        return file.exists() && file.isFile();
    }
}

