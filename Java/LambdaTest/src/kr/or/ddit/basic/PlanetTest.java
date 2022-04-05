package kr.or.ddit.basic;

/*
문제) 태양계 행성을 나타내는 enum Planet을 이용하여 구하시오.
(단, enum 객체 생성시 반지름을 이용하도록 정의하시오.) 

예) 행성의 반지름(KM):
수성(2439), 
금성(6052), 
지구(6371), 
화성(3390), 
목성(69911), 
토성(58232), 
천왕성(25362), 
해왕성(24622)
*/
public class PlanetTest {
	static final float PI = 3.141592f;
	public enum Planet{
		수성(2439), 금성(6052), 지구(6371), 화성(3390), 목성(69911), 토성(58232), 천왕성(25362), 해왕성(24622);
		
		private int r;
		
		Planet(int radius){
			r = radius;
		}
		
		public int getRadius() {
			return r;
		}
	}
	
	public void calcApparent() {
		for (int i = 0; i < 8; i++) {
			Planet[] planet;
			planet = Planet.values();
			double rSquare = Math.pow(planet[i].r, 2);
			double apparent = 4 * PI * rSquare;
			System.out.println(planet[i].name()+"의 표면적은 " + apparent + "입니다.");
		}
	}
	
	public static void main(String[] args) {
		PlanetTest obj = new PlanetTest();
		obj.calcApparent();
	}
}
