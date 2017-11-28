package cpu.registrators;
import java.io.*;
import java.util.ArrayList;
public class MemReg {
	private ArrayList<String> MAR;
	private ArrayList<Integer> MBR;
	private int acc;
	private ArrayList<Integer> commonRegister;
	private String file;
	public MemReg() {
		this.MAR = new ArrayList<String>();
		this.MBR = new ArrayList<Integer>();
		this.acc = 0;
		this.commonRegister = new ArrayList<Integer>();
		this.file = "";
	}
	public ArrayList<String> getMAR() {
		return MAR;
	}
	public void setMAR(ArrayList<String> mAR) {
		MAR = mAR;
	}
	public ArrayList<Integer> getMBR() {
		return MBR;
	}
	public void setMBR(ArrayList<Integer> mBR) {
		MBR = mBR;
	}
	public int getAcc() {
		return acc;
	}
	public void setAcc(int acc) {
		this.acc = acc;
	}
	public ArrayList<Integer> getCommonRegister() {
		return commonRegister;
	}
	public void setCommonRegister(ArrayList<Integer> commonRegister) {
		this.commonRegister = commonRegister;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public void IntializeMem(String fileIn) throws FileNotFoundException {
		this.setFile(fileIn);
		BufferedReader br = new BufferedReader(new FileReader(this.file));
		
		try {
			
			String line = br.readLine();
			while(line != null) {
					String[] keyVal  = line.split(":");
					this.MAR.add(keyVal[0]);
					this.MBR.add(Integer.parseInt(keyVal[1]));
				
				line = br.readLine();
			}
			
			
		}
		catch(IOException ex) {
			System.out.println("Arquivo não encontrado");
		}
	}
	public void IntializeReg(String fileIn) throws FileNotFoundException {
		this.setFile(fileIn);
		BufferedReader br = new BufferedReader(new FileReader(this.file));
		
		try {
			
			String line = br.readLine();
			while(line != null) {
				this.commonRegister.add(Integer.parseInt(line));
				line = br.readLine();
			}
			
			
		}
		catch(IOException ex) {
			System.out.println("Arquivo não encontrado");
		}
		
	}
	public void showItMem() {
		for(int i = 0; i<this.MAR.size();i++) {
			System.out.println("MAR : " + this.MAR.get(i) + " MBR : " + this.MBR.get(i));
		}
	}
	public void showItCRegister() {
		for(int i = 0; i<this.commonRegister.size();i++) {
			System.out.printf("Registrador[%d] : %d\n",i,this.commonRegister.get(i));
		}
	}

}
