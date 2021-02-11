package Sandbox;
import CRUD.*;
import java.math.BigInteger;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * @author
 * Rafa Narvaiza
 * ADTO03
 * Esta clase implementa un menú por consola para ir ejecutando los ejercicios propuestos.
 */

public class MenuActividad {
    public static void showMenu() {
        Scanner scanner = new Scanner(System.in);
        Scanner scannerForBigInts = new Scanner(System.in);
        boolean continuar = true;
        int opcion;
        int empleadoEliminado;
        BigInteger IDEmpleadoEliminado;


        while (continuar){
            System.out.println();
            System.out.println("##############################################################################################################################");
            System.out.println("######################################################    APARTADO 1   #######################################################");
            System.out.println("##############################################################################################################################");
            System.out.println("1.- Seleccione 1 para consulta: Apellido, nombre, salario y número de empleado con un salario inferior al mileurista");
            System.out.println("2.- Seleccione 2 para consulta: Número de empleado, departamento y salario de los empleados, ordenados de menor a mayor salario.");
            System.out.println("3.- Seleccione 3 para consulta: Datos de empleados cuyo departamento no esté en MADRID.");
            System.out.println("4.- Seleccione 4 para consulta: Apellido, salario y número de departamento de los empleados cuyo salario sea  mayor que el máximo salario del departamento 1.");
            System.out.println("5.- Seleccione 5 para consulta: Empleados con salario menor que alguno de los empleados  del departamento 30.");
            System.out.println("6.- Seleccione 6 para consulta: Mostrar nombre y total de empleados de aquellos departamentos con más de un empleado adscrito. Ordena el resultado por número de empleado.");
            System.out.println("##############################################################################################################################");
            System.out.println("######################################################    APARTADO 2   #######################################################");
            System.out.println("######################################################      TESTS      #######################################################");
            System.out.println("##############################################################################################################################");
            System.out.println("7.- Seleccione 7 para consulta: Mostrar el contenido de cada Clase (tabla).");
            System.out.println("8.- Seleccione 8 para consulta: Mostrar para cada empleado, su departamento y donde está situado.");
            System.out.println("9.- Seleccione 9 para consulta: Mostrar para cada proyecto su nombre y el nombre del departamento que lo gestiona.");
            System.out.println("##############################################################################################################################");
            System.out.println("######################################################    APARTADO 2   #######################################################");
            System.out.println("######################################################    ENUNCIADOS   #######################################################");
            System.out.println("##############################################################################################################################");
            System.out.println("10.- Seleccione 10 para insertar un nuevo objeto empleado.");
            System.out.println("11.- Seleccione 11 para modificar el salario de un objeto Empleados de la BD (empleado 34, con los nuevos valores 2200).");
            System.out.println("12.- Seleccione 12 para eliminar un empleado.A continuación introduzca el ID del empleado que quiere eliminar:");
            System.out.println("13.- Seleccione 13 para consulta de empleados con salario superior a 50000 €:");
            System.out.println("14.- Seleccione 14 para mostrar el listado donde aparezcan los datos de cada departamento y a continuación los datos de los proyectos que controla. En el listado deben aparecer también los departamentos que no controlen proyectos.");
            System.out.println("15.- Seleccione 15 para salir del programa.");
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
                        Test.test1(Constants.QUERY_SELECT_AP1,Constants.QUERY_SELECT_AP6,Constants.QUERY_SELECT_AP7);
                        break;
                    case 8:
                        Test.test2(Constants.QUERY_SELECT_AP1);
                        break;
                    case 9:
                        Test.test3(Constants.QUERY_SELECT_AP7);
                        break;
                    case 10:
                        Crud.insertaEmpleado();
                        break;
                    case 11:
                        Crud.actualizaEmpleado();
                        break;
                    case 12:
                        System.out.println("A continuación introduzca el ID del empleado que quiere eliminar:");
                        empleadoEliminado = scannerForBigInts.nextInt();
                        IDEmpleadoEliminado= BigInteger.valueOf(empleadoEliminado);
                        Crud.eliminaEmpleado(IDEmpleadoEliminado);
                        break;
                    case 13:
                        Crud.consultaSalarios(Constants.QUERY_SELECT_AP8);
                        break;
                    case 14:
                        Crud.consultaDepartamentos(Constants.QUERY_SELECT_AP6, Constants.QUERY_SELECT_AP7);
                        break;
                    case 15:
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
