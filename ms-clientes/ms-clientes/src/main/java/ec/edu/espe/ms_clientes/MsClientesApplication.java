package ec.edu.espe.ms_clientes;

import ec.edu.espe.ms_clientes.model.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class MsClientesApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsClientesApplication.class, args);
    }

}