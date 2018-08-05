package cn.huan.kindergarten.service;

import java.util.List;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.core.ProxySelf;
import com.huan.HTed.system.service.IBaseService;
import cn.huan.kindergarten.dto.KgCarousel;

public interface IKgCarouselService extends IBaseService<KgCarousel>, ProxySelf<IKgCarouselService>{

	public int adminQueryCount(IRequest request, KgCarousel record);

	public void adminDelete(IRequest request, String webPath, List<KgCarousel> dto);

}