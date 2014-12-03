package br.ifes.edu.poo2.fabricarolamento.cdp.maquinas;

import br.ifes.edu.poo2.fabricarolamento.cdp.rolamentos.AbstractRolamento;
import java.util.*;

public class maquinas
{
	private String proxima;
	private double tempoTotalExecucao;
	private static double tempoUniversal;
	private int disponibilidadeTorno;
	private int disponibilidadeMandril;
	private int disponibilidadeFresa;
	
	public static ArrayList<AbstractRolamento> listaMandril = new ArrayList<>();
	public static ArrayList<AbstractRolamento> listaTorno = new ArrayList<>();
	public static ArrayList<AbstractRolamento> listaFresa = new ArrayList<>();
	public static ArrayList<AbstractRolamento> finalizados = new ArrayList<>();
	public static ArrayList<AbstractRolamento> listaUniversal = new ArrayList<>();
	public static ArrayList<torno> maquinaTorno = new ArrayList<>();
	public static ArrayList<fresa> maquinaFresa = new ArrayList<>();
	public static ArrayList<mandril> maquinaMandril = new ArrayList<>();
	
	public int getDisponibilidadeTorno()
	{
		return this.disponibilidadeTorno;
	}
	
	public int getDisponibilidadeMandril()
	{
		return this.disponibilidadeMandril;
	}
	
	public int getDisponibilidadeFresa()
	{
		return this.disponibilidadeFresa;
	}
	
	public void setDisponibilidadeFresa(String s)
	{
		this.disponibilidadeFresa = Integer.parseInt(s);
		for(int i=0; i<disponibilidadeFresa; i++)
		{
			maquinaFresa.add(new fresa());
		}
	}
	
	public void setDisponibilidadeMandril(String s)
	{
		this.disponibilidadeMandril = Integer.parseInt(s);
		for(int i=0; i<disponibilidadeMandril; i++)
		{
			maquinaMandril.add(new mandril());
		}
	}
	
	public void setDisponibilidadeTorno(String s)
	{
		this.disponibilidadeTorno = Integer.parseInt(s);
		for(int i=0; i<disponibilidadeTorno; i++)
		{
			maquinaTorno.add(new torno());
		}
	}
	
	public void addListaUniversal(AbstractRolamento r)
	{
		listaUniversal.add(r);
	}
	
	public void setTempoUniversal(double d)
	{
		maquinas.tempoUniversal = d;
	}
	
	public double getTempoUniversal()
	{
		return maquinas.tempoUniversal;
	}
	
