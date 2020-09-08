package view;

import javax.swing.JOptionPane;
import controller.ProcessosController;

public class Principal {

	public static void main(String[] args) {
		int opc=0;
		while (opc!=3){
			ProcessosController procController = new ProcessosController();
			String os = procController.os();
			opc = Integer.parseInt(JOptionPane.showInputDialog("Bem-vindo! Selecione a op��o desejada.\n\n 1 - Visualizar processos em execu��o\n\n 2 - Finalizar um processo\n\n 3 - Sair\n "));
			switch(opc){
				case 1:
					if(os.contains("Windows")){
						String process = "TASKLIST /FO TABLE";
						procController.readProcess(process, os);
					}else{
						String process = "ps -e";
						procController.readProcess(process, os);
					}
				break;
				case 2:
					if(os.contains("Windows")){
						String process = JOptionPane.showInputDialog("Digite o nome ou PID do processo que deseja finalizar.");
						procController.killProcess(process, os);
					}else{
						String process = JOptionPane.showInputDialog("Digite o nome ou PID do processo que deseja finalizar.");
						procController.killProcess(process, os);
					}
				break;
				}
		}
	}
}