package com.test.main;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 예외처리
		// 프로그램 수행중에 오류(예외)가 발생하거나 발생할 가능성이 있다면 JVM은
		// 오류가 JVM이나 OS에 전파되는 것을 막기위해 프로그램을 강제 종료시킨다.
		// 이때, 강제 종류가 되지 않게 하고 개발자가 준비한 오류 발생시 수행되어야
		// 하는 코드가 수행될 수 있도록 하여 오류를 대비하게 해주는 것이
		// 예외 처리
		// try : 원하는 처리의 코드
		// catch : 오류가 발생했거나 발생할 가능성이 있을때 동작하는 코드
		
		// 원하는 처리를 수행할 수 있는 코드 중에
		// 예외가 발생할 가능성이 있는 부분을 try로 묶어준다.
		try {
			// 0으로 나눈다.
			// int a1 = 10 / 0;
			
			// 객체의 아이디를 가지고 있지 않은 변수를 통해 객체 접근을 시도한다.
			// TestClass1 t1 = null;
			// t1.testMethod1();
			
			int [] array1 = new int[10];
			array1[20] = 200;
			System.out.println("오류 발생 시점 이후의 try 코드");
		}catch(ArithmeticException e){
			System.out.println("수학연산 오류가 발생했습니다");
		}catch(NullPointerException e) {
			System.out.println("객체 참조 오류가 발생했습니다");
		}catch(Exception e) {
			// 오류가 발생했을 때 처리될 코드는 catch부분에 만든다.
			System.out.println("오류가 발생했습니다");
			System.out.println(e);
			e.printStackTrace();
		}
		System.out.println("이 부분이 실행할까요??");
		
		try {
			TestClass1 t100 = new TestClass1();
			t100.testMethod2(100);
			t100.testMethod2(-100);
		}catch(Exception e) {
			System.out.println("오류발생");
		}
		
		// testMethod3는 예외들을 throws하고 있기 때문에
		// 반드시 예외처리를 해줘야 한다.
		try {
			TestClass1 t200 = new TestClass1();
			t200.testMethod3(100);
			t200.testMethod3(200);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
}

class TestClass1{
	public void testMethod1() {
		System.out.println("TestClass1의 testMethod1");
	}
	
	public void testMethod2(int a1) {
		// a1에 전달된 값이 양수가 아니면 오류를 발생시킨다.
		if(a1 <= 0) {
			throw new ArithmeticException("양수만 넣어라...");
		}
		System.out.println("a1은 양수입니다");
	}
	
	// throws : 이 메서드를 수행할 때 지정한 예외가 발생할 가능성이 있다고
	// 알려준다.
	// 이렇게 해주면 이 메서드를 호출 하는 쪽은 무조건 예외처리를 해야 한다.
	// RunTimeException을 상속받은 것들은  throws 해도 예외처리에대한
	// 강제성을 줄 수 없기 때문에 Exception도 전달한다.
	public void testMethod3(int a1) throws Exception, ArithmeticException, NullPointerException {
		// a1에 전달된 값이 양수가 아니면 오류를 발생시킨다.
		if(a1 <= 0) {
			throw new ArithmeticException("양수만 넣어라...");
		}
		System.out.println("a1은 양수입니다");
	}
}









