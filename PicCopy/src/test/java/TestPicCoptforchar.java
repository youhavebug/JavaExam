import javax.swing.*;
import java.io.File;

public class TestPicCoptforchar {
    public static void main1(String[] args) {
        PicCopyforchar piccopyforchar = null;
        JFileChooser jFileChooser = new JFileChooser();
        int i = jFileChooser.showOpenDialog(null);

        if (i == 0) {
            File selectedFile = jFileChooser.getSelectedFile();
            piccopyforchar = new PicCopyforchar(selectedFile.getAbsolutePath());
            jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int j = jFileChooser.showOpenDialog(null);
            if (j == 0) {
                piccopyforchar.uploadPic(jFileChooser.getSelectedFile().getParent());
            }
            //piccopyforchar.close();
        }
    }
    public static void main(String[] args) {
        PicCopyforstream piccopyforchar = null;
        JFileChooser jFileChooser = new JFileChooser();
        int i = jFileChooser.showOpenDialog(null);

        if (i == 0) {
            File selectedFile = jFileChooser.getSelectedFile();
            piccopyforchar = new PicCopyforstream(selectedFile.getAbsolutePath());
            System.out.println("--"+selectedFile.getAbsolutePath());
            jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int j = jFileChooser.showOpenDialog(null);
            if (j == 0) {
                piccopyforchar.uploadPic(jFileChooser.getSelectedFile().getPath());
                System.out.println("---"+jFileChooser.getSelectedFile().getPath());
            }
            //piccopyforchar.close();
        }
    }
}
