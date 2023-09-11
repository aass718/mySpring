package com.keduit.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {
	          //더미데이블
	@Select("select sysdate from dual ")
	public String getTime();
	
	public String getTime2();
	
}
