import javax.swing.*;
import java.io.*;
import java.util.Date;

public class PicCopyforchar {
    FileReader fr;
    FileWriter fw;
    String extName;

    public PicCopyforchar(String picPath) {
        File file = new File(picPath);
        if(!file.exists()){
            JOptionPane.showConfirmDialog(null,"输入图片不存在");
            return;
        }
        else {
            try {
                extName = picPath.substring(picPath.lastIndexOf("."));
                fr = new FileReader(file);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void uploadPic(String targetPath) {
        File file = new File(targetPath + "/" + new Date().getTime() + extName);
        try {
            fw = new FileWriter(file);
            int length;
            char[] size = new char[1024];
            while ((length = fr.read()) != -1) {
                fw.write(size, 0, length);
                fw.flush();
            }
            fw.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("文件上传成功");
    }
 public void close(){
        try{
            if(fw!=null)
                fw.close();
            if(fr!=null){
                fr.close();
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
