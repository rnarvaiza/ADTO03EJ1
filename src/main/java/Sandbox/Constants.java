package Sandbox;

public class Constants {

    public static final String QUERY_SELECT_AP1 = "from Empleado";
    public static final String QUERY_SELECT_AP2 = "from Empleado e order by e.salario asc";
    public static final String QUERY_SELECT_AP3 = "from Empleado";
    public static final String QUERY_SELECT_AP4 = "from Empleado where salario>(select max(salario) from Empleado e where e.departamento.id=1)";
    public static final String QUERY_SELECT_AP5 = "from Empleado where salario<(select min(salario) from Empleado e where e.departamento.id=1)";
    public static final String QUERY_SELECT_AP6 = "from Departamento";
    public static final String QUERY_SELECT_AP7 = "from Proyecto";
    public static final String QUERY_SELECT_AP8 = "from Empleado where salario>50000";


   // select e.nombre, e.salario, e.departamento from Empleado e where e.salario>(select max(e.salario) from Empleado e where e.departamento.id=1)


}
