package com.css.nsfw.dp.service.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.css.nsfw.dp.dao.wt.OnlineTaxServerDao;

@Component
public class OnlineTaxServerService {
	@Autowired
	private OnlineTaxServerDao onlineTaxServerDao;

	/**
	 * @param timeSpan
	 * @return
	 * @createDate 2018年5月31日
	 * @author wmhuang
	 */
	public List getYwslqk(String timeSpan) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("timeSpan", timeSpan);
		return onlineTaxServerDao.getywslqk(params);
	}

}
