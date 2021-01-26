package CRUD;

import POJO.Empleado;
import Sandbox.HibernateUtil;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.HibernateError;
import org.hibernate.Session;
import org.hibernate.Transaction;
import Sandbox.Constants;
import org.hibernate.query.Query;

public class Select {
    public static void main(String[] args) {
        Session session = null;
        Transaction transaction = null;
        BigDecimal salarioMinimo = new BigDecimal(12000);
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            Query queryAll = session.createQuery("from Empleado");
            List<Empleado> listaTodosEmpleado = queryAll.list();
            for (Empleado empleado : listaTodosEmpleado) {
                if(empleado.getSalario().compareTo(salarioMinimo)==-1){
                    System.out.println("Empleado [" + empleado.getId() + "," + empleado.getNombre() + "," + empleado.getApellidos()+ "," +empleado.getSalario() + "]");
                }
            }
/*
            //Todos los registros
            List<Object[]> empleados = session.createNativeQuery("SELECT * FROM EMPLEADOS").list();
            for (Object[] objects : empleados) {
                Integer id = (Integer) objects[0];
                String nombre = (String) objects[1];
                String apellido = (String) objects[2];
                BigDecimal salario = (BigDecimal) objects[5];
                if (salario.compareTo(salarioMinimo)==1){
                    System.out.println("Empleado [" + apellido + "," + nombre + "," + salario + "," +id + "]");
                }

            }
            */
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

}
