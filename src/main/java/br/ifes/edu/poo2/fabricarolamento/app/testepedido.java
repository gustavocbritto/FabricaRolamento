package br.ifes.edu.poo2.fabricarolamento.app;

import br.ifes.edu.poo2.fabricarolamento.cgt.ControlePedido;


// Aluno: Gustavo Collistet Britto

public class testepedido
{
	public static void main(String[] args)
	{
            ControlePedido novo;
            novo = new ControlePedido();
            novo.abreArq("pedidos.txt");
            novo.elaboracao();
            novo.imprimeDados();
	}
}