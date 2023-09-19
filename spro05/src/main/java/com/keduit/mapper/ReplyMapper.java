package com.keduit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.keduit.domain.Criteria;
import com.keduit.domain.replyVO;

public interface ReplyMapper {
	
	public int insert(replyVO vo);
	
	public replyVO read(Long rno);
	
	public int delete(Long rno);
	
	public int update(replyVO reply);
	
	public List<replyVO> getListWithPaging(
							@Param("cri")Criteria cri, 
							@Param("bno")Long bno);

	//bno count 이기에 int타입 꼬옥 확인 하고 타입을 적자.
	public int getCountByBno(Long bno);
	
}
