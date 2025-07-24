package sistema;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import departamentos.*;
import entidades.*;
import funcionarios.*;
import util.DadosInvalidosException;
import util.Departamentos;
import util.FuncionarioNaoEncontradoException;
import util.MedicamentoNaoDisponivelException;
import util.PacienteNaoEncontradoException;
import util.SistemaHospitalarException;

public class Principal {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Paciente> pacientes = new ArrayList<>();
    private static List<Medico> medicos = new ArrayList<>();
    private static List<Enfermeiro> enfermeiros = new ArrayList<>();
    private static List<Farmaceutico> farmaceuticos = new ArrayList<>();
    private static List<AuxiliarLimpeza> auxiliaresLimpeza = new ArrayList<>();
    private static List<Medicamento> medicamentos = new ArrayList<>();
    private static List<Leito> leitos = new ArrayList<>();
    
    private static Enfermaria enfermaria;
    public static Internacao internacao;
    private static Patologia patologia;

    public static void main(String[] args) {
        try {
            inicializarDadosTeste();
            exibirMenuPrincipal();
        } catch (SistemaHospitalarException e) {
            System.err.println("Erro no sistema: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
        } finally {
            scanner.close();
            System.out.println("Sistema encerrado.");
        }
    }

    private static void inicializarDadosTeste() {
        try {
            medicos.add(new Medico("Eric Sales", "15/03/1975", "12345678901", 1001, 15000, 40, "CRM/SP 12345", "Clínico Geral"));
            enfermeiros.add(new Enfermeiro("Isabelly Remígio", "20/05/1980", "23456789012", 2001, 8000, 36, 54321, Departamentos.ENFERMARIA));
            farmaceuticos.add(new Farmaceutico("Débora Alves", "10/11/1985", "34567890123", 3001, 9000, 40, 98765));
            auxiliaresLimpeza.add(new AuxiliarLimpeza("Gustavo Lima", "05/07/1990", "45678901234", 4001, 2500, 44, "Manhã", Departamentos.INTERNACAO));
            
            pacientes.add(new Paciente("Maria da Silva", "01/01/1990", "11111111111"));
            pacientes.add(new Paciente("Pedro Alves", "02/02/1995", "22222222222"));
            
            medicamentos.add(new Medicamento("Paracetamol", "500mg"));
            medicamentos.add(new Medicamento("Dipirona", "1g"));
            medicamentos.add(new Medicamento("Amoxicilina", "250mg"));
            
            for (int i = 1; i <= 10; i++) {
                leitos.add(new Leito(i));
            }
            
            enfermaria = new Enfermaria(farmaceuticos.get(0));
            for (Enfermeiro enf : enfermeiros) {
                enfermaria.adicionarEnfermeiro(enf);
            }
            for (Medicamento med : medicamentos) {
                enfermaria.adicionarMedicamento(med);
            }
            
            internacao = new Internacao(leitos, medicos.get(0), enfermeiros, auxiliaresLimpeza.get(0));
            patologia = new Patologia(enfermeiros);
        } catch (Exception e) {
            throw new SistemaHospitalarException("Falha ao inicializar dados de teste: " + e.getMessage());
        }
    }

    private static void exibirMenuPrincipal() {
        while (true) {
            try {
                System.out.println("\n=== HOSPITAL SANTA ORIENTAÇÃO - EMERGÊNCIA ===");
                System.out.println("1. Atendimento ao Paciente");
                System.out.println("2. Enfermaria");
                System.out.println("3. Internação");
                System.out.println("4. Patologia");
                System.out.println("5. Cadastros");
                System.out.println("0. Sair");
                System.out.print("Escolha uma opção: ");
                
                int opcao = lerInteiro();
                
                switch (opcao) {
                    case 1:
                        menuAtendimento();
                        break;
                    case 2:
                        menuEnfermaria();
                        break;
                    case 3:
                        menuInternacao();
                        break;
                    case 4:
                        menuPatologia();
                        break;
                    case 5:
                        menuCadastros();
                        break;
                    case 0:
                        System.out.println("Saindo do sistema...");
                        return;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (SistemaHospitalarException e) {
                System.err.println("Erro: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Erro inesperado: " + e.getMessage());
            }
        }
    }

    private static void menuAtendimento() {
        while (true) {
            try {
                System.out.println("\n=== ATENDIMENTO AO PACIENTE ===");
                System.out.println("1. Atender Paciente");
                System.out.println("2. Ver Histórico do Paciente");
                System.out.println("0. Voltar");
                System.out.print("Escolha uma opção: ");
                
                int opcao = lerInteiro();
                
                switch (opcao) {
                    case 1:
                        atenderPaciente();
                        break;
                    case 2:
                        verHistoricoPaciente();
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (SistemaHospitalarException e) {
                System.err.println("Erro no atendimento: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Erro inesperado: " + e.getMessage());
            }
        }
    }

    private static void atenderPaciente() {
        System.out.println("\n=== ATENDER PACIENTE ===");
        
        if (pacientes.isEmpty()) {
            throw new PacienteNaoEncontradoException("Nenhum paciente cadastrado");
        }
        
        //  paciente
        System.out.println("Pacientes cadastrados:");
        for (int i = 0; i < pacientes.size(); i++) {
            System.out.println((i+1) + ". " + pacientes.get(i).getNome());
        }
        System.out.print("Selecione o paciente: ");
        int idxPaciente = lerInteiro() - 1;
        
        if (idxPaciente < 0 || idxPaciente >= pacientes.size()) {
            throw new PacienteNaoEncontradoException("Índice " + (idxPaciente+1));
        }
        
        Paciente paciente = pacientes.get(idxPaciente);
        
        // triagem antes do atendimento 
        if (enfermeiros.isEmpty()) {
            throw new FuncionarioNaoEncontradoException("Nenhum enfermeiro cadastrado para triagem");
        }
        
        System.out.println("\nEnfermeiros disponíveis para triagem:");
        for (int i = 0; i < enfermeiros.size(); i++) {
            System.out.println((i+1) + ". " + enfermeiros.get(i).getNome());
        }
        System.out.print("Selecione o enfermeiro para triagem: ");
        int idxEnfermeiro = lerInteiro() - 1;
        
        if (idxEnfermeiro < 0 || idxEnfermeiro >= enfermeiros.size()) {
            throw new FuncionarioNaoEncontradoException("Índice " + (idxEnfermeiro+1));
        }
        
        Enfermeiro enfermeiro = enfermeiros.get(idxEnfermeiro);
        Triagem triagem = enfermeiro.acompanharPaciente(paciente);
        
        System.out.println("\nTriagem concluída:");
        System.out.println("Classificação: " + triagem.getClassificacao());
        System.out.println("Sintomas: " + String.join(", ", triagem.getSintomas()));
        
        // seleciona um médico
        if (medicos.isEmpty()) {
            throw new FuncionarioNaoEncontradoException("Nenhum médico cadastrado");
        }
        
        System.out.println("\nMédicos disponíveis:");
        for (int i = 0; i < medicos.size(); i++) {
            System.out.println((i+1) + ". " + medicos.get(i).getNome() + " - " + medicos.get(i).getEspecialidade());
        }
        System.out.print("Selecione o médico: ");
        int idxMedico = lerInteiro() - 1;
        
        if (idxMedico < 0 || idxMedico >= medicos.size()) {
            throw new FuncionarioNaoEncontradoException("Índice " + (idxMedico+1));
        }
        
        Medico medico = medicos.get(idxMedico);
        
        medico.atenderPaciente(paciente, triagem);
    }

    private static void verHistoricoPaciente() {
        System.out.println("\n=== HISTÓRICO DO PACIENTE ===");
        
        if (pacientes.isEmpty()) {
            throw new PacienteNaoEncontradoException("Nenhum paciente cadastrado");
        }
        
        System.out.println("Pacientes cadastrados:");
        for (int i = 0; i < pacientes.size(); i++) {
            System.out.println((i+1) + ". " + pacientes.get(i).getNome());
        }
        System.out.print("Selecione o paciente: ");
        int idxPaciente = lerInteiro() - 1;
        
        if (idxPaciente < 0 || idxPaciente >= pacientes.size()) {
            throw new PacienteNaoEncontradoException("Índice " + (idxPaciente+1));
        }
        
        Paciente paciente = pacientes.get(idxPaciente);
        
        System.out.println("\nDados do paciente:");
        paciente.dadosPaciente();
        
        System.out.println("\nHistórico de atendimentos:");
        paciente.listarAtendimentos();
        
    }

    private static void menuEnfermaria() {
        while (true) {
            try {
                System.out.println("\n=== ENFERMARIA ===");
                System.out.println("1. Listar Enfermeiros");
                System.out.println("2. Listar Medicamentos");
                System.out.println("3. Administrar Medicamento");
                System.out.println("0. Voltar");
                System.out.print("Escolha uma opção: ");
                
                int opcao = lerInteiro();
                
                switch (opcao) {
                    case 1:
                        enfermaria.listarEnfermeiros();
                        break;
                    case 2:
                        System.out.println("\nMedicamentos disponíveis:");
                        for (Medicamento med : enfermaria.listarMedicamentos()) {
                            System.out.println("- " + med.getNome() + " (" + med.getDosagem() + ")");
                        }
                        break;
                    case 3:
                        administrarMedicamento();
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (SistemaHospitalarException e) {
                System.err.println("Erro na enfermaria: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Erro inesperado: " + e.getMessage());
            }
        }
    }

    private static void administrarMedicamento() {
        System.out.println("\n=== ADMINISTRAR MEDICAMENTO ===");

        if (pacientes.isEmpty()) {
            throw new PacienteNaoEncontradoException("Nenhum paciente cadastrado");
        }

        System.out.println("Pacientes disponíveis:");
        for (int i = 0; i < pacientes.size(); i++) {
            System.out.println((i+1) + ". " + pacientes.get(i).getNome());
        }
        System.out.print("Selecione o paciente: ");
        int idxPaciente = lerInteiro() - 1;
        
        if (idxPaciente < 0 || idxPaciente >= pacientes.size()) {
            throw new PacienteNaoEncontradoException("Índice " + (idxPaciente+1));
        }

        List<Medicamento> meds = enfermaria.listarMedicamentos();
        if (meds.isEmpty()) {
            throw new MedicamentoNaoDisponivelException("Nenhum medicamento cadastrado");
        }

        System.out.println("\nMedicamentos disponíveis:");
        for (int i = 0; i < meds.size(); i++) {
            System.out.println((i+1) + ". " + meds.get(i).getNome() + " (" + meds.get(i).getDosagem() + ")");
        }
        System.out.print("Selecione o medicamento: ");
        int idxMedicamento = lerInteiro() - 1;
        
        if (idxMedicamento < 0 || idxMedicamento >= meds.size()) {
            throw new MedicamentoNaoDisponivelException("Índice " + (idxMedicamento+1));
        }

        if (enfermeiros.isEmpty()) {
            throw new FuncionarioNaoEncontradoException("Nenhum enfermeiro cadastrado");
        }

        System.out.println("\nEnfermeiros disponíveis:");
        for (int i = 0; i < enfermeiros.size(); i++) {
            System.out.println((i+1) + ". " + enfermeiros.get(i).getNome());
        }
        System.out.print("Selecione o enfermeiro: ");
        int idxEnfermeiro = lerInteiro() - 1;
        
        if (idxEnfermeiro < 0 || idxEnfermeiro >= enfermeiros.size()) {
            throw new FuncionarioNaoEncontradoException("Índice " + (idxEnfermeiro+1));
        }
        
        Paciente paciente = pacientes.get(idxPaciente);
        Medicamento medicamento = meds.get(idxMedicamento);
        Enfermeiro enfermeiro = enfermeiros.get(idxEnfermeiro);
        
        enfermeiro.administrarMedicamento(paciente, medicamento);
    }

    private static void menuInternacao() {
        while (true) {
            try {
                System.out.println("\n=== INTERNAÇÃO ===");
                System.out.println("1. Ocupar Leito");
                System.out.println("2. Limpar Leitos");
                System.out.println("3. Listar Leitos Disponíveis");
                System.out.println("4. Listar Leitos Ocupados");
                System.out.println("0. Voltar");
                System.out.print("Escolha uma opção: ");
                
                int opcao = lerInteiro();
                
                switch (opcao) {
                    case 1:
                        internacao.ocuparLeito();
                        break;
                    case 2:
                        internacao.limparLeito();
                        break;
                    case 3:
                        System.out.println("\nLeitos disponíveis:");
                        for (Leito leito : internacao.getLeitosDisponiveis()) {
                            System.out.println("- " + leito);
                        }
                        break;
                    case 4:
                        System.out.println("\nLeitos ocupados:");
                        for (Leito leito : internacao.getLeitosOcupados()) {
                            System.out.println("- " + leito);
                        }
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (SistemaHospitalarException e) {
                System.err.println("Erro na internação: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Erro inesperado: " + e.getMessage());
            }
        }
    }

    private static void menuPatologia() {
        while (true) {
            try {
                System.out.println("\n=== PATOLOGIA ===");
                System.out.println("1. Registrar Exame");
                System.out.println("2. Listar Exames");
                System.out.println("3. Listar Enfermeiros");
                System.out.println("0. Voltar");
                System.out.print("Escolha uma opção: ");
                
                int opcao = lerInteiro();
                
                switch (opcao) {
                    case 1:
                        registrarExame();
                        break;
                    case 2:
                        System.out.println("\nExames registrados:");
                        for (Exame exame : patologia.listarExames()) {
                            System.out.println("- " + exame.getNome() + " (Paciente: " + exame.getPaciente().getNome() + ")");
                        }
                        break;
                    case 3:
                        System.out.println("\nEnfermeiros de Patologia:");
                        for (Enfermeiro enf : patologia.listarEnfermeiros()) {
                            System.out.println("- " + enf.getNome());
                        }
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (SistemaHospitalarException e) {
                System.err.println("Erro na patologia: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Erro inesperado: " + e.getMessage());
            }
        }
    }

    private static void registrarExame() {
        System.out.println("\n=== REGISTRAR EXAME ===");
        
        if (pacientes.isEmpty()) {
            throw new PacienteNaoEncontradoException("Nenhum paciente cadastrado");
        }
        
        System.out.println("Pacientes disponíveis:");
        for (int i = 0; i < pacientes.size(); i++) {
            System.out.println((i+1) + ". " + pacientes.get(i).getNome());
        }
        System.out.print("Selecione o paciente: ");
        int idxPaciente = lerInteiro() - 1;
        
        if (idxPaciente < 0 || idxPaciente >= pacientes.size()) {
            throw new PacienteNaoEncontradoException("Índice " + (idxPaciente+1));
        }
        
        if (enfermeiros.isEmpty()) {
            throw new FuncionarioNaoEncontradoException("Nenhum enfermeiro cadastrado");
        }
        
        System.out.println("\nEnfermeiros disponíveis:");
        for (int i = 0; i < enfermeiros.size(); i++) {
            System.out.println((i+1) + ". " + enfermeiros.get(i).getNome());
        }
        System.out.print("Selecione o enfermeiro responsável: ");
        int idxEnfermeiro = lerInteiro() - 1;
        
        if (idxEnfermeiro < 0 || idxEnfermeiro >= enfermeiros.size()) {
            throw new FuncionarioNaoEncontradoException("Índice " + (idxEnfermeiro+1));
        }
        
        System.out.print("\nNome do exame: ");
        String nomeExame = scanner.nextLine();
        
        if (nomeExame == null || nomeExame.trim().isEmpty()) {
            throw new DadosInvalidosException("Nome do exame");
        }
        
        Paciente paciente = pacientes.get(idxPaciente);
        Enfermeiro enfermeiro = enfermeiros.get(idxEnfermeiro);
        
        Exame exame = new Exame(nomeExame, enfermeiro, paciente);
        patologia.registrarExame(exame);
        
        System.out.println("Exame registrado com sucesso!");
    }

    private static void menuCadastros() {
        while (true) {
            try {
                System.out.println("\n=== CADASTROS ===");
                System.out.println("1. Cadastrar Paciente");
                System.out.println("2. Cadastrar Médico");
                System.out.println("3. Cadastrar Enfermeiro");
                System.out.println("4. Cadastrar Farmacêutico");
                System.out.println("5. Cadastrar Auxiliar de Limpeza");
                System.out.println("6. Cadastrar Medicamento");
                System.out.println("0. Voltar");
                System.out.print("Escolha uma opção: ");
                
                int opcao = lerInteiro();
                
                switch (opcao) {
                    case 1:
                        cadastrarPaciente();
                        break;
                    case 2:
                        cadastrarMedico();
                        break;
                    case 3:
                        cadastrarEnfermeiro();
                        break;
                    case 4:
                        cadastrarFarmaceutico();
                        break;
                    case 5:
                        cadastrarAuxiliarLimpeza();
                        break;
                    case 6:
                        cadastrarMedicamento();
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (SistemaHospitalarException e) {
                System.err.println("Erro nos cadastros: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Erro inesperado: " + e.getMessage());
            }
        }
    }

    private static void cadastrarPaciente() {
        System.out.println("\n=== CADASTRAR PACIENTE ===");
        
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        if (nome == null || nome.trim().isEmpty()) {
            throw new DadosInvalidosException("Nome");
        }
        
        System.out.print("Data de Nascimento (DD/MM/AAAA): ");
        String dataNascimento = scanner.nextLine();
        if (dataNascimento == null || dataNascimento.trim().isEmpty()) {
            throw new DadosInvalidosException("Data de Nascimento");
        }
        
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new DadosInvalidosException("CPF");
        }
        
        Paciente paciente = new Paciente(nome, dataNascimento, cpf);
        pacientes.add(paciente);
        
        System.out.println("Paciente cadastrado com sucesso!");
    }

    private static void cadastrarMedico() {
        System.out.println("\n=== CADASTRAR MÉDICO ===");
        
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        if (nome == null || nome.trim().isEmpty()) {
            throw new DadosInvalidosException("Nome");
        }
        
        System.out.print("Data de Nascimento (DD/MM/AAAA): ");
        String dataNascimento = scanner.nextLine();
        if (dataNascimento == null || dataNascimento.trim().isEmpty()) {
            throw new DadosInvalidosException("Data de Nascimento");
        }
        
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new DadosInvalidosException("CPF");
        }
        
        System.out.print("Matrícula: ");
        int matricula = lerInteiro();
        
        System.out.print("Salário: ");
        double salario = lerDouble();
        
        System.out.print("Carga Horária Semanal: ");
        int cargaHoraria = lerInteiro();
        
        System.out.print("CRM: ");
        String crm = scanner.nextLine();
        if (crm == null || crm.trim().isEmpty()) {
            throw new DadosInvalidosException("CRM");
        }
        
        System.out.print("Especialidade: ");
        String especialidade = scanner.nextLine();
        if (especialidade == null || especialidade.trim().isEmpty()) {
            throw new DadosInvalidosException("Especialidade");
        }
        
        Medico medico = new Medico(nome, dataNascimento, cpf, matricula, salario, cargaHoraria, crm, especialidade);
        medicos.add(medico);
        
        System.out.println("Médico cadastrado com sucesso!");
    }

    private static void cadastrarEnfermeiro() {
        System.out.println("\n=== CADASTRAR ENFERMEIRO ===");
        
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        if (nome == null || nome.trim().isEmpty()) {
            throw new DadosInvalidosException("Nome");
        }
        
        System.out.print("Data de Nascimento (DD/MM/AAAA): ");
        String dataNascimento = scanner.nextLine();
        if (dataNascimento == null || dataNascimento.trim().isEmpty()) {
            throw new DadosInvalidosException("Data de Nascimento");
        }
        
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new DadosInvalidosException("CPF");
        }
        
        System.out.print("Matrícula: ");
        int matricula = lerInteiro();
        
        System.out.print("Salário: ");
        double salario = lerDouble();
        
        System.out.print("Carga Horária Semanal: ");
        int cargaHoraria = lerInteiro();
        
        System.out.print("COREN: ");
        int coren = lerInteiro();
        
        System.out.println("Departamentos disponíveis:");
        for (Departamentos depto : Departamentos.values()) {
            System.out.println("- " + depto);
        }
        System.out.print("Departamento: ");
        String deptoStr = scanner.nextLine().toUpperCase();
        Departamentos departamento;
        
        try {
            departamento = Departamentos.valueOf(deptoStr);
        } catch (IllegalArgumentException e) {
            throw new DadosInvalidosException("Departamento");
        }
        
        Enfermeiro enfermeiro = new Enfermeiro(nome, dataNascimento, cpf, matricula, salario, cargaHoraria, coren, departamento);
        enfermeiros.add(enfermeiro);
        
        if (departamento == Departamentos.ENFERMARIA) {
            enfermaria.adicionarEnfermeiro(enfermeiro);
        } else if (departamento == Departamentos.PATOLOGIA) {
            patologia.adicionarEnfermeiro(enfermeiro);
        }
        
        System.out.println("Enfermeiro cadastrado com sucesso!");
    }

    private static void cadastrarFarmaceutico() {
        System.out.println("\n=== CADASTRAR FARMACÊUTICO ===");
        
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        if (nome == null || nome.trim().isEmpty()) {
            throw new DadosInvalidosException("Nome");
        }
        
        System.out.print("Data de Nascimento (DD/MM/AAAA): ");
        String dataNascimento = scanner.nextLine();
        if (dataNascimento == null || dataNascimento.trim().isEmpty()) {
            throw new DadosInvalidosException("Data de Nascimento");
        }
        
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new DadosInvalidosException("CPF");
        }
        
        System.out.print("Matrícula: ");
        int matricula = lerInteiro();
        
        System.out.print("Salário: ");
        double salario = lerDouble();
        
        System.out.print("Carga Horária Semanal: ");
        int cargaHoraria = lerInteiro();
        
        System.out.print("CRF: ");
        int crf = lerInteiro();
        
        Farmaceutico farmaceutico = new Farmaceutico(nome, dataNascimento, cpf, matricula, salario, cargaHoraria, crf);
        farmaceuticos.add(farmaceutico);
        
        System.out.println("Farmacêutico cadastrado com sucesso!");
    }

    private static void cadastrarAuxiliarLimpeza() {
        System.out.println("\n=== CADASTRAR AUXILIAR DE LIMPEZA ===");
        
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        if (nome == null || nome.trim().isEmpty()) {
            throw new DadosInvalidosException("Nome");
        }
        
        System.out.print("Data de Nascimento (DD/MM/AAAA): ");
        String dataNascimento = scanner.nextLine();
        if (dataNascimento == null || dataNascimento.trim().isEmpty()) {
            throw new DadosInvalidosException("Data de Nascimento");
        }
        
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new DadosInvalidosException("CPF");
        }
        
        System.out.print("Matrícula: ");
        int matricula = lerInteiro();
        
        System.out.print("Salário: ");
        double salario = lerDouble();
        
        System.out.print("Carga Horária Semanal: ");
        int cargaHoraria = lerInteiro();
        
        System.out.print("Turno: ");
        String turno = scanner.nextLine();
        if (turno == null || turno.trim().isEmpty()) {
            throw new DadosInvalidosException("Turno");
        }
        
        System.out.println("Áreas disponíveis:");
        for (Departamentos area : Departamentos.values()) {
            System.out.println("- " + area);
        }
        System.out.print("Área Responsável: ");
        String areaStr = scanner.nextLine().toUpperCase();
        Departamentos area;
        
        try {
            area = Departamentos.valueOf(areaStr);
        } catch (IllegalArgumentException e) {
            throw new DadosInvalidosException("Área Responsável");
        }
        
        AuxiliarLimpeza auxiliar = new AuxiliarLimpeza(nome, dataNascimento, cpf, matricula, salario, cargaHoraria, turno, area);
        auxiliaresLimpeza.add(auxiliar);
        
        System.out.println("Auxiliar de Limpeza cadastrado com sucesso!");
    }

    private static void cadastrarMedicamento() {
        System.out.println("\n=== CADASTRAR MEDICAMENTO ===");
        
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        if (nome == null || nome.trim().isEmpty()) {
            throw new DadosInvalidosException("Nome");
        }
        
        System.out.print("Dosagem: ");
        String dosagem = scanner.nextLine();
        if (dosagem == null || dosagem.trim().isEmpty()) {
            throw new DadosInvalidosException("Dosagem");
        }
        
        Medicamento medicamento = new Medicamento(nome, dosagem);
        medicamentos.add(medicamento);
        enfermaria.adicionarMedicamento(medicamento);
        
        System.out.println("Medicamento cadastrado com sucesso!");
    }

    // Métodos auxiliares para leitura segura de dados
    private static int lerInteiro() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Valor inválido. Digite um número inteiro: ");
            }
        }
    }

    private static double lerDouble() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Valor inválido. Digite um número decimal: ");
            }
        }
    }
}