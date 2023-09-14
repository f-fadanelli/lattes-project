package view;
import java.util.Scanner;
import java.util.ArrayList;
import lattes.Aluno;
import lattes.Artigo;
import lattes.CarregadorDeDados;
import lattes.Data;
import lattes.Pesquisador;
import lattes.Professor;
import lattes.Projeto;
import excecoes.ExcecaoNaoEncontrouNaLista;
import excecoes.ExcecaoDeListaCheia;
import excecoes.ExcecaoJaEstavaNaLista;
import persistencias.PersistidorArquivoTexto;
public class InterfaceUsu {
	/**
	 * O programa faz o uso de arrayLists de pesquisadores, projetos e artigos.
	 * Também utiliza um contador para cada um deles, que serve tanto para determinar qual o id do
	 * próximo item a ser inserido.
	 */
	private ArrayList<Pesquisador> pesquisadores = new ArrayList<>();
	private ArrayList<Projeto> projetos = new ArrayList<>();
	private ArrayList<Artigo> artigos = new ArrayList<>();
	private Pesquisador pesquisadoraux;
	private Artigo artigoaux;
	private Projeto projetoaux;
	private int nPesquisadores=0;
	private int nProjetos=0;
	private int nArtigos=0;
	CarregadorDeDados carregador = new CarregadorDeDados();
	public void menu() {
		int opcao;
		String nome, area, universidade, cpf, tituloProj, descProj, tituloArt, tituloRevista;
		int dia, mes, ano, qtdPes, id, idProj, idArt, achou, tipo;
		Data dtIni, dtFim;
		ArrayList <Pesquisador> pesq = new ArrayList<>();
		Scanner input = new Scanner(System.in);
		System.out.println("Deseja carregar os Dados Anteriormente? 1-SIM | 2-NAO");
		opcao = input.nextInt();
		/**
		 * O programa solicita se o usuário deseja fazer o carregamento prévio de dados;
		 * caso sim, são carregados os dados disponibilizados no arquivo Lattes.txt.
		 */
		if(opcao==1) {
			pesquisadores=carregador.carregaPesquisadores();
			nPesquisadores=pesquisadores.size();
			projetos=carregador.carregaProjetos(pesquisadores);
			nProjetos=projetos.size();
			artigos=carregador.carregaArtigos(pesquisadores);
			nArtigos=artigos.size();
			System.out.println("Dados lidos com sucesso!\n");
		}
		/**
		 * Para uma boa interação com o usuário, ele deve poder vincular e remover pesquisadores
		 * aos artigos e projetos, e caso queira modificar algum dado de importante, como por 
		 * exemplo, a quantidade de pesquisadores de um projeto, ele pode alterar os dados desse
		 * projeto, possibilitando mais flexibilidade a partir da criação do mesmo.
		 */
		System.out.println("0.Sair");
		System.out.println("1.Cadastrar novo Pesquisador");
		System.out.println("2.Cadastrar novo Projeto");
		System.out.println("3.Cadastrar novo Artigo");
		System.out.println("4.Vincular Pesquisador a um Projeto");
		System.out.println("5.Vincular Pesquisador a um Artigo");
		System.out.println("6.Remover Pesquisador de um Projeto");
		System.out.println("7.Remover Pesquisador de um Artigo");
		System.out.println("8.Alterar dados de Pesquisador");
		System.out.println("9.Alterar dados de Projeto");
		System.out.println("10.Alterar dados de Artigo");
		System.out.println("11.Listar pesquisadores de uma mesma Universidade");
		System.out.println("12.Listar os autores de um determinado artigo");
		System.out.println("13.Listar todos os projetos de um determinado pesquisador");
		System.out.println("14.Listar os pesquisadores de projetos já finalizados");
		opcao = input.nextInt();
		while(opcao!=0) {
			switch(opcao) {
			/**
			 * Os cadastros (casos 1 a 3), em geral, seguem o mesmo padrão: solicitam os dados necessários
			 * para criar um novo objeto e o adiciona em seu respectivo arrayList. 
			 */
			case 1:
				System.out.println("Cadastro de Pesquisador");
				System.out.println("O pesquisador é professor ou estudante? 1-Professor 2-Aluno 3-Não Especificar ");
				tipo = input.nextInt();
				System.out.print("Nome: ");
				input.nextLine();
				nome = input.nextLine();
				System.out.print("CPF: ");
				cpf = input.nextLine();
				pesquisadoraux=verificaSeHaPesquisadorComCPF(cpf);
				if(pesquisadoraux!=null) {
					System.out.println("Esse CPF já foi utilizado no cadastro de outro Pesquisador!");
					break;
				}
				/**
				 * Um diferencial no cadatro de pesquisadores é a verificação do seu cpf,
				 * caso já haja algum outro pesquisador com o cpf informado, é impossibilitado
				 * o seu cadastro.
				 */
				System.out.print("Área Acadêmica: ");
				area = input.nextLine();
				System.out.print("Universidade: ");
				universidade = input.nextLine();
				id = nPesquisadores +1;
				if(tipo==1) {
					pesquisadores.add(new Professor(id, nome, cpf, area, universidade)); 
				}
				else if(tipo==2) {
					pesquisadores.add(new Aluno(id, nome, cpf, area, universidade)); 
				}
				else if(tipo==3) {
					pesquisadores.add(new Pesquisador(id, nome, cpf, area, universidade)); 
				}
				System.out.println("Pesquisador Cadastrado com Sucesso!");
				System.out.println("Id do Pesquisador: " + id);
				nPesquisadores++;
				break;
			case 2:
				System.out.println("Cadastro de Projeto");
				System.out.print("Título do Projeto: ");
				input.nextLine();
				tituloProj = input.nextLine();
				System.out.print("Descrição de Projeto: ");
				descProj = input.nextLine();
				System.out.println("Data Inicial de Projeto: ");
				System.out.print("Dia: ");
				dia = input.nextInt();
				System.out.print("Mês: ");
				mes = input.nextInt();
				System.out.print("Ano: ");
				ano = input.nextInt();
				dtIni = new Data(dia, mes, ano);
				System.out.println("Data Final de Projeto: ");
				System.out.print("Dia: ");
				dia = input.nextInt();
				System.out.print("Mês: ");
				mes = input.nextInt();
				System.out.print("Ano: ");
				ano = input.nextInt();
				dtFim = new Data(dia, mes, ano);
				if(dtIni.dataEhMenorQueDt(dtFim)==false) {
					System.out.print("A Data Inicial do Projeto deve ser menor que a Data Final! ");
					break;
				}
				/**
				 * Um outro diferencial, agora no cadastro de projetos, é a verificação da data 
				 * inicial e a data final; a data inicial deve ser anterior a data final, caso 
				 * isso não aconteça não é possível cadastrar o projeto.
				 * Esse teste é realizado através da função dataEhMenorQueDt, que compara,
				 * na classe de Datas, se a data que acessa a função é anterior a data 
				 * passada por parâmetro.
				 */
				System.out.print("Quantidade de Pesquisadores envolvidos no Projeto: ");
				qtdPes = input.nextInt();
				id=nProjetos+1;
				projetos.add(new Projeto(id, tituloProj, descProj, dtIni, dtFim, qtdPes)); 
				System.out.println("Projeto Cadastrado com Sucesso!");
				System.out.println("Id do Projeto: " + id);
				nProjetos++;
				break;
			case 3:
				System.out.println("Cadastro de Artigo");
				System.out.print("Título do Artigo: ");
				input.nextLine();
				tituloArt = input.nextLine();
				System.out.print("Título da Revista do Artigo: ");
				tituloRevista = input.nextLine();
				System.out.print("Ano de Publicação: ");
				ano = input.nextInt();
				System.out.print("Quantidade de Pesquisadores envolvidos no Artigo: ");
				qtdPes = input.nextInt();
				id=nArtigos+1;
				artigos.add(new Artigo(id, tituloArt, tituloRevista, ano, qtdPes));
				System.out.println("Artigo Cadastrado com Sucesso!");
				System.out.println("Id do Artigo: " + id);
				nArtigos++;
				break;
			/**
			 * Os vínculos (casos de 4 e 5) também seguem um padrao; ao informar 
			 * os Id´s dos objetos a serem vinculados, sempre é verificado se há
			 * um objeto com esse Id. Para facilitar, é impressa uma lista com os Id´s 
			 * e nomes/titulos dos objetos ja cadastrados, mas mesmo assim, caso seja
			 * informado um id ainda não implementado, não é possível realizar o vinculo.
			 * 
			 * Uma outra observação a ser feita é essa busca para verificar se o Id já foi
			 * criado. É feita através das funções verificaSeHa"Objeto", que retorna o 
			 * objeto desejado caso ele ja tenha sido adicionado no arraylist.
			 * 
			 * No vínculo de pesquisadores a artigos e projetos, foi implementada uma 
			 * verificação que, antes de vincular os objetos, testa se o mesmo ainda não foi 
			 * inserido, com a finalidade de evitar duplicidades. Essa função também auxilia
			 * a verificar se a quantidade de pesquisadores informada no cadastro de 
			 * projetos/artigos não foi extrapolada. Assim, não são vinculados mais 
			 * pesquisadore do que deveria. Essas verificações foram implementadas na
			 * função vinculaPesq dos artigos e projetos. 
			 */
			case 4:
				System.out.println("Vincular Pesquisador a um Projeto");
				System.out.println("Informe o Id do Pesquisador que você deseja vincular: ");
				ListaPesquisadores();
				System.out.print("Id: ");
				id = input.nextInt();
				try {
					pesquisadoraux=verificaSeHaPesquisador(id);
				}
				catch(ExcecaoNaoEncontrouNaLista e){
					System.out.println("Não foi encontrado nenhum Pesquisador com esse Id.");
					break;
				}
				System.out.println("Informe o Id do Projeto que você deseja vincular esse Pesquisador: ");
				ListaProjetos();
				System.out.print("Id: ");
				idProj = input.nextInt();
				try {
					projetoaux=verificaSeHaProjeto(idProj);
				}
				catch(ExcecaoNaoEncontrouNaLista e) {
					System.out.println("Não foi encontrado nenhum Projeto com esse Id.");
					break;
				}	
				try {
					projetoaux.vinculaPesq(pesquisadoraux);
				}
				catch(ExcecaoJaEstavaNaLista e) {
					System.out.println("O Pesquisador já havia sido Vinculado ao Projeto anteriormente!");
					break;
				}
				catch(ExcecaoDeListaCheia e) {
					System.out.println("A quantidade de Pesquisadores do Projeto foi excedida!");
					break;
				}
				System.out.println("Pesquisador vinculado ao Projeto com Sucesso!");
				break;
			case 5:
				System.out.println("Vincular Pesquisador a um Artigo");
				System.out.println("Informe o Id do Pesquisador que você deseja vincular: ");
				ListaPesquisadores();
				System.out.print("Id: ");
				id = input.nextInt();
				try {
					pesquisadoraux=verificaSeHaPesquisador(id);
				}
				catch(ExcecaoNaoEncontrouNaLista e){
					System.out.println("Não foi encontrado nenhum Pesquisador com esse Id.");
					break;
				}
				System.out.println("Informe o Id do Artigo que você deseja vincular esse Pesquisador: ");
				ListaArtigos();
				System.out.print("Id: ");
				idArt = input.nextInt();
				try {
					artigoaux=verificaSeHaArtigo(idArt);
				}
				catch(ExcecaoNaoEncontrouNaLista e) {
					System.out.println("Não foi encontrado nenhum Artigo com esse Id.");
					break;
				}
				try {
					artigoaux.vinculaPesq(pesquisadoraux);
				}
				catch(ExcecaoDeListaCheia e) {
					System.out.println("A quantidade de Pesquisadores do Artigo foi excedida!");
					break;
				}
				catch(ExcecaoJaEstavaNaLista e) {
					System.out.println("O Pesquisador já havia sido Vinculado ao Artigo anteriormente!");
					break;
				}
				System.out.println("Pesquisador vinculado ao Artigo com Sucesso!");
				break;
				/**
				 * O mesmo ocorre nas remoções de vínculo (casos 6 e 7):
				 * Para vinculos de pesquisadores e artigos/projetos, foi implementada uma
				 * função que verifica se o pesquisador informado realmente fazia parte
				 * daquele projeto. Se já está, é removido. Função: removePesq
				 */
			case 6:
				System.out.println("Remover Pesquisador de um Projeto");
				System.out.println("Informe o Id do Pesquisador que você deseja remover: ");
				ListaPesquisadores();
				System.out.print("Id: ");
				id = input.nextInt();
				try {
					pesquisadoraux=verificaSeHaPesquisador(id);
				}
				catch(ExcecaoNaoEncontrouNaLista e){
					System.out.println("Não foi encontrado nenhum Pesquisador com esse Id.");
					break;
				}
				System.out.println("Informe o Id do Projeto que você deseja remover esse Pesquisador: ");
				ListaProjetos();
				System.out.print("Id: ");
				idProj = input.nextInt();
				try {
					projetoaux=verificaSeHaProjeto(idProj);
				}
				catch(ExcecaoNaoEncontrouNaLista e) {
					System.out.println("Não foi encontrado nenhum Projeto com esse Id.");
					break;
				}
				try {
					projetoaux.removePesq(pesquisadoraux);
				}
				catch(ExcecaoNaoEncontrouNaLista e) {
					System.out.println("O Pesquisador informado não havia sido vinculado ao Projeto anteriormente!");
					break;
				}
				System.out.println("Pesquisador Removido do Projeto com Sucesso! ");
				break;
			case 7:
				System.out.println("Remover Pesquisador de um Artigo");
				System.out.println("Informe o Id do Pesquisador que você deseja remover: ");
				ListaPesquisadores();
				System.out.print("Id: ");
				id = input.nextInt();
				try {
					pesquisadoraux=verificaSeHaPesquisador(id);
				}
				catch(ExcecaoNaoEncontrouNaLista e){
					System.out.println("Não foi encontrado nenhum Pesquisador com esse Id.");
					break;
				}
				System.out.println("Informe o Id do Artigo que você deseja remover esse Pesquisador: ");
				ListaArtigos();
				System.out.print("Id: ");
				idArt = input.nextInt();
				try {
					artigoaux=verificaSeHaArtigo(idArt);
				}
				catch(ExcecaoNaoEncontrouNaLista e) {
					System.out.println("Não foi encontrado nenhum Artigo com esse Id.");
					break;
				}
				try {
					artigoaux.removePesq(pesquisadoraux);
				}
				catch(ExcecaoNaoEncontrouNaLista e) {
					System.out.println("O Pesquisador informado não havia sido vinculado ao Artigo anteriormente!");
					break;
				}
				System.out.println("Pesquisador Removido do Artigo com sucesso!");
				break;
				/**
				 * As alterações (casos 8 a 10) são bem parecidas com os cadastros, a diferença é que 
				 * não são usados construtores para criar novos objetos, mas sim uma 
				 * função alteraDados, que solicita para a alteração todos os dados menos
				 * o Id, que deve continuar o mesmo.
				 * 
				 * Ao alterar os dados de artigos e projetos, é importante
				 * analisar um aspecto: se a quantidade de pesquisadores mudar, 
				 * deve-se verificar se isso nao irá afetar os pesquisador ja vinculados no 
				 * arrayList de pesquisadores do artigo/projeto.
				 * Por conta disso, na função alteraDados das classes artigos e projetos
				 * é verificado se há essa modificação.
				 * Se foi modificado e a nova quantidade de pesquisadores for menor
				 * do que a quantidade de pesquisadores ja vinculados, não é possível 
				 * realizar a alteração dos dados, pois seria perdido um ou mais pesquisadores.
				 * Nesse caso nenhuma das alterações ocorre.
				 * Se a quantidade for igual ou maior, o ArrayList segue o mesmo.
				 * função: alteraDados
				 */
			case 8:
				System.out.println("Alterar dados de Pesquisador");
				System.out.println("Informe o Id do Pesquisador que você deseja alterar: ");
				ListaPesquisadores();
				System.out.print("Id: ");
				id = input.nextInt();
				try {
					pesquisadoraux=verificaSeHaPesquisador(id);
				}
				catch(ExcecaoNaoEncontrouNaLista e){
					System.out.println("Não foi encontrado nenhum Pesquisador com esse Id.");
					break;
				}
				System.out.print("Nome: ");
				input.nextLine();
				nome = input.nextLine();
				System.out.print("CPF: ");
				cpf = input.nextLine();
				Pesquisador pesquisadoraux2=verificaSeHaPesquisadorComCPF(cpf);
				if(pesquisadoraux2!=null && !pesquisadoraux2.equals(pesquisadoraux)) {
					System.out.println("Esse CPF já foi utilizado no cadastro de outro Pesquisador!");
					break;
				}
				System.out.print("Área Acadêmica: ");
				area = input.nextLine();
				System.out.print("Universidade: ");
				universidade = input.nextLine();
				pesquisadoraux.alteraDados(nome, cpf, area, universidade);
				System.out.println("Dados da Universidade alterados com Sucesso!");
				break;
			case 9:
				System.out.println("Alterar dados de Projeto");
				System.out.println("Informe o Id do Projeto que você deseja alterar: ");
				ListaProjetos();
				System.out.print("Id: ");
				idProj = input.nextInt();
				try {
					projetoaux=verificaSeHaProjeto(idProj);
				}
				catch(ExcecaoNaoEncontrouNaLista e) {
					System.out.println("Não foi encontrado nenhum Projeto com esse Id.");
					break;
				}
				System.out.print("Título do Projeto: ");
				input.nextLine();
				tituloProj = input.nextLine();
				System.out.print("Descrição de Projeto: ");
				descProj = input.nextLine();
				System.out.println("Data Inicial de Projeto: ");
				System.out.print("Dia: ");
				dia = input.nextInt();
				System.out.print("Mês: ");
				mes = input.nextInt();
				System.out.print("Ano: ");
				ano = input.nextInt();
				dtIni = new Data(dia, mes, ano);
				System.out.println("Data Final de Projeto: ");
				System.out.print("Dia: ");
				dia = input.nextInt();
				System.out.print("Mês: ");
				mes = input.nextInt();
				System.out.print("Ano: ");
				ano = input.nextInt();
				dtFim = new Data(dia, mes, ano);
				if(dtIni.dataEhMenorQueDt(dtFim)==false) {
					System.out.print("A Data Inicial do Projeto deve ser menor que a Data Final! ");
					break;
				}
				System.out.print("Quantidade de Pesquisadores envolvidos no Projeto: ");
				qtdPes = input.nextInt();
				try {
					projetoaux.alteraDados(tituloProj, descProj, dtIni, dtFim, qtdPes);
				}
				catch(ExcecaoDeListaCheia e) {
					System.out.println("Não é possível mudar a quantidade de Pesquisadores do Projeto pois já há mais de " + qtdPes + " Pesquisadores vinculados!\nRemova Pesquisadores do Projeto para poder realizar essa alteração!");
					break;
				}
				System.out.println("Dados do Projeto alterados com Sucesso!");
				break;
			case 10:
				System.out.println("Alterar dados de Artigo");
				System.out.println("Informe o Id do Artigo que você deseja alterar: ");
				ListaArtigos();
				System.out.print("Id: ");
				idArt = input.nextInt();
				try {
					artigoaux=verificaSeHaArtigo(idArt);
				}
				catch(ExcecaoNaoEncontrouNaLista e) {
					System.out.println("Não foi encontrado nenhum Artigo com esse Id.");
					break;
				}
				System.out.print("Título do Artigo: ");
				input.nextLine();
				tituloArt = input.nextLine();
				System.out.print("Título da Revista do Artigo: ");
				tituloRevista = input.nextLine();
				System.out.print("Ano de Publicação: ");
				ano = input.nextInt();
				System.out.print("Quantidade de Pesquisadores envolvidos no Artigo: ");
				qtdPes = input.nextInt();
				try {
					artigoaux.alteraDados(tituloArt, tituloRevista, ano, qtdPes);
				}
				catch(ExcecaoDeListaCheia e) {
					System.out.println("Não é possível mudar a quantidade de Pesquisadores do Artigo pois já há mais de " + qtdPes + " Pesquisadores vinculados!\nRemova Pesquisadores do Artigo para poder realizar essa alteração!");
					break;
				}
				System.out.println("Dados do Artigo alterados com Sucesso!");
				break;
			case 11:
				/**
				 * nessa listagem é verificado se a universidade dos pesquisadores é
				 * igual a informada. Caso for, imprime o id e nome do pesquisador
				 * Caso não for encontrado nenhum pesquisador com a universidade informada,
				 * é impressa a mensagem contendo essa informação.
				 */
				System.out.println("Listar pesquisadores de uma mesma Universidade");
				System.out.println("Informe o nome da Universidade que você deseja consultar: ");
				System.out.print("Universidade: ");
				input.nextLine();
				universidade = input.nextLine();
				achou=0;
				System.out.println("Pesquisadores da universidade "+ universidade+": ");
				for(Pesquisador p:pesquisadores) {
					if(p.getUniversidade().compareTo(universidade)==0) {
						achou=1;
						System.out.println(p.toString());
					}
				}
				if(achou==0) {
					System.out.println("Não foi encontrado nenhum Pesquisador Vinculado à Universidade.");
				}
				break;
			case 12:
				/**
				 * nessa listagem, são solicitados os pesquisadores vinculados ao
				 * artigo informado, portanto, primeiro é verificado se o artigo em
				 * questão tem pesquisadores vinculados, se não tiver a mensagem é impressa.
				 * Se tiver, são impressos os Id´s e nomes dos pesquisadores.
				 */
				System.out.println("Listar os autores de um determinado artigo");
				System.out.println("Informe o Id do Artigo que você deseja consultar: ");
				ListaArtigos();
				System.out.print("Id: ");
				idArt = input.nextInt();
				try {
					artigoaux=verificaSeHaArtigo(idArt);
				}
				catch(ExcecaoNaoEncontrouNaLista e) {
					System.out.println("Não foi encontrado nenhum Artigo com esse Id.");
					break;
				}
				achou = 0;
				System.out.println("Autores do artigo "+ artigoaux.getTitulo()+": ");
				if(artigoaux.getPesquisadoresVinculados()>0) {
					pesq = artigoaux.getPesq();
					for(Pesquisador p:pesq){
						if(p!=null) {
							System.out.println(p.toString());
							achou=1;
						}
					}
				}
				
				if (achou==0) {
					System.out.println("Ainda não foram Vinculados Pesquisadores a este Artigo.");
				}
				break;
			case 13:
				/**
				 * nessa listagem, é solicitado o id do pesquisador e é percorrido o 
				 * array de projetos buscando se o pesquisador está presente em algum deles.
				 * Para isso, também deve ser percorrido o vetor de pesquisadores dentro da 
				 * classe projetos, e é aí onde verificamos se o pesquisador informado foi
				 * encontrado.
				 * Caso não seja encontrado em nenhum projeto, é impressa a mensagem com essa
				 * informação.
				 */
				System.out.println("Listar todos os projetos de um determinado pesquisador");
				System.out.println("Informe o Id do Pesquisador que você deseja consultar: ");
				ListaPesquisadores();
				System.out.print("Id: ");
				id = input.nextInt();
				try {
					pesquisadoraux=verificaSeHaPesquisador(id);
				}
				catch(ExcecaoNaoEncontrouNaLista e){
					System.out.println("Não foi encontrado nenhum Pesquisador com esse Id.");
					break;
				}
				System.out.println("Projetos de "+ pesquisadoraux.getNome()+": ");
				achou=0;
				for(Projeto p: projetos) {
					if(p.getPesquisadoresVinculados()>0) {
						pesq = p.getPesq();
						for(Pesquisador pes:pesq) {
							if(pes.equals(pesquisadoraux)) {
								System.out.println(p.toString());
								achou=1;
								break;
							}
						}
					}
				}
				if(achou==0) {
					System.out.println("Esse Pesquisador ainda não foi Vinculado a nenhum Projeto.");
				}
				break;
			case 14:
				/**
				 * nessa listagem, é verificado se a data final dos projetos do arrayList 
				 * é menor que a data atual. Para isso é usada a função dtEhMenorQueDtAtual,
				 * implementada na classe de Datas.
				 * Se a data for menor, é porque o projeto ja foi finalizado.
				 * Nesse caso, são impressos o nome do projeto e os nomes dos
				 * pesquisadores que o compõem.
				 */
				System.out.println("Listar os pesquisadores de projetos já finalizados");
				achou=0;
				for(Projeto p:projetos) {
					if(p.getDtFim().dataEhMenorQueDtAtual()) {
						achou=1;
						System.out.println("\nPesquisadores do " + p.toString() + ": ");
						if(p.getPesquisadoresVinculados()>0) {
							pesq = p.getPesq();
							for(Pesquisador pes:pesq) {
								if(pes!=null)
									System.out.println(pes.toString());
							}
						}
						else{
							System.out.println("Ainda não há nenhum Pesquisador vinculado.");
						}
					}
				}
				if(achou==0) {
					System.out.println("Ainda não há nenhum Projeto já finalizado.");
				}
				break;
			}
			System.out.println("\n0.Sair");
			System.out.println("1.Cadastrar novo Pesquisador");
			System.out.println("2.Cadastrar novo Projeto");
			System.out.println("3.Cadastrar novo Artigo");
			System.out.println("4.Vincular Pesquisador a um Projeto");
			System.out.println("5.Vincular Pesquisador a um Artigo");
			System.out.println("6.Remover Pesquisador de um Projeto");
			System.out.println("7.Remover Pesquisador de um Artigo");
			System.out.println("8.Alterar dados de Pesquisador");
			System.out.println("9.Alterar dados de Projeto");
			System.out.println("10.Alterar dados de Artigo");
			System.out.println("11.Listar pesquisadores de uma mesma Universidade");
			System.out.println("12.Listar os autores de um determinado artigo");
			System.out.println("13.Listar todos os projetos de um determinado pesquisador");
			System.out.println("14.Listar os pesquisadores de projetos já finalizados");
			opcao = input.nextInt();
		}
		/**
		 * Também é solicitado se o usuário deseja gravar os dados de volta no arquivo Lattes.txt,
		 * a fim de nao perder as alterações realizadas. 
		 */
		System.out.println("Deseja gravar os Dados no Arquivo texto? 1-SIM | 2-NAO");
		opcao = input.nextInt();
		if(opcao==1){
			PersistidorArquivoTexto pText = new PersistidorArquivoTexto();
			pText.escreveDados(pesquisadores, projetos, artigos);		
			System.out.println("Dados Gravados com Sucesso!");
		}
		System.out.println("Fim do Programa!");
		input.close();
	}
	public void ListaPesquisadores() {
		if(!pesquisadores.isEmpty()) {
			for(Pesquisador p:pesquisadores) {
				System.out.println(p.toString());
			}
		}
		else {
			System.out.println("Ainda não há nenhum Pesquisador Cadastrado!");
		}
	}
	public void ListaProjetos() {
		if(!projetos.isEmpty()) {
			for(Projeto p:projetos) {
				System.out.println(p.toString());
			}
		}
		else {
			System.out.println("Ainda não há nenhum Projeto Cadastrado!");
		}
	}
	public void ListaArtigos() {
		if(!artigos.isEmpty()) {
			for(Artigo a:artigos) {
				System.out.println(a.toString());
			}
		}	
		else {
			System.out.println("Ainda não há nenhum Artigo Cadastrado!");
		}
	}
	public Pesquisador verificaSeHaPesquisador(int id) throws ExcecaoNaoEncontrouNaLista {
		for(Pesquisador p:pesquisadores) {
			if(p.getId()==id) {
				return p;
			}
		}
		throw new ExcecaoNaoEncontrouNaLista();
	}
	public Pesquisador verificaSeHaPesquisadorComCPF(String cpf) {
		if(!pesquisadores.isEmpty()) {
			for(Pesquisador p:pesquisadores) {
				if(p.getCpf().equals(cpf)) {
					return p;
				}
			}
		}
		return null;
	}
	public Projeto verificaSeHaProjeto(int id) throws ExcecaoNaoEncontrouNaLista {
		for(Projeto p:projetos) {
			if(p.getId()==id) {
				return p;
			}
		}
		throw new ExcecaoNaoEncontrouNaLista();
	}
	public Artigo verificaSeHaArtigo(int id) throws ExcecaoNaoEncontrouNaLista {
		for(Artigo a:artigos) {
			if(a.getId()==id) {
				return a;
			}
		}
		throw new ExcecaoNaoEncontrouNaLista();
	}
}
