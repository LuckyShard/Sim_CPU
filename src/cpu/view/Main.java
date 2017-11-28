package cpu.view;
import cpu.registrators.*;

import java.io.FileNotFoundException;
import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String file = "End_Data/add.txt";
		String file1 = "End_Data/Mem_data.txt";
		String file2 = "End_Data/cRegister.txt";
		PCIR tri = new PCIR();
		MemReg zoe = new MemReg();
		try {
			tri.Intialize(file);
			zoe.IntializeMem(file1);
			zoe.IntializeReg(file2);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tri.showIt();
		System.out.println("-----------------------------------------------");
		zoe.showItMem();
		System.out.println("-----------------------------------------------");
		zoe.showItCRegister();

	}

}
