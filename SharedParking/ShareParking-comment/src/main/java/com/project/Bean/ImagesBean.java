package com.project.Bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 车位图片类
 * @author zxc
 *
 */
@Data
@Accessors(chain = true)
public class ImagesBean {
	private int id;
	private String image1;
	private String image2;
	private String image;
}