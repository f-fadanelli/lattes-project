package lattes;

public class Aluno extends Pesquisador {
	public Aluno(int id, String nome, String cpf, String area, String universidade) {
		super(id, nome, cpf, area, universidade);
	}
	
	@Override
	public String toString() {
		return "Pesquisador "+this.getId()+": "+ this.getNome()+" - Aluno(a)";
	}
}
