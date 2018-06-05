package com.css.nsfw.dp.service.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.css.nsfw.dp.dao.arm.SelfHelpTaxServerDao;
import com.css.nsfw.dp.utils.Utils;

@Component
public class SelfHelperTaxServerService {
	@Autowired
	private SelfHelpTaxServerDao selfHelpTaxServerDao;

	// 业务受理总量
	public Map getBusiTotalCount(String timeSpan) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("timeSpan", timeSpan);
		return selfHelpTaxServerDao.getBusiTotalCount(params);
	}

	// 重点事项实时办理量
	public Map getKeyBusiCount(String timeSpan) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("timeSpan", timeSpan);
		return selfHelpTaxServerDao.getKeyBusiCount(params);
	}

	// arm机区域分布
	public List getArmLocation(String timeSpan) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("timeSpan", timeSpan);
		return selfHelpTaxServerDao.getArmLocation(params);
	}

	// 业务受理量TOP5
	public List getBusiRank(String timeSpan) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("timeSpan", timeSpan);

		List<Map<String, Object>> queryResult = selfHelpTaxServerDao.getBusiRank(params);
		Map<String, Object> row = queryResult.get(0);
		// 取查询结果第一条整理成目标数据
		List<Map<String, Object>> dealResult = Utils.rowToCol(Utils.mapSortByValue(row));
		return dealResult;
	}

	// 业务量分局TOP5
	public List getStationRankByBusi(String timeSpan, String swjgDm) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("timeSpan", timeSpan);
		params.put("swjgDm", swjgDm);
		List<Map<String, Object>> queryResult = selfHelpTaxServerDao.getStationRankByBusi(params);
		Map<String, Object> row = queryResult.get(0);
		// 取查询结果第一条整理成目标数据
		List<Map<String, Object>> dealResult = Utils.rowToCol(Utils.mapSortByValue(row));
		return dealResult;
	}

}
