package com.css.nsfw.dp.utils;

import java.util.HashMap;
import java.util.Map;

public class Env {
	// 分局arm机 ywcl.tj_ywltjzd 列对应中文
	public static final Map<String, String> TJ_YWLTJZD = new HashMap<String, String>();
	static {
		TJ_YWLTJZD.put("业务总量", "zfs");
		TJ_YWLTJZD.put("申报数", "sb_fs");
		TJ_YWLTJZD.put("报税数", "bs_fs");
		TJ_YWLTJZD.put("发票验旧", "yj_fs");
		TJ_YWLTJZD.put("发票认证", "zprz_fs+hyrz_fs");
		TJ_YWLTJZD.put("纳税清单打印", "nsqd_fs");
		TJ_YWLTJZD.put("个税凭证打印", "gspz_fs");
		TJ_YWLTJZD.put("发票销售", "fply_fs");
		TJ_YWLTJZD.put("发票代开", "zpdk_fs+ppdk_fs+tydk_fs");
	}

}
