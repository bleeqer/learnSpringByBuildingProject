package com.bleeqer.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {
	
	private int startPage;
	private int endPage;
	private boolean prev, next;
	
	private int total;
	private Criteria cri;
	
	public PageDTO(Criteria cri, int total) {
		
		this.cri = cri;
		this.total = total;
		
//		10페이지 단위로 마지막 페이지 계산 
		this.endPage = (int) (Math.ceil(cri.getPageNum() / 10.0)) * 10;
		
//		마지막 페이지 - 9 하여 시작 페이지 계산 (페이지 번호를 10개 나타낸다고 했을 떄)
		this.startPage = this.endPage - 9;
		
//		실제 필요한 페이지 수 계산
		int realEnd = (int) (Math.ceil((total * 1.0) / cri.getAmount()));
		
//		실제 필요한 페이지 수가 endPage보다 작을 경우 realEnd를 endPage에 할당
		if (realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		
//		startPage가 1보다 클 경우 true
		this.prev = this.startPage > 1;
		
//		realEnd가 endPage보다 클 경우 true
		this.next = this.endPage < realEnd;
	}
}
