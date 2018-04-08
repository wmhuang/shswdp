package com.css.nsfw.dp.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.Cookie;

public class Utils {
	// 分钟转小时分钟形式显示
	public static String FormatMinToHour(Double min) {
		if (min == null) {
			return "取值异常";
		}
		BigDecimal bd = new BigDecimal(min).setScale(0, BigDecimal.ROUND_HALF_UP);
		int mina = Integer.parseInt(bd.toString());
		int hour = mina / 60;
		int mins = mina % 60;
		if (hour > 0) {
			return hour + "小时" + mins + "分钟";
		} else {
			return mins + "分钟";
		}

	}

	// 通过cookie名称获取cookie值
	public static String getCookieValueByName(Cookie[] cookies, String cookieName) {
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals(cookieName)) {
					return cookies[i].getValue();
				}
			}
		}
		return "";
	}

	// 单行转列返回大写NAME VALUE
	public static List<Map<String, Object>> rowToCol(Map<String, Long> row) {

		List<Map<String, Object>> result = new ArrayList<>();
		Iterator<Map.Entry<String, Long>> it = row.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Long> entry = it.next();
			Map<String, Object> tempMap = new HashMap<String, Object>();
			tempMap.put("NAME", entry.getKey());
			tempMap.put("VALUE", entry.getValue());
			result.add(tempMap);
		}
		return result;
	}

	// 单行转列返回小name value
	public static List<Map<String, Object>> rowToColSmall(Map<String, Long> row) {

		List<Map<String, Object>> result = new ArrayList<>();
		Iterator<Map.Entry<String, Long>> it = row.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Long> entry = it.next();
			Map<String, Object> tempMap = new HashMap<String, Object>();
			tempMap.put("name", entry.getKey());
			tempMap.put("value", entry.getValue());
			result.add(tempMap);
		}
		return result;
	}

	// [{NAME=a,VALUE=1},{NAME=b,VALUE=2}]转换成[{name=a,value=1},{name=b,value=2}]
	public static List<Map<String, Object>> ListMapUpcaseToLowercase(List<Map<String, Object>> list) {
		List<Map<String, Object>> result = new ArrayList<>();
		for (Map<String, Object> map : list) {
			Iterator<Map.Entry<String, Object>> iter = map.entrySet().iterator();
			Map<String, Object> tempMap = new HashMap<String, Object>();
			while (iter.hasNext()) {
				Map.Entry<String, Object> entry = iter.next();
				tempMap.put(entry.getKey().toLowerCase(), entry.getValue());
			}
			result.add(tempMap);
		}
		return result;
	}

	// 单行转列返回小name value 非零
	public static List<Map<String, Object>> rowToColSmallNotZero(Map<String, Long> row) {

		List<Map<String, Object>> result = new ArrayList<>();
		Iterator<Map.Entry<String, Long>> it = row.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Long> entry = it.next();
			Map<String, Object> tempMap = new HashMap<String, Object>();
			if (entry.getValue() == 0L) {
				// 不返回有零的数据
				continue;
			}
			tempMap.put("name", entry.getKey());
			tempMap.put("value", entry.getValue());
			result.add(tempMap);
		}
		return result;
	}

	// map排序
	public static Map<String, Long> mapSortByValue(Map<String, Object> map) {
		// if(map==null){
		// return new HashMap<String,Long>();
		// }
		Map<String, Long> sortedMap = new LinkedHashMap<String, Long>();
		List<Map.Entry<String, Object>> entryList = new ArrayList<Map.Entry<String, Object>>(map.entrySet());
		Collections.sort(entryList, new MapValueComparator());

		Iterator<Map.Entry<String, Object>> iter = entryList.iterator();
		Map.Entry<String, Object> tmpEntry = null;
		while (iter.hasNext()) {
			tmpEntry = iter.next();
			sortedMap.put(tmpEntry.getKey(), ((BigDecimal) tmpEntry.getValue()).longValue());
		}
		return sortedMap;
	}
}

class MapValueComparator implements Comparator<Map.Entry<String, Object>> {

	@Override
	public int compare(Entry<String, Object> me1, Entry<String, Object> me2) {
		return ((Long) ((BigDecimal) me2.getValue()).longValue())
				.compareTo((Long) ((BigDecimal) me1.getValue()).longValue());
	}

}