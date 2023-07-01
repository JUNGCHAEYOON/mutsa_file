package com.test.main;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// java.lang 패키지
		// 자바에서 유일하게 import 하지 않고 사용할 수 있는 패키지이다.
		// java.lang 패키지에는 자바 프로그램을 작성할 때 많이 사용하는 것들이
		// 모여 있다.
		
		TestClass4 t4 = new TestClass4();
		TestClass3 t3 = t4;
		TestClass2 t2 = t4;
		TestClass1 t1 = t4;
		Object obj = t4;
	}
}
// Java에서 클래스를 정의할 때 상속 받는 코드를 작성하지 않으면
// extends Object가 추가된다고 생각하면 된다.
class TestClass1{
	
}
class TestClass2 extends TestClass1{
	
}
class TestClass3 extends TestClass2{
	
}
class TestClass4 extends TestClass3{
	
}







