package com.css.nsfw.dp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.css.nsfw.dp.dao.local.StationEntityTaxServerDao;

@Component
public class StationEntityTaxServerService {
	@Autowired
	private StationEntityTaxServerDao stationEntityTaxServerDao;

	// 当日预约数量
	 
	public Map getEntityWindows(String timeSpan) {
		List<Map<String,Object>> data = (List<Map<String,Object>>)stationEntityTaxServerDao.getEntityWindows();
		Map<String, List<Map<String, Object>>> result = new HashMap<String, List<Map<String, Object>>>();
		for (int i = 0; i < data.size(); i++) {
			String childCode=String.valueOf(data.get(i).get("STATION_CHILD_CODE"));
			List<Map<String, Object>> tem = result.get(childCode);
			if(tem==null){
				tem = new ArrayList<Map<String, Object>>();
				result.put(childCode, tem);
			}
			tem.add(data.get(i));
		}
		
		return result;
	}

}
