package entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import funcionarios.Enfermeiro;
import util.Classificavel;

public class Triagem implements Classificavel{
	private Paciente paciente;
	private Enfermeiro enfermeiro;
	private String pressaoArterial;
	private float temperatura;
	private int oximetro;
	private List<String> sintomas = new ArrayList<>();
	private String classificacao;
	private String diagnostico;
	
	public Triagem(Paciente paciente, Enfermeiro enfermeiro, String pressaoArterial, float temperatura, int oximetro,
			List<String> sintomas, String classificacao) {
		this.paciente = paciente;
		this.enfermeiro = enfermeiro;
		this.pressaoArterial = pressaoArterial;
		this.temperatura = temperatura;
		this.oximetro = oximetro;
		this.sintomas = sintomas;
		this.classificacao = classificacao;
	}
	
	private static final Scanner entrada = new Scanner(System.in);

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Enfermeiro getEnfermeiro() {
		return enfermeiro;
	}

	public void setEnfermeiro(Enfermeiro enfermeiro) {
		this.enfermeiro = enfermeiro;
	}

	public String getPressaoArterial() {
		return pressaoArterial;
	}

	public void setPressaoArterial(String pressaoArterial) {
		this.pressaoArterial = pressaoArterial;
	}

	public float getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(float temperatura) {
		this.temperatura = temperatura;
	}

	public int getOximetro() {
		return oximetro;
	}

	public void setOximetro(int oximetro) {
		this.oximetro = oximetro;
	}

	public List<String> getSintomas() {
		return sintomas;
	}

	public void setSintomas(List<String> sintomas) {
		this.sintomas = sintomas;
	}

	public String getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}
	
	public String getDiagnostico() {
	    return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
	    this.diagnostico = diagnostico;
	}

	@Override
	public void classificar() {
	    if (temperatura > 39.0 || oximetro < 90) {
	        this.classificacao = "vermelho";
	    } else if (temperatura > 37.5 || oximetro < 95) {
	        this.classificacao = "amarelo";
	    } else {
	        this.classificacao = "verde";
	    }
	}

	
	public void medirTemperatura() {
		System.out.print("Temperatura (°C): ");
		this.temperatura = entrada.nextFloat();
	}

	public void medirPressaoArterial() {
		System.out.print("Pressão arterial (ex: 12/8): ");
		this.pressaoArterial = entrada.next(); 
	}

	public void medirOxigenacao() {
		System.out.print("Oxigenação (%): ");
		this.oximetro = entrada.nextInt();
	}

	
	public void adicionarSintomas() {
	    System.out.println("Sintomas do paciente (digite 'fim' para encerrar):");
	    while (true) {
	        String sintoma = entrada.nextLine().trim();
	        if (sintoma.equalsIgnoreCase("fim")) {
	            break;
	        }
	        if (!sintoma.isEmpty()) {
	            this.sintomas.add(sintoma);
	        }
	    }
	}
	
}
