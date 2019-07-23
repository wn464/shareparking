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
	private String comtime;
	private String imgName;
	private OrderBean o_id;
	private MemberBean mem_j_id;
	private MemberBean mem_y_id;
    private MarkBean status;
}
