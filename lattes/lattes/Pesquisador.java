package lattes;

public class Pesquisador {
	private int id;
	private String nome;
	private String cpf;
	private String areaAcademica;
	private String universidade;
	public Pesquisador(int id, String nome, String cpf, String area, String universidade){
		this.id=id;
		this.nome = nome;
		this.cpf = cpf;
		this.areaAcademica = area;
		this.universidade = universidade;
	}
	public int getId() {
		return id;
	}
	public void setId(int i) {
		this.id = i;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String n) {
		this.nome = n;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String c) {
		this.cpf = c;
	}
	public String getArea() {
		return areaAcademica;
	}
	public void setArea(String a) {
		this.areaAcademica = a;
	}
	public String getUniversidade() {
		return universidade;
	}
	public void setUniversidade(String u) {
		this.universidade = u;
	}
	public void alteraDados(String nome, String cpf, String area, String universidade) {
		this.setNome(nome);
		this.setCpf(cpf);
		this.setArea(area);
		this.setUniversidade(universidade);
	}
	public String toString() {
		return "Pesquisador "+this.getId()+": "+ this.getNome();
	}
}
