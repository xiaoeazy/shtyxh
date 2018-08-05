package com.huan.HTed.generator.service;

import java.util.List;

import com.huan.HTed.generator.dto.GeneratorInfo;


public interface IHapGeneratorService {
    public List<String> showTables();

    public int generatorFile(GeneratorInfo info);

}
