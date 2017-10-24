import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileAndFolderCopy {
    public static void copyFile(File from, File to) throws IOException {
        if (from == null || to == null) {
            throw new IllegalArgumentException("Null file pointer");
        }
        try (FileInputStream fileInputStream = new FileInputStream(from); FileOutputStream fos = new FileOutputStream(to)) {
            byte[] buffer = new byte[1024 * 1024];
            int readByte;
            for (; (readByte = fileInputStream.read(buffer)) > 0;) {
                fos.write(buffer, 0, readByte);
            }
        }

    }

    public static void copyFolder(File folderFrom, File folderTo, FileFilter fileFilter) throws IOException {
        if (folderFrom == null || folderTo == null || fileFilter == null) {
            throw new IllegalArgumentException("Null file pointer");
        }

        File[] files = folderFrom.listFiles(fileFilter);
        for (File file : files) {
            copyFile(file, new File(folderTo, file.getName()));
        }
    }
} 
