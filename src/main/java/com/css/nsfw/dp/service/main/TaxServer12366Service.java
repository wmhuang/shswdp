package com.css.nsfw.dp.service.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.css.nsfw.dp.dao.nfpt.TaxServer12366Dao;
import com.css.nsfw.dp.utils.Utils;

@Component
public class TaxServer12366Service {
	@Autowired
	private TaxServer12366Dao taxServer12366Dao;

	// 话务总量，人工服务量，自动接听服务量
	public Map getCallCount(String timeSpan, String unitCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("timeSpan", timeSpan);
		params.put("unitCode", unitCode);
		return taxServer12366Dao.getCallCount(params);
	}

	// 接通率
	public Map getCallSuccess(String timeSpan, String unitCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("timeSpan", timeSpan);
		params.put("unitCode", unitCode);
		return taxServer12366Dao.getCallSuccess(params);
	}

	// 满意率
	public Map getCallDegree(String timeSpan, String unitCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("timeSpan", timeSpan);
		params.put("unitCode", unitCode);
		return taxServer12366Dao.getCallDegree(params);
	}

	// 咨询热点 占比
	public List getHotQuestion(String timeSpan, String unitCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("timeSpan", timeSpan);
		params.put("unitCode", unitCode);
		return taxServer12366Dao.getHotQuestion(params);
	}

	// 当前来电
	public Map getCurrentCallNum(String unitCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("unitCode", unitCode);
		return taxServer12366Dao.getCurrentCallNum(params);
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
		List o = taxServer12366Dao.getHotQuestion(params);

		int oLength = o.size();
		// 其他的业务量

		int otherCount = 0;

		// 临时map
		Map<String, Long> tempMap = new HashMap<>();

		for (int i = 0; i < oLength; i++) {
			Map one = (Map) o.get(i);
			if (i > 4) {
				otherCount += Long.parseLong(one.get("SL").toString());
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
		// 添加数据 其他
		Map<String, Object> seriesOtherData = new HashMap<>();
		seriesOtherData.put("name", "其他");
		seriesOtherData.put("value", otherCount);
		outsideData.add(seriesOtherData);

		// map转list
		insideData = Utils.rowToColSmall(tempMap);

		result.put("insideData", insideData);
		result.put("outsideData", outsideData);
		return result;
	}

	/**
	 * 由于12366平台的税务机关代码和纳服平台的税务机关代码不匹配 需要通过该方法转换
	 * 
	 * @param swjgDm
	 * @return
	 * @createDate 2018年5月24日
	 * @author wmhuang
	 */
	public String changeUnitCode(String swjgDm) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("swjgDm", swjgDm);
		Map result = taxServer12366Dao.changeUntiCode(params);
		if (result!=null) {
			return (String) result.get("UNITCODE");
		}
		return swjgDm;
	}

}
