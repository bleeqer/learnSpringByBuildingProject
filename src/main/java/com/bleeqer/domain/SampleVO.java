package com.bleeqer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor // ����ִ� �����ڸ� ����� ���� ������̼�
public class SampleVO {
	
	private Integer mno;
	private String firstname;
	private String lastName;
}
