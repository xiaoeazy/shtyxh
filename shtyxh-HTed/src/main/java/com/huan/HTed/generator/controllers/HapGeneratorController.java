package com.huan.HTed.generator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huan.HTed.generator.dto.GeneratorInfo;
import com.huan.HTed.generator.service.IHapGeneratorService;
import com.huan.HTed.system.controllers.BaseController;
import com.huan.HTed.system.dto.ResponseData;

@Controller
@RequestMapping(value = "/generator")
public class HapGeneratorController extends BaseController {
    @Autowired
    IHapGeneratorService service;

    @RequestMapping(value = "/alltables", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData showTables() {
        return new ResponseData(service.showTables());
    }

    @RequestMapping(value = "/newtables")
    @ResponseBody
    public int generatorTables(GeneratorInfo generatorInfo) {
        int rs = service.generatorFile(generatorInfo);
        return rs;
    }

}
