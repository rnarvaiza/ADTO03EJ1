package CRUD;

import POJO.*;
import Sandbox.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class Test {

    public static void test1(String query1, String query2,String query3){
        Session session = null;
        Transaction transaction = null;
        List<Empleado> resultadoEmpleado = null;
        List<Departamento> resultadoDepartamento = null;
        List<Proyecto> resultadoProyecto = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Query queryAllEmpl = session.createQuery(query1);
            resultadoEmpleado=queryAllEmpl.list();
            Query queryAllDept = session.createQuery(query2);
            resultadoDepartamento=queryAllDept.list();
            Query queryAllProy= session.createQuery(query3);
            resultadoProyecto=queryAllProy.list();

            System.out.println("Contenido tabla Departamento: ");

            System.out.println(resultadoDepartamento);

            System.out.println("Contenido tabla Empleado: ");

            System.out.println(resultadoEmpleado);

            System.out.println("Contenido tabla Proyecto: ");

            System.out.println(resultadoProyecto);


        }catch (NullPointerException npe){
            System.out.println(npe.getMessage());
        } finally {
            if (session != null){
                session.close();
            }
        }
        // HibernateUtil.shutdown();
    }

    public static void test2(String query){
        List <Empleado> getList = null;
        getList=getListOfEmpleadosFromDB(query);
        for(Empleado empleado:getList){
            System.out.println("El empleado: "
                    + empleado.getNombre()
                    + " con ID: "
                    + empleado.getId()
                    + " trabaja en el departamento: "
                    + empleado.getDepartamento().getNombre()
                    + " y cobra: "
                    + empleado.getSalario());
        }

        //HibernateUtil.shutdown();
    }

    public static void test3(String query){
        Session session = null;
        Transaction transaction;
        List<Proyecto> list = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Query queryAllProy = session.createQuery(query);
            list = queryAllProy.list();
            if(list.isEmpty()){
                System.out.println("El listado empleados está vacio.");

            }
            else
                for(Proyecto proyecto:list){
                    System.out.println("El proyecto: "
                            + proyecto.getNombre()
                            + " es controlado por: "
                            + proyecto.getDepartamento().getNombre());
                }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

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

        return list;
    }
}
