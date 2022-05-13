package healthcare;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Main Class
 */
public class HealthCare {


    
    static Scanner scanner = new Scanner(System.in);

    /**
     * Displays a patient menu:
     *             1- List patients
     *             2- Add a patient
     *             3- Modify a patient
     *             4- Delete a patient
     *             0- Exit
     */
    public static void main(String[] args) {
        XmlReaderWriter xmlTool = new XmlReaderWriter();
        
        
        
        ArrayList<Patient> patients = xmlTool.readUsersFromXml("patients.xml");
        
        int choice = -1;
        while (choice != 0) {
            switch (choice) {
                case 1 -> listPatients(patients);
                case 2 -> addNewPatient(patients);
                case 3 -> modifyPatient(patients);
                case 4 -> deletePatient(patients);
            }
            System.out.println("1 - List patients");
            System.out.println("2 - Add new patient");
            System.out.println("3 - Modify a patient");
            System.out.println("4 - Delete a patient");
            System.out.println("0 - Exit");
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
                if (choice < 0 || choice > 4) {
                    System.out.println("Not valid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Not valid option.");
                scanner.nextLine();
            }
        }

        xmlTool.saveUsersToXml(patients, "patients.xml");

    }


    private static void listPatients(ArrayList<Patient> patient) {
        for(Patient student:patient){
            System.out.println(student.toString());
            System.out.println("");
        }
    }


    private static void addNewPatient(ArrayList<Patient> patient) throws InputMismatchException {
        System.out.print("Enter patient's name: ");
        String name = scanner.nextLine();
        int age;
        try {
            System.out.print("Enter patient's age: ");
            age = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Not valid option.");
            scanner.nextLine();
            return;
        }
        System.out.print("Enter patient's gender: ");
        String gender = scanner.nextLine();
        System.out.print("Enter patient's condition: ");
        String Condition = scanner.nextLine();


        
        patient.add(new Patient(name, age, Gender.valueOf(gender.toUpperCase()), Condition));
        System.out.println("Patient added");
    }


    private static boolean deletePatient(ArrayList<Patient> patients) {
        System.out.print("Enter patient's name: ");
        String name= scanner.nextLine();
        for(int i=0;i<patients.size();i++){
            if(patients.get(i).getName().equals(name)){
                patients.remove(i);
                System.out.println("Patient deleted");
                return true;
            }
        }
        System.out.println("Patient not found");
        return false;
    }

    private static boolean modifyPatient(ArrayList<Patient> patients) throws InputMismatchException {
        System.out.print("Enter patient's patient name: ");
        String name = scanner.nextLine();
        for(Patient patient: patients){
            if(patient.getName().equals(name)){

                System.out.print("Enter patient's name: ");
                name = scanner.nextLine();
                int age;
                try {
                    System.out.print("Enter patient's age: ");
                    age = scanner.nextInt();
                    scanner.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("Not valid option.");
                    scanner.nextLine();
                    return false;
                }
                System.out.print("Enter patient's gender: ");
                String gender = scanner.nextLine();

                System.out.print("Enter patient's Condition: ");
                String Condition = scanner.nextLine();

                patient.setName(name);

                patient.setAge(age);

                patient.setGender(Gender.valueOf(gender.toUpperCase()));

                patient.setCondition(Condition);

                System.out.println("Patient modified");
                return true;
            }
        }
        System.out.println("Patient not found");
        return false;
        
    }

}
