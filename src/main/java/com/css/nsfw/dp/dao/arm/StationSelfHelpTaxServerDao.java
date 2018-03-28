package com.css.nsfw.dp.dao.arm;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface StationSelfHelpTaxServerDao {
	// 自助终端arm机分类
	List getArmByType(Map<String, Object> params);

	// 自助终端arm业务总量排行榜
	List getArmHandledCountRank(Map<String, Object> params);

	// 自助终端arm放置点用量票排行
	List getArmUsedCountRank(Map<String, Object> params);

	// arm业务近12个月各业务总量
	List getArmHandledCountTotal(Map<String, Object> params);

	// 不同业务或者总量近12个月走势图
	List getArmHandledCountLineByBusiness(Map<String, Object> params);
}
