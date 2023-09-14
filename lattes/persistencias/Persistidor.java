package persistencias;
import java.util.ArrayList;
import lattes.Pesquisador;
import lattes.Artigo;
import lattes.Projeto;
public interface Persistidor {
	public void escreveDados(ArrayList<Pesquisador> pesquisadores, ArrayList<Projeto> projetos, ArrayList<Artigo> artigos);

	public ArrayList<Pesquisador> recuperaPesquisadores();
	public ArrayList<Projeto> recuperaProjetos(ArrayList<Pesquisador> pesquisadores);
	public ArrayList<Artigo> recuperaArtigos(ArrayList<Pesquisador> pesquisadores);

}
