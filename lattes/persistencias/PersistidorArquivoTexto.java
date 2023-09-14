package persistencias;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import lattes.Pesquisador;
import lattes.Artigo;
import lattes.Projeto;
import lattes.Data;
public class PersistidorArquivoTexto implements Persistidor{
	
	public void escreveDados(ArrayList<Pesquisador> pesquisadores, ArrayList<Projeto> projetos, ArrayList<Artigo> artigos) {
		try {
			FileWriter fw = new FileWriter("Lattes.txt");
			BufferedWriter escrevedor = new BufferedWriter(fw);
			ArrayList<Pesquisador> pesqAux=new ArrayList<>();
			for(Pesquisador p : pesquisadores) {
				escrevedor.write("#Pesquisador");
				escrevedor.newLine();
				escrevedor.write("Nome: " + p.getNome());
				escrevedor.newLine();
				escrevedor.write("Universidade: " + p.getUniversidade());
				escrevedor.newLine();
			}
			for(Projeto p:projetos) {
				escrevedor.write("#Projeto");
				escrevedor.newLine();
				escrevedor.write("Data Início: " + p.getDtIni().toString());
				escrevedor.newLine();
				escrevedor.write("Data Final: " + p.getDtFim().toString());
				escrevedor.newLine();
				escrevedor.write("Título: " + p.getTitulo());
				escrevedor.newLine();
				escrevedor.write("Descrição: " + p.getDescricao());
				escrevedor.newLine();
				escrevedor.write("Pesquisadores: ");
				pesqAux=p.getPesq();
				Pesquisador ultimo = pesqAux.get(pesqAux.size() - 1);
				for(Pesquisador pes:pesqAux) {
					if(pes.equals(ultimo)) {
						escrevedor.write(pes.getNome());
					}
					else {
						escrevedor.write(pes.getNome()+"; ");
					}
				}
				escrevedor.newLine();
			}
			for(Artigo a:artigos) {
				escrevedor.write("#Artigo");
				escrevedor.newLine();
				escrevedor.write("Titulo: " + a.getTitulo());
				escrevedor.newLine();
				escrevedor.write("Ano: " + a.getAnoPublicacao());
				escrevedor.newLine();				
				escrevedor.write("Revista: " + a.getTituloRevista());
				escrevedor.newLine();
				escrevedor.write("Autores: ");
				pesqAux=a.getPesq();
				Pesquisador ultimo = pesqAux.get(pesqAux.size() - 1);
				for(Pesquisador pes:pesqAux) {
					if(pes.equals(ultimo)) {
						escrevedor.write(pes.getNome());
					}
					else {
						escrevedor.write(pes.getNome()+"; ");
					}
				}
				escrevedor.newLine();
			}
			escrevedor.flush();
			escrevedor.close();
			
		}
		catch(IOException e) {
			System.out.println("erro de io!");
		}
	}
	public ArrayList<Pesquisador> recuperaPesquisadores(){
		ArrayList<Pesquisador> pesquisadores = new ArrayList<>();
		
		try {
			FileReader fr = new FileReader("Lattes.txt");
			BufferedReader leitor = new BufferedReader(fr);
			String aux, nome, universidade;
			int id=0;
			nome="";
			aux = leitor.readLine();
			while(!aux.startsWith("#Projeto")) {
				if(aux.startsWith("Nome:")) {
					id++;
					nome=aux.substring(6);
				}
				else if(aux.startsWith("Universidade:")) {
					universidade=aux.substring(14);
					pesquisadores.add(new Pesquisador(id, nome,"","", universidade)) ;
				}
				aux = leitor.readLine();
			}
			leitor.close();
		}
		catch(IOException e) {
			System.out.println("erro de io!");
		}
		
		return pesquisadores;
	}
	public ArrayList<Projeto> recuperaProjetos(ArrayList<Pesquisador>pesquisadores){
		ArrayList<Projeto> projetos = new ArrayList<>();
		
		try {
			FileReader fr = new FileReader("Lattes.txt");
			BufferedReader leitor = new BufferedReader(fr);
			String aux, nomes, descricao, titulo, dia, mes, ano;
			int dI, mI, aI, dF, mF, aF, id;
			titulo=descricao=nomes="";
			dI=mI=aI=dF=mF=aF=0;
			aux = leitor.readLine();
			while(!aux.startsWith("#Projeto")) {
				aux = leitor.readLine();
			}
			id=0;
			while(!aux.startsWith("#Artigo")) {
				if(aux.startsWith("Data Início:")) {
					dia=aux.substring(13, 15);
					mes=aux.substring(16, 18);
					ano=aux.substring(19, 23);
					dI=Integer.parseInt(dia);
					mI=Integer.parseInt(mes);
					aI=Integer.parseInt(ano);
				}
				else if(aux.startsWith("Data Final:")) {
					dia=aux.substring(12, 14);
					mes=aux.substring(15, 17);
					ano=aux.substring(18, 22);
					dF=Integer.parseInt(dia);
					mF=Integer.parseInt(mes);
					aF=Integer.parseInt(ano);
				}
				else if(aux.startsWith("Título:")) {
					titulo=aux.substring(8);
				}
				else if(aux.startsWith("Descrição:")) {
					descricao=aux.substring(11);
				}
				else if(aux.startsWith("Pesquisadores:") || aux.startsWith("Integrantes:")) {
					ArrayList<Pesquisador> pesqAux = new ArrayList<>();
					if(aux.startsWith("Pesquisadores:")){
						nomes=aux.substring(15);
					}
					else {
						nomes=aux.substring(13);
					}
					String[] nomesSplit=nomes.split("; ");
					for(String s:nomesSplit) {
						for(Pesquisador p:pesquisadores) {
							if(p.getNome().compareTo(s)==0) {
								pesqAux.add(p);
							}
						}
					}
					id++;
					projetos.add(new Projeto(id, titulo, descricao, new Data(dI, mI, aI), new Data(dF, mF, aF), pesqAux.size(), pesqAux));
				}
				aux = leitor.readLine();
			}
			leitor.close();
		}
		catch(IOException e) {
			System.out.println("erro de io!");
		}
		
		return projetos;
	}
	public ArrayList<Artigo> recuperaArtigos(ArrayList<Pesquisador>pesquisadores){
		ArrayList<Artigo> artigos = new ArrayList<>();
		
		try {
			FileReader fr = new FileReader("Lattes.txt");
			BufferedReader leitor = new BufferedReader(fr);
			String aux, nomes, revista, titulo, ano;
			int a, id;
			titulo=revista=nomes="";
			a=0;
			aux = leitor.readLine();
			while(!aux.startsWith("#Artigo")) {
				aux = leitor.readLine();
			}
			id=0;
			while(aux!=null) {
				if(aux.startsWith("Titulo:")) {
					titulo=aux.substring(8);
				}
				else if(aux.startsWith("Ano:")) {
					ano=aux.substring(5);
					a=Integer.parseInt(ano);
				}
				else if(aux.startsWith("Revista:")) {
					revista=aux.substring(9);
				}
				else if(aux.startsWith("Autores:")) {
					ArrayList<Pesquisador> pesqAux = new ArrayList<>();
					nomes=aux.substring(9);
					String[] nomesSplit=nomes.split("; ");
					for(String s:nomesSplit) {
						for(Pesquisador p:pesquisadores) {
							if(p.getNome().compareTo(s)==0) {
								pesqAux.add(p);
							}
						}
					}
					id++;
					artigos.add(new Artigo(id, titulo, revista, a, pesqAux.size(), pesqAux));
				}
				aux = leitor.readLine();
			}
			leitor.close();
		}
		catch(IOException e) {
			System.out.println("erro de io!");
		}
		
		return artigos;
	}
}
