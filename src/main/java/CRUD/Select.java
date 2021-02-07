package CRUD;

import java.math.BigInteger;
import java.util.*;

import Sandbox.Departamento;
import Sandbox.Empleado;
import Sandbox.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


/**
 * @author rnarvaiza
 * ADTO03
 */

public class Select {

    /**
     * Consultas mediante HQL.
     * Apellido, nombre, salario y número de empleado con un salario inferior al mileurista.
     * @param query
     * Para ello pasamos un select de todos los campos de la tabla empleados que cumplan la condición salarial.
     * Después procedemos a mostrar los datos del select que nos interesen.
     * Tomamos los salarios como anuales y definimos el salario en 12000.
     */

    public static void select1(String query){
        List<Empleado> resultado;
        try{
            float salarioMinimo = 120000f;
            resultado=getListOfEmpleadosFromDB(query);
            for (Empleado empleado : resultado) {
                if(empleado.getSalario().compareTo(salarioMinimo)==-1){
                    System.out.println("Empleado ["
                            + empleado.getId() + ","
                            + empleado.getNombre() + ","
                            + empleado.getApellidos()+ ","
                            + empleado.getSalario() + "]");
                }
            }
        }catch (NullPointerException ne){
            System.out.println(ne.getMessage());
        }

       // HibernateUtil.shutdown();


    }

    /**
     * Consultas mediante HQL.
     * Número de empleado, departamento y salario de los empleados, ordenados de menor a mayor salario.
     * @param query
     * Recogemos los datos de la consulta ordenada por salario ascendente e imprimimos ID de empleado, nombre, salario y número de departamento.
     */

    public static void select2(String query){
        List<Empleado> resultado;
        resultado=getListOfEmpleadosFromDB(query);
        for (Empleado empleado : resultado) {
            System.out.println("Empleado ["
                    + empleado.getId() + ","
                    + empleado.getNombre() + ","
                    + empleado.getApellidos() + ","
                    + empleado.getSalario() + ","
                    + empleado.getDepartamento().getNombre() + "]");
        }

    }


    /**
     * Consultas mediante HQL.
     * Datos de empleados cuyo departamento no esté en MADRID.
     * @param query
     * Extraemos a una lista de Empleados los datos de todos los empleados.
     * En el POJO Empleado, referenciando a la FK, existe el campo id_departamento. Se representa con una instancia del objeto Departamento.
     * De esta forma cada instancia del POJO Empleado contiene una instancia del objeto Departamento que le corresponde.
     * Simplemente filtramos comparando dos Strings para dejar de lado la localización Madrid.
     */

    public static void select3(String query){

        List<Empleado> resultado;
        resultado=getListOfEmpleadosFromDB(query);
        for (Empleado empleado : resultado) {
            if(!empleado.getDepartamento().getLocalizacion().equals("MADRID")){
                System.out.println("Empleado ["
                        + empleado.getId() + ","
                        + empleado.getNombre() + ","
                        + empleado.getApellidos() + ","
                        + empleado.getSalario() + ","
                        + empleado.getDepartamento().getNombre() + ","
                        + empleado.getDepartamento().getLocalizacion() +"]");
            }
        }
    }

    /**
     * Consultas mediante HQL.
     * Apellido, salario y número de departamento de los empleados cuyo salario sea  mayor que el máximo salario del departamento 30. (Como el departamento 30 no existe, comparamos con el departamento 1).
     * @param query
     */

    public static void select4(String query){

        List<Empleado> resultado;
        resultado=getListOfEmpleadosFromDB(query);

        for (Empleado empleado : resultado) {
            System.out.println("Empleado ["
                    + empleado.getApellidos() + ","
                    + empleado.getSalario() + ","
                    + empleado.getDepartamento().getId() + "]");
        }

    }

    /**
     * Consultas mediante HQL.
     * Empleados con salario menor que alguno de los empleados  del departamento 30. (Como el departamento 30 no existe, comparamos con el departamento 1).
     * @param query
     */

