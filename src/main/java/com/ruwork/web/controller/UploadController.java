package com.ruwork.web.controller;

import com.ruwork.web.entity.FileData;
import com.ruwork.web.model.CustomerModel;
import com.ruwork.web.model.SaleModel;
import com.ruwork.web.service.ExcelFileProcessService;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@Log
public class UploadController {

    private ExcelFileProcessService excelFileProcessService;

    @RequestMapping(path = "/upload")
    public String upload(){
        return "upload";


    }

    @PostMapping(path = "/uploading")
    @ResponseBody
    public String uploading(@RequestParam("file") MultipartFile file)
    {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }
        String fileName = file.getOriginalFilename();
        String filePath = System.getenv("FILEPATH")+"/tmp/ruwork";
        File dir = new File(filePath);
        if (!dir.exists()) dir.mkdirs();
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            FileData fileData =  excelFileProcessService.resolve(dest);
            excelFileProcessService.saveSaleData(fileData.getSaleData());
            excelFileProcessService.saveCustomerData(fileData.getCustomerData());
            log.info("上传成功");
            return "上传成功";
        } catch ( IOException e) {
            log.warning(e.toString());
        }
        return "上传失败！";

    }
}
