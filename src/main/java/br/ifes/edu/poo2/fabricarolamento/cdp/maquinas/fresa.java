package br.ifes.edu.poo2.fabricarolamento.cdp.maquinas;


import br.ifes.edu.poo2.fabricarolamento.cdp.rolamentos.AbstractRolamento;


public class fresa extends maquinas
{
	private static int quantidade;
	protected AbstractRolamento rMaquina;
	protected double instante=0;
	protected int status=0;
	
	public void addRolamento(AbstractRolamento r)
	{
		this.rMaquina=r;
	}
	
	public AbstractRolamento getRolamento()
	{
		return this.rMaquina;
	}
	
	public void setInstante(double i)
	{
		this.instante=i;
	}
	
	public double getInstante()
	{
		return this.instante;
	}
	
	public void removeRolamento()
	{
		this.status=0;
		this.rMaquina=null;
	
	}
	
	public int getStatus()
	{
		return status;
	}
	
	public void setStatus(int i)
	{
		this.status=i;
	}
}