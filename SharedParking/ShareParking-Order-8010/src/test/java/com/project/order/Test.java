package com.project.order;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Test {
	public static void main(String[] args) {
		 String d1 = "2019-07-22 18:53:37";
	        String d2 = "2019-07-22 19:53:37";
	         
	        /* 先转成毫秒并求差 */
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        long m = 0;
			try {
				m = sdf.parse(d2).getTime() - sdf.parse(d1).getTime();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	         System.out.println(m);
	        /* 根据你的需求进行单位转换 */
	        System.out.println("---------------------相差分钟数:"+ m/(1000*60) );
	}
	

}
