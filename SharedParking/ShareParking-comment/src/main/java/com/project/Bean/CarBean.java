package com.project.Bean;

import lombok.Data;
import lombok.experimental.Accessors;

/***
 * 汽车类
 * @author zxc
 *
 */
@Data
@Accessors(chain = true)
public class CarBean {
	private int id;
	private MemberBean m_id;
	private String carnumber;
}
