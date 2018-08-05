package cn.huan.kindergarten.service;

import java.util.List;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.core.ProxySelf;
import com.huan.HTed.system.service.IBaseService;

import cn.huan.kindergarten.dto.KgNews;

public interface IKgNewsService extends IBaseService<KgNews>, ProxySelf<IKgNewsService>{
	 public List<KgNews> selectWithOtherInfo(IRequest request,  KgNews condition, Integer pageNum, Integer pageSize);
	 
	 public int adminQueryCount(IRequest request,KgNews record);
	 
	 public void adminDelete(IRequest request, String webPath , List<KgNews> dto) ;
}