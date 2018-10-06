package com.shtyxh.manager.service;

import java.util.List;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.core.ProxySelf;
import com.huan.HTed.system.service.IBaseService;
import com.shtyxh.manager.dto.KgCarousel;

public interface IKgCarouselService extends IBaseService<KgCarousel>, ProxySelf<IKgCarouselService>{

	public int adminQueryCount(IRequest request, KgCarousel record);

	public void adminDelete(IRequest request, String webPath, List<KgCarousel> dto);

}