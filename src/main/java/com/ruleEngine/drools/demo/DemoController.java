package com.ruleEngine.drools.demo;

import com.ruleEngine.drools.demo.dto.Product;
import com.ruleEngine.drools.demo.service.DemoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
public class DemoController {

    private  DemoService demoService;

    public DemoController(DemoService demoService){
        this.demoService=demoService;
    }

    @PostMapping(value = "/fetch/product",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Product> fetchProductPrice(
            @RequestPart(value = "file", required = true) MultipartFile ruleSet,
            @RequestPart(value="input") Product input) throws IOException {
        return new ResponseEntity<Product>(demoService.fetchProduct(ruleSet,input),HttpStatus.OK);
    }

}

