package Sandbox;

import CRUD.*;


import java.math.BigInteger;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuActividad2 {
    public static void showMenu() {
        Scanner scanner = new Scanner(System.in);
        Scanner scannerForBigInts = new Scanner(System.in);
        boolean continuar = true;
        int opcion, numero;
        int empleadoEliminado = 0;
        BigInteger IDEmpleadoEliminado=null;


        while (continuar){
            System.out.println();
            System.out.println("##############################################################################################################################");
            System.out.println("######################################################    APARTADO 2   #######################################################");
            System.out.println("######################################################      TESTS      #######################################################");
            System.out.println("##############################################################################################################################");
            System.out.println("1.- Seleccione 1 para consulta: Mostrar el contenido de cada Clase (tabla).");
            System.out.println("2.- Seleccione 2 para consulta: Mostrar para cada empleado, su departamento y donde está situado.");
            System.out.println("3.- Seleccione 3 para consulta: Mostrar para cada proyecto su nombre y el nombre del departamento que lo gestiona.");
            System.out.println("##############################################################################################################################");
            System.out.println("######################################################    APARTADO 2   #######################################################");
            System.out.println("######################################################    ENUNCIADOS   #######################################################");
            System.out.println("##############################################################################################################################");
            System.out.println("4.- Seleccione 4 para insertar un nuevo objeto empleado.");
            System.out.println("5.- Seleccione 5 para modificar el salario de un objeto Empleados de la BD (empleado 34, con los nuevos valores 2200).");
            System.out.println("6.- Seleccione 6 para eliminar un empleado.A continuación introduzca el ID del empleado que quiere eliminar:");
            System.out.println("7.- Seleccione 7 para consulta de empleados con salario superior a 50000 €:");
            System.out.println("8.- Seleccione 8 para mostrar el listado donde aparezcan los datos de cada departamento y a continuación los datos de los proyectos que controla. En el listado deben aparecer también los departamentos que no controlen proyectos.");
            System.out.println("9.- Seleccione 9 para salir del programa.");
            System.out.println("Introduzca una opción:");
            try{
                opcion = scanner.nextInt();
                switch (opcion){
                    case 1:
                        Test.test1(Constants.QUERY_SELECT_AP1,Constants.QUERY_SELECT_AP6,Constants.QUERY_SELECT_AP7);
                        break;
                    case 2:
                        Test.test2(Constants.QUERY_SELECT_AP1);
                        break;
                    case 3:
                        Test.test3(Constants.QUERY_SELECT_AP7);
                        break;
                    case 4:
                        Crud.insertaEmpleado();
                        break;
                    case 5:
                        Crud.actualizaEmpleado();
                        break;
                    case 6:
                        System.out.println("A continuación introduzca el ID del empleado que quiere eliminar:");
                        empleadoEliminado = scannerForBigInts.nextInt();
                        IDEmpleadoEliminado=BigInteger.valueOf(empleadoEliminado);
                        Crud.eliminaEmpleado(IDEmpleadoEliminado);
                        break;
                    case 7:
                        Crud.consultaSalarios(Constants.QUERY_SELECT_AP8);
                        break;
                    case 8:
                        Crud.consultaDepartamentos(Constants.QUERY_SELECT_AP6, Constants.QUERY_SELECT_AP7);
                        break;
                    case 9:
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
