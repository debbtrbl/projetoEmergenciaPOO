package funcionarios;

import entidades.Funcionario;
import entidades.Leito;
import entidades.Medicamento;
import entidades.Paciente;
import entidades.Atendimento;
import entidades.Exame;
import entidades.Triagem;
import sistema.Principal;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Medico extends Funcionario {
    private String crm;
    private String especialidade;

    public Medico(String nome, String dataNascimento, String cpf, int matricula,
                  double salario, int ch, String crm, String especialidade) {
        super(nome, dataNascimento, cpf, matricula, salario, ch);
        this.crm = crm;
        this.especialidade = especialidade;
    }

    @Override
    public void dadosFuncionario() {
        System.out.println("Dados do Médico:");
        System.out.println("Nome: " + getNome());
        System.out.println("Nascimento: " + getDataNascimento());
        System.out.println("CPF: " + getCpf());
        System.out.println("Matrícula: " + getMatricula());
        System.out.println("Salário: R$ " + getSalario());
        System.out.println("Carga Horária: " + getCargaHorariaSemanal() + "h");
        System.out.println("CRM: " + crm);
        System.out.println("Especialidade: " + especialidade);
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
    Scanner scanner = new Scanner(System.in);
    public void atenderPaciente(Paciente paciente, Triagem triagem) {
        if (paciente == null || triagem == null) {
            System.out.println("Paciente ou triagem inválidos!");
            return;
        }

        System.out.println("\n🩺 Médico " + getNome() + " atendendo paciente " + paciente.getNome());
        
        // Mostrar dados da triagem
        System.out.println("\nDados da Triagem:");
        System.out.println("Classificação: " + triagem.getClassificacao());
        System.out.println("Sintomas: " + String.join(", ", triagem.getSintomas()));
        System.out.println("Pressão arterial: " + triagem.getPressaoArterial());
        System.out.println("Temperatura: " + triagem.getTemperatura() + "°C");
        
        // Realizar diagnóstico
        System.out.print("\nDigite o diagnóstico: ");
		String diagnostico = scanner.nextLine();
        triagem.setDiagnostico(diagnostico);
        
        // Opções de tratamento
        int opcao;
        do {
            System.out.println("\nOpções de Tratamento:");
            System.out.println("1. Prescrever Medicamento");
            System.out.println("2. Solicitar Exame");
            System.out.println("3. Internar Paciente");
            System.out.println("4. Finalizar Atendimento");
            System.out.print("Escolha uma opção: ");
            
            try {
                opcao = Integer.parseInt(scanner.nextLine());
                
                switch (opcao) {
                    case 1:
                        prescreverMedicamento(paciente);
                        break;
                    case 2:
                        solicitarExame(paciente);
                        break;
                    case 3:
                        internarPaciente(paciente);
                        break;
                    case 4:
                        System.out.println("Atendimento finalizado.");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido!");
                opcao = 0;
            }
        } while (opcao != 4);
        
        // Registrar atendimento
        String dataHoje = LocalDate.now().toString();
        Atendimento atendimento = new Atendimento(dataHoje, this, paciente, triagem);
        atendimento.gerarReceituario();
        atendimento.darAlta();
        
        paciente.adicionarAtendimento(atendimento);
        System.out.println("\nAtendimento registrado com sucesso para o paciente " + paciente.getNome());
    }
    
    private void prescreverMedicamento(Paciente paciente) {
        System.out.println("\nPrescrição de Medicamento");
        System.out.print("Nome do medicamento: ");
        String nomeMed = scanner.nextLine();
        System.out.print("Dosagem: ");
        String dosagem = scanner.nextLine();
        System.out.print("Frequência (ex: 8/8 horas): ");
        String frequencia = scanner.nextLine();
        System.out.print("Duração do tratamento (ex: 7 dias): ");
        String duracao = scanner.nextLine();
        
        Medicamento medicamento = new Medicamento(nomeMed, dosagem);
        System.out.println("\nMedicamento " + nomeMed + " prescrito para " + paciente.getNome());
        System.out.println("Como tomar: " + dosagem + " a cada " + frequencia + " durante " + duracao);
        paciente.administrarMedicamento(medicamento);
    }
    
    private void solicitarExame(Paciente paciente) {
        System.out.println("\n🔬 Solicitação de Exame");
        System.out.print("Nome do exame: ");
        String nomeExame = scanner.nextLine();
        System.out.print("Descrição/observações: ");
        String observacoes = scanner.nextLine();
        
        if (!paciente.getExames().isEmpty()) {
            Enfermeiro enfermeiro = paciente.getExames().get(0).getResponsavel();
            Exame exame = new Exame(nomeExame, enfermeiro, paciente);
            paciente.adicionarExame(exame);
            System.out.println("Exame " + nomeExame + " solicitado para " + paciente.getNome());
        } else {
            System.out.println("Nenhum enfermeiro disponível para realizar o exame.");
        }  
    }
    private void internarPaciente(Paciente paciente) {
        System.out.println("\n=== SOLICITAR INTERNAÇÃO ===");
        
        if (Principal.internacao.getLeitosDisponiveis().isEmpty()) {
            System.out.println("Não há leitos disponíveis no momento.");
            return;
        }
        
        System.out.println("\nLeitos disponíveis:");
        List<Leito> leitosDisponiveis = Principal.internacao.getLeitosDisponiveis();
        for (int i = 0; i < leitosDisponiveis.size(); i++) {
            System.out.println((i+1) + ". " + leitosDisponiveis.get(i));
        }
        
        System.out.print("Selecione o leito para internação: ");
        int idxLeito = Integer.parseInt(scanner.nextLine()) - 1;
        
        if (idxLeito < 0 || idxLeito >= leitosDisponiveis.size()) {
            System.out.println("Leito inválido!");
            return;
        }
        
        Leito leitoSelecionado = leitosDisponiveis.get(idxLeito);
        
        System.out.print("Confirmar internação no leito " + leitoSelecionado.getNumero() + "? (S/N): ");
        String confirmacao = scanner.nextLine();
        
        if (confirmacao.equalsIgnoreCase("S")) {
            leitoSelecionado.ocupar(paciente);
            
            Principal.internacao.getLeitosDisponiveis().remove(leitoSelecionado);
            Principal.internacao.getLeitosOcupados().add(leitoSelecionado);
            
            System.out.println("Paciente " + paciente.getNome() + " internado no leito " + leitoSelecionado.getNumero());
            
            System.out.print("Motivo da internação: ");
            String motivo = scanner.nextLine();
            paciente.adicionarObservacao("Internado no leito " + leitoSelecionado.getNumero() + " - Motivo: " + motivo);
        } else {
            System.out.println("Internação cancelada.");
        }
    }
}
