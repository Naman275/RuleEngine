package com.ruleEngine.drools.demo.service;

import com.ruleEngine.drools.demo.dto.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface DemoService {
    Product fetchProduct(MultipartFile ruleSet,Product product) throws IOException;
}
