package com.css.nsfw.dp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.css.nsfw.dp.dao.nfpt.Station12366TaxServerDao;
import com.css.nsfw.dp.utils.Utils;

@Component
public class Station12366TaxServerService {
	@Autowired
	private Station12366TaxServerDao station12366TaxServerDao;

	// 话务总量，人工服务量，自动接听服务量
	public Map getCallCount(String timeSpan, String unitCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("timeSpan", timeSpan);
		params.put("unitCode", unitCode);
		return station12366TaxServerDao.getCallCount(params);
	}

	// 接通率
	public Map getCallSuccess(String timeSpan, String unitCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("timeSpan", timeSpan);
		params.put("unitCode", unitCode);
		return station12366TaxServerDao.getCallSuccess(params);
	}

	// 满意率
	public Map getCallDegree(String timeSpan, String unitCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("timeSpan", timeSpan);
		params.put("unitCode", unitCode);
		return station12366TaxServerDao.getCallDegree(params);
	}

	// 咨询热点 占比
	public List getHotQuestion(String timeSpan, String unitCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("timeSpan", timeSpan);
		params.put("unitCode", unitCode);
		return station12366TaxServerDao.getHotQuestion(params);
	}

	public Map getCurrentCallNum(String unitCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("unitCode", unitCode);
		return station12366TaxServerDao.getCurrentCallNum(params);
	}

	public Map getQuestionTypePersent(String timeSpan, String unitCode) {

		Map<String, List> result = new HashMap<>();
		// 内环数据
		List<Map<String, Object>> insideData = new ArrayList<>();
		// 外环数据
		List<Map<String, Object>> outsideData = new ArrayList<>();

		// 查询参数
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("timeSpan", timeSpan);
		params.put("unitCode", unitCode);
		List o = station12366TaxServerDao.getHotQuestion(params);

		int oLength = o.size();
		// 其他的业务量

		int otherCount=0;
		
		// 临时map
		Map<String, Long> tempMap = new HashMap<>();

		for (int i = 0; i < oLength; i++) {
			Map one = (Map) o.get(i);
			if(i>4) {
				otherCount+=Long.parseLong( one.get("SL").toString());
				continue;
			}

			Map<String, Object> seriesData = new HashMap<>();

			seriesData.put("name", one.get("EJMC"));
			seriesData.put("value", one.get("SL"));
			outsideData.add(seriesData);

			// 处理内部圈圈数据 暂时未显示
			String tempName = (String) one.get("YJMC");
			Long tempCount = Long.parseLong(one.get("SL").toString());
			if (tempMap.get(tempName) != null) {
				tempMap.put(tempName, tempMap.get(tempName) + tempCount);
			} else {
				tempMap.put(tempName, tempCount);
			}
			
		}
		//添加数据 其他
		Map<String, Object> seriesOtherData = new HashMap<>();
		seriesOtherData.put("name", "其他");
		seriesOtherData.put("value", otherCount);
		outsideData.add(seriesOtherData);
		
		//map转list
		insideData= Utils.rowToColSmall(tempMap);
		
		result.put("insideData", insideData);
		result.put("outsideData", outsideData);
		return result;
	}

}
