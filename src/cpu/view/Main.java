package cpu.view;
import cpu.registrators.*;
import java.io.FileNotFoundException;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String file = "End_Data/add.txt";
		String file1 = "End_Data/Mem_data.txt";
		String file2 = "End_Data/cRegister.txt";
		MainFrame mF = new MainFrame();
		
		try {			
			mF.insCycle(file,file1,file2);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
