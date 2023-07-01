package com.test.main;

import com.test.pkg1.TestClass3;
import com.test.pkg1.TestClass6;
import com.test.pkg1.TestClass8;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 접근 제한자
		// 외부에서의 접근 허용 여부를 설정하기 위해 사용한다.
		// 데이터의 무결성이나 공개하고 싶지 않은 메서드 등을 만들 때 사용한다.
		// public : 제한 없음
		// private : 접근 불가
		// protected : 패키지가 같으면 제한이 없고 패키지가 다를 경우 상속관계에서만
		// 접근이 가능하다.
		// default : 같은 패키지 내부에서만 접근이 가능하다.
		
		// 같은 패키지에 있는 public 클래스의 객체를 생성한다.
		TestClass1 t1 = new TestClass1();
		// 같은 패키지에 있는 default 클래스의 객체를 생성한다.
		TestClass2 t2 = new TestClass2();
		// 다른 패키지에 있는 public 클래스의 객체를 생성한다.
		TestClass3 t3 = new TestClass3();
		// 다른 패키지에 있는 default 클래스의 객체를 생성한다.
		TestClass4 t4 = new TestClass4();
		
		// 같은 패키지에 있는 객체를 생성한다(생성자 접근제한자 테스트)
		// public
		TestClass5 t50 = new TestClass5();
		// default
		TestClass5 t51 = new TestClass5(10);
		// protected
		TestClass5 t52 = new TestClass5(10, 20);
		// private
		TestClass5 t53 = new TestClass5(10, 20, 30);
		
		// 다른 패키지의 클래스를 통해 객체를 생성한다(생성자 테스트)
		// public
		TestClass6 t60 = new TestClass6();
		// default
		TestClass6 t61 = new TestClass6(10);
		// protected
		TestClass6 t62 = new TestClass6(10, 20);
		// private
		TestClass6 t63 = new TestClass6(10, 20, 30);
		
		// 같은 패키지의 클래스의 객체를 생성한다(변수, 메서드 테스트)
		TestClass7 t7 = new TestClass7();
		// public
		System.out.printf("t7.memberA : %d\n", t7.memberA);
		// default
		System.out.printf("t7.memberB : %d\n", t7.memberB);
		// protected
		System.out.printf("t7.memberC : %d\n", t7.memberC);
		// private
		System.out.printf("t7.memberD : %d\n", t7.memberD);
		
		// public
		t7.method1();
		// default
		t7.method2();
		// protected
		t7.method3();
		// private
		t7.method4();
		
		// 다른 패키지의 클래스를 통해 객체를 생성한다(변수, 메서드 테스트)
		TestClass8 t8 = new TestClass8();
		// public
		System.out.printf("t8.memberA : %d\n", t8.memberA);
		// default
		System.out.printf("t8.memberB : %d\n", t8.memberB);
		// protected
		System.out.printf("t8.memberC : %d\n", t8.memberC);
		// private
		System.out.printf("t8.memberD : %d\n", t8.memberD);
		
		// public
		t8.method1();
		// default
		t8.method2();
		// protected
		t8.method3();
		// private
		t8.method4();
	}
}

// 같은 패키지에 있는 public 클래스를 상속받은 클래스
class SubClass1 extends TestClass1{
	
}
// 같은 패키지에 있는 default 클래스를 상속받은 클래스
class SubClass2 extends TestClass2{
	
}
// 다른 패키지에 있는 public 클래스를 상속받은 클래스
class SubClass3 extends TestClass3{
	
}
// 다른 패키지에 있는 default 클래스를 상속
class SubClass4 extends TestClass4{
	
}
// 같든 패키지에 있는 클래스를 상속 받는다.(접근제한자 테스트)
class SubClass5 extends TestClass5{
	// 기본적으로 부모클래스의 생성자중 매개변수가 없는 생성자를
	// 호출한다.
	// TestClass5의 기본생성자가 public 이므로 문제가 발생하지 않는다.
	public SubClass5() {
		// 부모의 public 생성자 호출
		super();
	}
	public SubClass5(int a1) {
		// 부모의 default 생성자 호출
		super(10);
	}
	public SubClass5(int a1, int a2) {
		// 부모의 protected 생성자 호출
		super(10, 20);
	}
	public SubClass5(int a1, int a2, int a3) {
		// 부모의 private 생성자 호출
		super(10, 20, 30);
	}
}
// 다른 패키지의 클래스를 상속받는다(생성자 테스트)
class SubClass6 extends TestClass6{
	// public
	public SubClass6() {
		super();
	}
	// default
	public SubClass6(int a1) {
		super(10);
	}
	// protected
	public SubClass6(int a1, int a2) {
		super(10, 20);
	}
	// private
	public SubClass6(int a1, int a2, int a3) {
		super(10, 20, 30);
	}
}

// 같은 패키지의 클래스를 상속 받은 클래스(변수, 메서드 테스트)
class SubCclass7 extends TestClass7{
	
	public void testMethod() {
		// 변수
		// public
		System.out.printf("memberA : %d\n", memberA);
		// default
		System.out.printf("memberB : %d\n", memberB);
		// protected
		System.out.printf("memberC : %d\n", memberC);
		// private
		System.out.printf("memberD : %d\n", memberD);
		
		// 메서드
		// public
		method1();
		// default
		method2();
		// protected
		method3();
		// private
		method4();
	}
}

// 다른 패키지의 클래스를 상속 받은 클래스(변수, 메서드 테스트)
class SubClass8 extends TestClass8{
	
	public void testMethod() {
		// 변수
		// public
		System.out.printf("memberA : %d\n", memberA);
		// default
		System.out.printf("memberB : %d\n", memberB);
		// protected
		System.out.printf("memberC : %d\n", memberC);
		// private
		System.out.printf("memberD : %d\n", memberD);
		// 메서드
		// public
		method1();
		// default
		method2();
		// protected
		method3();
		// private
		method4();
	}
}















