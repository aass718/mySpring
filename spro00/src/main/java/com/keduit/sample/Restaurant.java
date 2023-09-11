package com.keduit.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lombok.Data;


@Component
@Data
public class Restaurant {
	
	//필드 주입
	@Autowired
	private Chef chef;

	
}
