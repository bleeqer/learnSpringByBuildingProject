package com.bleeqer.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// lombok ÀÌ¿כ
@Getter
@Setter
@ToString
public class Criteria {
	
	private int pageNum;
	private int amount;
	
	public Criteria() {
		this(1, 10);
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
}
