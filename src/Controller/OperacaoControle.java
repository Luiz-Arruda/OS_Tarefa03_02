package Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

public class OperacaoControle {

	public OperacaoControle() {
		super ();
	}
   
	//Chamando os processos 
   private String chamaProcesso (String so, String processo) {
     
      
    	 if(so.contains("indows")) {
                processo = "TASKLIST /FO TABLE";
                JOptionPane.showMessageDialog(null,"Seu Sistema Operacional é:\n"+so);
        } else
            if(so.contains("inux")) {
                processo = "ps -ef";
                JOptionPane.showMessageDialog(null,"Seu Sistema Operacional é:\n"+so);
            } 


        try {
            Runtime.getRuntime().exec(processo);
        } 
        catch (Exception e) {
            String msgErro = e.getMessage();
            
            if (msgErro.contains("740")) {
                StringBuffer buffer = new StringBuffer();  
				buffer.append("cmd /c");					
				buffer.append(" ");						
				buffer.append(processo);	
            
                try {									
                    Runtime.getRuntime().exec(buffer.toString());
                } 
                catch (IOException e1) {	
                    e1.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null,"Ocorreu um erro, veja no Console");
                System.err.println(msgErro);
            }
        }
        return processo;
    }


    //Listando os processos
    public void listaProcessos(String so, String processo) {
        
        try {
            Process p = Runtime.getRuntime().exec(chamaProcesso(so, processo));
            InputStream fluxo = p.getInputStream();
            InputStreamReader leitor = new InputStreamReader(fluxo);
            BufferedReader buffer = new BufferedReader(leitor);
            String linha = buffer.readLine();
            JOptionPane.showMessageDialog(null,"Veja o Console");


            while (linha != null) {
                linha = buffer.readLine();
                System.out.println(linha);
            } 
            buffer.close();
            leitor.close();
            fluxo.close();
        } catch (Exception e) {
             e.printStackTrace();
        }
    }


    // Mate um processo pelo PID.
    public void matarProcessoPID(String so, String processo, int PID) {
        String cmdMPid = "";


		if(so.contains("indows")) {
            cmdMPid  = "TASKKILL /PID";
        } else
            if(so.contains("inux")) {
                cmdMPid  = "kill -9";
            } 
        							
		int pid = 0;									
		StringBuffer buffer= new StringBuffer();		
					
		try {    
			pid = PID;					
			buffer.append(cmdMPid);							
			buffer.append(" ");								
			buffer.append(pid);	
            Runtime.getRuntime().exec(buffer.toString());	
            JOptionPane.showMessageDialog(null,"Visualize a operação no Console");						
		} 
		catch (Exception e){	
            JOptionPane.showMessageDialog(null,"Ocorreu um erro, veja detalhes no Console");				
            e.printStackTrace();
		}
		chamaProcesso(so, processo);
    }


    //Mate um processo pelo Nome.
    public void mataProcessoNome(String so, String processo, String Name) {
        String cmdNome = "";


		if(so.contains("indows")) {
            cmdNome = "TASKKILL /IM";
        } else
            if(so.contains("inux")) {
                cmdNome = "pkill -f";
            } 
        		
		String name = "";
		StringBuffer buffer= new StringBuffer();		
		
        try {
		    name = Name;
            buffer.append(cmdNome);							
            buffer.append(" ");								
            buffer.append(name);
            Runtime.getRuntime().exec(buffer.toString());
            JOptionPane.showMessageDialog(null,"Visualize a operação no Console");


		} 
		catch (Exception e) {	
            JOptionPane.showMessageDialog(null,"Ocorreu um erro, veja detalhes no Console");	
            e.printStackTrace();
        }
        chamaProcesso(so, processo);
    }
}
