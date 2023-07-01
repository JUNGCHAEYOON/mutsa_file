package com.test.main;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// 코끼리 객체를 생성한다.
		AnimalClass a1 = new AnimalClass() {
			
			public void howEat() {
				System.out.println("코를 이용해 먹습니다");
			};
		};
		a1.type = "코끼리";
		a1.name = "코코코";
		a1.numberLegs = 4;
		
		// 강아지 객체를 생성한다
		AnimalClass a2 = new AnimalClass() {
			public void howEat() {
				System.out.println("사료를 먹습니다");
			}
		};
		a2.type = "강아지";
		a2.name = "몽몽이";
		a2.numberLegs = 4;
		
		System.out.println(a1.type);
		System.out.println(a1.name);
		System.out.println(a1.numberLegs);
		a1.howEat();
		
		System.out.println(a2.type);
		System.out.println(a2.name);
		System.out.println(a2.numberLegs);
		a2.howEat();
	}
}

class AnimalClass{
	String type;
	String name;
	int numberLegs;
	
	public void howEat() {
		
	}
}






