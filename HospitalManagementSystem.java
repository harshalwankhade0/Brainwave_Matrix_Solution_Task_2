 import java.util.*;

class Patient {
    String id;
    String name;
    int age;
    String gender;
    String contactInfo;

    public Patient(String id, String name, int age, String gender, String contactInfo) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.contactInfo = contactInfo;
    }

    @Override
    public String toString() {
        return "Patient ID: " + id + ", Name: " + name + ", Age: " + age + ", Gender: " + gender + ", Contact Info: " + contactInfo;
    }
}

class Appointment {
    String appointmentId;
    String patientId;
    Date date;
    String doctorName;

    public Appointment(String appointmentId, String patientId, Date date, String doctorName) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.date = date;
        this.doctorName = doctorName;
    }

    @Override
    public String toString() {
        return "Appointment ID: " + appointmentId + ", Patient ID: " + patientId + ", Date: " + date + ", Doctor: " + doctorName;
    }
}

class EHR {
    String patientId;
    List<String> medicalHistory;

    public EHR(String patientId) {
        this.patientId = patientId;
        this.medicalHistory = new ArrayList<>();
    }

    public void addRecord(String record) {
        medicalHistory.add(record);
    }

    @Override
    public String toString() {
        return "Patient ID: " + patientId + ", Medical History: " + medicalHistory;
    }
}

class Hospital {
    Map<String, Patient> patients = new HashMap<>();
    Map<String, Appointment> appointments = new HashMap<>();
    Map<String, EHR> ehrs = new HashMap<>();

    public void registerPatient(Patient patient) {
        patients.put(patient.id, patient);
        ehrs.put(patient.id, new EHR(patient.id));
    }

    public void scheduleAppointment(Appointment appointment) {
        appointments.put(appointment.appointmentId, appointment);
    }

    public void addMedicalRecord(String patientId, String record) {
        if (ehrs.containsKey(patientId)) {
            ehrs.get(patientId).addRecord(record);
        } else {
            System.out.println("Patient not found!");
        }
    }

    public void displayPatientDetails(String patientId) {
        if (patients.containsKey(patientId)) {
            System.out.println(patients.get(patientId));
            System.out.println(ehrs.get(patientId));
        } else {
            System.out.println("Patient not found!");
        }
    }

    public void displayAppointmentDetails(String appointmentId) {
        if (appointments.containsKey(appointmentId)) {
            System.out.println(appointments.get(appointmentId));
        } else {
            System.out.println("Appointment not found!");
        }
    }
}

public class HospitalManagementSystem {
    public static void main(String[] args) {
        Hospital hospital = new Hospital();

        // Registering patients
        hospital.registerPatient(new Patient("P001", "Sushil", 20, "Male", "123-456-7890"));
        hospital.registerPatient(new Patient("P002", "Atharv", 25, "Female", "987-654-3210"));

        // Scheduling appointments
        hospital.scheduleAppointment(new Appointment("A001", "P001", new Date(), "Dr. Chudhary"));
        hospital.scheduleAppointment(new Appointment("A002", "P002", new Date(), "Dr. Yadav"));

        // Adding medical records
        hospital.addMedicalRecord("P001", "Initial checkup");
        hospital.addMedicalRecord("P001", "cancer checkup");

        // Displaying details
        hospital.displayPatientDetails("P001");
        hospital.displayAppointmentDetails("A001");
    }
}