	/*verifica se ah alguma maquina com rolamento dentro*/
	public boolean verificaMaquinaTorno(ArrayList<torno> m)
	{
		for(torno t : maquinaTorno)
		{
			if(t.getRolamento()!=null)
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean verificaMaquinaFresa(ArrayList<fresa> m)
	{
		for(fresa f : maquinaFresa)
		{
			if(f.getRolamento()!=null)
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean verificaMaquinaMandril(ArrayList<mandril> e)
	{
		for(mandril m : maquinaMandril)
		{
			if(m.getRolamento()!=null)
			{
				return true;
			}
		}
		return false;
	}
	
	/*metodo para atribuir os instantes dos rolamentos */
	public double executaTorno()
	{
		
		double menorTempo = getTempoUniversal()+1;
		ordenaPeloTempoDeSaidaT(maquinaTorno);
		
		for(int i=maquinaTorno.size()-1 ; i>=0 && maquinaTorno.size()>0 && verificaMaquinaTorno(maquinaTorno) ; i--) //percorre a lista de maquina e verifica os status dos rolamentos
		{
			if(maquinaTorno.get(i).getStatus()==1)//verifica se ela esta ocupada
			{
				if(maquinaTorno.get(i).getInstante() -maquinaTorno.get(i).getRolamento().getInstante()>0)//adiciona o tmepo parado
					{
						maquinaTorno.get(i).getRolamento().addTempoParado(maquinaTorno.get(i).getInstante() -maquinaTorno.get(i).getRolamento().getInstante());
					}
					else
					{
						maquinaTorno.get(i).getRolamento().addTempoParado(0);
						maquinaTorno.get(i).setInstante(maquinaTorno.get(i).getRolamento().getInstante());
					}
				/*se seu tempo parado+ tempo que ira ficar na maquina + instante de chegada for menor que o tempo atual ele executa */
				/*vale lembrar que o instante para mim é o instante que o rolamento solicita a entrada em uma maquina(ou que vai pra fila ou que sai da maquina)*/
				if( (maquinaTorno.get(i).getRolamento().getInstante()+maquinaTorno.get(i).getRolamento().getTempoTorno() + maquinaTorno.get(i).getRolamento().getTempoParado()  <= getTempoUniversal()))
				{
				
					maquinaTorno.get(i).getRolamento().setInstante(maquinaTorno.get(i).getRolamento().getTempoParado()+maquinaTorno.get(i).getRolamento().getInstante() +maquinaTorno.get(i).getRolamento().getTempoTorno());
					maquinaTorno.get(i).setInstante(maquinaTorno.get(i).getRolamento().getInstante());
					System.out.println(maquinaTorno.get(i).getRolamento().getTipo() + " Pronto na maquina Torno");
					
					if(listaTorno.size()==0)
					{
						maquinaTorno.get(i).setInstante(0);
					}  
					addLista(maquinaTorno.get(i).getRolamento(), maquinaTorno.get(i).getRolamento().getProxMaquina());
					
					/*retorna o menor tempo, no caso o instante que o rolamento sai da maquina*/
					if(menorTempo > maquinaTorno.get(i).getRolamento().getInstante())
					{
						menorTempo=maquinaTorno.get(i).getRolamento().getInstante();
					}
					maquinaTorno.get(i).removeRolamento();
					disponibilidadeTorno++;
					break;
				}
			}
		}
		return menorTempo;
	}
	
	public double executaFresa()
	{
		double menorTempo = getTempoUniversal()+1;
		
		ordenaPeloTempoDeSaidaF(maquinaFresa);
		for(int i=maquinaFresa.size()-1; i>=0 && maquinaFresa.size()>0 && verificaMaquinaFresa(maquinaFresa) ; i--) //percorre a lista de maquina e verifica os status dos rolamentos
		{
			
			if(maquinaFresa.get(i).getStatus()==1)
			{
				
				if(maquinaFresa.get(i).getInstante() -maquinaFresa.get(i).getRolamento().getInstante()>0)
					{
						maquinaFresa.get(i).getRolamento().addTempoParado(maquinaFresa.get(i).getInstante() -maquinaFresa.get(i).getRolamento().getInstante());
					}
					else
					{
						maquinaFresa.get(i).getRolamento().addTempoParado(0);
						maquinaFresa.get(i).setInstante(maquinaFresa.get(i).getRolamento().getInstante());
					}
		
				if( (maquinaFresa.get(i).getRolamento().getInstante()+maquinaFresa.get(i).getRolamento().getTempoFresa() + maquinaFresa.get(i).getRolamento().getTempoParado()  <= getTempoUniversal()))
				{
				
					maquinaFresa.get(i).getRolamento().setInstante(maquinaFresa.get(i).getRolamento().getTempoParado()+maquinaFresa.get(i).getRolamento().getInstante() +maquinaFresa.get(i).getRolamento().getTempoFresa());
					maquinaFresa.get(i).setInstante(maquinaFresa.get(i).getRolamento().getInstante());
				
					System.out.println(maquinaFresa.get(i).getRolamento().getTipo() + " Pronto na maquina Fresa");
					
					if(listaFresa.size()==0)
					{
						maquinaFresa.get(i).setInstante(0);
					}  
					
					addLista(maquinaFresa.get(i).getRolamento(), maquinaFresa.get(i).getRolamento().getProxMaquina());
					
					if(menorTempo > maquinaFresa.get(i).getRolamento().getInstante())
					{
						menorTempo=maquinaFresa.get(i).getRolamento().getInstante();
					}
					maquinaFresa.get(i).removeRolamento();
					disponibilidadeFresa++;
					break;
				}
			}
			
		}
		return menorTempo;
	}
	
	public double executaMandril()
	{
		double menorTempo= getTempoUniversal()+1;
		
		ordenaPeloTempoDeSaidaM(maquinaMandril);
		
		for(int i=maquinaMandril.size()-1; i>=0  && maquinaMandril.size()>0 && verificaMaquinaMandril(maquinaMandril) ; i--)
		{
			if(maquinaMandril.get(i).getStatus()==1)
			{
				
				if(maquinaMandril.get(i).getInstante() -maquinaMandril.get(i).getRolamento().getInstante()>0)
					{
						maquinaMandril.get(i).getRolamento().addTempoParado(maquinaMandril.get(i).getInstante() -maquinaMandril.get(i).getRolamento().getInstante());
					}
					else
					{
						maquinaMandril.get(i).getRolamento().addTempoParado(0);
						maquinaMandril.get(i).setInstante(maquinaMandril.get(i).getRolamento().getInstante());
					}
		
				if( (maquinaMandril.get(i).getRolamento().getInstante()+maquinaMandril.get(i).getRolamento().getTempoMandril()+  maquinaMandril.get(i).getRolamento().getTempoParado() <= getTempoUniversal()))
				{				
				
					maquinaMandril.get(i).getRolamento().setInstante(maquinaMandril.get(i).getRolamento().getTempoParado()+maquinaMandril.get(i).getRolamento().getInstante() +maquinaMandril.get(i).getRolamento().getTempoMandril());

					maquinaMandril.get(i).setInstante(maquinaMandril.get(i).getRolamento().getInstante());
					
					System.out.println(maquinaMandril.get(i).getRolamento().getTipo() + " Pronto na maquina Mandril");
				
					if(listaMandril.size()==0)
					{
						maquinaMandril.get(i).setInstante(0);
					}  
					
					addLista(maquinaMandril.get(i).getRolamento(), maquinaMandril.get(i).getRolamento().getProxMaquina());

					if(menorTempo > maquinaMandril.get(i).getRolamento().getInstante())
					{
						menorTempo=maquinaMandril.get(i).getRolamento().getInstante(); 
					}
					
					maquinaMandril.get(i).removeRolamento();
					disponibilidadeMandril++;
				break;
				}
			}
		}
		return menorTempo;
	}
	
	/*verifica qual eh a maquina que possui o menor tempo de saida, para poder chamar as maquinas na ordem correta que acontessem os fatos*/
	public String verificaMenorTempo()
	{
		double tempoTorno;
		double tempoMandril;
		double tempoFresa;
		
		ordenaPeloTempoDeSaidaM(maquinaMandril);
		ordenaPeloTempoDeSaidaF(maquinaFresa);
		ordenaPeloTempoDeSaidaT(maquinaTorno);
		
		if(maquinaTorno.get(0).getRolamento() != null)
		{
			tempoTorno=maquinaTorno.get(0).getInstante()+maquinaTorno.get(0).getRolamento().getTempoTorno();
		}
		else
		{
			tempoTorno=getTempoUniversal()+1;
		}
		
		 if(maquinaFresa.get(0).getRolamento() != null)
		{
			tempoFresa=maquinaFresa.get(0).getInstante()+maquinaFresa.get(0).getRolamento().getTempoFresa();
		}
		else
		{
			tempoFresa=getTempoUniversal()+1;
		}
		
		if(maquinaMandril.get(0).getRolamento() != null)
		{
			tempoMandril=maquinaMandril.get(0).getInstante()+maquinaMandril.get(0).getRolamento().getTempoMandril();
		}
		else
		{
			tempoMandril=getTempoUniversal()+1;
		}
		
		if(tempoTorno <= tempoMandril && tempoTorno <= tempoFresa)
		{	
			return "Torno";
		}
			
		if(tempoFresa <= tempoTorno && tempoFresa <= tempoMandril)
		{
			return "Fresa";
		}
			
		if(tempoMandril <= tempoTorno && tempoMandril <= tempoFresa)
		{
			return "Mandril";
		}
		
		return "Erro";
	}
	
	/* metodo para colocar as maquinas para "trabalhar"*/
	public void producao()
	{
		double menorTempo=getTempoUniversal()+1;
		String proxMaquina;
		
		proxMaquina=verificaMenorTempo();
		
		if(proxMaquina.equals("Torno"))
			menorTempo=executaTorno();
			
		if(proxMaquina.equals("Fresa"))
			menorTempo=executaFresa();
			
		if(proxMaquina.equals("Mandril"))
			menorTempo=executaMandril();	
		
		if(proxMaquina.equals("Erro"))
			System.out.println("Erro.");
		
		if(menorTempo<=getTempoUniversal()) // se o menor tempo for menor que o tempo do proximo rolamento ele continua a producao 
		{
			jogaPraMaquina();
			producao();
		}
	}
	
	/*coloca o rolamento na maquina*/
	public void jogaPraMaquina()
	{
		
		while(disponibilidadeTorno >= 1 && listaTorno.size()>=1 )
		{
			disponibilidadeTorno--;
			addMaquina(listaTorno.get(0), "Torno");				
			listaTorno.remove(listaTorno.get(0));
		}
			
		while(disponibilidadeFresa >= 1 && listaFresa.size()>=1)
		{
			disponibilidadeFresa--;
			addMaquina(listaFresa.get(0), "Fresa");
			listaFresa.remove(listaFresa.get(0));	
		}
		
		while(disponibilidadeMandril >= 1 && listaMandril.size()>=1)
		{
			disponibilidadeMandril--;
			addMaquina(listaMandril.get(0), "Mandril");
			listaMandril.remove(listaMandril.get(0));
		}
	}
	
	/*adiciona o rolamento a maquina*/
	public void addMaquina(AbstractRolamento r, String s)
	{
		
		if(s.equals("Torno") )
		{
			for(torno t : maquinaTorno)
			{
				if(t.getStatus()==0)
				{
					t.addRolamento(r);
					t.setStatus(1);
					break;
				}
			}
		}
		
		if(s.equals("Fresa") )
		{
			for(fresa f : maquinaFresa)
			{
				if(f.getStatus()==0)
				{
					f.addRolamento(r);
					f.setStatus(1);
					break;
				}
			}
		}
		
		if(s.equals("Mandril") )
		{
			for(mandril m : maquinaMandril)
			{
				if(m.getStatus()==0)
				{
					m.addRolamento(r);
					m.setStatus(1);
					break;
				}
			}
		}
	}
	
	/*adiciona o rolamento a fila da respectiva maquina*/
	public void addLista(AbstractRolamento r, String s)
	{
	
		if(s.equals("Torno") && (r.getEtapa()!=-1))
		{
			listaTorno.add(r);
			checaPrioridades(listaTorno);
			checaPrioridades(listaTorno);
		}
		
		if(s.equals("Fresa") && (r.getEtapa()!=-1))
		{
			listaFresa.add(r);
			checaPrioridades(listaFresa);
			checaPrioridades(listaFresa);
		}
		
		if(s.equals("Mandril") && (r.getEtapa()!=-1))
		{
			listaMandril.add(r);
			checaPrioridades(listaMandril);
			checaPrioridades(listaMandril);
		}
		
		if(s.equals("FIM"))
		{
			
			if(r.getInstante()<=getTempoTotalExecucao())
			{
				System.out.println("Adicionando " + r.getTipo() + " Na ListaFinalizados");
				addFinalizados(r);
			}
			
	    }		
	}
	
	/*adiciono o rolamento na lista de finalizados*/
	public void addFinalizados(AbstractRolamento r)
	{
		finalizados.add(r);
		r.setQuantidade(1);
		r.setTempoTotal(r.getInstante()-r.getInstanteInicial());
	}
	
	/*ordena as maquinas pelo tempo de saida dos rolamentos*/
	public void ordenaPeloTempoDeSaidaT(ArrayList<torno> m)
	{
		int tam= m.size();
			
		for(int i=0; i<tam; i++)
		{
			for(int j=i+1;j<tam;j++)
			{
				if(m.get(i).getStatus()==1 && m.get(j).getStatus()==1)
				{
					if(m.get(i).getRolamento().getInstante()+m.get(i).getRolamento().getTempoTorno() < m.get(j).getRolamento().getInstante()+m.get(j).getRolamento().getTempoTorno())
					{
						torno aux = m.get(i);
						m.remove(m.get(i));
						m.add(aux);
					}
				}
			}
		}
	}
	
	public void ordenaPeloTempoDeSaidaF(ArrayList<fresa> m)
	{
		int tam= m.size();
		for(int i=0; i<tam; i++)
		{
			for(int j=i+1;j<tam;j++)
			{
				if(m.get(i).getStatus()==1 && m.get(j).getStatus()==1)
				{
					if(m.get(i).getRolamento().getInstante()+m.get(i).getRolamento().getTempoFresa() < m.get(j).getRolamento().getInstante()+m.get(j).getRolamento().getTempoFresa())
					{
						fresa aux = m.get(i);
						m.remove(m.get(i));
						m.add(aux);
					}
				}
			}
		}
	}
	
	public void ordenaPeloTempoDeSaidaM(ArrayList<mandril> m)
	{
		int tam= m.size();
		for(int i=0; i<tam; i++)
		{
			for(int j=i+1;j<tam;j++)
			{
				if(m.get(i).getStatus()==1 && m.get(j).getStatus()==1)
				{
					if(m.get(i).getRolamento().getInstante()+m.get(i).getRolamento().getTempoMandril() < m.get(j).getRolamento().getInstante()+m.get(j).getRolamento().getTempoMandril())
					{
						mandril aux = m.get(i);
						m.remove(m.get(i));
						m.add(aux);
					}
				}
			}
		}
	}
	
	/*chega a prioridada dos rolamentos da fila(organizar lista)*/
	public void checaPrioridades(ArrayList<AbstractRolamento> l)//Apenas traz o de maior prioridade para frente da lista.
		{
		int tam= l.size();
		for(int i=0; i<tam; i++)
		{
			for(int j=i+1;j<tam;j++)
			{
				if( l.get(i).getPrioridade() < l.get(j).getPrioridade() )
				{
					AbstractRolamento aux = l.get(i);
					l.remove(l.get(i));
					l.add(aux);
				}
			}
		}
	}
	
	 public void setTempoTotalExecucao(String s)
	{
		this.tempoTotalExecucao = Double.parseDouble(s); 
	}
	
	public double getTempoTotalExecucao()
	{
		return this.tempoTotalExecucao;
	}
	 
	
	public ArrayList<AbstractRolamento> getFinalizados()
	{
		return maquinas.finalizados;
	}
}