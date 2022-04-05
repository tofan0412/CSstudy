package kr.or.ddit.structural.decorator;

public class YellowShapedDecorator extends ShapeDecorator {

	public YellowShapedDecorator(Shape decoratedShape) {
		super(decoratedShape);
	}
	
	@Override
	public void draw() {
		decoratedShape.draw();
		
		System.out.println("경계색을 노란색으로 칠하고 있음");
	}
}
