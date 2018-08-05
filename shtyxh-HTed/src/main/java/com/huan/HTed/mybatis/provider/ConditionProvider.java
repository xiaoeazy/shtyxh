

package com.huan.HTed.mybatis.provider;

import org.apache.ibatis.mapping.MappedStatement;

import com.huan.HTed.mybatis.mapperhelper.MapperHelper;
import com.huan.HTed.mybatis.mapperhelper.MapperTemplate;

/**
 * ConditionProvider实现类，基础方法实现类
 */
public class ConditionProvider extends MapperTemplate {

    private ExampleProvider exampleProvider;

    public ConditionProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
        exampleProvider = new ExampleProvider(mapperClass, mapperHelper);
    }

    /**
     * 根据Condition查询总数
     *
     * @param ms
     * @return
     */
    public String selectCountByCondition(MappedStatement ms) {
        return exampleProvider.selectCountByExample(ms);
    }

    /**
     * 根据Condition删除
     *
     * @param ms
     * @return
     */
    public String deleteByCondition(MappedStatement ms) {
        return exampleProvider.deleteByExample(ms);
    }


    /**
     * 根据Condition查询
     *
     * @param ms
     * @return
     */
    public String selectByCondition(MappedStatement ms) {
        return exampleProvider.selectByExample(ms);
    }

    /**
     * 根据Condition查询
     *
     * @param ms
     * @return
     */
    public String selectByConditionAndRowBounds(MappedStatement ms) {
        return exampleProvider.selectByExample(ms);
    }

    /**
     * 根据Example更新非null字段
     *
     * @param ms
     * @return
     */
    public String updateByConditionSelective(MappedStatement ms) {
        return exampleProvider.updateByExampleSelective(ms);
    }

    /**
     * 根据Condition更新
     *
     * @param ms
     * @return
     */
    public String updateByCondition(MappedStatement ms) {
        return exampleProvider.updateByExample(ms);
    }
}
