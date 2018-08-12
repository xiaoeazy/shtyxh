package cn.huan.kindergarten.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.huan.HTed.core.IRequest;

import cn.huan.kindergarten.dto.KgDownload;
import cn.huan.kindergarten.exception.FileReadIOException;
import cn.huan.kindergarten.exception.KgFileException;
import cn.huan.kindergarten.service.IKgDownloadService;
import cn.huan.kindergarten.service.IKgNewsAttributeService;
import cn.huan.kindergarten.utils.CommonUtil;

@Controller
public class IndexDownloadController extends IndexBaseController{
	/**
     * 文件下载默认编码.
     */
    private static final String ENC = "UTF-8";
    
	
	@Autowired
	private IKgDownloadService iKgDownloadService;
	@Autowired
	private IKgNewsAttributeService iKgNewsAttributeService;
	
	@RequestMapping(value = "/index/admin/download")
    @ResponseBody
    public ModelAndView download( @RequestParam(defaultValue = DEFAULT_PAGE) int page,@RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int limit,
    		HttpServletRequest request) {
		limit=20;
    	ModelAndView mv = new ModelAndView(getViewPath() + "/index/download/download");
    	 IRequest requestContext = createRequestContext(request);
    	 
    	 KgDownload kl = new KgDownload();
    	 kl.setSortorder("desc");
    	 kl.setSortname("createdate");
    	 List<KgDownload> list =iKgDownloadService.select(requestContext, kl, page, limit);
    	 int count = iKgDownloadService.adminQueryCount(requestContext, null);
    	 int allPageNum = count%limit==0?count/limit:count/limit+1;
	     if(count==0) allPageNum=1;
    	
    	 mv.addObject("downloadList",list);
    	 mv.addObject("page", page);
         mv.addObject("allPageNum",allPageNum);
         
         loadNavigation(mv, requestContext, IndexController.CH_INDEX);
         iKgNewsAttributeService.loadAttriteNews(mv, requestContext,3);
         loadSysConfig(mv);
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
    @RequestMapping(value = {"/index/admin/file/download","/index/admin/file/download"})
    public void detail(HttpServletRequest request, HttpServletResponse response, String fileId,String password) throws KgFileException, FileNotFoundException, IOException{
    	 HttpSession session = request.getSession(false);
         if(session==null) {
        	 throw new KgFileException(null, "请先登陆！", null);
         }
        IRequest requestContext = createRequestContext(request);
        KgDownload kg = new KgDownload();
        kg.setId(fileId);
        KgDownload sysFile = iKgDownloadService.selectByPrimaryKey(requestContext, kg);
        
        if(sysFile.getPassword()!=null&&!("").equals(sysFile.getPassword())) {
        	if(password==null||!password.equals(sysFile.getPassword())) {
        		 throw new KgFileException(null, "下载文件密码不对", null);
            }
        }
        File file = new File(request.getServletContext().getRealPath("/") +sysFile.getFilePath());
        if (file.exists()) {
            response.addHeader("Content-Disposition", "attachment;filename=\"" + URLEncoder.encode(sysFile.getFileTitle(), ENC) + "\"");
//            response.setContentType(sysFile.getFileType() + ";charset=" + ENC);
            response.setHeader("Accept-Ranges", "bytes");
            int fileLength = (int) file.length();
            response.setContentLength(fileLength);
            if (fileLength > 0) {
                CommonUtil.writeFileToResp(response, file);
            }
        } else {
            throw new KgFileException(null, "文件不存在", null);
        }
    }
	
    
    @RequestMapping(value = {"/index/file/excel"})
    public void detailinfo(HttpServletRequest request, HttpServletResponse response, String fileId,String password) throws KgFileException, FileNotFoundException, IOException{
    	IRequest requestContext = createRequestContext(request);
      
        File file = new File(request.getServletContext().getRealPath("/") +"/resources/upload/aaaa.doc");
        if (file.exists()) {
            response.addHeader("Content-Disposition", "attachment;filename=\"aaaa.doc\"");
            response.setContentType("application/msword" + ";charset=" + ENC);
            response.setHeader("Accept-Ranges", "bytes");
            int fileLength = (int) file.length();
            response.setContentLength(fileLength);
            if (fileLength > 0) {
            	CommonUtil.writeFileToResp(response, file);
            }
        } else {
            throw new KgFileException(null, "文件不存在", null);
        }
    }
	

  

   
}