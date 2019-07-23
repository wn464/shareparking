package com.project.Bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)//链式表达
public class ComplaintBean implements Serializable{
	private int id;
	private String message;
	private String Comtime;
	private String imgName;
	private int o_id;
	private int mem_j_id;
	private int mem_y_id;

}
