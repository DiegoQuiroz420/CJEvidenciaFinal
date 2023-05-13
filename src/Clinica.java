import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.Iterator;
import java.util.HashMap;

public class Clinica {
    public static Scanner leer = new Scanner(System.in);
    public static String outputDoctores = "C:\\Users\\KATSURA\\IdeaProjects\\CJEvidenciaFinal\\src\\Doctores.csv";
    public static String outputPacientes = "C:\\Users\\KATSURA\\IdeaProjects\\CJEvidenciaFinal\\src\\Pacientes.csv";
    public static String outputCitas = "C:\\Users\\KATSURA\\IdeaProjects\\CJEvidenciaFinal\\src\\Citas.csv";
    public static String outputAdministradores = "C:\\Users\\KATSURA\\IdeaProjects\\CJEvidenciaFinal\\src\\Administradores.csv";

    public static ArrayList <Doctor> Doctr = new ArrayList<Doctor>();
    public static ArrayList <Paciente> Pacnt = new ArrayList<Paciente>();

    public static void main(String[] args) throws IOException, ParseException{
        BufferedWriter bwDoctores = new BufferedWriter(new FileWriter(outputDoctores, true));
        BufferedWriter bwPacientes = new BufferedWriter(new FileWriter(outputPacientes, true));
        BufferedWriter bwCitas = new BufferedWriter(new FileWriter(outputCitas, true));
        BufferedWriter bwAdmin = new BufferedWriter(new FileWriter(outputAdministradores, true));

        leer.useDelimiter("\n");
        int op, bann = 0, bann1;
        boolean acceso = false;
        HashMap<String, String> mapa = new HashMap<String, String>();
        System.out.println("---- Bienvenido al sistema de Clinica ----");
        do{
            bann1 = archivV("C:\\Users\\KATSURA\\IdeaProjects\\CJEvidenciaFinal\\src\\Administradores.csv");
            if(bann1 == 1)
            {
                System.out.println("---Ingreso para administradores---");
                System.out.println("Ingrese ID: ");
                String id = leer.next();
                System.out.println("Ingrese contraseña: ");
                String pass = leer.next();
                load(mapa);
                acceso = contrasena(mapa,id,pass);
            }
            else
            {
                System.out.println("No hay Administradores dados de alta");
                System.out.println("--- Alta de Administrador ---");
                System.out.println("Ingrese ID: ");
                String id = leer.next();
                System.out.println("Ingrese contraseña: ");
                String pass = leer.next();
                creaAdministrador(mapa, id, pass, bwAdmin);
            }
        }while ( acceso == false );
        do
        {
            try{
                System.out.println("-Seleccione una opción:");
                System.out.println("1. Alta Administrador");
                System.out.println("2. Alta Doctor ");
                System.out.println("3. Alta Paciente");
                System.out.println("4. Agendar Cita");
                System.out.println("5. Mostrar Cita");
                System.out.println("0. Salida");

                op = leer.nextInt();
                switch(op)
                {
                    case 1:
                        System.out.println("--- Alta de Administrador ---");
                        System.out.println("Ingrese ID: ");
                        String id = leer.next();
                        System.out.println("Ingrese contraseña: ");
                        String pass = leer.next();
                        creaAdministrador(mapa, id, pass, bwAdmin);
                        break;

                    case 2:
                        creaDoctor(bwDoctores);
                        break;

                    case 3:
                        creaPaciente(bwPacientes);
                        break;

                    case 4:
                        creaCita(bwCitas);
                        break;

                    case 5:
                        Cita[] citas = new Cita[0];

                        int idCita = 0;
                        Cita.mostrarCita(citas, idCita);

                        break;

                    case 0:
                        System.out.println("Salir");
                        bann = 1;
                        break;

                    default:

                        System.out.println("Opción no valida");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error! Intente de nuevo.");
                break;
            }
        }while(bann == 0);
    }
    public static void creaDoctor(BufferedWriter bw) throws IOException {
        System.out.print("Ingresar nombre del doctor");
        String nombreDoc = leer.next();
        System.out.print("Ingresar especialidad del doctor");
        String espeDoc = leer.next();
        System.out.print("Ingresar ID del doctor");
        String idDoc = leer.next();

        Doctor doctorInfo = new Doctor(idDoc, nombreDoc, espeDoc);

        try(FileWriter fw = new FileWriter(outputDoctores, true);
            BufferedWriter bww = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bww)){

            out.print(doctorInfo.id);
            out.print(",");
            out.print(doctorInfo.nomDoctor);
            out.print(",");
            out.println(doctorInfo.esp);
        }
        catch(IOException e) {
            System.out.println("IOException catched while writing: " + e.getMessage());
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                    System.out.println("Datos guardados");
                }
            } catch (IOException e) {
                System.out.println("IOException catched while closing: " + e.getMessage());
            }
        }
    }
    public static void creaPaciente(BufferedWriter bw) throws IOException {
        System.out.print("Ingresar nombre del Paciente");
        String nombrePaciente = leer.next();
        System.out.print("Ingresar Id del paciente");
        String idPaciente = leer.next();

        Paciente PacienteInfo = new Paciente(nombrePaciente,idPaciente);

        try(FileWriter fw = new FileWriter(outputPacientes, true);
            BufferedWriter bww = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bww)){

            out.print(PacienteInfo.id);
            out.print(",");
            out.println(PacienteInfo.nomPaciente);
        }
        catch(IOException e) {
            System.out.println("IOException catched while writing: " + e.getMessage());
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                    System.out.println("Datos guardados");
                }
            } catch (IOException e) {
                System.out.println("IOException catched while closing: " + e.getMessage());
            }
        }
    }
    public static void creaCita(BufferedWriter bw) throws IOException {
        try{
            System.out.println("Ingresar id de la cita:");
            int idC = leer.nextInt();
            System.out.print("Ingresar fecha de la cita");
            String fechaCitaString = leer.next();
            Date fechaCita = new SimpleDateFormat("dd/MM/yyyy").parse(fechaCitaString);
            System.out.print("Ingresar motivo de la cita");
            leer.nextLine();
            String motivoCita = leer.nextLine();
            System.out.print("Ingresar id del doctor");
            String idDoctor = leer.next();
            System.out.print("Ingresar id del paciente");
            String idPaciente = leer.next();
            Cita citaInfo = new Cita(idC, fechaCita, Integer.parseInt(idDoctor), Integer.parseInt(idPaciente), motivoCita);

            try(FileWriter fw = new FileWriter(outputCitas, true);
                BufferedWriter bww = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bww)){

                out.print(citaInfo.getId());
                out.print(",");
                out.println(citaInfo.getFechaCita());
                out.print(",");
                out.print(citaInfo.getIdDoctor());
                out.print(",");
                out.print(citaInfo.getIdPaciente());
                out.print(",");
                out.println(citaInfo.getMotivoCita());
            }
            catch(IOException e) {
                System.out.println("IOException catched while writing: " + e.getMessage());
            } finally {
                try {
                    if (bw != null) {
                        bw.close();
                        System.out.println("Datos guardados");
                    }
                } catch (IOException e) {
                    System.out.println("IOException catched while closing: " + e.getMessage());
                }
            }
        }
        catch(ParseException e) {
            System.out.println("ParseException catched while writing: " + e.getMessage());
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                System.out.println("IOException catched while closing: " + e.getMessage());
            }
        }
    }
    public static int archivV(String archivo)
    {
        int i = 0;
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            if (br.readLine() == null)
            {
                System.out.println("Archivo vacio");
            }
            else i = 1;
        }
        catch(IOException e) {
            System.out.println("IOException catched while writing: " + e.getMessage());
        }
        return i;
    }
    public static void creaAdministrador(HashMap <String,String> mapa, String id, String pw, BufferedWriter bw)throws IOException{
        if(mapa.containsKey(id))
        {
            System.out.println("Error!..No se puede registrar dos veces el mismo Administrador");
        }
        else{
            mapa.put(id, pw);
            System.out.println("Administrador creado");
        }
        Iterator<String> iterator = mapa.keySet().iterator();
        String inputFilename = "C:\\Users\\KATSURA\\IdeaProjects\\CJEvidenciaFinal\\src\\Administradores.csv";
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(inputFilename));
            while(iterator.hasNext())
            {
                String llave = iterator.next();
                bufferedWriter.write(llave+","+mapa.get(llave)+"\n");
            }
        }
        catch(IOException e) {
            System.out.println("IOException catched while writing: " + e.getMessage());
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                    System.out.println("Datos guardados");
                }
            } catch (IOException e) {
                System.out.println("IOException catched while closing: " + e.getMessage());
            }
        }
    }
    public static boolean contrasena(HashMap <String,String> mapa, String id, String pw)
    {
        boolean i ;
        if(mapa.containsKey(id))
        {
            if(mapa.get(id).equals(pw))
                i = true;
            else
            {
                System.out.println("La contraseña es incorrecta");
                i = false;
            }
        }
        else
        {
            System.out.println("Administrador no existente");
            i = false;
        }
        return i;
    }
    public static void load(HashMap<String, String> m)
    {
        String inputFilename = "C:\\Users\\KATSURA\\IdeaProjects\\CJEvidenciaFinal\\src\\Administradores.csv";
        String a [];
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(inputFilename));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                a = line.split(",");
                m.put(a[0],a[1]);
            }
        } catch(IOException e) {
            System.out.println("IOException catched while reading: " + e.getMessage());
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                System.out.println("IOException catched while closing: " + e.getMessage());
            }
        }
    }
    public static void loadPaciente(ArrayList Pac)
    {
        String inputFilename = outputPacientes;
        String a [];
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(inputFilename));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                a = line.split(",");
                Pac.add(a[0]);
                Pac.add(a[1]);
            }
        } catch(IOException e) {
            System.out.println("IOException catched while reading: " + e.getMessage());
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                System.out.println("IOException catched while closing: " + e.getMessage());
            }
        }
    }
    public static void loadDoctor(ArrayList Doc)
    {
        String inputFilename = outputDoctores;
        String a [];
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(inputFilename));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                a = line.split(",");
                Doc.add(a[0]);
                Doc.add(a[1]);
                Doc.add(a[2]);
            }
        } catch(IOException e) {
            System.out.println("IOException catched while reading: " + e.getMessage());
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                System.out.println("IOException catched while closing: " + e.getMessage());
            }
        }
    }
}