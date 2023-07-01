package com.test.main;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 중첩 클래스
		// 클래스 안에 클래스는 만드는 것을 의미한다.
		
		// 일반 중첩 클래스의 객체를 생성한다.
		// 외부 클래스의 객체를 생성한다.
		OuterClass1 out1 = new OuterClass1();
		// 이를 통해 내부 클래스의 객체를 생성한다.
		OuterClass1.InnerClass1 in1 = out1.new InnerClass1();
		System.out.printf("in1 : %s\n", in1);
		
		
		// static 중첩 클래스는 외부클래스의 객체 없이 바로 생성이 가능하다.
		OuterClass2.InnerClass2 in2 = new OuterClass2.InnerClass2();
		System.out.printf("in2 : %s\n", in2);
		
		
		// 익명 중첩 클래스
		// SuperClass100을 상속받은 이름이 없는 클래스를 작성하고
		// 필요한 메서드들을 overriding한다.
		// 그리고 자식클래스의 객체를 생성해준다.
		SuperClass100 s100 = new SuperClass100() {

			public void superMethod100() {
				System.out.println("overriding된 메서드");
			}
		};
		
		s100.superMethod100();
	}
}

// 일반 중첩 클래스
// 클래스 안에 클래스를 만든 개념
// 클래스 내부의 클래스는 외부 클래스의 객체를 통해서 생성할 수 있다.
// 특정 클래스에 정의된 멤버를 자주 사용하는 경우 사용한다.
// 장점 : 내부 클래스에서 외부 클래스의 멤버를 사용하는 것이 자유롭다.
// 단점 : 내부 클래스의 객체를 생성하려면 반드시 외부 클래스의 객체를 생성해야 한다.
class OuterClass1{
	
	int outerMemberA = 100;
	
	public void outerMethodA() {
		System.out.println("outerMethod!");
		// 내부 클래스의 멤버를 사용한다.
		System.out.printf("innerMemberA : %d\n", innerMemberA);
		innerMethodA();
	}
	
	class InnerClass1{
		
		int innerMemberA = 200;
		
		public void innerMethodA() {
			System.out.println("innerMethod!");
		}
		
		public void innerMethodB() {
			// 외부 클래스에 정의된 멤버를 사용한다.
			System.out.printf("outerMemberA : %d\n", outerMemberA);
			outerMethodA();
		}
	}
}

// static 중첩 클래스
// 장점 : 모르겠습니다..ㅠㅠ
// 단점 : 내부 클래스가 외부 클래스의 멤버를 자유롭게 사용할 수 있다는 특징이
// 사라져버린다. 오로지 외부 클래스에 정의된 static 변수나 메서드만 사용이 가능하다.
class OuterClass2{
	
	// 외부 클래스에 정의된 static 변수
	static int outerMemberA = 200;
	// 외부 클래스에 정의된 멤버 변수
	int outerMemberB = 300;
	
	public void outerMethod1() {
		// 내부 클래스의 static 멤버 사용
		System.out.println(InnerClass2.innerMemberA);
	}

	// static 중첩 클래스
	static class InnerClass2{
		// 내부 클래스에 정의된 static 변수
		static int innerMemberA = 100;
		
		public void innerMethod1() {
			// 외부 클래스에 정의된 static 변수
			// 외부 클래스의 이름을 생략할 수 있다.
			System.out.println(outerMemberA);
			// 외부 클래스에 정의된 멤버 변수
			System.out.println(outerMemberB);
		}
	}
}

// 지역 중첩 클래스
// 메서드 내부에 클래스를 작성하여 사용하는 클래스
// 메서드의 수행이 끝나면 클래스가 메모리에서 사라지기 때문에
// 메서드 외부에서는 클래스를 사용할 수 없다.
// 장점 : 메서드에서 사용하는 클래스를 작성할 때 다른 곳에 작성하지 않고
// 바로 작성해 사용할 수 있다.
// 단점 : 메서드 내부에서만 사용이 가능하다.
class OuterClass3{
	
	// 지역 중첩클래스 타입의 멤버변수 선언
	InnerClass3 in30;
	
	public void outerMethod3() {
		// 지역 중첩 클래스
		class InnerClass3{
			
		}
		// 지역 중첩 클래스 객체 생성
		InnerClass3 in3 = new InnerClass3();
		System.out.println(in3);
	}
	
	// 다른 메서드에서 사용
	public void outerMethod30(InnerClass3 in34) {
		InnerClass3 in33;
	}
}


// 익명 중첩 클래스
// 클래스의 이름이 없다는 뜻이다.
// 익명 중첩 클래스는 상속관계의 클래스는 만드는 것이다.
// 특정 클래스를 상속받은 클래스를 만듬과 동시에 객체를 생성할 때 사용한다.
// 메서드 overriding 때문에 사용한다.
// 특정 클래스를 상속받은 클래스의 객체를 단 하나만 만들어 사용할 때 사용한다.
class SuperClass100{
	
	public void superMethod100() {
		System.out.println("superMethod100");
	}
}











