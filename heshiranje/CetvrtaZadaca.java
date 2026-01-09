import java.util.*;


public class CetvrtaZadaca {
    static class IP implements Comparable<IP> {
        String ip;
        IP(String ip){
            String[] tmp = ip.split("\\.");
            this.ip = tmp[0] + "." + tmp[1] + "." + tmp[2];
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;         // Check if they are the same reference
            if (o == null || getClass() != o.getClass()) return false;
            IP other = (IP) o;
            return this.ip.equals(other.ip);    // Check if the 'ip' string is the same
        }

        @Override
        public int compareTo(IP o) {
            return this.ip.compareTo(o.ip);
        }
        @Override
        public String toString(){
            return ip;
        }
        @Override
        public int hashCode() {
            return this.ip.hashCode();
        }
    }
    static class Person{
        String name = "";
        int budget = 0;
        String ip_raw = "";
        String time = "";
        String city = "";
        int price = 0;

        Person(){}
        Person(String name, int budget, String ip_raw, String time, String city, int price){
            this.name = name;
            this.budget = budget;
            this.ip_raw = ip_raw;
            this.time = time;
            this.city = city;
            this.price = price;
        }
        @Override
        public String toString(){
            return name + " with salary " + budget + " from address " + ip_raw + " who logged in at " + time;
        }
        public boolean laterThan12(){
            int t = Integer.parseInt(time.substring(0,2));
            return t >= 12;
        }
        public int min(){
            if(time.isEmpty()) return Integer.MAX_VALUE;
            return Integer.parseInt(time.substring(3));
        }
        public int hour(){
            if(time.isEmpty()) return Integer.MAX_VALUE;
            return Integer.parseInt(time.substring(0,2));
        }
        public boolean canBuy(){
            return budget >= price;
        }
    }
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        scan.nextLine();
        HashMap<IP, ArrayList<Person>> map = new HashMap<>();
        for(int i = 0; i<n; i++){
            String[] input = scan.nextLine().split("\\s+");
            IP ip = new IP(input[3]);
            Person p = new Person(input[0] +" "+ input[1], Integer.parseInt(input[2]), input[3], input[4], input[5], Integer.parseInt(input[6]));
            if(p.laterThan12()) {
                if(map.get(ip) != null){
                    ArrayList<Person> tmp = map.get(ip);
                    tmp.add(p);
                    map.remove(ip);
                    map.put(ip, tmp);
                }
                else{
                    ArrayList<Person> tmp = new ArrayList<>();
                    tmp.add(p);
                    map.put(ip, tmp);
                }
            }
        }
        int m = scan.nextInt();
        scan.nextLine();
        for(int i = 0; i<m; i++){
            String[] input = scan.nextLine().split("\\s+");
            IP ip = new IP(input[3]);
            ArrayList<Person> list = map.get(ip);
            Person Max = new Person();
            for (Person person : list) {
                if (person.hour() <= Max.hour()){
                    if(person.hour() == Max.hour()){
                        if(person.min() < Max.min()) Max = person;
                    }
                    else Max = person;
                }
            }
            System.out.println("IP network: " + ip + " has the following number of users: ");
            System.out.println(list.size());
            System.out.println("The user who logged on earliest after noon from that network is: ");
            System.out.println(Max);
            System.out.println();
        }
    }
}
