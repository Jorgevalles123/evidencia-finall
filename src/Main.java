import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

class Doctor {
    private int id;
    private String nombre;
    private String especialidad;

    public Doctor(int id, String nombre, String especialidad) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }
}

class Paciente {
    private int id;
    private String nombre;

    public Paciente(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}

class Cita {
    private int id;
    private Date fecha;
    private String motivo;
    private Doctor doctor;
    private Paciente paciente;

    public Cita(int id, Date fecha, String motivo, Doctor doctor, Paciente paciente) {
        this.id = id;
        this.fecha = fecha;
        this.motivo = motivo;
        this.doctor = doctor;
        this.paciente = paciente;
    }

    public int getId() {
        return id;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getMotivo() {
        return motivo;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Paciente getPaciente() {
        return paciente;
    }
}

public class Main {
    private static List<Doctor> doctores = new ArrayList<>();
    private static List<Paciente> pacientes = new ArrayList<>();
    private static List<Cita> citas = new ArrayList<>();
    private static int doctorIdCounter = 1;
    private static int pacienteIdCounter = 1;
    private static int citaIdCounter = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean salir = false;
        while (!salir) {
            System.out.println("\nSeleccione una opción:");
            System.out.println("1. Agregar doctor");
            System.out.println("2. Agregar paciente");
            System.out.println("3. Agendar cita");
            System.out.println("4. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer de entrada

            switch (opcion) {
                case 1:
                    agregarDoctor(scanner);
                    break;
                case 2:
                    agregarPaciente(scanner);
                    break;
                case 3:
                    agendarCita(scanner);
                    break;
                case 4:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione nuevamente.");
            }
        }

        scanner.close();
    }

    private static void agregarDoctor(Scanner scanner) {
        System.out.println("Ingrese el nombre del doctor:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese la especialidad del doctor:");
        String especialidad = scanner.nextLine();

        Doctor doctor = new Doctor(doctorIdCounter++, nombre, especialidad);
        doctores.add(doctor);

        System.out.println("Doctor agregado exitosamente.");
    }

    private static void agregarPaciente(Scanner scanner) {
        System.out.println("Ingrese el nombre del paciente:");
        String nombre = scanner.nextLine();

        Paciente paciente = new Paciente(pacienteIdCounter++, nombre);
        pacientes.add(paciente);

        System.out.println("Paciente agregado exitosamente.");
    }

    private static void agendarCita(Scanner scanner) {
        if (doctores.isEmpty()) {
            System.out.println("Debe agregar al menos un doctor antes de agendar una cita.");
            return;
        }
        if (pacientes.isEmpty()) {
            System.out.println("Debe agregar al menos un paciente antes de agendar una cita.");
            return;
        }

        System.out.println("Seleccione el doctor para la cita:");
        for (int i = 0; i < doctores.size(); i++) {
            System.out.println((i + 1) + ". " + doctores.get(i).getNombre());
        }
        int doctorIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Limpiar el buffer de entrada

        if (doctorIndex < 0 || doctorIndex >= doctores.size()) {
            System.out.println("Doctor no válido.");
            return;
        }

        Doctor doctor = doctores.get(doctorIndex);

        System.out.println("Seleccione el paciente para la cita:");
        for (int i = 0; i < pacientes.size(); i++) {
            System.out.println((i + 1) + ". " + pacientes.get(i).getNombre());
        }
        int pacienteIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Limpiar el buffer de entrada

        if (pacienteIndex < 0 || pacienteIndex >= pacientes.size()) {
            System.out.println("Paciente no válido.");
            return;
        }

        Paciente paciente = pacientes.get(pacienteIndex);

        System.out.println("Ingrese la fecha y hora de la cita (YYYY-MM-DD HH:MM):");
        String fechaHoraStr = scanner.nextLine();
        Date fechaHora = null;
        try {
            fechaHora = new Date(fechaHoraStr);
        } catch (Exception e) {
            System.out.println("Formato de fecha y hora inválido. Utilice el formato especificado.");
            return;
        }

        System.out.println("Ingrese el motivo de la cita:");
        String motivo = scanner.nextLine();

        Cita cita = new Cita(citaIdCounter++, fechaHora, motivo, doctor, paciente);
        citas.add(cita);

        System.out.println("Cita agendada exitosamente:");
        System.out.println("Doctor: " + doctor.getNombre());
        System.out.println("Paciente: " + paciente.getNombre());
        System.out.println("Fecha y hora: " + fechaHora);
        System.out.println("Motivo: " + motivo);
    }
}