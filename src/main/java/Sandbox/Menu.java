package Sandbox;

import CRUD.Select;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    public static void showMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;
        int opcion, numero;


        while (continuar){
            System.out.println();
            System.out.println("1.- Seleccione 1 para consulta: Apellido, nombre, salario y número de empleado con un salario inferior al mileurista");
            System.out.println("2.- Seleccione 2 para consulta: Número de empleado, departamento y salario de los empleados, ordenados de menor a mayor salario.");
            System.out.println("3.- Seleccione 3 para consulta: Datos de empleados cuyo departamento no esté en MADRID.");
            System.out.println("4.- Seleccione 4 para consulta: Apellido, salario y número de departamento de los empleados cuyo salario sea  mayor que el máximo salario del departamento 1.");
            System.out.println("5.- Seleccione 5 para consulta: Empleados con salario menor que alguno de los empleados  del departamento 30.");
            System.out.println("6.- Seleccione 6 para consulta: Mostrar nombre y total de empleados de aquellos departamentos con más de un empleado adscrito. Ordena el resultado por número de empleado.");
            System.out.println("7.- Seleccione 7 para salir del programa.");
            System.out.println("Introduzca una opción:");
            try{
                opcion = scanner.nextInt();
                switch (opcion){
                    case 1:
                        Select.select1(Constants.QUERY_SELECT_AP1);
                        break;
                    case 2:
                        Select.select2(Constants.QUERY_SELECT_AP2);
                        break;
                    case 3:
                        Select.select3(Constants.QUERY_SELECT_AP3);
                        break;
                    case 4:
                        Select.select4(Constants.QUERY_SELECT_AP4);
                        break;
                    case 5:
                        Select.select5(Constants.QUERY_SELECT_AP5);
                        break;
                    case 6:
                        Select.select6(Constants.QUERY_SELECT_AP6, Constants.QUERY_SELECT_AP3);
                        break;
                    case 7:
                        HibernateUtil.shutdown();
                        continuar=false;
                        break;
                    default:
                        System.out.println("Introduce una opción correcta (1-7)");
                }
            }catch (InputMismatchException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
