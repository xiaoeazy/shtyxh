package cn.huan.kindergarten.service;

import java.util.List;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.core.ProxySelf;
import com.huan.HTed.system.service.IBaseService;
import cn.huan.kindergarten.dto.KgOffers;

public interface IKgOffersService extends IBaseService<KgOffers>, ProxySelf<IKgOffersService>{

	int adminQueryCount(IRequest request, KgOffers record);

	List<KgOffers> selectWithOtherInfo(IRequest request, KgOffers condition, Integer pageNum, Integer pageSize);

}