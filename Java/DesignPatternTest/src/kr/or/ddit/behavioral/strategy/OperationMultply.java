package kr.or.ddit.behavioral.strategy;

public class OperationMultply implements Strategy{
	
	@Override
	public int doOperation(int num1, int num2) {
		return num1*num2;
	}
}
