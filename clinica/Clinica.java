package clinica;

import java.util.ArrayList;
import java.util.Scanner;

public class Clinica {

    public static void main(String[] args) {
        ArrayList<Paciente> pacientesCadastrados = new ArrayList<>();
        ArrayList<Agendamento> agendamentos = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Cadastrar Paciente");
            System.out.println("2. Ver Pacientes Cadastrados");
            System.out.println("3. Ver Agendamentos");
            System.out.println("4. Sair");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    cadastrarPaciente(scanner, pacientesCadastrados);
                    break;
                case 2:
                    verPacientes(pacientesCadastrados);
                    solicitarAgendamento(scanner, pacientesCadastrados, agendamentos);
                    break;
                case 3:
                    verAgendamentos(agendamentos, scanner);
                    break;
                case 4:
                    System.out.println("Saindo do programa. Até mais!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void cadastrarPaciente(Scanner scanner, ArrayList<Paciente> pacientesCadastrados) {
        System.out.println("Digite o nome do paciente:");
        scanner.nextLine();
        String nome = scanner.nextLine();
        System.out.println("Digite o telefone do paciente:");
        String telefone = scanner.next();

        if (pacienteJaCadastrado(pacientesCadastrados, telefone)) {
            System.out.println("Paciente já cadastrado!");
        } else {
            Paciente novoPaciente = new Paciente(nome, telefone);
            pacientesCadastrados.add(novoPaciente);
            System.out.println("Paciente cadastrado com sucesso!");
        }
    }

    private static boolean pacienteJaCadastrado(ArrayList<Paciente> pacientesCadastrados, String telefone) {
        for (Paciente paciente : pacientesCadastrados) {
            if (paciente.getTelefone().equals(telefone)) {
                return true;
            }
        }
        return false;
    }

    private static void verPacientes(ArrayList<Paciente> pacientesCadastrados) {
        System.out.println("Lista de Pacientes Cadastrados:");
        for (int i = 0; i < pacientesCadastrados.size(); i++) {
            System.out.println((i + 1) + ". " + pacientesCadastrados.get(i).getNome());
        }
    }

    private static void solicitarAgendamento(Scanner scanner, ArrayList<Paciente> pacientesCadastrados,
                                             ArrayList<Agendamento> agendamentos) {
        System.out.println("Escolha o número do paciente para agendar a consulta:");
        int numeroPaciente = scanner.nextInt();

        if (numeroPaciente >= 1 && numeroPaciente <= pacientesCadastrados.size()) {
            Paciente pacienteSelecionado = pacientesCadastrados.get(numeroPaciente - 1);

            System.out.println("Digite o dia da consulta:");
            String diaConsulta = scanner.next();

            System.out.println("Digite a hora da consulta:");
            String horaConsulta = scanner.next();

            if (consultaDisponivel(agendamentos, diaConsulta, horaConsulta)) {
                System.out.println("Digite a especialidade desejada para a consulta:");
                String especialidadeConsulta = scanner.next();

                Agendamento novoAgendamento = new Agendamento(pacienteSelecionado, diaConsulta, horaConsulta, especialidadeConsulta);
                agendamentos.add(novoAgendamento);

                System.out.println("Agendamento realizado com sucesso!");
            } else {
                System.out.println("Data ou hora já agendadas. Escolha outra data ou hora.");
            }

        } else {
            System.out.println("Número de paciente inválido.");
        }
    }

    private static boolean consultaDisponivel(ArrayList<Agendamento> agendamentos, String diaConsulta, String horaConsulta) {
        for (Agendamento agendamento : agendamentos) {
            if (agendamento.getDiaConsulta().equals(diaConsulta) && agendamento.getHoraConsulta().equals(horaConsulta)) {
                return false;
            }
        }
        return true;
    }

    private static void verAgendamentos(ArrayList<Agendamento> agendamentos, Scanner scanner) {
        System.out.println("Lista de Agendamentos:");
        for (int i = 0; i < agendamentos.size(); i++) {
            Agendamento agendamento = agendamentos.get(i);
            System.out.println((i + 1) + ". " + agendamento.getPaciente().getNome() +
                    " - " + agendamento.getDiaConsulta() + " às " + agendamento.getHoraConsulta() +
                    " - Especialidade: " + agendamento.getEspecialidadeConsulta());
        }

        if (!agendamentos.isEmpty()) {
            System.out.println("Escolha o número do agendamento para remarcar ou cancelar:");
            int numeroAgendamento = scanner.nextInt();

            if (numeroAgendamento >= 1 && numeroAgendamento <= agendamentos.size()) {
                Agendamento agendamentoSelecionado = agendamentos.get(numeroAgendamento - 1);

                System.out.println("Agendamento Selecionado:");
                System.out.println("Paciente: " + agendamentoSelecionado.getPaciente().getNome());
                System.out.println("Data e Hora: " + agendamentoSelecionado.getDiaConsulta() + " às " + agendamentoSelecionado.getHoraConsulta());
                System.out.println("Especialidade: " + agendamentoSelecionado.getEspecialidadeConsulta());

                System.out.println("Deseja cancelar o agendamento? (S/N)");
                String resposta = scanner.next().toUpperCase();

                if (resposta.equals("S")) {
                    agendamentos.remove(agendamentoSelecionado);
                    System.out.println("Agendamento cancelado com sucesso!");
                } else {
                    System.out.println("Operação cancelada. Voltando ao menu principal.");
                }
            } else {
                System.out.println("Número de agendamento inválido.");
            }
        } else {
            System.out.println("Não há agendamentos disponíveis.");
        }
    }
}
