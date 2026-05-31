package com.kk.kkautogenerator.utils;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public  class FileUtil {
    /**
     * 判断文件是否存在，不存在就创建
     * @param file
     */
    public static void createFile(File file) {
        if (file.exists()) {
            System.out.println("File exists");
        } else {
            System.out.println("File not exists, create it ...");
            //getParentFile() 获取上级目录(包含文件名时无法直接创建目录的)
            if (file.getParentFile() !=null && !file.getParentFile().exists()) {
                System.out.println("not exists");
                //创建上级目录
                file.getParentFile().mkdirs();
            }
            try {
                //在上级目录里创建文件
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void createDir(File file) {
        if (file.exists()) {
            System.out.println("File exists");
        } else {
            System.out.println("File not exists, create it ...");
            //getParentFile() 获取上级目录(包含文件名时无法直接创建目录的)

                //创建上级目录
                file.mkdirs();
        }
    }

    public static void createAndWriteTxtFile(String filePath,String content) {

        FileWriter fw = null;
        try
        {
            File file = new File(filePath);
            createFile(file);
            fw = new FileWriter(filePath);
            fw.write(content);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                fw.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }
    }
    public static void deleteFileAll(String sourcePath)
    {
        File file = new File(sourcePath);//输入要删除文件目录的绝对路径
        if(file.exists()) {
            if (deleteFile(file)) {
                System.out.println("文件删除成功！");
            }
        }
    }
    public static Boolean deleteFile(File file) {
        //判断文件不为null或文件目录存在
        if (file == null || !file.exists()) {
            System.out.println("文件删除失败,请检查文件是否存在以及文件路径是否正确");
            return false;
        }
        //获取目录下子文件
        File[] files = file.listFiles();
        if(files==null) return false;
        //遍历该目录下的文件对象
        for (File f : files) {
            //判断子目录是否存在子目录,如果是文件则删除
            if (f.isDirectory()) {
                //递归删除目录下的文件
                deleteFile(f);
            } else {
                //文件删除
                f.delete();
                //打印文件名
                System.out.println("文件名：" + f.getName());
            }
        }
        //文件夹删除
        file.delete();
        System.out.println("目录名：" + file.getName());
        return true;
    }

    /**
     * sourceFile一定要是文件夹
     * 默认会在同目录下生成zip文件
     *
     * @param sourceFilePath
     * @throws Exception
     */
    public static String fileToZip(String sourceFilePath) throws Exception {
        fileToZip(new File(sourceFilePath));
        return sourceFilePath + ".zip";
    }

    /**
     * sourceFile一定要是文件夹
     * 默认会在同目录下生成zip文件
     *
     * @param sourceFile
     * @throws Exception
     */
    public static void fileToZip(File sourceFile) throws Exception {

        if (!sourceFile.exists()) {
            throw new RuntimeException("不存在");
        }
        if (!sourceFile.isDirectory()) {
            throw new RuntimeException("不是文件夹");
        }
        //zip文件生成位置
        File zipFile = new File(sourceFile.getAbsolutePath() + ".zip");
        FileOutputStream fos = new FileOutputStream(zipFile);
        ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(fos));
        fileToZip(zos, sourceFile, "");
        zos.close();
        fos.close();
    }


    private static void fileToZip(ZipOutputStream zos, File sourceFile, String path) throws Exception {

        System.out.println(sourceFile.getAbsolutePath());

        //如果是文件夹只创建zip实体即可，如果是文件，创建zip实体后还要读取文件内容并写入
        if (sourceFile.isDirectory()) {
            path = path + sourceFile.getName() + "/";
            ZipEntry zipEntry = new ZipEntry(path);
            zos.putNextEntry(zipEntry);
            for (File file : sourceFile.listFiles()) {
                fileToZip(zos, file, path);
            }
        } else {
            //创建ZIP实体，并添加进压缩包
            ZipEntry zipEntry = new ZipEntry(path + sourceFile.getName());
            zos.putNextEntry(zipEntry);
            byte[] bufs = new byte[1024 * 10];
            //读取待压缩的文件并写进压缩包里
            FileInputStream fis = new FileInputStream(sourceFile);
            BufferedInputStream bis = new BufferedInputStream(fis, 1024 * 10);
            int read = 0;
            while ((read = bis.read(bufs, 0, 1024 * 10)) != -1) {
                zos.write(bufs, 0, read);
            }
            bis.close();
            fis.close();
        }
    }

}
