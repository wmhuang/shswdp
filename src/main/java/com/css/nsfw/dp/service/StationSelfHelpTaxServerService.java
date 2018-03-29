package com.css.nsfw.dp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.css.nsfw.dp.dao.arm.StationSelfHelpTaxServerDao;
import com.css.nsfw.dp.utils.Env;
import com.css.nsfw.dp.utils.Utils;

@Component
public class StationSelfHelpTaxServerService {
	@Autowired
	private StationSelfHelpTaxServerDao stationSelfHelpTaxServerDao;

	// arm分类
	public List getArmByType(String unitCode, String type) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("unitCode", unitCode);
		params.put("type", type);
		List resutl = stationSelfHelpTaxServerDao.getArmByType(params);
		return resutl;
	}

	// 自助终端arm业务总量排行榜
	public List getArmHandledCountRank(String unitCode, String timeSpan) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("unitCode", unitCode);
		params.put("timeSpan", timeSpan);

		List<Map<String, Object>> queryResult = stationSelfHelpTaxServerDao
				.getArmHandledCountRank(params);
		Map<String, Object> row = queryResult.get(0);
		// 取查询结果第一条整理成目标数据
		List<Map<String, Object>> result = Utils.rowToCol(Utils
				.mapSortByValue(row));
		// 返回前五
		if (result.size() > 5) {
			return result.subList(0, 5);
		}
		return result;
	}

	// 自助终端arm放置点用量票排行
	public List getArmUsedCountRank(String unitCode, String timeSpan) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("unitCode", unitCode);
		params.put("timeSpan", timeSpan);

		List<Map<String, Object>> queryResult = stationSelfHelpTaxServerDao
				.getArmUsedCountRank(params);
		return queryResult;
	}

	// 业务总量 近12个月趋势折线图
	public Map<String, Object> getArmHandledCountTotalLine(String unitCode) {
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		// 排前三业务名称已经总量
		String businesNameArr[] = new String[4];
		result.put("businesNames", businesNameArr);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("unitCode", unitCode);
		List<Map<String, Object>> queryResult = stationSelfHelpTaxServerDao
				.getArmHandledCountTotal(params);
		Map<String, Object> row = queryResult.get(0);
		// 取查询结果第一条整理成目标数据
		List<Map<String, Object>> sortQueryResult = Utils.rowToCol(Utils
				.mapSortByValue(row));
		// 总量
		businesNameArr[0] = "业务总量";
		// 前三事项事项
		for (int i = 0; i < sortQueryResult.size(); i++) {
			if (i > 2) {
				// 取前三
				break;
			}
			businesNameArr[i + 1] = (String) sortQueryResult.get(i + 1).get(
					"NAME");
		}
		List queryThreeResult = get12MonthByBusiness(unitCode, businesNameArr);

		Object MonthArr[] = new Object[12];
		Object totalArr[] = new Object[12];
		Object firstArr[] = new Object[12];
		Object secondArr[] = new Object[12];
		Object thirdArr[] = new Object[12];
		for (int i = 0; i < queryThreeResult.size(); i++) {
			Map one = (Map) queryThreeResult.get(i);
			MonthArr[i] = one.get("NAME");
			totalArr[i] = one.get("COUNT0");
			firstArr[i] = one.get("COUNT1");
			secondArr[i] = one.get("COUNT2");
			thirdArr[i] = one.get("COUNT3");
		}
		result.put("时间", MonthArr);
		result.put("total", totalArr);
		result.put("first", firstArr);
		result.put("second", secondArr);
		result.put("third", thirdArr);

		return result;
	}

	/**
	 * 
	 * @param unitCode
	 *            机构code
	 * @param businessNameCN
	 *            业务中文名
	 * @return 近12个月数量的数组
	 */
	public List get12MonthByBusiness(String unitCode, String[] businessNameCNs) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("unitCode", unitCode);
		String totalColName = "";
		for (int i = 0; i < businessNameCNs.length; i++) {
			totalColName += Env.TJ_YWLTJZD.get(businessNameCNs[i])
					+ " as count" + i + " , ";

		}
		params.put("colName", totalColName);
		List<Map<String, Object>> queryResult2 = stationSelfHelpTaxServerDao
				.getArmHandledCountLineByBusiness(params);

		return queryResult2;
	}

	public Map getArmHandledTotalCount(String unitCode, String timeSpan) {
		Map result = new HashMap();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("unitCode", unitCode);
		params.put("timeSpan", timeSpan);
		List<Map<String, Object>> queryResult = stationSelfHelpTaxServerDao
				.getArmHandledCountRank(params);
		Map<String, Object> row = queryResult.get(0);
		// 取查询结果第一条整理成目标数据
		List<Map<String, Object>> dealResult = Utils.rowToColSmallNotZero(Utils
				.mapSortByValue(row));
		// 业务总量
		Long tempTotalCount = 0L;
		for (Map<String, Object> map : dealResult) {
			tempTotalCount += (Long) (map.get("value"));
		}
		result.put("value", tempTotalCount);

		// 判断处理业务总量是否为零，为零则显示当天未产生数据
		if (tempTotalCount == 0L) {
			List<Map<String, Object>> tempList = new ArrayList<>();
			Map<String, Object> tempMap = new HashMap<String, Object>();
			tempMap.put("name", "未产生数据");
			tempMap.put("value", 0);
			tempList.add(tempMap);
			result.put("list", tempList);
		} else {
			result.put("list", dealResult);
		}
		return result;
	}
}
