package com.test.main;

import java.util.Calendar;
import java.util.Date;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Date : 컴퓨터 입장에서의 시간 관리
		// Calendar : 사람 입장에서의 시간 관리
		
		// 현재 시간을 관리하는 Date 객체를 생성한다.
		Date date1 = new Date();
		System.out.println(date1);
		
		// date 객체가 관리하는 시간값을 유닉스타임으로 가져온다.
		long time1 = date1.getTime();
		System.out.println(time1);
		
		// 유닉스 타임을 지정하여 Date 객체를 생성한다.
		Date date2 = new Date(2000000000000L);
		System.out.println(date2);
		
		// 0을 지정하여 Date 객체를 생성한다.
		Date date3 = new Date(0);
		System.out.println(date3);
		
		// 최대 시간
		Date date4 = new Date(Long.MAX_VALUE);
		// 최소 시간
		Date date5 = new Date(Long.MIN_VALUE);
		
		System.out.println(date4);
		System.out.println(date5);
		
		// 현재 시간을 관리하는 Calendar 생성
		Calendar c1 = Calendar.getInstance();
		System.out.println(c1);
		
		int year = c1.get(Calendar.YEAR);
		int month = c1.get(Calendar.MONTH) + 1;
		int day = c1.get(Calendar.DAY_OF_MONTH);
		int hour = c1.get(Calendar.HOUR);
		int minute = c1.get(Calendar.MINUTE);
		int second = c1.get(Calendar.SECOND);
		int ms = c1.get(Calendar.MILLISECOND);
		System.out.printf("%d-%d-%d %d:%d:%d:%d\n", year,
				month, day, hour, minute, second, ms);
		
		// 2023년 12월 31일 17시 37분 23초 800ms 를 지정한다.
		Calendar c2 = Calendar.getInstance();
		c2.set(Calendar.YEAR, 2023);
		c2.set(Calendar.MONTH, 11);
		c2.set(Calendar.DAY_OF_MONTH, 31);
		c2.set(Calendar.HOUR_OF_DAY, 17);
		c2.set(Calendar.MINUTE, 37);
		c2.set(Calendar.MILLISECOND, 800);
		
		// Date 객체로 가져온다.
		Date date100 = c2.getTime();
		System.out.println(date100);
		
	}
}


















