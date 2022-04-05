package kr.or.ddit.creational.abstractFactory;


// 어떤 객체를 만들지 정해서 factory를 먼저 받아오고, 받아온 factory 통해 객체 생성하기
public class AbstractFactoryPatternDemo {
	public static void main(String[] args) {
		AbstractFactory shapeFactory = FactoryProducer.getFactory(false);
		Shape shape1 = shapeFactory.getShape("RECTANGLE");
		shape1.draw();
		
		Shape shape2 = shapeFactory.getShape("SQUARE");
		shape2.draw();
		
		// RoundedShapeFactory 가져오기
		AbstractFactory roundedFactory = FactoryProducer.getFactory(true);
		shape1 = roundedFactory.getShape("RECTANGLE");
		shape1.draw();
		
		shape2 = roundedFactory.getShape("SQUARE");
		shape2.draw();
	}
}