package com.bleeqer.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

// using lombok, Data automatically generates getters/setters and toString
@Data
public class BoardVO {
	
	private Long bno;
	private String title;
	private String content;
	private String writer;
	private Date regDate;
	private Date updateDate;
	
	private int replyCnt; // ��� �� ǥ�ø� ���� ���� �߰��� Į��
	
	private List<BoardAttachVO> attachList;
}

