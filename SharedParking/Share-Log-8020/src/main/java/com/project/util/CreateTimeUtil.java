package com.project.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class CreateTimeUtil {
	

	
	public static String getCreateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String str = sdf.format(date);
       
		return str;
	}
}
