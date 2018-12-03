package com.example;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.dao.EnployeeRepository;
import com.example.entites.Enployee;

@SpringBootApplication
public class TestserverApplication {
	private static Connection connection;
	private static String nameDataBase="p";
	public static void main(String[] args) throws IOException {
		//	creatDataBase("mydatabasetest");
		updatApplicationProperties(nameDataBase); 

		creatDataBase(nameDataBase);
		showDatabase();

		System.out.println("Base  mytabl1 created  ...");


		creatEntity("mytabl1",5);
		System.out.println("Table mytabl1 created  ...");

		creatEntity("mytabl2",3);
		System.out.println("Table mytabl4 created  ...");

		creatEntity("mytabl3",4);	
		System.out.println("Table mytabl3 created  ...");


		System.out.println("welcom to Java EE  ...");
		ApplicationContext ctx=SpringApplication.run(TestserverApplication.class, args);
		System.out.println("welcom to Spring Boot ... ");
		System.out.println("welcom to Spring Data ...");
		//	public Enployee(String name, String pasword, Long servicid, Date datenes) {


		EnployeeRepository er=  ctx.getBean(EnployeeRepository.class);
for (int i = 0; i < 20; i++) {
	long l=new Long(i);
	er.save( new Enployee("Enployee"+i,((i*i)^i)+"", l,new Date()));	 
	
}
	 	 


		//List<Enployee> list =er.findByName("djamel");
		List<Enployee> list =er.findAll();

		list.forEach(enpl->{
			System.out.println("#### :"+enpl.getId()+" NAME: "+enpl.getName()+" DATE: "
					+ ""+enpl.getDatenes());
		} );


	}
	/*public static void initEntityManager() throws SQLException {
	    Connection connection =
	        DriverManager.getConnection("jdbc:mysql://localhost/?user=root?password=root");
	    Statement stmt = connection.createStatement();
	    stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS pppp" );
	    Persistence emf = (Persistence) Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
	    EntityManager em = ((EntityManagerFactory) emf).createEntityManager();
	}*/

	public static void creatEntity(String  name,int index) throws IOException {

		String text = "Hello world";
		StringBuffer buffer= new StringBuffer() ;
		buffer.append ("package com.example.entites;\r\n" + 
				"import java.util.Date;\r\n" + 
				"\r\n" + 
				"import javax.persistence.Column;\r\n" + 
				"import javax.persistence.Entity;\r\n" + 
				"import javax.persistence.GeneratedValue;\r\n" + 
				"import javax.persistence.GenerationType;\r\n" + 
				"import javax.persistence.Id;\n");
		buffer.append ("@Entity\n");
		buffer.append ("public class "+ name +" {\n");
		buffer.append ("@Id\n");
		buffer.append ("@GeneratedValue(strategy = GenerationType.AUTO)\n");
		buffer.append (" @Column(name=\"id\")\n");
		buffer.append ("private Long id ;\n");



		/*     public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    } */
		for (int i = 1; i < index+1; i++) {
			buffer.append ("private String ch"+i+" ;\n");
			buffer.append ("public String getch"+i+"() { ;\n");
			buffer.append (" return ch"+i+"; \n");
			buffer.append ("}\n");

		}
		buffer.append ("}\n");
		/*  

     @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")

		 */

		text=buffer.toString();
		BufferedWriter output = null;
		try {
			File file = new File("../testserver\\src\\main\\java\\com\\example\\entites\\"+name+".java");
			output = new BufferedWriter(new FileWriter(file));
			output.write(text);
			output.flush();
		} catch ( IOException e ) {
			e.printStackTrace();
		} finally {
			if ( output != null ) {
				output.close();
			}
		}



	}

	public static int creatDataBase(String databaseName){
		try {
			// String databaseName = "d";
			String userName = "root";
			String password = "root";

			String url = "jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=convertToNull";
			connection = DriverManager.getConnection(url,userName, password);

			String sql = "CREATE DATABASE IF NOT EXISTS model_" + databaseName;

			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);
			statement.close();
			JOptionPane.showMessageDialog(null,databaseName + " Database has been created successfully", "System Message", JOptionPane.INFORMATION_MESSAGE);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	private static int showDatabase(){
		Statement stmt;
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SHOW DATABASES;");
			while (rs.next()) {
				String database = rs.getString(1);
				if (database.startsWith("model_")) {
					System.out.println(database + "\n");

				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}
	static String updatApplicationProperties(String databaseName) throws IOException{
		String text;

		StringBuffer buffer= new StringBuffer() ;

		buffer.append ("spring.datasource.url=jdbc:mysql://localhost:3306/model_"+databaseName+" \r\n");
		buffer.append("spring.datasource.username=root\r\n" + 
				"spring.datasource.password=root\r\n" + 
				"spring.datasource.driverClassName=com.mysql.jdbc.Driver\r\n" + 
				"spring.jpa.hibernate.ddl-auto=create-drop"
				);  


		text=buffer.toString();
		BufferedWriter output = null;
		try {
			File file = new File("../testserver/src/main/resources/application.properties");
			output = new BufferedWriter(new FileWriter(file));
			output.write(text);
			output.flush();
		} catch ( IOException e ) {
			e.printStackTrace();
		} finally {
			if ( output != null ) {
				output.close();
			}
		}



		return null;	
	}


}
