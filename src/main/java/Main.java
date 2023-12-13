import io.github.cdimascio.dotenv.Dotenv;


public class Main {
    public static void main(String[] args) {
        Dotenv dotenv = null;
        dotenv = Dotenv.configure().load();
        System.out.println(String.format(
            "Hello %s",
            dotenv.get("WHO")
        ));
    }
}