package kr.or.ddit.structural.proxy;

public class ProxyImage implements Image{
	private RealImage realImage;
	private String fileName;
	
	public ProxyImage(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public void display() {
		if(realImage == null) {
			// 읽은 파일이 없으면 그 때 생성.. 
			// Proxy : 중간에 필터링을 거친다.
			realImage = new RealImage(fileName);
		}
		// 기존에 존재하면 존재하는 객체의 메서드를 활용
		realImage.display();
	}
	
	
}
