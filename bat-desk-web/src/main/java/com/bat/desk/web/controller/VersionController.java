package com.bat.desk.web.controller;

import com.bat.desk.web.config.GitConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {

    @Autowired
    private GitConfiguration.GitRepositoryState gitRepositoryInformation;

    @RequestMapping(method = RequestMethod.GET, value = "/api/version", produces = {"application/json", "application/xml"})
    public GitConfiguration.GitRepositoryState gitStatus() {


        return gitRepositoryInformation;
    }
}
