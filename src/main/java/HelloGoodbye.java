public class HelloGoodbye {
    public static void main(String[] args){
        String firstname = args[0];
        String secondname = args[1];

        System.out.printf("Hello %s and %s.%n", firstname, secondname);
        System.out.printf("Goodbye %s and %s.%n", secondname, firstname);

    }
}
