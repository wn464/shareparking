package com.project.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CountPrice {
	
//	public static void main(String[] args) throws ParseException {
//		countPrice("2019-07-27 15:06:00.0",40);
//	}

	public static double countPrice(String beginTime,double price){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	
		String differenceFormat = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//yyyy-mm-dd, 会出现时间不对, 因为小写的mm是代表: 秒
		Date sTime=null;
		try {
			sTime = sdf.parse(beginTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Long eTime = Calendar.getInstance().getTimeInMillis();
		long difference =  eTime  - sTime.getTime() ;
		long countTime = difference/(1000*60);
		System.out.println("相差分钟数"+countTime);
		price = price/60;
		System.out.println("每分钟单价为"+price);
		double totalPrice = price*countTime;
		System.out.println("总价为"+totalPrice);
		return totalPrice;
		
	
}
}