import javax.xml.transform.Source;
import java.sql.SQLOutput;
import java.util.*;
public class VtoraZadaca{
    static class City implements Comparable<City>{
        String city;
        City(String city){
            this.city = city;
        }
        @Override
        public int compareTo(City o) {
            return this.city.compareTo(o.city);
        }
        @Override
        public int hashCode(){
            return city.hashCode();
        }
        @Override
        public boolean equals(Object o){
            if(this.getClass() != o.getClass()) return false;
            if(!this.city.equals(((City) o).city)) return false;
            return true;
        }
    }
    static class Person{
        String name;
        int wage;
        String IP;
        int ticketPrice;
        Person(String name, int wage, String IP, int ticketPrice){
            this.name = name;
            this.wage = wage;
            this.IP = IP;
            this.ticketPrice = ticketPrice;
        }
        public boolean canAfford(){
            return wage >=ticketPrice;
        }
    }

    //Jovan Todorov [0] + [1]    1000 [2]    10.73.112.200 [3]     16:30[4]   Bitola[5]     760[6]
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        OBHT<City, List<Person>> table = new OBHT<>(n);
        scan.nextLine();
        for(int i = 0; i<n ; i++){
            String[] input = scan.nextLine().split("\\s+");
            City c = new City(input[5]);
            Person p = new Person(input[0] + " " + input[1], Integer.parseInt(input[2]), input[3], Integer.parseInt(input[6]));
            if(table.search(c) == -1 && p.canAfford()){
                List<Person> list = new ArrayList<>();
                list.add(p);
                table.insert(c, list);
            }else{
                if(p.canAfford()) {
                    table.getBucket(table.search(c)).value.add(p);
                }
            }
        }
        int m = scan.nextInt();
        scan.nextLine();
        for(int i = 0; i<m; i++){
            String[] input = scan.nextLine().split("\\s+");
            City c = new City(input[5]);
            List<Person> found = table.getBucket(table.search(c)).value;
            System.out.println("City: " + input[5] + " has the following number of customers:");
            System.out.println(found.size());
            System.out.println("The user who spent the most purchasing for that city is:");
            Person max = null;
            for(Person p : found){
                if(p.canAfford()){
                    if(max == null) max = p;
                    else if(max.ticketPrice < p.ticketPrice && p.canAfford()) max = p;
                }
            }
            System.out.println(max.name + " with salary " + max.wage + " from address " + max.IP + " who spent " + max.ticketPrice);
            System.out.println();
        }


    }
}
