package CRUD;

import java.math.BigInteger;
import java.util.*;

import POJO.Departamento;
import POJO.Empleado;
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
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Query queryAll = session.createQuery(query);
            List<Empleado> listaTodosEmpleado = queryAll.list();
            for (Empleado empleado : listaTodosEmpleado) {
                System.out.println("POJO.Empleado ["
                        + empleado.getId() + ","
                        + empleado.getNombre() + ","
                        + empleado.getApellidos()+ ","
                        + empleado.getSalario() + "]");
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        HibernateUtil.shutdown();

    }

    /**
     * Alternativa al select anterior.
     * En vez de comprobar la condición por HQL lo realizamos en java con el operador .compareTo entre dos BigDecimals, el del salario en sí y
     * otra instancia del objeto con valor del salario mileurista.
     * Tomamos los salarios como anuales y definimos el salario en 12000.
     */

    public static void queryHQLAlternativa() {
        Session session = null;
        Transaction transaction = null;
        float salarioMinimo = 12000f;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            Query queryAll = session.createQuery("from Empleado");
            List<Empleado> listaTodosEmpleado = queryAll.list();
            for (Empleado empleado : listaTodosEmpleado) {
                if(empleado.getSalario().compareTo(salarioMinimo)==-1){
                    System.out.println("POJO.Empleado ["
                            + empleado.getId() + ","
                            + empleado.getNombre() + ","
                            + empleado.getApellidos()+ ","
                            + empleado.getSalario() + "]");
                }
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        HibernateUtil.shutdown();
    }

    /**
     * Consultas mediante HQL.
     * Número de empleado, departamento y salario de los empleados, ordenados de menor a mayor salario.
     * @param query
     * Recogemos los datos de la consulta ordenada por salario ascendente e imprimimos ID de empleado, nombre, salario y número de departamento.
     */

    public static void select2(String query){
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Query queryAll = session.createQuery(query);
            List<Empleado> listaTodosEmpleado = queryAll.list();
            for (Empleado empleado : listaTodosEmpleado) {
                System.out.println("Empleado ["
                        + empleado.getId() + ","
                        + empleado.getNombre() + ","
                        + empleado.getApellidos() + ","
                        + empleado.getSalario() + ","
                        + empleado.getDepartamento().getNombre() + "]");
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        HibernateUtil.shutdown();
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
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Query queryAll = session.createQuery(query);
            List<Empleado> listaTodosEmpleado = queryAll.list();
            for (Empleado empleado : listaTodosEmpleado) {
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
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        HibernateUtil.shutdown();
    }

    /**
     * Consultas mediante HQL.
     * Apellido, salario y número de departamento de los empleados cuyo salario sea  mayor que el máximo salario del departamento 30. (Como el departamento 30 no existe, comparamos con el departamento 1).
     * @param query
     */

    public static void select4(String query){
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Query queryAll = session.createQuery(query);
            List<Empleado> listaTodosEmpleado = queryAll.list();
            for (Empleado empleado : listaTodosEmpleado) {

                System.out.println("Empleado ["
                        + empleado.getApellidos() + ","
                        + empleado.getSalario() + ","
                        + empleado.getDepartamento().getId() + "]");
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        HibernateUtil.shutdown();
    }

    /**
     * Consultas mediante HQL.
     * Empleados con salario menor que alguno de los empleados  del departamento 30. (Como el departamento 30 no existe, comparamos con el departamento 1).
     * @param query
     */

    public static void select5(String query){
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Query queryAll = session.createQuery(query);
            List<Empleado> listaTodosEmpleado = queryAll.list();
            for (Empleado empleado : listaTodosEmpleado) {

                System.out.println("Empleado ["
                        + empleado.getNombre() + ","
                        + empleado.getApellidos() + ","
                        + empleado.getSalario() + "]");
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        HibernateUtil.shutdown();
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
            Query queryAll = session.createQuery(query1);
            listadoDepartamentos = queryAll.list();

            if(!listadoDepartamentos.isEmpty()){
                System.out.println("Almacenado el listado de departamentos. El listado tiene: " + listadoDepartamentos.size() + " elementos");
                System.out.println(listadoDepartamentos);
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Query queryAll = session.createQuery(query2);
            listadoEmpleados = queryAll.list();
            if(!listadoEmpleados.isEmpty()){
                System.out.println("Almacenado el listado de empleados. El listado tiene: " + listadoEmpleados.size() + " elementos");
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        HibernateUtil.shutdown();

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
        sorted = sortValues(idDepartamentoCantidadEmpleadosMap);
        Set set = sorted.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){

            Map.Entry me = (Map.Entry)iterator.next();
                System.out.println("Departamento: " + listadoDepartamentos.get((int)me.getKey()).getNombre() + " tiene " + me.getValue() + " empleados.");
        }
    }

    private static HashMap sortValues(HashMap map) {
        List list = new LinkedList(map.entrySet());

        Collections.sort(list, new Comparator(){

            @Override
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry)(o1)).getValue()).compareTo(((Map.Entry)(o2)).getValue());
            }
        });
        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();){
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }

}
