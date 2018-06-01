package com.css.nsfw.dp.service.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.css.nsfw.dp.dao.nfpt.DpIndexViewDao;

@Component
public class DpIndexViewService {
	@Autowired
	private DpIndexViewDao dpIndexViewDao;

	public String getUnitNameByCode(String unitCode) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("unitCode", unitCode);
		List result = dpIndexViewDao.getUnitNameByUnitCode(params);
		if (!(result.size() == 1)) {
			throw new Exception("请检查税务局编码{"+unitCode+"}是否正确！");
		}
		Map<String, Object> one = (Map<String, Object>) result.get(0);
		return (String) one.get("MC");
	}
}
