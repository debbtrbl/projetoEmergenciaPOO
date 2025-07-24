package entidades;

public class Medicamento {
    private String nome;
    private String dosagem;

    public Medicamento(String nome, String dosagem) {
        this.nome = nome;
        this.dosagem = dosagem;
    }

    public String getNome() {
        return nome;
    }
    public String getDosagem() {
        return dosagem;
    }

    public void aplicar() {
        System.out.println("Aplicando medicamento: " + nome + " - Dosagem: " + dosagem);
    }

	public void setDosagem(String dosagem) {
		this.dosagem = dosagem;
		
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
