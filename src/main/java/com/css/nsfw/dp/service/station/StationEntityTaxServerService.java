package com.css.nsfw.dp.service.station;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.css.nsfw.dp.dao.nfpt.StationEntityTaxServerDao;
import com.css.nsfw.dp.utils.Utils;

@Component
public class StationEntityTaxServerService {
	@Autowired
	private StationEntityTaxServerDao stationEntityTaxServerDao;

	// 分局实体窗口
	public Map getEntityWindows(String unitCode, String busiType) {
		// 参数 分局机构编码 业务大类代码
		Map<String, Object> params = new HashMap<>();
		params.put("unitCode", unitCode);
		params.put("busiType", busiType);

		// 查询结果
		List<Map<String, Object>> data = (List<Map<String, Object>>) stationEntityTaxServerDao.getEntityWindows(params);

		// 返回结果
		Map<String, List<Map<String, Object>>> result = new HashMap<String, List<Map<String, Object>>>();

		// 将list结构的查询结果 转换成 Map<大厅名称,list>结构的返回结果

		for (int i = 0; i < data.size(); i++) {

			String childCode = String.valueOf(data.get(i).get("DTMC"));

			List<Map<String, Object>> tem = result.get(childCode);

			if (tem == null) {
				tem = new ArrayList<Map<String, Object>>();
				result.put(childCode, tem);
			}

			tem.add(data.get(i));
		}

		return result;
	}

	public Map getDetailInfo(String dtdm, String ckdm) {
		Map<String, Object> params = new HashMap<>();
		params.put("dtdm", dtdm);
		params.put("ckdm", ckdm);
		Map<String, Object> result = stationEntityTaxServerDao.getDetailInfo(params);
		// 格式化工作时长和休息时长
		if (result.get("GZSC") != null) {
			result.put("GZSC", Utils.FormatMinToHour(Double.valueOf(result.get("GZSC").toString())));
		}
		if (result.get("XXSC") != null) {
			result.put("XXSC", Utils.FormatMinToHour(Double.valueOf(result.get("XXSC").toString())));
		}
		if (result.get("YWPJSC") != null) {
			result.put("YWPJSC", result.get("YWPJSC") + "分钟");
		}
		return result;
	}

	public List getFwsxphb(String unitCode, String timeSpan) {
		Map<String, Object> params = new HashMap<>();
		params.put("unitCode", unitCode);
		params.put("timeSpan", timeSpan);
		return stationEntityTaxServerDao.getFwsxphb(params);
	}

	public List getFwmxphb(String unitCode, String timeSpan) {
		Map<String, Object> params = new HashMap<>();
		params.put("unitCode", unitCode);
		params.put("timeSpan", timeSpan);
		return stationEntityTaxServerDao.getFwmxphb(params);
	}

	public List getPieEcharts(String unitCode, String timeSpan) {
		Map<String, Object> params = new HashMap<>();
		params.put("unitCode", unitCode);
		params.put("timeSpan", timeSpan);
		return Utils.ListMapUpcaseToLowercase(stationEntityTaxServerDao.getPieEcharts(params));
	}

	public List getIndexEntityRank(String unitCode, String timeSpan) {
		Map<String, Object> params = new HashMap<>();
		params.put("unitCode", unitCode);
		params.put("timeSpan", timeSpan);
		return stationEntityTaxServerDao.getIndexEntityRank(params);
	}

	public Map getTotalCount(String unitCode, String timeSpan) {
		Map<String, Object> params = new HashMap<>();
		params.put("unitCode", unitCode);
		params.put("timeSpan", timeSpan);
		return stationEntityTaxServerDao.getTotalCount(params);
	}

}
