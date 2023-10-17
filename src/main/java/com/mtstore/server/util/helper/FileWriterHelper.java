package com.mtstore.server.util.helper;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;


@Component
@Slf4j
public class FileWriterHelper {

    public static String saveToPath(MultipartFile file, String saveFileDir, boolean isRandomName, boolean unZip) throws IOException {
        String fileName = saveToPath(file, saveFileDir, isRandomName);
        if (unZip) {
            FileUtil.unZipFiles(fileName, "/");
        }
        return fileName;
    }

    /**
     * 保存上传的文件，并对文件进行解压
     * @param file
     * @param saveFileDir  文件上传后的存放路径以及解压后的文件的存放路径
     * @return  解压后execl文件的路径
     * @throws IOException
     */
    public static String saveAndUnZip(MultipartFile file, String saveFileDir) throws IOException {
        String fileName = saveFile(file, saveFileDir);
        String filepath = "";
        // 判断如果文件是zip格式的则进行解压
        if (file !=null && StringUtils.isNotBlank(file.getOriginalFilename())&&file.getOriginalFilename().endsWith(".zip")){
           filepath = FileUtil.unZipFileToZipDir(new File(fileName), saveFileDir+File.separator);
        }else{
            throw new RuntimeException("暂不支持除Zip格式之外的文件！");
        }
        return filepath;
    }

    /**
     * 保存上传的文件，并对文件进行解压
     * @param file
     * @param saveFileDir  文件上传后的存放路径以及解压后的文件的存放路径
     * @return  解压后execl文件的路径
     * @throws IOException
     */
    public static String getUnZipArray(MultipartFile file, String saveFileDir) throws IOException {
        String fileName = saveFile(file, saveFileDir);
        String filepath = "";
        // 判断如果文件是zip格式的则进行解压
        if (file !=null && StringUtils.isNotBlank(file.getOriginalFilename())&&file.getOriginalFilename().endsWith(".zip")){
            File zipFile = new File(fileName);
            filepath = FileUtil.unZipFile(zipFile, saveFileDir+File.separator);
            FileUtil.deleteFile(zipFile);
        }else{
            throw new RuntimeException("暂不支持除Zip格式之外的文件！");
        }
        return filepath;
    }



    /**
     *
     * @param file
     * @param saveFileDir
     * @param isRandomName 是否生成随机文件名
     * @return
     */
    public static String saveToPath(MultipartFile file, String saveFileDir, boolean isRandomName) {
        if (file.isEmpty()) {
            throw new RuntimeException("上传文件失败,文件为空");
        }
        String fileNameLong = file.getOriginalFilename();
//        String fileNameLong = file.getOriginalFilename().toLowerCase().contains("cert") ?
//                  "/cert.zip": file.getOriginalFilename();
        /* 重命名文件 */
        log.info(fileNameLong);
        String saveFileName;
        if (isRandomName) {
            String filename = UUID.randomUUID().toString();
            /* 获取文件扩展名 */
            String extensionName = fileNameLong.substring(fileNameLong.lastIndexOf(".") + 1);
            saveFileName = saveFileDir + filename + "." + extensionName;
        } else {
            saveFileName = saveFileDir + fileNameLong;
        }

        log.info("saveFileName:{},{}",saveFileDir,saveFileName);

        File fil = new File(saveFileName);
        try {
            BufferedOutputStream bf = new BufferedOutputStream(new FileOutputStream(fil));
            bf.write(file.getBytes());
            bf.flush();
            bf.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return saveFileName;
    }



    /**
     *
     * @param file
     * @param saveFileDir
     * @return
     */
    public static String saveFile(MultipartFile file, String saveFileDir) {
        if (file.isEmpty()) {
            throw new RuntimeException("上传文件失败,文件为空");
        }
        File filedir = new File(saveFileDir);
        if (!filedir.exists()){
            filedir.mkdirs();
        }
        String saveFileName  = saveFileDir +File.separator+ file.getOriginalFilename();
        log.info("saveFileName:{},{}",saveFileDir,saveFileName);
        File fil = new File(saveFileName);
        try {
            if (!fil.exists()){
                fil.createNewFile();
            }
            BufferedOutputStream bf = new BufferedOutputStream(new FileOutputStream(fil));
            bf.write(file.getBytes());
            bf.flush();
            bf.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return saveFileName;
    }

    private void saveUploadedFiles(List<MultipartFile> files) throws IOException {
        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue; //next pls
            }

            byte[] bytes = file.getBytes();
            Path path = Paths.get("temp/" + file.getOriginalFilename());
            Files.write(path, bytes);
        }
    }
}
