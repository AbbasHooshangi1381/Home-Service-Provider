package com.example.springbootfinal.image;
import org.apache.commons.io.FilenameUtils;

import javax.imageio.ImageIO;
import java.awt.*;
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

            throw new IllegalArgumentException("Please upload a photo with JPG extension.");

        }

        Image image = ImageIO.read(file);
        if (image == null) {
            throw new IllegalArgumentException("The provided file is not a valid image.");
        }

        return Files.readAllBytes(file.toPath());
    }

    public static boolean isImageExists(File file) {
        return file.exists() && file.isFile();
    }
}

