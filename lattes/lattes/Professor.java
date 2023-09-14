package lattes;

public class Professor extends Pesquisador{
	public Professor(int id, String nome, String cpf, String area, String universidade) {
		super(id, nome, cpf, area, universidade);
	}
	@Override
	public String toString() {
		return "Pesquisador "+this.getId()+": "+ this.getNome() + " - Professor(a)";
	}
}
