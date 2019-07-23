package com.project.Bean;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
/**
 * 分页信息
 * @author x1c
 *
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class PageBean implements Serializable{

	private int page;
	private int size;
	private int totalNumber;
	private int totalPage;
	private List<?> list;
	
	
	
}
