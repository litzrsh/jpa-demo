package com.example.demo.serial.utils;

import com.example.demo.serial.service.SerialService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SerialUtils implements InitializingBean {

    private static SerialService service;

    public static Long get(final String id) {
        return service.get(id);
    }

    private final SerialService serialService;

    @Override
    public void afterPropertiesSet() throws Exception {
        SerialUtils.service = serialService;
    }
}
