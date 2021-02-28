package view;

import javax.swing.JOptionPane;

import Controller.OperacaoControle;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OperacaoControle opControle = new OperacaoControle();
		 int ops = 0;
		 String so = System.getProperty("os.name");
		 String suporte = " ";
		 
		 while (ops != 99) {
			 ops = Integer.parseInt(JOptionPane.showInputDialog("Seleciona uma Opção \n 1 - Listar processos ativos \n 2 - Finalizar processo por PID \n 3 - Finalizar processo por nome \n 99 - Sair")); 
			 
			 switch (ops) {
			 
			 case 1:
				 opControle.listaProcessos(so, suporte);
				 break;
			 case 2:
				 int PID = 0;
				 PID = Integer.parseInt(JOptionPane.showInputDialog("Digitar o PID do processo")); 
				 opControle.matarProcessoPID(so, suporte, PID);
				 break;
				 
			 case 3: // chamaProcesso
				 String nome = JOptionPane.showInputDialog("Digitar o nome do processo"); 
				 opControle.mataProcessoNome(so, suporte, nome);
				 break;
			 }
		 }
				 
	}

}
