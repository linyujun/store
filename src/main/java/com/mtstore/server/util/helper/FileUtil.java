package com.mtstore.server.util.helper;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.poi.excel.BigExcelWriter;
import cn.hutool.poi.excel.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

@Slf4j
public class FileUtil {

    /**
     * 解压到指定目录
     *
     * @param zipPath
     * @param descDir
     */
    public static void unZipFiles(String zipPath, String descDir) throws IOException {
        unZipFiles(new File(zipPath), descDir);
    }


    /**
     * 解压文件到指定目录
     * 解压后的文件名，和之前一致
     *
     * @param zipFile 待解压的zip文件
     * @param descDir 指定目录
     * @return 解压的文件名
     */
    @SuppressWarnings("rawtypes")
    public static void unZipFiles(File zipFile, String descDir) throws IOException {
        log.info("=======开始解压=source:{},target:{}======", zipFile, descDir);
        ZipFile zip = new ZipFile(zipFile);//解决中文文件夹乱码
        String name = zip.getName().substring(zip.getName().lastIndexOf('\\') + 1, zip.getName().lastIndexOf('.'));

        File pathFile = new File(descDir + name);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }

        for (Enumeration<? extends ZipEntry> entries = zip.entries(); entries.hasMoreElements(); ) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            String zipEntryName = entry.getName();
            InputStream in = zip.getInputStream(entry);
            String outPath = (descDir + name + "/" + zipEntryName).replaceAll("\\*", "/");

            // 判断路径是否存在,不存在则创建文件路径
            File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
            if (!file.exists()) {
                file.mkdirs();
            }
            // 判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
            if (new File(outPath).isDirectory()) {
                continue;
            }


