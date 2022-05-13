package healthcare;


/**
 *  Patient Class stores important information about patients
 */
public class Patient {
    private String name;
    private int age;
    private Gender gender;
    private String Condition;


    /**
     * @param name
     * @param age
     * @param gender
     * @param Condition
     */
    public Patient(String name, int age, Gender gender, String Condition){
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.Condition = Condition;
    }


    /**
     * @return patient name
     */
    public String getName(){

        return this.name;
    }


    /**
     * @return patient age
     */
    public int getAge(){

        return this.age;
    }


    /**
     * @return patient gender
     */
    public Gender getGender(){

        return this.gender;
    }

    /**
     * @return patient condition
     */
    public String getCondition(){

        return this.Condition;
    }


    /**
     * @param name set patient name
     */
    public void setName(String name){

        this.name = name;
    }


    /**
     * @param age set patient age
     */
    public void setAge(int age){

        this.age = age;
    }


    /**
     * @param gender set patient gender male or female
     */
    public void setGender(Gender gender){
        this.gender = gender;
    }

    /**
     * @param Condition set patient condition
     */
    public void setCondition(String Condition){

        this.Condition = Condition;
    }


    /**
     * @return patient object as a string
     */
    @Override
    public String toString(){

        return "Name: "+name+ "\nAge: "+age+"\nGender: "+ gender+"\nCondition : "+Condition;
    }
}
