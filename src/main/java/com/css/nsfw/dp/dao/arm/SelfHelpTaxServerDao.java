package com.css.nsfw.dp.dao.arm;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface SelfHelpTaxServerDao {
	// 业务受理总量
	Map getBusiTotalCount(Map<String, Object> params);

	// 重点事项实时办理量
	Map getKeyBusiCount(Map<String, Object> params);

	//arm机区域分布
	List getArmLocation(Map<String,Object> params);

	//业务受理量TOP5
	List getBusiRank(Map<String,Object> params);
	
	//业务量分局TOP5
	List getStationRankByBusi(Map<String,Object> params);
	
}