            FileOutputStream out = new FileOutputStream(outPath);
            byte[] buf1 = new byte[1024];
            int len;
            while ((len = in.read(buf1)) > 0) {
                out.write(buf1, 0, len);
            }
            in.close();
            out.close();
        }
        zip.close();
        log.info("===============解压完成====================");
        return;
    }


    /**
     * 解压文件到指定目录
     * 解压后的文件名，和之前一致
     *
     * @param zipFile 待解压的zip文件
     * @param descDir 指定目录
     * @return 解压后的execl文件路径
     */
    @SuppressWarnings("rawtypes")
    public static String unZipFileToZipDir(File zipFile, String descDir) throws IOException {
        log.info("=======开始解压=source:{},target:{}======", zipFile, descDir);
        //解决中文文件夹乱码
        ZipFile zip = new ZipFile(zipFile, Charset.forName("gbk"));
        File pathFile = new File(descDir);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }
        // 解压路径
        String outPath = "";
        // 用于返回Execl的文件路径
        String returnPath = "";
        descDir = descDir.substring(0, descDir.lastIndexOf(File.separator)) + "/" + descDir.substring(descDir.lastIndexOf(File.separator) + 1, descDir.length());
        int zipcount = 0;
        for (Enumeration<? extends ZipEntry> entries = zip.entries(); entries.hasMoreElements(); ) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            String zipEntryName = entry.getName();
            InputStream in = zip.getInputStream(entry);
            // 以文件上传的路径来判断文件解压的路径（类似于windows中的解压到当前文件夹）
            outPath = (descDir + zipEntryName).replaceAll("\\*", "/");
            // 判断路径是否存在,不存在则创建文件路径
            String filePath = outPath.lastIndexOf("/") > 0 ? outPath.substring(0, outPath.lastIndexOf('/')) : outPath;
            File file = new File(filePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            // 判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
            if (new File(outPath).isDirectory()) {
                continue;
            }
            if (outPath.endsWith(".xls")) {
                zipcount++;
                if (zipcount > 1) {
                    throw new RuntimeException("压缩包内只能存在一个xls类型文件！");
                }
                returnPath = outPath;
            }
            FileOutputStream out = new FileOutputStream(outPath);
            byte[] buf1 = new byte[1024];
            int len;
            while ((len = in.read(buf1)) > 0) {
                out.write(buf1, 0, len);
            }
            in.close();
            out.close();
        }
        //关闭zip文件
        zip.close();
        log.info("===============解压完成====================");
        return returnPath;
    }

    /**
     * 解压文件到指定目录
     * 解压后的文件名，和之前一致
     *
     * @param zipFile 待解压的zip文件
     * @param descDir 指定目录
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static String unZipFile(File zipFile, String descDir) throws IOException {
        log.info("=======开始解压=source:{},target:{}======", zipFile, descDir);
        ZipFile zip = new ZipFile(zipFile);//解决中文文件夹乱码
        String name = zip.getName().substring(zip.getName().lastIndexOf('\\') + 1, zip.getName().lastIndexOf('.'));

        File pathFile = new File(descDir);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }
        for (Enumeration<? extends ZipEntry> entries = zip.entries(); entries.hasMoreElements(); ) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            String zipEntryName = entry.getName();
            // 跳过文件夹
            if (entry.isDirectory()){
                continue;
            }
            InputStream in = zip.getInputStream(entry);
            // 将所有文件保存到指定的文件夹
            String outPath = (descDir  + File.separator + zipEntryName
                    .substring(zipEntryName.lastIndexOf(File.separator)+1)).replaceAll("\\*", "/");
            // 判断路径是否存在,不存在则创建文件路径
            File file = new File(outPath.substring(0, outPath.lastIndexOf(File.separator)));
            if (!file.exists()) {
                file.mkdirs();
            }
            // 判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
            if (new File(outPath).isDirectory()) {
                continue;
            }
            FileOutputStream out = new FileOutputStream(outPath);
            byte[] buf1 = new byte[1024];
            int len;
            while ((len = in.read(buf1)) > 0) {
                out.write(buf1, 0, len);
            }
            in.close();
            out.close();
        }
        zip.close();
        log.info("===============解压完成====================");
        return pathFile.getPath();
    }


    /**
     * 递归删除文件
     *
     * @param file
     */
    public static void deleteFile(File file) {
        if (file.isDirectory()) {
            File[] subfiles = file.listFiles();
            for (File fil : subfiles
            ) {
                deleteFile(fil);
            }
            file.delete();
        } else {
            file.delete();
        }
    }

    /**
     * File类转换成MultipartFile
     *
     * @param picPath 文件路径
     * @return
     */
    public static MultipartFile getMulFileByPath(String picPath) {
        FileItem fileItem = createFileItem(picPath);
        MultipartFile mfile = new CommonsMultipartFile(fileItem);
        return mfile;
    }

    /**
     * 生成FileItem对象
     *
     * @param filePath
     * @return
     */
    private static FileItem createFileItem(String filePath) {
        FileItemFactory factory = new DiskFileItemFactory(16, null);
        String textFieldName = "textField";
        int num = filePath.lastIndexOf(".");
        String extFile = filePath.substring(num);
        FileItem item = factory.createItem(textFieldName, "text/plain", true,
                "MyFileName" + extFile);
        File newfile = new File(filePath);
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        try {
            FileInputStream fis = new FileInputStream(newfile);
            OutputStream os = item.getOutputStream();
            while ((bytesRead = fis.read(buffer, 0, 8192))
                    != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return item;
    }

    /**
     * 保存文件
     * @param file 上传上来的文件对象
     * @param fileDir 要保存的目录
     * @throws IOException
     */
    public static void saveFile(MultipartFile file, String fileDir) throws IOException {
        File fil = new File(fileDir);
        // 判断目录是否存在，如果不存在则先创建目录，再创建文件
        if (!fil.getParentFile().exists()){
           fil.getParentFile().mkdirs();
           fil.createNewFile();
        }
        try (
                InputStream in = file.getInputStream();
                FileOutputStream out = new FileOutputStream(fileDir)
        ) {
            byte[] buf1 = new byte[1024];
            int len;
            while ((len = in.read(buf1)) > 0) {
                out.write(buf1, 0, len);
            }
        }
    }

    /**
     * 导出excel
     */
    public static void downloadExcel(List<Map<String, Object>> list, HttpServletResponse response) throws IOException {
        String tempPath =System.getProperty("java.io.tmpdir") + IdUtil.fastSimpleUUID() + ".xlsx";
        File file = new File(tempPath);
        BigExcelWriter writer= ExcelUtil.getBigWriter(file);
        // 一次性写出内容，使用默认样式，强制输出标题
        writer.write(list, true);
        //response为HttpServletResponse对象
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        //test.xls是弹出下载对话框的文件名，不能为中文，中文请自行编码
        response.setHeader("Content-Disposition","attachment;filename=file.xlsx");
        ServletOutputStream out=response.getOutputStream();
        // 终止后删除临时文件
        file.deleteOnExit();
        writer.flush(out, true);
        //此处记得关闭输出Servlet流
        IoUtil.close(out);
    }

    //测试
    public static void main(String[] args) {
        try {
            String s = unZipFile(new File("D:/pic.zip"), "upgrade/"+ UUID.randomUUID().toString()+"/");
            System.out.println(s);
            File file = new File(s);
            if (file.isDirectory()){
                File[] files = file.listFiles();
                for (File file1 : files) {
                    System.out.println(file.getName());
                }
            }
            //deleteFile(file);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
