package lattes;
import java.util.ArrayList;
import persistencias.PersistidorArquivoTexto;
public class CarregadorDeDados {
	public ArrayList<Pesquisador> carregaPesquisadores() {
		PersistidorArquivoTexto ptext=new PersistidorArquivoTexto();
		ArrayList<Pesquisador> pesquisadores=new ArrayList<>();
		pesquisadores=ptext.recuperaPesquisadores();
		return pesquisadores;
	}
	public ArrayList<Projeto> carregaProjetos(ArrayList<Pesquisador> pesquisadores) {
		PersistidorArquivoTexto ptext=new PersistidorArquivoTexto();
		ArrayList<Projeto> projetos=new ArrayList<>();
		projetos=ptext.recuperaProjetos(pesquisadores);
		return projetos;
	}
	public ArrayList<Artigo> carregaArtigos(ArrayList<Pesquisador> pesquisadores) {
		PersistidorArquivoTexto ptext=new PersistidorArquivoTexto();
		ArrayList<Artigo>artigos=new ArrayList<>();
		artigos=ptext.recuperaArtigos(pesquisadores);
		return artigos;
	}
}
