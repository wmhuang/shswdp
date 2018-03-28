package com.css.nsfw.dp.dao.local;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface StationEntityTaxServerDao {
	// 
	List getEntityWindows();
}
