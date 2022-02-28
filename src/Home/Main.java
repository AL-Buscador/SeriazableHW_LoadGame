package Home;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {

    public static void main(String[] args) {
        String path = "D:\\Мои документы\\Java Files\\JavaHW\\Games\\savegames\\";

        try (ZipInputStream zin = new ZipInputStream(
                new FileInputStream(path + "savedFIles.zip"))) {
            ZipEntry entry;
            String name;

            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName();
                FileOutputStream fout = new FileOutputStream(
                        path + name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }

            GameProgress gameProgress = null;
            File file = new File(path);
            File[] unpackedList = file.listFiles();
            String nameFile = null;
            if (unpackedList[0].isFile())
                nameFile = unpackedList[0].getName();
            try (FileInputStream fis = new FileInputStream(
                    path + nameFile);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                gameProgress = (GameProgress) ois.readObject();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            System.out.println(gameProgress);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}