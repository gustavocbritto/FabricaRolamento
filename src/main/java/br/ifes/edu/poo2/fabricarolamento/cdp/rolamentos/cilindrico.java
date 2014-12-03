package br.ifes.edu.poo2.fabricarolamento.cdp.rolamentos;

import br.ifes.edu.poo2.fabricarolamento.cdp.rolamentos.AbstractRolamento;


public class cilindrico extends AbstractRolamento
{
	private static int quantidade=0;
	private static double tempoTotal=0;
	private final static String ordem[]={"Torno","Fresa","Torno", "Mandril"};
	private int etapa=0; //Qual maquina o rolamento esta

	
	public cilindrico()
	{
		setTempoMandril(1.2);
		setTempoTorno(0.8);
		setTempoFresa(0.5);
		setPrioridade(1);
		setTipo("cilindrico");
	}
	
        @Override
	public void setQuantidade(int i)
	{
		cilindrico.quantidade=cilindrico.quantidade+i;
	}	
	
        @Override
	public void setStatus(int x)
	{
		this.status=x;
	}
	
        @Override
	public int getStatus()
	{
		return this.status;
	}
	
        @Override
	public String getOrdem(int x)
	{
		return ordem[x];
	}
	
        @Override
	public void addTempoParado(double t)
	{
		this.tempoParado=t;
	}
	
        @Override
	public int getEtapa()
	{
		return this.etapa;
	}
	
        @Override
	public void setTempoTotal(double d)
	{
		cilindrico.tempoTotal= cilindrico.tempoTotal + d;
	} 
	
         @Override
	public double getTempoTotal()
	{
		return cilindrico.tempoTotal;
	} 
	
        @Override
	public String getProxMaquina() // essa funcao pega a proxima maquina
	{
		if(etapa==0)
		{
			this.etapa=this.etapa+1;
			return "Fresa";
		}
		if(etapa==1)
		{
			this.etapa=this.etapa+1;
			return "Torno";
		}
		if(etapa==2)
		{
			this.etapa=this.etapa+1;
			return "Mandril";
		}
		
		this.etapa=-1;
		return "FIM";
	}
	
        @Override
	public int getQuantidade()
	{
		return cilindrico.quantidade;
	}
}