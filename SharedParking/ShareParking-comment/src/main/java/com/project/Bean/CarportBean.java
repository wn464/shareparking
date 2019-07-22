package com.project.Bean;

import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;
/**
 * 车位对象
 * @author zxc
 *
 */
@Data
@Accessors(chain = true)//链式表达
public class CarportBean {
	private int id;
	private String address;
	private String carportnumber;
	private double coordinate_x;
	private double coordinate_y;
	private Date begintime;
	private Date endtime;
	private MarkBean status;
	private ImagesBean imgs_id;
	private String statusimage;
	private double price;
	private MemberBean m_id;
}
