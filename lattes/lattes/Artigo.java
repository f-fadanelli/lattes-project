package lattes;
import java.util.ArrayList;
import excecoes.ExcecaoDeListaCheia;
import excecoes.ExcecaoJaEstavaNaLista;
import excecoes.ExcecaoNaoEncontrouNaLista;
public class Artigo {
	private int id;
	private String titulo;
	private String tituloRevista;
	private int anoPublicacao;
	private int quantidadePesquisadores;
	private int pesquisadoresVinculados;
	private ArrayList<Pesquisador> pesq = new ArrayList<>();
	public Artigo(int id, String titulo, String tituloRev, int anoPub, int qtdPes){
		this.id = id;
		this.titulo = titulo;
		this.tituloRevista = tituloRev;
		this.anoPublicacao = anoPub;
		this.quantidadePesquisadores = qtdPes;
		this.pesquisadoresVinculados = 0;
	}
	public Artigo(int id, String titulo, String tituloRev, int anoPub, int qtdPes, ArrayList<Pesquisador> p){
		this.id = id;
		this.titulo = titulo;
		this.tituloRevista = tituloRev;
		this.anoPublicacao = anoPub;
		this.quantidadePesquisadores = qtdPes;
		this.pesquisadoresVinculados = qtdPes;
		this.pesq = p;
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
	public String getTituloRevista() {
		return tituloRevista;
	}
	public void setTituloRevista(String t) {
		this.tituloRevista = t;
	}
	public int getAnoPublicacao() {
		return anoPublicacao;
	}
	public void setAnoPublicacao(int ano) {
		this.anoPublicacao = ano;
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
	public void alteraDados(String titulo, String tituloRev, int anoPub, int qtdPes) throws ExcecaoDeListaCheia {
		if(qtdPes>=this.getPesquisadoresVinculados()) {
			this.setTitulo(titulo);
			this.setTituloRevista(tituloRev);
			this.setAnoPublicacao(anoPub);
			if(qtdPes!=this.getQuantidadePesquisadores()) {
				this.setQuantidadePesquisadores(qtdPes);
			}
		}	
		else {
			throw new ExcecaoDeListaCheia();
		}
	}
	public void vinculaPesq(Pesquisador pes) throws ExcecaoDeListaCheia, ExcecaoJaEstavaNaLista{
		if(this.pesquisadoresVinculados<this.quantidadePesquisadores) {
			for(Pesquisador p: pesq) {
				if(p.equals(pes)) 
					throw new ExcecaoJaEstavaNaLista();
			}
			this.pesq.add(pes);
			this.pesquisadoresVinculados++;
			return ;
		}
		else
			throw new ExcecaoDeListaCheia();
	}
	public void removePesq(Pesquisador pes) throws ExcecaoNaoEncontrouNaLista{
		for(Pesquisador p: pesq) {
			if(p.equals(pes)) {
				pesq.remove(p);
				this.pesquisadoresVinculados--;
				return;
			}
		}
		throw new ExcecaoNaoEncontrouNaLista();
	}
	public String toString() {
		return "Artigo "+this.getId()+": "+this.getTitulo();
	}
}
