package com.shtyxh.manager.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.shtyxh.manager.dto.KgVisitcount;
import com.shtyxh.manager.service.IJedisService;
import com.shtyxh.manager.service.IKgVisitcountService;

public class VisitCountListener implements ServletContextListener{
	/** 日志记录. **/
	private static Logger logger = LoggerFactory.getLogger(VisitCountListener.class);
	/**
     * 销毁
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    	IJedisService jedisService = WebApplicationContextUtils.getRequiredWebApplicationContext(
                sce.getServletContext()).getBean(IJedisService.class);
    	IKgVisitcountService kgVisitcountService = WebApplicationContextUtils.getRequiredWebApplicationContext(
                sce.getServletContext()).getBean(IKgVisitcountService.class);
    	long num = jedisService.loadVisitCount();
    	KgVisitcount  kc = new KgVisitcount();
    	kc.setId(1l);
    	kc.setValue(num+"");
    	kgVisitcountService.updateByPrimaryKeySelective(null, kc);
    	logger.error("关闭tomcat ，插入visitcount："+num);
    }
    /**
     * 创建
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
    	IJedisService jedisService = WebApplicationContextUtils.getRequiredWebApplicationContext(
                sce.getServletContext()).getBean(IJedisService.class);
    	IKgVisitcountService kgVisitcountService = WebApplicationContextUtils.getRequiredWebApplicationContext(
                sce.getServletContext()).getBean(IKgVisitcountService.class);
    	long num = jedisService.loadVisitCount();
    	KgVisitcount  kc = new KgVisitcount();
    	kc.setId(1l);
    	kc.setValue(num+"");
    	kgVisitcountService.updateByPrimaryKeySelective(null, kc);
    	logger.error("启动tomcat ，插入visitcount："+num);
    }   
}
