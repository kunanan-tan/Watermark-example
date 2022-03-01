package com.example.watermark.controller;

import com.example.watermark.bean.ExampleWaterMarkBean;
import com.example.watermark.config.CustomizeException;
import com.example.watermark.serviceImpl.ExampleWaterMarkImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author kunanan.t
 */
@Slf4j
@RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
public class ExampleWaterMarkController {

    @Autowired
    private ExampleWaterMarkImpl exampleWaterMarkImpl;
    @Autowired
    private Environment env;

    @ApiOperation(value = "", notes = "Test WaterMark")
    @PostMapping(value = "/getWatermark")
    public ResponseEntity<Object> getTestSwagger(
            // check authen not used
//            @RequestHeader(value = "Authorization", required = true) String authorization,
            @RequestHeader(value = "version", required = false) String apiVersion,
            @RequestHeader(value = "accept-language", required = false) String language,
            @RequestBody(required = true) ExampleWaterMarkBean bean) {

        if ( StringUtils.isEmpty(bean) ) {
            throw new CustomizeException(env.getProperty("error.default"));
        }
        return ResponseEntity.ok(exampleWaterMarkImpl.getWaterMark(bean));
    }
}

