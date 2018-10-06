package com.shtyxh.manager.service;

import org.springframework.web.servlet.ModelAndView;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.core.ProxySelf;
import com.huan.HTed.system.service.IBaseService;
import com.shtyxh.manager.dto.KgNewsAttribute;

public interface IKgNewsAttributeService extends IBaseService<KgNewsAttribute>, ProxySelf<IKgNewsAttributeService>{
	public int adminQueryCount(IRequest request,KgNewsAttribute record);
	public void loadAttriteNews(ModelAndView mv,IRequest requestContext,Long typeid,int attributeSize);
	public void loadAttriteNews(ModelAndView mv, IRequest requestContext, int attributeSize);
}