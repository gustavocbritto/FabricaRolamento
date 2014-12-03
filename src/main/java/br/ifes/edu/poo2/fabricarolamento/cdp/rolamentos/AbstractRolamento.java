package br.ifes.edu.poo2.fabricarolamento.cdp.rolamentos;

public abstract class AbstractRolamento
{
	private int prioridade;
	private String tipo;
	protected double tempoMandril;
	protected double tempoTorno;
	protected double tempoFresa;
	private double instante;
	protected int status;
	protected double instanteInicial=0;
	protected double tempoParado=0;
	
	public abstract void setQuantidade(int i);
	public abstract int getQuantidade();
	public abstract String getProxMaquina();
	public abstract int getEtapa();
	public abstract void addTempoParado(double t);
	public abstract String getOrdem(int x);
	public abstract void setStatus(int x);
	public abstract int getStatus();
	public abstract void setTempoTotal(double d);
	public abstract double getTempoTotal();
	
	public double getTempoParado()
	{
		return this.tempoParado;
	}
	
	public void setTipo(String t)
	{
		this.tipo=t;
	}
	
	public String getTipo()
	{
		return this.tipo;
	}
	
	public double getTempoMandril()
	{
		return this.tempoMandril;
	}
	
	public double getTempoTorno()
	{
		return this.tempoTorno;
	}
	
	public double getTempoFresa()
	{
		return this.tempoFresa;
	}
	
	public int getPrioridade()
	{
		return this.prioridade;
	}
	
	public double getInstante()
	{
		return this.instante;
	}
	
	public void setTempoMandril(double tempo)
	{
		this.tempoMandril=tempo;
	}
	public void setTempoTorno(double tempo)
	{
		this.tempoTorno=tempo;
	}
	
	public void setTempoFresa(double tempo)
	{
		this.tempoFresa=tempo;
	}
	
	public void setPrioridade(int x)
	{
		this.prioridade=x;
	}
	
	public void setInstante(String s)
	{
		this.instante = Double.parseDouble(s);
	}
	
	public void setInstanteInicial(String s)
	{
		this.instanteInicial = Double.parseDouble(s);
	}
	
	public double getInstanteInicial()
	{
		return this.instanteInicial;
	}
	
	public void setInstante(double d)
	{
		this.instante=d;
	}
}