package Home;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {

    public static void main(String[] args) {
        try (ZipInputStream zin = new ZipInputStream(
                new FileInputStream("D:\\Мои документы\\Java Files\\JavaHW\\Games\\savegames\\savedFIles.zip"))) {
            ZipEntry entry;
            String name;
            entry = zin.getNextEntry();
            name = entry.getName();
            FileOutputStream fout = new FileOutputStream(
                    "D:\\Мои документы\\Java Files\\JavaHW\\Games\\savegames\\" + name);
            for (int c = zin.read(); c != -1; c = zin.read()) {
                fout.write(c);
            }
            fout.flush();
            zin.closeEntry();
            fout.close();

            GameProgress gameProgress = null;
            try (FileInputStream fis = new FileInputStream(
                    "D:\\Мои документы\\Java Files\\JavaHW\\Games\\savegames\\" + name);
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
