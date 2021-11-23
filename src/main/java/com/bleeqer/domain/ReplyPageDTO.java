package com.bleeqer.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
public class ReplyPageDTO {
	
	// @AllArgsConstructor 어노테이션으로 모든 attribute들을 parameter로 받는 생성자를 생성
	private int replyCnt;
	private List<ReplyVO> list;
}
