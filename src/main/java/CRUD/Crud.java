package CRUD;

import POJO.Departamento;
import POJO.Empleado;
import POJO.Proyecto;
import Sandbox.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.math.BigInteger;
import java.util.*;

public class Crud {
    public static Session session = null;
    public static Transaction transaction = null;
    public static Date todayDate = Calendar.getInstance().getTime();

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
