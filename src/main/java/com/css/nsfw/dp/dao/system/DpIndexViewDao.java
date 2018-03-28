package com.css.nsfw.dp.dao.system;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface DpIndexViewDao {
	List getUnitNameByUnitCode(Map<String, Object> params);
}
