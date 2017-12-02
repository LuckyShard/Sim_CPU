package cpu.registrators;

import java.io.FileNotFoundException;

public class MainFrame {
	private PCIR iR;
	private MemReg Regis;
	private String MAR;
	private int MBR;
	private String[] helper;
	private int contador;
	private Boolean[] valParam1;
	private Boolean[] valParam2;
	private Boolean[] valDest;
	private String op,param1,param2,dest;
	public MainFrame() {
		this.helper = new String[3];
		this.valDest = new Boolean[]{ false,false};
		this.valParam1 = new Boolean[] {false,false};
		this.valParam2 = new Boolean[] {false,false};
		this.op = "";
		this.param1 = "";
		this.param2 = "";
		this.dest = "";
		this.iR = new PCIR();
		this.Regis = new MemReg();
		this.contador = 0;
	}
	public Boolean[] getValParam1() {
		return valParam1;
	}
	public void setValParam1(Boolean[] valParam1) {
		this.valParam1 = valParam1;
	}
	public Boolean[] getValParam2() {
		return valParam2;
	}
	public void setValParam2(Boolean[] valParam2) {
		this.valParam2 = valParam2;
	}
	public Boolean[] getValDest() {
		return valDest;
	}
	public void setValDest(Boolean[] valDest) {
		this.valDest = valDest;
	}
	public String getOp() {
		return op;
	}
	public void setOp(String op) {
		this.op = op;
	}
	public String getParam1() {
		return param1;
	}
	public void setParam1(String param1) {
		this.param1 = param1;
	}
	public String getParam2() {
		return param2;
	}
	public void setParam2(String param2) {
		this.param2 = param2;
	}
	public String getDest() {
		return dest;
	}
	public void setDest(String dest) {
		this.dest = dest;
	}
	public int getContador() {
		return contador;
	}
	public void setContador(int contador) {
		this.contador = contador;
	}
	
	public PCIR getiR() {
		return iR;
	}
	public void setiR(PCIR iR) {
		this.iR = iR;
	}
	public MemReg getRegis() {
		return Regis;
	}
	public void setRegis(MemReg regis) {
		Regis = regis;
	}
	public String getMAR() {
		return MAR;
	}
	public void setMAR(String mAR) {
		MAR = mAR;
	}
	public int getMBR() {
		return MBR;
	}
	public void setMBR(int mBR) {
		MBR = mBR;
	}
	public String[] getHelper() {
		return helper;
	}
	public void setHelper(String[] helper) {
		this.helper = helper;
	}
	public void counter_helper() {
		for(int i = 0; i < helper.length;i++) {
			if(helper[i] != null) {
				contador++;
			}
		}
		
	}
	public void insCycle(String fileIr,String memReg,String comReg) throws FileNotFoundException {
		this.iR.Intialize(fileIr);
		this.Regis.IntializeMem(memReg);
		this.Regis.IntializeReg(comReg);
		int i = 0;
		boolean flag = false;
		while(flag != true) {
			System.out.println("----- Ciclo de Instrução -----\n");
			helper = iR.getIR().get(i).split(" ");
			counter_helper();
			if(this.getContador() == 4) {
				op = helper[0];
				param1 = helper[1];
				param2 = helper[2];
				dest = helper[3];
				System.out.printf("----- Instrução : %s %s %s %s -----\n",op,param1,param2,dest);
				System.out.printf("PC : %d     IR : %s \n MAR : %s\n",this.iR.getPC(),this.iR.getIR().get(i),this.iR.getIR().get(i));
				this.dataCycle();
			}
			if(this.getContador() == 3) {
				op = helper[0];
				param1 = helper[1];
				dest = helper[2];
				System.out.printf("----- Instrução : %s %s  %s -----\n",op,param1,dest);
				System.out.printf("PC : %d     IR : %s \n MAR : %s\n",this.iR.getPC(),this.iR.getIR().get(i),this.iR.getIR().get(i));
				this.dataCycle();
			}
			/*for(int j = 0;j<helper.length;j++) {
				
				
				//System.out.printf(helper[j]+"\n");
				
			}*/
			
			
			i++;
			this.iR.setPC(i);
			this.setContador(0);
			if(i == iR.getIR().size()) {
				flag = true;
			}
		}
		
		
	} // end ciclo instrução
	private void dataCycle() {
		System.out.println("\n----- Ciclo de Dados -----\n");
	}

}
