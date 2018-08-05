package cn.huan.kindergarten.utils;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;

public class InitLog4jServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8627227305628207993L;
	
//	private static Logger log = Logger.getLogger(InitLog4jServlet.class);
	
	public void init(ServletConfig config){
//		String prefix = config.getServletContext().getRealPath("/"); 
//	    String file = "/WEB-INF/properties/log4j.properties"; 
//	    String filePath = prefix + file; 
//	    Properties props = new Properties(); 
//	    try { 
//	    	FileInputStream istream = new FileInputStream(filePath); 
//	    	props.load(istream); 
//	    	istream.close(); 
//	    	String logFile = prefix + props.getProperty("log4j.appender.R.File");//����·�� 
//	    	props.setProperty("log4j.appender.R.File",logFile); 
//	    	PropertyConfigurator.configure(props);//װ��log4j������Ϣ 
//	    } catch (Exception e) { 
//	    	log.error("test log4j",e);
//	    	return; 
//	    } 
	}
}
