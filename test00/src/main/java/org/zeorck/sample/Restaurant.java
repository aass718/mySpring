package org.zeorck.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Setter;

@Component
@Data
public class Restaurant {
	//필드 주입
	@Setter(onMethod_ = @Autowired)
	private Chef chef;
}
