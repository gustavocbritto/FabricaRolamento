package br.ifes.edu.poo2.fabricarolamento.cgt;

import br.ifes.edu.poo2.fabricarolamento.cdp.maquinas.maquinas;
import br.ifes.edu.poo2.fabricarolamento.cdp.rolamentos.cilindrico;
import br.ifes.edu.poo2.fabricarolamento.cdp.rolamentos.conico;
import br.ifes.edu.poo2.fabricarolamento.cdp.rolamentos.esfericoAco;
import br.ifes.edu.poo2.fabricarolamento.cdp.rolamentos.esfericoTit;
import br.ifes.edu.poo2.fabricarolamento.cdp.rolamentos.AbstractRolamento;  
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ControlePedido 
{
	private AbstractRolamento x,y,z,k;
	private String primeiraLinha;
	private final maquinas maquina = new maquinas();
	
	/*Metodo para abrir e ler o arquivo. Adiciona as os pedidos numa listaUniversal*/
	public void abreArq(String nome)
	{
		try
		{
			Scanner scn = new Scanner(new File(nome));	//Abre, especifica o arquivo a ser lido.	 
			
			System.out.println();
			
			primeiraLinha = scn.next(); // le a linha e atribui a quantidades de maquinas disponiveis
			maquina.setDisponibilidadeMandril(primeiraLinha);
			System.out.println("Quantidade Mandril: " + maquina.getDisponibilidadeMandril());
			
			primeiraLinha = scn.next();
			maquina.setDisponibilidadeTorno(primeiraLinha);
			System.out.println("Quantidade Torno: " + maquina.getDisponibilidadeTorno());
			
			primeiraLinha = scn.next();
			maquina.setDisponibilidadeFresa(primeiraLinha);
			System.out.println("Quantidade Fresa: " + maquina.getDisponibilidadeFresa());
			
			System.out.println();
			
			x = new conico();
			y = new esfericoAco();
			z = new esfericoTit();
			k = new cilindrico();
			while (scn.hasNext()) //le os rolamentos do arquivo e coloca numa lista universal
			{
				String instante = scn.next();
				String Pedidos = scn.next();
				
				if(Pedidos.equals("conico"))
				{
					x = new conico();
					x.setInstante(instante);
					x.setInstanteInicial(instante);
					maquina.addListaUniversal(x);
				}
				if(Pedidos.equals("esfericoAco"))
				{
					y = new esfericoAco();
					y.setInstante(instante);
					y.setInstanteInicial(instante);
					maquina.addListaUniversal(y);
				}	
				if(Pedidos.equals("esfericoTit"))
				{
					z = new esfericoTit();
					z.setInstante(instante);
					z.setInstanteInicial(instante);
					maquina.addListaUniversal(z);
				}	
				if(Pedidos.equals("cilindrico"))
				{
					k = new cilindrico();
					k.setInstante(instante);
					k.setInstanteInicial(instante);
					maquina.addListaUniversal(k);
				}
				if(Pedidos.equals("fim"))
				{
					maquina.setTempoTotalExecucao(instante);					
				}
			}
		}
		catch(FileNotFoundException ioe)
		{  
			ioe.printStackTrace();  
		}  
	}
	
	/*aonde coloca os pedidos para elaborar*/
	public void elaboracao()
	{
		double tempo=maquina.listaUniversal.get(0).getInstante();
		for(AbstractRolamento r : maquinas.listaUniversal)
		{	
			maquina.setTempoUniversal(r.getInstante());
			
			if(tempo!=r.getInstante())//quando o tempo do proximo pedido for diferente, ele começa a producao.
			{
				maquina.jogaPraMaquina();
				maquina.producao();
			}
			
			System.out.println(r.getInstante() + " adicionando: " + r.getTipo());
			maquina.addLista(r, r.getOrdem(0));
			tempo=r.getInstante();
		}
		
		maquina.setTempoUniversal(maquina.getTempoTotalExecucao());
		maquina.jogaPraMaquina();
		maquina.producao();
		
		System.out.println(maquina.getTempoUniversal() + " Fim da Execucao.");
	}
	
	/*Imprime a quantidade produzida de cada rolamento e seus tempos medios*/
	public void imprimeDados()
	{
		
		System.out.println();
		
		System.out.println("Quantidade de Cilindrico " + k.getQuantidade());
		System.out.println("Quantidade de Conicos " + x.getQuantidade());
		System.out.println("Quantidade de Esferico Aco " + y.getQuantidade());
		System.out.println("Quantidade de Esferico Titanio " + z.getQuantidade());
		
		
		System.out.println();
		
		if(k.getQuantidade()==0)
		{
			System.out.println("Tempo Medio Cilindrico: Nao foram produzidos Cilindrico.");
		}else
		{
			System.out.println("Tempo Medio Cilindrico: " + k.getTempoTotal()/k.getQuantidade());
		}
		
		if(x.getQuantidade()==0)
		{
			System.out.println("Tempo Medio Conicos: Nao foram produzidos conicos.");
		}else
		{
			System.out.println("Tempo Medio Conicos: " + x.getTempoTotal()/x.getQuantidade());
		}
		
		if(y.getQuantidade()==0)
		{
			System.out.println("Tempo Medio Esferico Aco: Nao foram produzidos Esferico Aco.");
		}else
		{
			System.out.println("Tempo Medio Esferico Aco: " + y.getTempoTotal()/y.getQuantidade());
		}
		
		if(z.getQuantidade()==0)
		{
			System.out.println("Tempo Medio Esferico Titanio: Nao foram produzidos Esferico Titanio.");
		}else
		{
			System.out.println("Tempo Medio Esferico Titanio: " + z.getTempoTotal()/z.getQuantidade());
		}
		
		
		
		System.out.println("Fim do Programa.");
		
	}
}