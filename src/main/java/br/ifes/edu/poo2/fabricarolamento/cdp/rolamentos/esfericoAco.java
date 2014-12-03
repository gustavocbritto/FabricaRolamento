package br.ifes.edu.poo2.fabricarolamento.cdp.rolamentos;


public class esfericoAco extends AbstractRolamento
{
	private static int quantidade=0;
	private final static String ordem[]={"Fresa","Mandril","Torno"};
	private int etapa=0; //Qual maquina o rolamento esta 
	private static double tempoTotal=0;

	
	public esfericoAco()
	{
		setTempoMandril(1.4);
		setTempoTorno(1.0);
		setTempoFresa(0.5);
		setPrioridade(3);
		setTipo("esfericoAco");
	}
	
        @Override
	public void setQuantidade(int i)
	{
		esfericoAco.quantidade=esfericoAco.quantidade+i;
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
		esfericoAco.tempoTotal= esfericoAco.tempoTotal + d;
	}
	
        @Override
	public double getTempoTotal()
	{
		return esfericoAco.tempoTotal;
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

		this.etapa=-1;
		return "FIM";
		
	}
	
	
        @Override
	public int getQuantidade()
	{
		return esfericoAco.quantidade;
	}
}