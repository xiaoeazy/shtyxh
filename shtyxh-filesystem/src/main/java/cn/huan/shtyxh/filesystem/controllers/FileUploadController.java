package cn.huan.shtyxh.filesystem.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.huan.shtyxh.common.bean.ExtAjax;

@Controller
public class FileUploadController {
	
	@RequestMapping(value =  "/fileUpload")
	@ResponseBody
	public ExtAjax UploadFile(@RequestParam("file") MultipartFile file, @RequestParam("theRealName") String theRealName) throws Exception {
		System.out.println(11);
		
		return new ExtAjax(true, "成功");
		//	    //form表单提交的参数测试为String类型
//	    if (file == null) return ;
//	    String fileName = file.getOriginalFilename();
//	    String path = getRequest().getServletContext().getRealPath("/upload/excel");
//	    //获取指定文件或文件夹在工程中真实路径，getRequest()这个方法是返回一个HttpServletRequest，封装这个方法为了处理编码问题
//	    FileOutputStream fos = FileUtils.openOutputStream(new     File(path+"/" +fileName));//打开FileOutStrean流
//	    IOUtils.copy(file.getInputStream(),fos);//将MultipartFile file转成二进制流并输入到FileOutStrean
//	    fos.close();//
//	　　......
	}
	
	
	@RequestMapping(value =  "/test")
	@ResponseBody
	public ExtAjax test() throws Exception {
		return new ExtAjax(true, "成功");
	}
}
