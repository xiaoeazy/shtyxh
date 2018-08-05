
package com.huan.HTed.core.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;


import freemarker.template.SimpleHash;

public class DefaultFreeMarkerView extends FreeMarkerView {

    private FreeMarkerBeanProvider beanProvider;

    protected FreeMarkerConfig autodetectConfiguration() throws BeansException {
        beanProvider = getApplicationContext().getBean(FreeMarkerBeanProvider.class);
        return super.autodetectConfiguration();
    }

    protected SimpleHash buildTemplateModel(Map<String, Object> model, HttpServletRequest request,
            HttpServletResponse response) {
        SimpleHash fmModel = super.buildTemplateModel(model, request, response);
        Map<String, Object> beans = beanProvider.getAvailableBean();
        if (beans != null) {
            fmModel.putAll(beans);
        }
        return fmModel;
    }
}
