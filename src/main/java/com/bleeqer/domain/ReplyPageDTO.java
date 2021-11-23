package com.bleeqer.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
public class ReplyPageDTO {
	
	// @AllArgsConstructor ������̼����� ��� attribute���� parameter�� �޴� �����ڸ� ����
	private int replyCnt;
	private List<ReplyVO> list;
}
