package Ex2;

public class TestBNDepot implements Runnable{

	basNiveau bNiveau;
	static TestBNDepot testbnd;
	
	public TestBNDepot(basNiveau bNiveau) {
		super();
		this.bNiveau = bNiveau;
		testbnd = this;
	}

	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				bNiveau.deposer(new String("OK"));
				System.out.println(bNiveau.toString());
				Thread.currentThread().sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			}
		}
		
	}
	
	public static void liberer (){
		testbnd.notify();
	}
	

}
