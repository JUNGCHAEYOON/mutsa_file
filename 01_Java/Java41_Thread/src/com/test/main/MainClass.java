package com.test.main;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 동기 : 작업을 순차적으로 처리하는 것을 의미한다.
		// 비동기 : 동시에 여러 작업을 처리하는 것을 의미한다.
		// 동기적인 작업 다수를 비동기적으로 처리하게 된다.
		// Thread : 자바에서 비동기 처리를 지원하기 위해 제공하는 클래스
		
		// 오류가 발생할 가능성이 높은 작업의 경우 별도의 쓰래드로 운영하면
		// 안정적으로 운영할 수 있다.
		
		// Thread 클래스를 상속받은 쓰래드 클래스
		TestClass1 t1 = new TestClass1();
		t1.start();
		
		// Runnable 인터페이스를 구현한 클래스
		TestClass2 t2 = new TestClass2();
		Thread thread = new Thread(t2);
		thread.start();
		
		
		try {
			for(int i = 0 ; i < 100 ; i++) {
				Thread.sleep(1000);
				System.out.println("main");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}

// Thread를 상속받은 클래스
class TestClass1 extends Thread{
	// 쓰래드를 발생시켜 처리할 메서드
	public void run() {
		try {
			for(int i = 0 ; i < 100 ; i++) {
				Thread.sleep(500);
				System.out.println("TestClass1");	
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}

// runnable 인터페이스를 구한한 클래스
class TestClass2 implements Runnable{
	@Override
	public void run() {
		try {
			for(int i = 0 ; i < 100 ; i++) {
				Thread.sleep(300);
				System.out.println("TestClass2");
				
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}







