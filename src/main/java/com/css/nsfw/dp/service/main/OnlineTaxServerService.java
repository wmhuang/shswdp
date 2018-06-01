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

	// 当日预约数量
	public Map getBespeakNum(String timeSpan) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("timeSpan", timeSpan);
		return onlineTaxServerDao.getBespeakNum(params);
	}

	// 微信取号总量
	public Map getOfferTotalCountByWeChat(String timeSpan) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("timeSpan", timeSpan);
		return onlineTaxServerDao.getOfferTotalCountByWeChat(params);
	}

	// 业务受理总量
	public Map getHanldedCount(String timeSpan) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("timeSpan", timeSpan);
		return onlineTaxServerDao.getHanldedCount(params);
	}

	// 税企互动总量
	public Map getTaxAndCominteractNum(String timeSpan) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("timeSpan", timeSpan);
		return onlineTaxServerDao.getTaxAndCominteractNum(params);
	}

	// 业务受理总量排行榜TOP5
	public List getHandldedRank(String timeSpan) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("timeSpan", timeSpan);
		return onlineTaxServerDao.getHandldedRank(params);
	}

	public List getBusinessRankByStation(String timeSpan, String swjgDm) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("timeSpan", timeSpan);
		params.put("swjgDm", swjgDm);
		return onlineTaxServerDao.getBusinessRankByStation(params);
	}

	public List getOfferNumByWeChatRank(String timeSpan) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("timeSpan", timeSpan);
		return onlineTaxServerDao.getOfferNumByWeChatRank(params);
	}

	public List getTbyyfj(String timeSpan) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("timeSpan", timeSpan);
		return onlineTaxServerDao.getTbyyfj(params);
	}

	public List getBqyyfj(String timeSpan) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("timeSpan", timeSpan);
		return onlineTaxServerDao.getBqyyfj(params);
	}

	/**
	 * 重点事项办理量 包括 增值税发票代开和其他四个
	 * 
	 * @param timeSpan
	 * @return
	 * @createDate 2018年6月1日
	 * @author wmhuang
	 */
	public Map getZdsxbll(String timeSpan) {
		Map<String, Object> result = new HashMap<String, Object>();

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("timeSpan", timeSpan);

		Map zzsfpdk = onlineTaxServerDao.getZdsxbllZzsfpdk(params);
		result.put("ZZSFPDK", zzsfpdk.get("VALUE"));
		List zdsxbll = onlineTaxServerDao.getZdsxbll(params);
		for (int i = 0; i < zdsxbll.size(); i++) {
			Map<String, Object> tempMap = (Map<String, Object>) zdsxbll.get(i);
			result.put((String) tempMap.get("XM_DM"), tempMap.get("VALUE"));
		}

		return result;
	}
}
