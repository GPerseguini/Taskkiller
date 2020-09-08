package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ProcessosController {

	public ProcessosController (){
		super();
	}
	
	//Retornar o sistema operacional ativo na máquina.
	public String os() {
		String os = System.getProperty("os.name");
		String version = System.getProperty("os.version");
		return os + version;
	}
	
	public void callProcess(String process){
		try {
			Runtime.getRuntime().exec(process);
		} catch (IOException e) {
			String msgErro = e.getMessage();
			//System.err.println(msgErro);
			if (msgErro.contains("740")){
				// para dar acesso de administrador no windows > //cmd /c caminho_do_processo
				StringBuffer buffer = new StringBuffer();
				buffer.append("cmd /c");
				buffer.append(" ");
				buffer.append(process);
				try {
					Runtime.getRuntime().exec(buffer.toString());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else{
				System.err.println(msgErro);
			}
		}
	}
	
	public void readProcess(String process, String os){
		try {
			Process p = Runtime.getRuntime().exec(process);
			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();

			if (os.contains("Mac")){
				while (linha != null){
					System.out.println(linha);
					linha = buffer.readLine();
				}
			}else{
				while (linha != null){
						System.out.println(linha);
						linha = buffer.readLine();
				}
			}
			buffer.close();
			leitor.close();
			fluxo.close();
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}

	
	

	public void killProcess(String process, String os){
		
		StringBuffer buffer = new StringBuffer();
		if (os.contains("Windows")){
			String cmdPid = "TASKKILL /PID";
			String cmdNome = "TASKKILL /IM";
			int pid = 0;
		
			try {
				pid = Integer.parseInt(process);
				buffer.append(cmdPid);
				buffer.append(" ");
				buffer.append(pid);
			}catch (Exception e){
				buffer.append(cmdNome);
				buffer.append(" ");
				buffer.append(process);
				buffer.append(".exe");
			}
		}else{
			int pid = 0;
			try {
				pid = Integer.parseInt(process);
				buffer.append("kill");
				buffer.append(" ");
				buffer.append(pid);
			}catch (Exception e){
				buffer.append("pkill");
				buffer.append(" ");
				buffer.append(process);
		}
		callProcess(buffer.toString());
		}
	}
}
