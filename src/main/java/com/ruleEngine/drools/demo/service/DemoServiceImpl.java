package com.ruleEngine.drools.demo.service;

import com.ruleEngine.drools.demo.config.RuleEngineConfig;
import com.ruleEngine.drools.demo.dto.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class DemoServiceImpl implements DemoService {

    private RuleEngineConfig ruleEngineConfig;

    DemoServiceImpl(RuleEngineConfig ruleEngineConfig){
        this.ruleEngineConfig=ruleEngineConfig;
    }

    @Override
    public Product fetchProduct(MultipartFile ruleSet, Product product) throws IOException {
        ruleEngineConfig.executeRules(ruleSet.getBytes(),product);
        return product;
    }
}
