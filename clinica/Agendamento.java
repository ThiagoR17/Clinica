package clinica;

public class Agendamento {
    private Paciente paciente;
    private String diaConsulta;
    private String horaConsulta;
    private String especialidadeConsulta;

    public Agendamento(Paciente paciente, String diaConsulta, String horaConsulta, String especialidadeConsulta) {
        this.paciente = paciente;
        this.diaConsulta = diaConsulta;
        this.horaConsulta = horaConsulta;
        this.especialidadeConsulta = especialidadeConsulta;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public String getDiaConsulta() {
        return diaConsulta;
    }

    public String getHoraConsulta() {
        return horaConsulta;
    }

    public String getEspecialidadeConsulta() {
        return especialidadeConsulta;
    }
}
