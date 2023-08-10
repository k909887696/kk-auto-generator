package com.kk.kkautogenerator.service.impl;

import com.kk.kkautogenerator.config.SystemSettingConfig;
import com.kk.kkautogenerator.constant.DbType;
import com.kk.kkautogenerator.constant.SysCodeTemplateClass;
import com.kk.kkautogenerator.constant.SysCodeTemplateLanguage;
import com.kk.kkautogenerator.constant.SysCodeTemplateType;
import com.kk.kkautogenerator.model.dto.BaseDataItemMapGetDto;
import com.kk.kkautogenerator.model.vo.BaseDataItemMapGetVo;
import com.kk.kkautogenerator.service.IBaseDataService;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

@Service
public class BaseDataServiceImpl implements IBaseDataService {
    private static final Logger log = LoggerFactory.getLogger(BaseDataServiceImpl.class);
    @Resource
    public SystemSettingConfig systemSettingConfig;
    public BaseDataItemMapGetDto getBaseDataItemMap(BaseDataItemMapGetVo vo) {
        BaseDataItemMapGetDto res = new BaseDataItemMapGetDto();
        Map<String, Map<String, Object>> result = new HashMap<>();
        if (null != vo && vo.getQueryTypeList() != null)
            vo.getQueryTypeList().forEach(queryType -> {
                Map<String, Object> items = new LinkedHashMap<>();
                if (queryType.toLowerCase().equals("dbtype".toLowerCase()))
                    DbType.CODE_VALUE_MAP.forEach( (k,v) ->{
                        items.put(k,v);
                    });
                if (queryType.toLowerCase().equals("SysCodeTemplateType".toLowerCase()))
                    SysCodeTemplateType.CODE_VALUE_MAP.forEach( (k,v) ->{
                        items.put(k,v);
                    });
                if (queryType.toLowerCase().equals("SysCodeTemplateClass".toLowerCase()))
                    SysCodeTemplateClass.CODE_VALUE_MAP.forEach( (k,v) ->{
                        items.put(k,v);
                    });
                if (queryType.toLowerCase().equals("SysCodeTemplateLanguage".toLowerCase()))
                    SysCodeTemplateLanguage.CODE_VALUE_MAP.forEach( (k,v) ->{
                        items.put(k,v);
                    });
                if (queryType.toLowerCase().equals("SystemSettingConfig".toLowerCase())) {
                    items.put("auth",systemSettingConfig.getAuth());
                    items.put("packageName",systemSettingConfig.getPackageName());
                    items.put("moduleName",systemSettingConfig.getModuleName());
                }
                result.put(queryType.toLowerCase(), items);
            });
        res.setQueryTypeResult(result);
        res.setValue(true);
        return res;
    }

    public void downloadFile(HttpServletRequest request, HttpServletResponse response, String path, String fileName, String preView) {
        try {
            Path filePath = Paths.get(System.getProperty("user.dir"), new String[] { path });
            String targetPath = filePath.toFile().getCanonicalPath();
            File file = new File(targetPath);
            if (!file.exists()) {
                String errorMessage = "Sorry. The file you are looking for does not exist";
                ServletOutputStream servletOutputStream = response.getOutputStream();
                servletOutputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
                servletOutputStream.close();
                return;
            }
            request.setCharacterEncoding("UTF-8");
            String mimeType = URLConnection.guessContentTypeFromName(file.getName());
            if (mimeType == null)
                mimeType = "application/octet-stream;charset=utf-8";
            response.reset();
            response.setContentType(mimeType);
            String header = request.getHeader("User-Agent");
            if (StringUtils.isNotBlank(header) && (header
                    .toUpperCase().contains("MSIE") || header
                    .toUpperCase().contains("TRIDENT") || header
                    .toUpperCase().contains("EDGE"))) {
                fileName = URLEncoder.encode(fileName, "UTF-8");
            } else {
                fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
            }
            String attach = "attachment;filename=" + fileName;
            if (StringUtils.isNotBlank(preView) && preView.equals("1"))
                attach = "inline;filename=" + fileName;
            log.info("fileName======================================" + attach);
            response.setHeader("Content-Disposition", attach);
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.addHeader("Access-Control-Allow-Methods", "*");
            response.addHeader("Access-Control-Allow-Credentials", "true");
            response.addHeader("Access-Control-Allow-Headers", "*");
            response.setContentLength((int)file.length());
            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
            FileCopyUtils.copy(inputStream, (OutputStream)response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
            String errorMessage = "Sorry. The file you are looking for does not exist";
            try {
                ServletOutputStream servletOutputStream = response.getOutputStream();
                servletOutputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
                servletOutputStream.close();
                return;
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
