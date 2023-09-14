package lattes;
import java.util.ArrayList;

import excecoes.ExcecaoDeListaCheia;
import excecoes.ExcecaoJaEstavaNaLista;
import excecoes.ExcecaoNaoEncontrouNaLista;
public class Projeto {
	private int id;
	private String titulo;
	private String descricao;
	private Data dtIni;
	private Data dtFim;
	private int quantidadePesquisadores;
	private int pesquisadoresVinculados;
	private ArrayList<Pesquisador> pesq=new ArrayList<>();
	public Projeto(int id, String titulo, String desc, Data dtIni, Data dtFim, int quantPes){
		this.id = id;
		this.titulo = titulo;
		this.descricao = desc;
		this.dtIni = dtIni;
		this.dtFim = dtFim;
		this.quantidadePesquisadores = quantPes;
		this.pesquisadoresVinculados = 0;
	}
	public Projeto(int id, String titulo, String desc, Data dtIni, Data dtFim, int quantPes, ArrayList<Pesquisador> p){
		this.id = id;
		this.titulo = titulo;
		this.descricao = desc;
		this.dtIni = dtIni;
		this.dtFim = dtFim;
		this.quantidadePesquisadores = quantPes;
		this.pesquisadoresVinculados = quantPes;
		this.pesq=p;
	}
	public int getId() {
		return id;
	}
	public void setId(int i) {
		this.id = i;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String t) {
		this.titulo = t;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String d) {
		this.descricao = d;
	}
	public Data getDtIni() {
		return dtIni;
	}
	public void setDtIni(Data d) {
		this.dtIni = d;
	}
	public Data getDtFim() {
		return dtFim;
	}
	public void setDtFim(Data d) {
		this.dtFim= d;
	}
	public ArrayList<Pesquisador> getPesq() {
		return pesq;
	}
	public void setPesq(ArrayList<Pesquisador> pesq) {
		this.pesq = pesq;
	}
	public int getQuantidadePesquisadores() {
		return quantidadePesquisadores;
	}
	public void setQuantidadePesquisadores(int qP) {
		this.quantidadePesquisadores = qP;
	}
	public int getPesquisadoresVinculados() {
		return pesquisadoresVinculados;
	}
	public void setPesquisadoresVinculados(int pesquisadoresVinculados) {
		this.pesquisadoresVinculados = pesquisadoresVinculados;
	}
	public void alteraDados(String titulo, String desc, Data dtIni, Data dtFim, int qtdPes) throws ExcecaoDeListaCheia{
		if(qtdPes>=this.getPesquisadoresVinculados()) {
			this.setTitulo(titulo);
			this.setDescricao(desc);
			this.setDtIni(dtIni);
			if(qtdPes>this.getQuantidadePesquisadores()) {
				this.setQuantidadePesquisadores(qtdPes);
			}
		}
		else {
			throw new ExcecaoDeListaCheia();
		}
	}
	public void vinculaPesq(Pesquisador pes) throws ExcecaoDeListaCheia, ExcecaoJaEstavaNaLista{
		if(this.pesquisadoresVinculados<this.quantidadePesquisadores) {
			for(Pesquisador p:pesq) {
				if(p.equals(pes)) 
					throw new ExcecaoJaEstavaNaLista() ;
			}
			this.pesq.add(pes);
			this.pesquisadoresVinculados++;
			return;
		}
		else
			throw new ExcecaoDeListaCheia();
	}
	public void removePesq(Pesquisador pes) throws ExcecaoNaoEncontrouNaLista{
		for(Pesquisador p:pesq) {
			if(p.equals(pes)) {
				pesq.remove(p);
				this.pesquisadoresVinculados--;
				return;
			}
		}
		throw new ExcecaoNaoEncontrouNaLista();
	}
	public String toString() {
		return "Projeto "+this.getId()+": "+this.getTitulo();
	}
}
