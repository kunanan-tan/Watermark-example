package com.example.swagger.serviceImpl;

import com.example.swagger.bean.ExampleWaterMarkBean;
import com.example.swagger.config.CustomizeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.apache.commons.io.FileUtils;
import java.io.File;

/**
 * @author kunanan.t
 */
@Service
@Log4j2
@RequiredArgsConstructor
public class ExampleWaterMarkImpl {

    @Value("${file.pdf}")
    private String file;
    @Autowired
    private Environment env;
    @Autowired
    private MasterServiceImpl masterServiceImpl;

    public byte[] getWaterMark(ExampleWaterMarkBean bean) {
        byte[] responseFile = new byte[0];
        boolean checkUpdate = false;
        log.info("Method: getWaterMark  value: {}", bean);
        try {
            String filePath = file + bean.getFileName();
            log.info("file == " + filePath);
            File filePathRes = new File(filePath);
            responseFile = FileUtils.readFileToByteArray(filePathRes);

            responseFile = masterServiceImpl.getWaterMark(responseFile,bean.getCodeName(),bean.getUserName());
        } catch (Exception e) {
            log.error("===== Method : getWaterMark  Error {} =====", e.getMessage(), e);
            throw new CustomizeException(env.getProperty("error.default"));
        }
        return responseFile;
    }
}
