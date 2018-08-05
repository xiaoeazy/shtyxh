

package com.huan.HTed.mybatis.spring;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;

import com.huan.HTed.mybatis.common.Marker;
import com.huan.HTed.mybatis.mapperhelper.MapperHelper;
import com.huan.HTed.mybatis.util.StringUtil;

public class MapperScannerConfigurer extends org.mybatis.spring.mapper.MapperScannerConfigurer {
    private MapperHelper mapperHelper = new MapperHelper();


    private Map<String,String> propertiesMap;


    public void setMarkerInterface(Class<?> superClass) {
        super.setMarkerInterface(superClass);
        if (Marker.class.isAssignableFrom(superClass)) {
            mapperHelper.registerMapper(superClass);
        }
    }

    public MapperHelper getMapperHelper() {
        return mapperHelper;
    }

    public void setMapperHelper(MapperHelper mapperHelper) {
        this.mapperHelper = mapperHelper;
    }

    /**
     * 属性注入
     *
     * @param properties
     */
    public void setProperties(Properties properties) {
        mapperHelper.setProperties(properties);
    }

    public void setPropertiesMap(Map<String, String> propertiesMap) {
        if (propertiesMap.get("ORDER") == null) {
            if ("JDBC".equalsIgnoreCase(propertiesMap.get("IDENTITY"))) {
                propertiesMap.put("ORDER", "AFTER");
            } else {
                propertiesMap.put("ORDER", "BEFORE");
            }
        }
        this.propertiesMap = propertiesMap;
        //Properties properties = new Properties();
        //propertiesMap.forEach((k, v) -> {
        //    properties.put(k, v);
        //});
        //setProperties(properties);
    }

    /**
     * 注册完成后，对MapperFactoryBean的类进行特殊处理
     *
     * @param registry
     */
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) {

        Properties config = new Properties();
        Properties p = new Properties();
        try {
            config.load(getClass().getResourceAsStream("/config.properties"));
            if (propertiesMap.get("ORDER") == null) {
                if ("JDBC".equalsIgnoreCase(propertiesMap.get("IDENTITY"))) {
                    p.put("ORDER", "AFTER");
                } else {
                    p.put("ORDER", "BEFORE");
                }
            }
//            propertiesMap.forEach((k,v)->{
//                if (v.startsWith("${") && v.endsWith("}")) {
//                    p.put(k, config.getProperty(v.substring(2, v.length() - 1), v));
//                } else {
//                    p.put(k, v);
//                }
//            });
            for(String k:propertiesMap.keySet()){
            	String v = propertiesMap.get(k);
            	if (v.startsWith("${") && v.endsWith("}")) {
                    p.put(k, config.getProperty(v.substring(2, v.length() - 1), v));
                } else {
                    p.put(k, v);
                }
            }
            setProperties(p);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        super.postProcessBeanDefinitionRegistry(registry);
        //如果没有注册过接口，就注册默认的Mapper接口
        this.mapperHelper.ifEmptyRegisterDefaultInterface();
        String[] names = registry.getBeanDefinitionNames();
        GenericBeanDefinition definition;
        for (String name : names) {
            BeanDefinition beanDefinition = registry.getBeanDefinition(name);
            if (beanDefinition instanceof GenericBeanDefinition) {
                definition = (GenericBeanDefinition) beanDefinition;
                if (StringUtil.isNotEmpty(definition.getBeanClassName())
                        && definition.getBeanClassName().equals("org.mybatis.spring.mapper.MapperFactoryBean")) {
                    definition.setBeanClass(MapperFactoryBean.class);
                    definition.getPropertyValues().add("mapperHelper", this.mapperHelper);
                }
            }
        }
        System.out.println("end  MapperScan postProcessBeanDefinitionRegistry");
    }
}