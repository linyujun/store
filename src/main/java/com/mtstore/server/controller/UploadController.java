package com.mtstore.server.controller;

/**
 * @author songsir
 * @date 2021/6/15
 **/
import com.mtstore.server.beans.common.R;
import com.mtstore.server.util.helper.FileWriterHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author songsir
 */
@RestController
@CrossOrigin
@Slf4j
@Api(tags="上传模块")
public class UploadController {
    private String[] permitPath = {"images", "avatar", "video", "file"};
    private static String UPLOADED_FOLDER = "upload/";

    /**
     * @param uploadFile
     * @param subDir     上传文件子目录
     * @return
     */
    @ApiOperation("上传文件[images, avatar, video]")
    @PostMapping("/upload/{subDir}")
    public Object uploadFile(
            HttpServletRequest request,
            @ApiParam(name = "upload", value = "文件", required = true)
            @RequestParam("upload") MultipartFile uploadFile,
            @PathVariable("subDir") String subDir) throws IOException {
        if (uploadFile.isEmpty()) {
            throw new RuntimeException("上传文件失败,文件为空！");
        }
        if (!Arrays.asList(permitPath).contains(subDir)) {

            throw new RuntimeException("没有上传该目录的权限!");
        }
        String fileDir = getFileDir(subDir);
//        log.info(fileDir);
        String fullPath = FileWriterHelper.saveToPath(uploadFile, fileDir, true, false);
        Map result = new HashMap<>();
        result.put("url", fullPath);

        return R.ok("上传成功", result);
    }

    private String getFileDir(String subDir) throws RuntimeException, FileNotFoundException {

        return UPLOADED_FOLDER + subDir + "/";
    }
}
