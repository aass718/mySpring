package com.keduit.domain;

import java.net.URI;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {
	private int pageNum;
	private int amount;
	
	// T, TC, TWC CW 제목, 제목+내용 제목+작성자+내용 이렇게 복합적인 검색을 위한 객체들이다.
	private String type;
	private String keyword;
	
	
	public Criteria() {
		this(1,10);
	}
	
	public Criteria(int pageNum, int amount) {
		this.amount =amount;
		this.pageNum = pageNum;
	}
	
	public String[] getTypeArr() {
		//여기서 type이 null일 경우 String[]배열을 반환하고 아닐경우. type에 무언가 있다면 type을 split으로 조각내서 반환한다.
		return type ==null? new String[] {}: type.split("");
	}
	
	public String getListLink() {
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
				.queryParam("pageNum", this.pageNum)
				.queryParam("amount", this.amount)
				.queryParam("type", this.type)
				.queryParam("keyword", this.keyword);
		return builder.toUriString();
	}
}
