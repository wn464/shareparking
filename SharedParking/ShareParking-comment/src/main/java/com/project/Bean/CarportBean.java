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
	private String address;//车位地址
	private String carportnumber;//车位编号
	private double coordinate_x;//车位经度
	private double coordinate_y;//车位纬度
	private String begintime;//车位开始出租时间
	private String endtime;//车位结束出租时间
	private MarkBean status;//车位出租状态
	private ImagesBean imgs_id;//车位图片
	private String statusimage;//车位审核图片
	private double price;//车位出租价格
	private MemberBean m_id;//车位所属人
	private MarkBean auditstatus; //车位审核状态
}
