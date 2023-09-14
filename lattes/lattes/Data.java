package lattes;
import java.util.Calendar;
public class Data {
	private int dia;
	private int mes;
	private int ano;
	
	public Data() {
		Calendar calendario = Calendar.getInstance();
		dia = calendario.get(Calendar.DATE);
		mes = calendario.get(Calendar.MONTH) + 1;
		ano = calendario.get(Calendar.YEAR);
	}
	
	public Data(int d, int m, int a) {
		if((d<=0) ||
		   (d >= 30 && m == 2 && a%4==0)||
		   (d >= 29 && m == 2 && a%4!=0)||
		   (d >= 31 && (m == 4 || m == 6 || m == 9 || m == 11 ))||
		   (d >= 32)||
		   (m <= 0)||
		   (m >= 13)){
				
			}
		else {
			dia = d;
			mes = m;
			ano = a;
		}
	}
	
	public int getDia() {
		return dia;
	}
	
	public void setDia(int d) {
		this.dia = d;
	}
	
	public int getMes() {
		return mes;
	}
	
	public void setMes(int m) {
		this.mes = m;
	}
	
	public int getAno() {
		return ano;
	}
	
	public void setAno(int a) {
		this.ano = a;
	}
	
	public String toString() {
		if(dia > 0 && mes >0 && ano > 0) {
			if(dia<10 && mes>=10) {
				return "0"+dia + "/" + mes + "/" + ano;
			}
			else if(dia>=10 && mes<10) {
				return dia + "/0" + mes + "/" + ano;
			}
			else if(dia<10 && mes<10) {
				return "0"+dia + "/0" + mes + "/" + ano;
			}
			else {
				return dia + "/" + mes + "/" + ano;
			}
		}
		else
			return "Data Inválida";
	}
	
	public boolean dataEhMenorQueDtAtual() {
		Data dtAtual = new Data();
		if((this.getAno()<dtAtual.getAno()) ||
		   (this.getAno()==dtAtual.getAno() && this.getMes()<dtAtual.getMes())||
		   (this.getAno()==dtAtual.getAno() && this.getMes()==dtAtual.getMes() && this.getDia()<dtAtual.getDia())) {
			return true;
		}
		else
			return false;
	}
	public boolean dataEhMenorQueDt(Data dt) {
		if((this.getAno()<dt.getAno()) ||
		   (this.getAno()==dt.getAno() && this.getMes()<dt.getMes())||
		   (this.getAno()==dt.getAno() && this.getMes()==dt.getMes() && this.getDia()<=dt.getDia())) {
			return true;
		}
		else
			return false;
	}
}
