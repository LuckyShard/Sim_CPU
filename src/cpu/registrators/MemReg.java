package cpu.registrators;
import java.io.*;
import java.util.ArrayList;
public class MemReg {
	private ArrayList<String> memAD;
	private ArrayList<String> rComAD;
	private ArrayList<Integer> memVal;
	private ArrayList<Integer> commonRegister;
	private int acc;
	private String file;
	public MemReg() {
		this.memAD = new ArrayList<String>();
		this.memVal = new ArrayList<Integer>();
		this.rComAD = new ArrayList<String>();
		this.commonRegister = new ArrayList<Integer>();
		this.acc = 0;
		this.file = "";
	}
	public ArrayList<String> getMemAD() {
		return memAD;
	}
	public void setMemAD(ArrayList<String> memAD) {
		this.memAD = memAD;
	}
	public ArrayList<String> getrComAD() {
		return rComAD;
	}
	public void setrComAD(ArrayList<String> rComAD) {
		this.rComAD = rComAD;
	}
	public ArrayList<Integer> getMemVal() {
		return memVal;
	}
	public void setMemVal(ArrayList<Integer> memVal) {
		this.memVal = memVal;
	}
	public ArrayList<Integer> getCommonRegister() {
		return commonRegister;
	}
	public void setCommonRegister(ArrayList<Integer> commonRegister) {
		this.commonRegister = commonRegister;
	}
	public int getAcc() {
		return acc;
	}
	public void setAcc(int acc) {
		this.acc = acc;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public void IntializeMem(String fileIn){
		this.setFile(fileIn);
		
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(this.file));
			String line = br.readLine();
			while(line != null) {
					String[] keyVal  = line.split(":");
					this.memAD.add(keyVal[0]);
					this.memVal.add(Integer.parseInt(keyVal[1]));
				
				line = br.readLine();
				
			}
			br.close();
			
			
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
				String[] keyVal  = line.split(":");
				this.rComAD.add(keyVal[0]);
				this.commonRegister.add(Integer.parseInt(keyVal[1]));
				line = br.readLine();
			}
			br.close();
			
			
		}
		catch(IOException ex) {
			System.out.println("Arquivo não encontrado");
		}
		
	}
	public void showItMem() {
		for(int i = 0; i<this.memAD.size();i++) {
			System.out.println("memAD : " + this.memAD.get(i) + " memVal : " + this.memVal.get(i));
		}
	}
	public void showItCRegister() {
		for(int i = 0; i<this.commonRegister.size();i++) {
			System.out.printf("Registrador[%d] : %d\n",i,this.commonRegister.get(i));
		}
	}

}
