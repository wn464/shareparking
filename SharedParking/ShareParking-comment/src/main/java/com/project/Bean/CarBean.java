package com.project.Bean;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/***
 * 汽车类
 * @author zxc
 *
 */
@Data
@Accessors(chain = true)
public class CarBean implements Serializable {
	/**
	 * 
	 */
	private int id;
	private MemberBean m_id;
	private String carnumber;
}