    public static void select5(String query){

        List<Empleado> resultado;
        resultado=getListOfEmpleadosFromDB(query);

        for (Empleado empleado : resultado) {

            System.out.println("Empleado ["
                    + empleado.getNombre() + ","
                    + empleado.getApellidos() + ","
                    + empleado.getSalario() + "]");
        }
    }
    /**
     * Consultas mediante HQL.
     * Mostrar nombre y total de empleados de aquellos departamentos con más de un empleado adscrito. Ordena el resultado por número de empleado.
     * @param query1, query2
     */

    public static void select6(String query1, String query2){
        Session session = null;
        Transaction transaction = null;
        List<Departamento> listadoDepartamentos = null;
        List<Empleado> listadoEmpleados =  null;
        HashMap<BigInteger, Integer> idDepartamentoCantidadEmpleadosMap = new HashMap<>();
        Map<BigInteger, Integer> sorted=null;
        int numeroDeEmpleados=0;


        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Query queryAllDept = session.createQuery(query1);
            listadoDepartamentos = queryAllDept.list();

            if(listadoDepartamentos.isEmpty()){
                System.out.println("El listado departamentos está vacio.");
            }

            Query queryAllEmp = session.createQuery(query2);
            listadoEmpleados = queryAllEmp.list();
            if(listadoEmpleados.isEmpty()){
                System.out.println("El listado departamentos está vacio.");

            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        //HibernateUtil.shutdown();

        /**
         * Procedemos a recorrer todos los departamentos.
         * Dentro de cada bucle de departamento, utilizamos otro bucle para recorrer los diversos objetos Empleado.
         * Siempre que haya más de un empleado con el mismo departamento, lo añadimos al map idDepartamentoCantidadEmpleadosMap.
         */

        for(Departamento departamento:listadoDepartamentos){
            for(Empleado empleado:listadoEmpleados){
                if(departamento.getId().compareTo(empleado.getDepartamento().getId())==0){
                    numeroDeEmpleados++;
                }
            }
            if(numeroDeEmpleados > 1){
                idDepartamentoCantidadEmpleadosMap.put(departamento.getId(),numeroDeEmpleados);
            }
            numeroDeEmpleados=0;
        }

        /**
         * Una vez tenemos un hashamp con los resultados ordenados de la forma que queremos, procedemos a recorrerlo con un bucle.
         * Dentro de cada iteración del bucle del HashMap, procedemos a recorrer el listado de todos los departamentos.
         * Cuando el entryset del HashMap nos devuelve un valor de key, buscamos dentro del listado de departamentos a que departamento pertenece.
         * Simplemente nos queda sacar por pantalla el nombre de ese departamento y la cantidad de empleados, que va directamente en el value del HashMap.
         */

        sorted = sortValues(idDepartamentoCantidadEmpleadosMap);
        for (Map.Entry<BigInteger, Integer> entry : sorted.entrySet()){
            for(Departamento departamento:listadoDepartamentos){
                if(entry.getKey()==departamento.getId()){
                    System.out.println("Departamento : " + departamento.getNombre() + " tiene: " + entry.getValue() + " empleados");
                }
            }
        }

    }

    /**
     * El siguiente método nos va ordenar un Map. El Map contiene un BigInt con el ID del departamento por un lado, y un int con la cantidad de empleados por otro.
     * Para su ordenación de MAYOR A MENOR, comparamos primero el o2 contra el o1. Si deseásemos que el orden fuese creciente, sería necesario
     * cambiar el orden de o1 por o2.
     * @param map
     * @return
     */
    private static HashMap sortValues(HashMap map) {
        List list = new LinkedList(map.entrySet());

        Collections.sort(list, (Comparator) (o1, o2) -> ((Comparable) ((Map.Entry)(o2)).getValue()).compareTo(((Map.Entry)(o1)).getValue()));
        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();){
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }

    public static List getListOfEmpleadosFromDB(String query){
        Session session = null;
        Transaction transaction;
        List<Empleado> list = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Query queryAllEmp = session.createQuery(query);
            list = queryAllEmp.list();
            if(list.isEmpty()){
                System.out.println("El listado empleados está vacio.");

            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        //HibernateUtil.shutdown();

        return list;
    }

}
