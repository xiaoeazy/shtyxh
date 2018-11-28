package com.shtyxh.manager.index.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.huan.HTed.core.IRequest;
import com.shtyxh.common.bean.ExtAjax;
import com.shtyxh.common.exception.GlobalException;
import com.shtyxh.common.utils.FileUtil;
import com.shtyxh.manager.dto.KgDownload;
import com.shtyxh.manager.service.IJedisService;
import com.shtyxh.manager.service.IKgDownloadService;
import com.shtyxh.manager.utils.CommonFuncUtil;

@Controller
public class IndexDownloadController extends IndexBaseController{
	/**
     * 文件下载默认编码.
     */
    private static final String ENC = "UTF-8";
     

	@Autowired
	private IJedisService iJedisService;
	@Autowired
	private IKgDownloadService iKgDownloadService;
	@RequestMapping(value = "/index/download")
    @ResponseBody
    public ModelAndView download( @RequestParam(defaultValue = DEFAULT_PAGE) int page,@RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int limit,
    		HttpServletRequest request) {
    	ModelAndView mv = new ModelAndView( "/index/download/download");
    	 List<KgDownload> downloadList = iJedisService.loadDownload();
    	 int count = downloadList.size();
    	 downloadList=CommonFuncUtil.listToPage(downloadList, page, limit);
    	 int allPageNum = count%limit==0?count/limit:count/limit+1;
	     if(count==0) allPageNum=1;
    	
    	 mv.addObject("downloadList",downloadList);
    	 mv.addObject("page", page);
         mv.addObject("allPageNum",allPageNum);
         
         loadNavigation(mv,  IndexController.CH_INDEX);
         loadAttriteNews(mv, 3);
         loadSysConfig(request,mv);
         return mv;
    }
    
    /**
     * 具体查看某个附件.
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @param fileId   文件id
     * @throws KgFileException 
     * @throws IOException 
     * @throws FileNotFoundException 
     * @throws Exception 
     * @throws FileReadIOException 文件读取IO异常
     */
    @RequestMapping(value = {"/index/file/download"})
    public ExtAjax detail(HttpServletRequest request, HttpServletResponse response, String fileId,String password) {
    	IRequest requestContext = createRequestContext(request);
    	KgDownload kg = new KgDownload();
        kg.setId(fileId);
        KgDownload sysFile = iKgDownloadService.selectByPrimaryKey(requestContext, kg);
        
        if(sysFile.getPassword()!=null&&!("").equals(sysFile.getPassword())) {
        	if(password==null||!password.equals(sysFile.getPassword())) {
        		 throw new  GlobalException("下载文件密码不对");
            }
        }
        
        return new ExtAjax(true, sysFile.getFilePath());
//        File file = new File(request.getServletContext().getRealPath("/") +sysFile.getFilePath());
//        if (file.exists()) {
//            response.addHeader("Content-Disposition", "attachment;filename=\"" + URLEncoder.encode(sysFile.getFileTitle(), ENC) + "\"");
////            response.setContentType(sysFile.getFileType() + ";charset=" + ENC);
//            response.setHeader("Accept-Ranges", "bytes");
//            int fileLength = (int) file.length();
//            response.setContentLength(fileLength);
//            if (fileLength > 0) {
//                CommonFuncUtil.writeFileToResp(response, file);
//            }
//        } else {
//            throw new GlobalException("文件不存在");
//        }
    }
	
    /**
     * 测试
     * @param request
     * @param response
     * @param fileId
     * @param password
     * @throws GlobalException
     * @throws FileNotFoundException
     * @throws IOException
     */
    @RequestMapping(value = {"/index/file/excel"})
    public void detailinfo(HttpServletRequest request, HttpServletResponse response, String fileId,String password) throws GlobalException, FileNotFoundException, IOException{
    	File file = new File(request.getServletContext().getRealPath("/") +"/resources/upload/aaaa.doc");
        if (file.exists()) {
            response.addHeader("Content-Disposition", "attachment;filename=\"aaaa.doc\"");
            response.setContentType("application/msword" + ";charset=" + ENC);
            response.setHeader("Accept-Ranges", "bytes");
            int fileLength = (int) file.length();
            response.setContentLength(fileLength);
            if (fileLength > 0) {
            	FileUtil.writeFileToResp(response, file);
            }
        } else {
            throw new GlobalException("文件不存在");
        }
    }
	


	

//	
//	@RequestMapping(value = "/index/admin/download")
//    @ResponseBody
//    public ModelAndView download( @RequestParam(defaultValue = DEFAULT_PAGE) int page,@RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int limit,
//    		HttpServletRequest request) {
//		limit=20;
//    	ModelAndView mv = new ModelAndView(getViewPath() + "/index/download/download");
//    	 IRequest requestContext = createRequestContext(request);
//    	 
//    	 KgDownload kl = new KgDownload();
//    	 kl.setSortorder("desc");
//    	 kl.setSortname("createdate");
//    	 List<KgDownload> list =iKgDownloadService.select(requestContext, kl, page, limit);
//    	 int count = iKgDownloadService.adminQueryCount(requestContext, null);
//    	 int allPageNum = count%limit==0?count/limit:count/limit+1;
//	     if(count==0) allPageNum=1;
//    	
//    	 mv.addObject("downloadList",list);
//    	 mv.addObject("page", page);
//         mv.addObject("allPageNum",allPageNum);
//         
//         loadNavigation(mv, IndexController.CH_INDEX);
//         iKgNewsAttributeService.loadAttriteNews(mv, requestContext,3);
//         loadSysConfig(mv);
//         return mv;
//    }


   
}