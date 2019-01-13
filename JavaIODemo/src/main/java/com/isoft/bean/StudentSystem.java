package com.isoft.bean;
import org.testng.annotations.Test;
import java.io.*;
import java.lang.String;
import javax.swing.*;
import java.util.*;
import static com.sun.deploy.cache.Cache.exists;

public class StudentSystem {
    List<Map<String,String>> userList;
    FileWriter fw;
    BufferedWriter bw;

    FileReader fr;
    BufferedReader br;

    RandomAccessFile raf;
    Scanner sc;
    String filename;
    public void login() throws IOException {
        System.out.println("学生信息管理系统");
        System.out.println("请输入用户名：");
        String name=sc.next();
        System.out.println("请输入密码：");
        String pwd=sc.next();
        Map map=new Hashtable();
        map.put(name,pwd);
        if(userList.contains(map)){
            System.out.print("欢迎");
            System.err.print(name);
            System.out.println("登录成功");
            StartSystem();
        }else{
            System.out.println("登录失败");
        }

    }
    public StudentSystem() throws IOException {
        userList=new ArrayList<Map<String,String>>();
        Map map1=new Hashtable();
        map1.put("zhangsan","111111");
        userList.add(map1);
        Map map2=new Hashtable();
        map2.put("lisi","222222");
        userList.add(map2);
        Map map3=new Hashtable();
        map3.put("wangwu","333333");
        userList.add(map3);

        filename="studentInfo.txt";
        fw=new FileWriter(filename,true);
        //fr=new FileReader(filename);
        bw=new BufferedWriter(fw);
        //br=new BufferedReader(fr);
        sc=new Scanner(System.in);
        raf=new RandomAccessFile(filename,"r");
    }

    @Test
    public void StartSystem() throws IOException{//alt+return 解决问题
        /*InputStreamReader isr=new InputStreamReader(System.in);//字符流
        BufferedReader br=new BufferedReader(isr);*/
        /*System.out.println("test system.in");
        Scanner scanner=new Scanner(System.in);
        System.out.println(scanner.nextLine());*/
        boolean temp=true;
        while(temp) {
            Menu.startMenu();
            // String op=br.readLine();
            System.out.println();
            //String op = JOptionPane.showInputDialog("请输入用户选择");
            String op=sc.next();
            switch (op) {
                case "1":
                    addStudentInfo();
                    break;
                case "2":
                    findStudentInfo();
                    break;
                case "3":
                    findAllStudentinfo();
                    break;
                case "4":
                    deleteStudentinfo();
                    break;
                case "5":
                    exitsSystem();
                    break;
            }
            /*int i = JOptionPane.showConfirmDialog(null, "是否继续使用本系统", "提示",
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);*/
            System.out.println("是否继续使用本系统？（1：是，0：否）");
            int i=sc.nextInt();
            if (i == 0)
                temp = false;
        }
        System.out.println("欢迎下次再来！");
        bw.close();
        fw.close();
        raf.close();

    }

    public void addStudentInfo() throws  IOException{
        boolean temp=true;
        while(temp) {
           /* String sid = JOptionPane.showInputDialog("请输入学号：");
            String name = JOptionPane.showInputDialog("请输入姓名：");
            String sex = JOptionPane.showInputDialog("请输入性别：");
            String score = JOptionPane.showInputDialog("请输入成绩：");*/
            System.out.println("请输入学号：");
            String sid=sc.next();
            System.out.println("请输入姓名：");
            String name=sc.next();
            System.out.println("请输入性别：");
            String sex=sc.next();
            System.out.println("请输入成绩：");
            String score=sc.next();
            bw.write(sid+","+name+","+sex+","+score);
            bw.newLine();
            bw.flush();//ctrl+shift+/   用于注释
            /*int i = JOptionPane.showConfirmDialog(null, "是否继续输入","提示",
                    JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);*/
            System.out.println("是否继续录入学生记录？（1：是，0：否）");
            int i=sc.nextInt();
            if (i == 0)
                temp=false;
        }
    }
    public void findStudentInfo() throws IOException {
        raf.seek(0);
        System.out.println("请输入查找学生的学号");
        //String sid = JOptionPane.showInputDialog("请输入学号：");
        String sid=sc.next();
        System.out.println("查找学生信息");
        System.out.println("————————————");
        int temp=0;
        String str=raf.readLine();
        while(str!=null){
            String[] strarr = str.split(",");
            if(strarr[0].equalsIgnoreCase(sid)) {//字符串比较是，不用==，用equals（不区分大小写）
                temp+=1;
                System.out.println(temp+"、学号："+strarr[0]+"，姓名："+charsetconvert.getutf8(strarr[1])
                        +"，性别："+charsetconvert.getutf8(strarr[2])+"，成绩："+strarr[3]+"分");
            }
            str=raf.readLine();
        }
        if(temp==0)
            System.out.println("没找到学生信息");
        System.out.println("————————————");
    }
    private void findAllStudentinfo() throws IOException {

        try {
            //String rowStr = br.readLine();
            raf.seek(0);
            String rowStr;
            System.out.println("查找全部学生信息");
            System.out.println("————————————");
            int temp=0;
            while ((rowStr=raf.readLine()) != null) {
                temp++;
                String[] strarr = rowStr.split(",");
                System.out.println(temp+"、学号："+strarr[0]+"，姓名："+charsetconvert.getutf8(strarr[1])
                        +"，性别："+charsetconvert.getutf8(strarr[2])+"，成绩："+strarr[3]+"分");
                //rowStr=br.readLine();
            }
            if(temp==0)
                System.out.println("没找到学生信息");
            System.out.println("————————————");
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    private void deleteStudentinfo() throws IOException {//ctrl加点击函数可以跳到函数体
        raf.seek(0);
        System.out.println("请输入要删除学生学号：");
        System.out.println("————————————");
        String sid=sc.next();
        String str=null;
        ArrayList<String> stuList=new ArrayList<>();
        while((str=raf.readLine())!=null){
            if(!str.split(",")[0].equalsIgnoreCase(sid)){
                stuList.add(str);
            }
        }
        fw=new FileWriter(filename);
        Iterator<String> iterator = stuList.iterator();
        while(iterator.hasNext()) {
            String row = iterator.next();
            bw.write(row);
            bw.newLine();
            bw.flush();
        }
        System.out.println("————————————");
    }
    private void exitsSystem() {
        /*int temp = JOptionPane.showConfirmDialog(null, "是否真的要推出系统？", "推出提示",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);*/
        System.out.println("是否退出本系统？（1：是，0：否）");
        int i=sc.nextInt();
        if (i == 1)
            System.exit(0);
    }

}
