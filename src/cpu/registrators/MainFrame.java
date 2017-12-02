package cpu.registrators;

import java.io.FileNotFoundException;

public class MainFrame {
	private PCIR iR;
	private MemReg Regis;
	private String MAR;
	private int MBR;
	private String[] helper;
	private int contador;
	private Boolean[] c_valParam1;
	private Boolean[] c_valParam2;
	private Boolean[] valDest;
	private String op,param1,param2,dest;
	private int valParam1,valParam2,indexer1,indexer2,indexerDest;
	public MainFrame() {
		this.helper = new String[3];
		this.valDest = new Boolean[]{ false,false};
		this.c_valParam1 = new Boolean[] {false,false};
		this.c_valParam2 = new Boolean[] {false,false};
		this.op = "";
		this.param1 = "";
		this.param2 = "";
		this.dest = "";
		this.iR = new PCIR();
		this.Regis = new MemReg();
		this.contador = 0;
		this.valParam1 = this.valParam2 = 0;
		this.indexer1 = 0;
		this.indexer2 = 0;
		this.indexerDest = 0;
	}
	public void setValParam1(int valParam1) {
		this.valParam1 = valParam1;
	}
	public void setValParam2(int valParam2) {
		this.valParam2 = valParam2;
	}
	public Boolean[] getValParam1() {
		return c_valParam1;
	}
	public void setValParam1(Boolean[] valParam1) {
		this.c_valParam1 = valParam1;
	}
	public Boolean[] getValParam2() {
		return c_valParam2;
	}
	public void setValParam2(Boolean[] valParam2) {
		this.c_valParam2 = valParam2;
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
	private void findP1() {
		Boolean flag = false;
		while(flag == false) {
			for(int i = 0; i < this.Regis.getMemAD().size();i++) {
				if(this.param1.equals(this.Regis.getMemAD().get(i))) {
					this.c_valParam1[0] = true;
					this.c_valParam1[1] = false;
					this.indexer1 = i;
					flag = true;
					return;
				}
			}
			for(int i = 0; i < this.Regis.getrComAD().size(); i++) {
				if(this.param1.equals(this.Regis.getrComAD().get(i))) {
					this.c_valParam1[0] = false;
					this.c_valParam1[1] = true;
					this.indexer1  = i;
					flag = true;
					return;
				}
			}
			this.c_valParam1[0] = false;
			this.c_valParam1[1] = false;
			flag = true;
		}
	}
	private void findP2() {
		Boolean flag = false;
		while(flag == false) {
			for(int i = 0; i < this.Regis.getMemAD().size();i++) {
				if(this.param2.equals(this.Regis.getMemAD().get(i))) {
					this.c_valParam2[0] = true;
					this.c_valParam2[1] = false;
					this.indexer2 = i;
					flag = true;
					return;
				}
			}
			for(int i = 0; i < this.Regis.getrComAD().size(); i++) {
				if(this.param2.equals(this.Regis.getrComAD().get(i))) {
					this.c_valParam2[0] = false;
					this.c_valParam2[1] = true;
					this.indexer2  = i;
					flag = true;
					return;
				}
			}
			this.c_valParam2[0] = false;
			this.c_valParam2[1] = false;
			flag = true;
		}
	}
	private void findDest() {
		Boolean flag = false;
		while(flag == false) {
			for(int i = 0; i < this.Regis.getMemAD().size();i++) {
				if(this.dest.equals(this.Regis.getMemAD().get(i))) {
					this.valDest[0] = true;
					this.valDest[1] = false;
					this.indexerDest = i;
					flag = true;
					return;
				}
			}
			for(int i = 0; i < this.Regis.getrComAD().size(); i++) {
				if(this.dest.equals(this.Regis.getrComAD().get(i))) {
					this.valDest[0] = false;
					this.valDest[1] = true;
					this.indexerDest  = i;
					flag = true;
					return;
				}
			}
			this.valDest[0] = false;
			this.valDest[1] = false;
			flag = true;
		}
	}
	private void dataCycle() {
		this.findP1();
		System.out.printf("\n----- Ciclo de Dados -----\n");
		System.out.printf("\n----- Buscando %s -----\n ",this.param1);
		if(this.getContador() == 4) {
			if(this.c_valParam1[0] == true && this.c_valParam1[1] == false) {
				this.valParam1 = this.Regis.getMemVal().get(this.indexer1);
				System.out.printf("MAR: %s      MBR: %d\n",this.param1,this.valParam1);
				this.dataCycle2();
			}
			else if(this.c_valParam1[0] == false && this.c_valParam1[1] == true) {
				this.valParam1 = this.Regis.getCommonRegister().get(this.indexer1);
				System.out.printf("MAR: %s      MBR: %d\n",this.param1,this.valParam1);
				this.dataCycle2();
			}
			if (this.c_valParam1[0] == false && this.c_valParam1[1] == false) {
				this.valParam1 = Integer.parseInt(this.param1);
				System.out.printf("MBR: %d\n",this.valParam1);
				this.dataCycle2();
			}

		}
		if(this.getContador() == 3) {
			if(this.c_valParam1[0] == true && this.c_valParam1[1] == false) {
				this.valParam1 = this.Regis.getMemVal().get(this.indexer1);
				System.out.printf("MAR: %s      MBR: %d\n",this.param1,this.valParam1);
				this.exCycle();
			}
			else if(this.c_valParam1[0] == false && this.c_valParam1[1] == true) {
				this.valParam1 = this.Regis.getCommonRegister().get(this.indexer1);
				System.out.printf("MAR: %s      MBR: %d\n",this.param1,this.valParam1);
				this.exCycle();
			}
			if (this.c_valParam1[0] == false && this.c_valParam1[1] == false) {
				this.valParam1 = Integer.parseInt(this.param1);
				System.out.printf("MBR: %d\n",this.valParam1);
				this.exCycle();
			}

		}
	}
	private void dataCycle2() {
		this.findP2();
		System.out.printf("\n----- Buscando %s -----\n ",this.param2);
		if(this.getContador() == 4) {
			if(this.c_valParam2[0] == true && this.c_valParam2[1] == false) {
				this.valParam2 = this.Regis.getMemVal().get(this.indexer2);
				System.out.printf("MAR: %s      MBR: %d\n",this.param2,this.valParam2);
				this.exCycle();
			}
			else if(this.c_valParam2[0] == false && this.c_valParam2[1] == true) {
				this.valParam2 = this.Regis.getCommonRegister().get(this.indexer2);
				System.out.printf("MAR: %s      MBR: %d\n",this.param2,this.valParam2);
				this.exCycle();
			}
			if (this.c_valParam2[0] == false && this.c_valParam2[1] == false) {
				this.valParam2 = Integer.parseInt(this.param2);
				System.out.printf("MBR: %d\n",this.valParam2);
				this.exCycle();
			}

		}

	}
	private void exCycle() {
		this.findDest();
		int expression = 0;
		if(this.getContador() == 4) {
			
			System.out.println("\n----- Ciclo de execução -----\n");
			System.out.printf("%d %d\n",this.valParam1,this.valParam2);
			if(this.valDest[0] == true && this.valDest[1] == false) {
				this.Regis.setAcc(this.valParam1);
				this.Regis.getCommonRegister().set(0,this.valParam2);
				int oldVal = 0;
				if(this.op.equals("ADD")) {
					oldVal = this.Regis.getMemVal().get(this.indexerDest);
					expression = this.valParam1+this.valParam2;
					expression+= this.Regis.getMemVal().get(this.indexerDest);
					this.Regis.getMemVal().add(this.indexerDest,expression);
					System.out.printf("ACC : %d",this.Regis.getAcc());
					System.out.printf(" R0 : %d",this.Regis.getCommonRegister().get(0));
					System.out.printf("   Mem[%d] : %d",this.indexerDest,oldVal);
					System.out.printf("  MAR : %s  MBR : %d",this.dest,expression);
					
				}
				if(this.op.equals("SUB")) {
					oldVal = this.Regis.getMemVal().get(this.indexerDest);
					expression = this.valParam1 - this.valParam2 - this.Regis.getMemVal().get(this.indexerDest);
					this.Regis.getMemVal().add(this.indexerDest,expression);
					System.out.printf("ACC : %d",this.Regis.getAcc());
					System.out.printf(" R0 : %d",this.Regis.getCommonRegister().get(0));
					System.out.printf("   Mem[%d] : %d",this.indexerDest,oldVal);
					System.out.printf("  MAR : %s  MBR : %d\n",this.dest,expression);
				}
				if(this.op.equals("MUL")) {
					oldVal = this.Regis.getMemVal().get(this.indexerDest);
					expression = this.valParam1 * this.valParam2 * this.Regis.getMemVal().get(this.indexerDest);
					this.Regis.getMemVal().add(this.indexerDest,expression);
					System.out.printf("ACC : %d",this.Regis.getAcc());
					System.out.printf(" R0 : %d",this.Regis.getCommonRegister().get(0));
					System.out.printf("   R[%d] : %d",this.indexerDest,oldVal);
					System.out.printf("  MAR : %s  MBR : %d",this.dest,expression);
				}

			}
			else if(this.valDest[0] == false && this.valDest[1] == true) {
				int ov = 0;
				this.Regis.setAcc(this.valParam1);
				this.Regis.getCommonRegister().set(0,this.valParam2);
				
				if(this.op.equals("ADD")) {
					ov = this.Regis.getCommonRegister().get(this.indexerDest);
					expression = this.valParam1+this.valParam2;
					expression+= this.Regis.getCommonRegister().get(this.indexerDest);
					this.Regis.getCommonRegister().add(this.indexerDest,expression);
					System.out.printf("ACC : %d",this.Regis.getAcc());
					System.out.printf(" R0 : %d",this.Regis.getCommonRegister().get(0));
					System.out.printf("   R[%d] : %d",this.indexerDest,ov);
					System.out.printf("  MAR : %s  MBR : %d",this.dest,expression);
				}
				if(this.op.equals("SUB")) {
					ov = this.Regis.getCommonRegister().get(this.indexerDest);
					expression = this.Regis.getAcc()-this.valParam2-this.Regis.getCommonRegister().get(this.indexerDest);
					this.Regis.getCommonRegister().add(this.indexerDest,expression);
					System.out.printf("ACC : %d",this.Regis.getAcc());
					System.out.printf(" R0 : %d",this.Regis.getCommonRegister().get(0));
					System.out.printf("   R[%d] : %d",this.indexerDest,ov);
					System.out.printf("  MAR : %s  MBR : %d",this.dest,expression);
				}
				if(this.op.equals("MUL")) {
					ov = this.Regis.getCommonRegister().get(this.indexerDest);
					expression = this.Regis.getAcc()*this.valParam2*this.Regis.getCommonRegister().get(this.indexerDest);
					this.Regis.getCommonRegister().add(this.indexerDest,expression);
					System.out.printf("ACC : %d",this.Regis.getAcc());
					System.out.printf(" R0 : %d",this.Regis.getCommonRegister().get(0));
					System.out.printf("   R[%d] : %d",this.indexerDest,ov);
					System.out.printf("  MAR : %s  MBR : %d",this.dest,expression);
				}
			}
			System.out.println("\n\n---------------- FIM -------------------\n");

		}
		if(this.getContador() == 3) {
			System.out.println("\n----- Ciclo de execução -----\n");
			if(this.valDest[0] == true && this.valDest[1] == false) {
				this.Regis.setAcc(this.valParam1);
				int oldVal = 0;
				if(this.op.equals("ADD")) {
					oldVal = this.Regis.getMemVal().get(this.indexerDest);
					expression = this.valParam1+this.Regis.getMemVal().get(this.indexerDest);
					this.Regis.getMemVal().add(this.indexerDest,expression);
					System.out.printf("ACC : %d",this.Regis.getAcc());
					System.out.printf("   Mem[%d] : %d",this.indexerDest,oldVal);
					System.out.printf("  MAR : %s  MBR : %d",this.dest,expression);
				}
				if(this.op.equals("SUB")) {
					oldVal = this.Regis.getMemVal().get(this.indexerDest);
					expression = this.valParam1-this.Regis.getMemVal().get(this.indexerDest);
					this.Regis.getMemVal().add(this.indexerDest,expression);
					System.out.printf("ACC : %d",this.Regis.getAcc());
					System.out.printf("   Mem[%d] : %d",this.indexerDest,oldVal);
					System.out.printf("  MAR : %s  MBR : %d",this.dest,expression);
				
				}
				if(this.op.equals("MUL")) {
					oldVal = this.Regis.getMemVal().get(this.indexerDest);
					expression = this.valParam1*this.Regis.getMemVal().get(this.indexerDest);
					this.Regis.getMemVal().add(this.indexerDest,expression);
					System.out.printf("ACC : %d",this.Regis.getAcc());
					System.out.printf("   Mem[%d] : %d",this.indexerDest,oldVal);
					System.out.printf("  MAR : %s  MBR : %d",this.dest,expression);
				}
				if(this.op.equals("MOV")) {
					oldVal = this.Regis.getMemVal().get(this.indexerDest);
					this.Regis.getMemVal().set(this.indexerDest, this.valParam1);
					System.out.printf("ACC : %d",this.Regis.getAcc());
					System.out.printf("   Mem[%d] : %d",this.indexerDest,oldVal);
					System.out.printf("  MAR : %s  MBR : %d",this.dest,this.Regis.getMemVal().get(this.indexerDest));
					

				}
			}
			else if(this.valDest[0] == false && this.valDest[1] == true) {
				this.Regis.setAcc(this.valParam1);
				int ov = 0;
				if(this.op.equals("ADD")) {
					ov = this.Regis.getCommonRegister().get(this.indexerDest);
					expression = this.Regis.getAcc()+this.Regis.getCommonRegister().get(this.indexerDest);
					this.Regis.getCommonRegister().add(this.indexerDest,expression);
					System.out.printf("ACC : %d",this.Regis.getAcc());
					System.out.printf("   R[%d] : %d",this.indexerDest,ov);
					System.out.printf("  MAR : %s  MBR : %d",this.dest,expression);
				}
				if(this.op.equals("SUB")) {
					ov = this.Regis.getCommonRegister().get(this.indexerDest);
					expression = this.Regis.getAcc()-this.Regis.getCommonRegister().get(this.indexerDest);
					this.Regis.getCommonRegister().add(this.indexerDest,expression);
					System.out.printf("ACC : %d",this.Regis.getAcc());
					System.out.printf("   R[%d] : %d",this.indexerDest,ov);
					System.out.printf("  MAR : %s  MBR : %d",this.dest,expression);
				}
				if(this.op.equals("MUL")) {
					ov = this.Regis.getCommonRegister().get(this.indexerDest);
					expression = this.Regis.getAcc()*this.Regis.getCommonRegister().get(this.indexerDest);
					this.Regis.getCommonRegister().add(this.indexerDest,expression);
					System.out.printf("ACC : %d",this.Regis.getAcc());
					System.out.printf("   R[%d] : %d",this.indexerDest,ov);
					System.out.printf("  MAR : %s  MBR : %d",this.dest,expression);
				}
				if(this.op.equals("MOV")) {
					ov = this.Regis.getCommonRegister().get(this.indexerDest);
					this.Regis.getCommonRegister().set(this.indexerDest, this.valParam1);
					System.out.printf("ACC : %d",this.Regis.getAcc());
					System.out.printf("   R[%d] : %d",this.indexerDest,ov);
					System.out.printf("  MAR : %s  MBR : %d",this.dest,this.Regis.getCommonRegister().get(this.indexerDest));
					

				}

			}
			System.out.println("\n\n---------------- FIM -------------------\n");

		}
	}

}
