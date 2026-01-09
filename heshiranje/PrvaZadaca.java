// Следните класи веќе се импортирани, не е дозволено копирање на класи овде, директно користејте ги како кога се достапни во други локални фајлови:
// The following classes are already imported, copying classes here is not allowed, use them directly as when they are available in other local files:

// CBHT, OBHT, MapEntry, SLLNode веќе се импортирани
// CBHT, OBHT, MapEntry, SLLNode are already imported
import java.util.Scanner;

// Овде креирајте ги помошните класи за клуч и вредност
// Исполнете ги барањата од текстот за toString методите
// Дополнително осигурете се дека вашата клуч класа ќе ги имплементира потребните
// hashCode и equals методи

// Create the helper classes for key and value here
// Fulfill the requirements from the text for the toString methods
// Additionally, make sure that your key class will implement the required
// hashCode and equals methods
class Person implements Comparable<Person> {
    // поставете ги потребните полиња овде
    // declare the required fields here
    String name;
    int age;

    // имплементирајте соодветен конструктор
    // implement the constructor
    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "<" + name + ", " + age + ">";
    }


    // имплементирајте ги следните два методи за да работи табелата правилно
    // implement the following two methods to make the table work properly
    @Override
    public boolean equals(Object o) {
        if(o.getClass() != this.getClass()) return false;
        Person cast = (Person) o;
        if(!cast.name.equals(this.name)) return false;
        if(this.age != cast.age) return false;
        return true;
    }
    @Override
    public int hashCode() {
        return this.name.charAt(0) * this.age;
    }

    @Override
    public int compareTo(Person o) {
        return this.name.compareTo(o.name);
    }
}

class Project {
    int hourly, duration;

    Project(int hourly, int duration) {
        this.hourly = hourly;
        this.duration = duration;
    }


    @Override
    public String toString() {
        return "<" + hourly + ", " + duration + ">";
    }
    int getWage(){
        return this.hourly * this.duration;
    }
}

public class PrvaZadaca {
    public static void main(String[] args) {
        // Креирајте ја табелата според барањата
        // Create the table as requested
        CBHT<Person,Project> table = new CBHT<Person, Project>(10) ;
        Scanner scan = new Scanner(System.in);
        // Прочитајте ги податоците од влезот и пополнете ја табелата
        // Read the input data and fill the table
        int n = scan.nextInt();
        scan.nextLine();
        for(int i = 0; i<n; i++){
            String[] input = scan.nextLine().split(" ");
            Person p = new Person(input[0], Integer.parseInt(input[1]));
            Project f = new Project(Integer.parseInt(input[2]), Integer.parseInt(input[3]));
            if(table.search(p) == null){
                table.insert(p, f);
            }else{
                if(table.search(p).element.value.getWage() < f.getWage()){
                    table.search(p).element.value = f;
                }
            }
        }
        // отпечатете ја вашата табела
        // print your table
        System.out.println(table);
    }
}

