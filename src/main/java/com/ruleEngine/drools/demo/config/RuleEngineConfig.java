package com.ruleEngine.drools.demo.config;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RuleEngineConfig {

    public Integer executeRules(byte[] fileArray, Object objectList) throws IOException {
        KieSession kieSession=configStateful(fileArray);
        kieSession.insert(objectList);
        int rulesFired = kieSession.fireAllRules();
        kieSession.dispose();
        return rulesFired;
    }

    private  KieSession configStateful(byte[] fileArray) throws IOException {
        KieBase kieBase = createKieBase(fileArray);
        return kieBase.newKieSession();
    }

    private KieBase createKieBase(byte[] fileArray){
        KieServices kieServices = KieServices.Factory.get();
        KieFileSystem kieFileSystem=kieServices.newKieFileSystem();
        Resource resource= ResourceFactory.newByteArrayResource(fileArray);
        resource.setSourcePath("src/main/resources/ruleFile");
        resource.setResourceType(ResourceType.DRL);
        kieFileSystem.write(resource);
        KieBuilder kb = kieServices.newKieBuilder(kieFileSystem);
        kb.buildAll();
        KieModule kieModule = kb.getKieModule();
        KieContainer kContainer = kieServices.newKieContainer(kieModule.getReleaseId());
        return kContainer.getKieBase();
    }

}
