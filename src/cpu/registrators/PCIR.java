package cpu.registrators;
import java.io.*;
import java.util.ArrayList;
public class PCIR {
	private int PC;
	private ArrayList<String> IR;
	private String file;
	public PCIR() {
		this.PC = 0;
		IR = new ArrayList<String>();
		file = "";

	}
	public int getPC() {
		return PC;
	}
	public void setPC(int pC) {
		PC = pC;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public ArrayList<String> getIR() {
		return IR;
	}
	public void setIR(ArrayList<String> iR) {
		IR = iR;
	}
	public void Intialize(String fileIn) throws FileNotFoundException {
		this.setFile(fileIn);
		BufferedReader br = new BufferedReader(new FileReader(this.file));
		try {
			String line = br.readLine();
			while(line != null) {
				IR.add(line);
				line = br.readLine();
			}
			br.close();
			
		}
		catch(IOException ex) {
			System.out.println("Arquivo não encontrado");
		}

	}
	public void showIt() {
		for(int i = 0; i<IR.size();i++) {
			System.out.println("PC: " + i + " IR: " + IR.get(i));
		}
	}

}
