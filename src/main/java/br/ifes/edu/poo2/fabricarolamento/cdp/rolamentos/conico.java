package br.ifes.edu.poo2.fabricarolamento.cdp.rolamentos;


public class conico extends AbstractRolamento
{
	private static int quantidade=0;
	private final static String ordem[]={"Torno","Mandril","Torno"};
	private int etapa=0; //Qual maquina o rolamento esta 
	private static double tempoTotal=0;

	public conico()
	{
		setTempoMandril(2.1);
		setTempoTorno(1.8);
		setPrioridade(2);
		setTipo("conico");
	}
	
        @Override
	public void setQuantidade(int i)
	{
		conico.quantidade=conico.quantidade+i;
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
		conico.tempoTotal= conico.tempoTotal + d;
	} 
	
         @Override
	public double getTempoTotal()
	{
		return conico.tempoTotal;
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
		return conico.quantidade;
	}
}