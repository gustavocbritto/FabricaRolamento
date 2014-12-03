package br.ifes.edu.poo2.fabricarolamento.cdp.rolamentos;


public class esfericoTit extends AbstractRolamento
{
	private static int quantidade=0;
	private static double tempoTotal=0;
	private final static String ordem[]={"Fresa","Mandril","Torno", "Fresa", "Torno"};
	private int etapa=0; //Qual maquina o rolamento esta 
	
	public esfericoTit()
	{
		setTempoMandril(1.5);
		setTempoTorno(1.6);
		setTempoFresa(0.6);
		setPrioridade(3);
		setTipo("esfericoTit");
	}
	
        @Override
	public void setQuantidade(int i)
	{
		esfericoTit.quantidade=esfericoTit.quantidade+i;
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
	public void setTempoTotal(double d)
	{
		esfericoTit.tempoTotal= esfericoTit.tempoTotal + d;
	}
	 
        @Override
	public int getEtapa()
	{
		return this.etapa;
	}
		
         @Override
		public double getTempoTotal()
	{
		return esfericoTit.tempoTotal;
	} 
	
        @Override
	public String getProxMaquina()
	{
		if(etapa==0)
		{
			this.etapa=this.etapa+1;
			return "Mandril";
		}
		if(etapa==1)
		{
			this.etapa=this.etapa+1;
			return "Torno";
		}
		if(etapa==2)
		{
			this.etapa=this.etapa+1;
			return "Fresa";
		}
		if(etapa==3)
		{
			this.etapa=this.etapa+1;
			return "Torno";
		}
		this.etapa=-1;
		return "FIM";
	}
	
	
	
        @Override
	public int getQuantidade()
	{
		return esfericoTit.quantidade;
	}
}