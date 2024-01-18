package image;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ImageInput {
    public static byte[] uploadProfilePicture(String filePath) throws IOException {
        File file = new File(filePath);

        long fileSize = file.length();
        if (fileSize > 300 * 1024) {
            throw new IllegalArgumentException("The size of the your photo is more than 300 KB.");
        }

        return Files.readAllBytes(file.toPath());
    }
}
