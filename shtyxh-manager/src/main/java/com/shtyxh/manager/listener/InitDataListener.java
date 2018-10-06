package com.shtyxh.manager.listener;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.context.ServletContextAware;

public class InitDataListener implements InitializingBean, ServletContextAware {
	  	/** 日志记录. **/
    	private static Logger logger = LoggerFactory.getLogger(InitDataListener.class);
		@Override
		public void setServletContext(ServletContext arg0) {
				logger.info("初始化加载完毕");
		}
		
		@Override
		public void afterPropertiesSet() throws Exception {
			// TODO Auto-generated method stub
			
		}
}
