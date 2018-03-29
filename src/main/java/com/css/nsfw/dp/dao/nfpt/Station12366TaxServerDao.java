package com.css.nsfw.dp.dao.nfpt;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface Station12366TaxServerDao {
	// 话务总量，人工服务量，自动接听服务量
	Map getCallCount(Map<String, Object> params);

	// 接通率
	Map getCallSuccess(Map<String, Object> params);

	// 满意率
	Map getCallDegree(Map<String, Object> params);

	// 咨询热点 占比
	List getHotQuestion(Map<String, Object> params);

	Map getCurrentCallNum(Map<String, Object> params);

}
