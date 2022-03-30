package concorrencia;

import javax.swing.JOptionPane;



public class EntregadorTeste {
	

		
		public static void main(String[] args) {
			
			ListaMembrosTest listaMembros = new ListaMembrosTest();
			Thread t1 = new Thread(new Entregador(listaMembros), "Entregador 1  ");
			Thread t2 = new Thread(new Entregador(listaMembros), "Entregador 2  ");
			t1.start();
			t2.start();
			while (true) {
				String email = JOptionPane.showInputDialog("Digite o email do membro");
				if(email == null || email.isEmpty()) {
					listaMembros.fecharLista();
					break;
				}
				listaMembros.adicionarEmailMembro(email);
			}	
		}
	}

