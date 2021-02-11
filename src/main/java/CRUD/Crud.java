package CRUD;

import POJO.*;
import Sandbox.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.math.BigInteger;
import java.util.*;

/**
 *
 * @author
 * Rafa Narvaiza
 * ADTO03
 *
 * Métodos para realizar la segunda parte de los ejercicios.
 */

public class Crud {
    public static Session session = null;
    public static Transaction transaction = null;
    public static Date todayDate = Calendar.getInstance().getTime();


    /**
     * Inserción de un nuevo objeto empleado llamado Antonio Negro, con el puesto de técnico, fecha de ingreso del día actual, salario de 3000€,
     * comisión del 20% en el Departamento con ID 1.
     */

    public static void insertaEmpleado(){
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Departamento dep = new Departamento();
            dep.setId(BigInteger.valueOf(01));
            Empleado emp = new Empleado("Antonio", "Negro", "Técnico", todayDate, 3000f, 20f, dep);
            emp.setDepartamento(dep);
            session.save(emp);
            session.getTransaction().commit();
            System.out.println(emp.toString());
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Actualización del sueldo de un empleado determinado (ID 34).
     */

    public static void actualizaEmpleado(){

        BigInteger idEmpleado = BigInteger.valueOf(34);
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Empleado emp = (Empleado) session.load(Empleado.class, idEmpleado);
            emp.setSalario(2200f);
            session.update(emp);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Eliminación de un empleado determinado.
     * En este caso en la llamada al método pasamos por parámetro el ID del empleado que queremos eliminar.
     * @param IDEmpleadoEliminado
     */

    public static void eliminaEmpleado(BigInteger IDEmpleadoEliminado){
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Empleado emp = (Empleado) session.load(Empleado.class, IDEmpleadoEliminado);
            session.delete(emp);
            session.getTransaction().commit();

        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * Consulta de los empleados con salario superior a 50.000€.
     * @param query
     */
    public static void consultaSalarios(String query){
        List<Empleado> getList = null;
        getList=Test.getListOfEmpleadosFromDB(query);
        for(Empleado empleado:getList){
            System.out.println("El empleado: "
                    + empleado.getNombre()
                    + " trabaja en el departamento: "
                    + empleado.getDepartamento().getId()
                    + " y cobra: "
                    + empleado.getSalario());
        }
    }

    /**
     * Método que nos va a mostrar los departamentos y que proyecto gestionan. Después,
     * mostrará los departamentos que no gestionan ningún proyecto.
     * @param query1
     * @param query2
     */

    public static void consultaDepartamentos(String query1, String query2){
        Session session = null;
        Transaction transaction = null;
        List<Departamento> listadoDepartamentos = null;
        List<Proyecto> listadoProyectos= null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Query queryAllDept = session.createQuery(query1);
            listadoDepartamentos = queryAllDept.list();

            if(listadoDepartamentos.isEmpty()){
                System.out.println("El listado departamentos está vacio.");
            }

            Query queryAllEmp = session.createQuery(query2);
            listadoProyectos = queryAllEmp.list();
            if(listadoProyectos.isEmpty()){
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
        for(Departamento departamento:listadoDepartamentos){
            for(Proyecto proyecto:listadoProyectos){
                if(proyecto.getDepartamento().getId()==departamento.getId()){
                    System.out.println("El departamento: "
                            + departamento.getNombre()
                            + " con ID: "
                            + departamento.getId()
                            + " está a cargo del proyecto: "
                            + proyecto.getNombre()
                    );
                }
                else {
                    System.out.println("El departamento: "
                            + departamento.getNombre()
                            + " con ID: "
                            + departamento.getId()
                            + " no está a cargo de ningún proyecto."
                    );

                }

            }

        }
    }
}
