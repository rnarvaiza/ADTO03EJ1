package CRUD;

import POJO.Empleado;
import Sandbox.HibernateUtil;

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
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            //Todos los registros
            List<Object[]> empleados = session.createNativeQuery("SELECT * FROM EMPLEADOS").list();
            for (Object[] objects : empleados) {
                Integer id = (Integer) objects[0];
                String nombre = (String) objects[1];
                String apellido = (String) objects[2];
                Integer salario = (Integer) objects[5];
                System.out.println("Empleado [" + apellido + "," + nombre + "," + salario + "," +id + "]");
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

}
