package guayacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UmatchingApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(UmatchingApp.class,args);
        System.out.println("The app is running...");

    }
}
