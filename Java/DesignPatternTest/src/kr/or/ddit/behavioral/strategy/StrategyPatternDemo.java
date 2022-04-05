package kr.or.ddit.behavioral.strategy;

public class StrategyPatternDemo {
	public static void main(String[] args) {
		Context context = new Context(new OperationAdd());
		System.out.println("10 + 5 : "  + context.executeStrategy(10, 5));
		
		context = new Context(new OperationSubtract());
		System.out.println("10 - 5 : " + context.executeStrategy(10, 5));
		
		context = new Context(new OperationMultply());	// 생성자를 통해 결정한다. 
		System.out.println("10 * 5 : " + context.executeStrategy(10, 5));
	}
}