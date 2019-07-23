package com.project.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class CreateOrderInfo {
	public static String  getOrderNumber() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String newDate = simpleDateFormat.format(new Date());
		String res = "";
		Random random = new Random();
		for (int i = 0; i < 3; i++) {
			res+=random.nextInt(10);
		}
		
		return newDate+res;
		
	}

	
	public static String getCreateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String str = sdf.format(date);
       
		return str;
	}
}
