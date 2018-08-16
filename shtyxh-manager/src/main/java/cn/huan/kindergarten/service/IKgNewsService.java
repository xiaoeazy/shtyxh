package cn.huan.kindergarten.service;

import java.util.List;
import java.util.Map;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.core.ProxySelf;
import com.huan.HTed.system.service.IBaseService;

import cn.huan.kindergarten.dto.KgNews;

public interface IKgNewsService extends IBaseService<KgNews>, ProxySelf<IKgNewsService>{
	 public List<KgNews> selectWithOtherInfo(IRequest request,  KgNews condition, Integer pageNum, Integer pageSize);
	 
	
	 
	 public void adminDelete(IRequest request, String webPath , List<KgNews> dto) ;

	 public List<KgNews> selectByMap(IRequest request,KgNews news,List<Long> typeids ,Integer pageNum, Integer pageSize);

	 
	 public int adminQueryCount(IRequest request,KgNews record);
	 
	 public int adminQueryCount(IRequest request,KgNews news,List<Long> typeids);
}