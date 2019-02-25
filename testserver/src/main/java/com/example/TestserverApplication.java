package com.example;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
 import java.util.List;
import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.example.dao.DossierRepository;
import com.example.dao.EmployeRepository;
import com.example.dao.ServiceRepository;
import com.example.entites.Dossier;
import com.example.entites.Employe;
import com.example.entites.Service;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FieldAccessor;

@SpringBootApplication
@EnableAutoConfiguration
 
public class TestserverApplication {
	public static String[] args;
	private static ConfigurableApplicationContext context;
	private static Connection connection;
 	// restart Spring 
	public static void restart() {
		// close previous context
	 context.close();
 		// and build new one
		TestserverApplication.context = SpringApplication.run(TestserverApplication.class, args);

	}

	public static void changeDataSource(String nameDB){
		context.refresh();
		DriverManagerDataSource databaseSource = (DriverManagerDataSource)context.getBean(nameDB);
		databaseSource.getUsername();	
	}

	public   void create(){
		Class<?> type = new ByteBuddy()
				.subclass(Object.class)
				.name("Person")
				.defineField("id", Integer.class)
				.defineMethod("getId", Integer.class)
				.intercept(FieldAccessor.ofBeanProperty())
				.defineMethod("setId", void.class).withParameter(Integer.class)
				.intercept(FieldAccessor.ofBeanProperty())
				.make()
				.load(getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
				.getLoaded();	
	}
	public static void main(String[] args) throws IOException {
		TestserverApplication.args = args;
   System.out.println("welcom to Java EE  ...");
		context=SpringApplication.run(TestserverApplication.class, args);
		System.out.println("welcom to Spring Boot ... ");
		System.out.println("welcom to Spring Data ...");
		 	
		ServiceRepository sr=  context.getBean(ServiceRepository.class);
		DossierRepository dr =context.getBean(DossierRepository.class);
		EmployeRepository er=  context.getBean(EmployeRepository.class);

		/*
 	for (int i = 1; i < 10; i++) {
			long l=new Long(i);
			er.save( new Employe("Employe"+i,((i*i)^i)+"","0669706401", l,new Date()));	 


		}
		//List<Employe> list =er.findByName("djamel");

//
//		for (int i = 1; i < 10; i++) {
//			Dossier d=new Dossier();
//		 	d.setch1("a1");
//		 	d.setch2("a2");
//		 	d.setch3("a3");
//		 	d.setch4("a4");
//		 	d.setch5("a5");
//
//		 	dr.save(d);	 
//		}
		for (int i = 1; i < 5; i++) {
			long l=new Long(i);
			sr.save( new Service("S"+i,"M"+i));	 
		} 
  
 */
		List<Employe> list =er.findAll();
		List<Dossier> list2 =dr.findAll();
		List<Service> list3 =sr.findAll();

		list.forEach(System.out::println);
		 
		list2.forEach(System.out::println);
		list3.forEach(System.out::println);


	}
	/*public static void initEntityManager() throws SQLException {
	    Connection connection =
	        DriverManager.getConnection("jdbc:mysql://localhost/?user=root?password=root");
	    Statement stmt = connection.createStatement();
	    stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS pppp" );
	    Persistence emf = (Persistence) Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
	    EntityManager em = ((EntityManagerFactory) emf).createEntityManager();
	}*/

	public static void configModale(String nameDataBase) throws IOException {
		if(nameDataBase!=null) {
			nameDataBase =nameDataBase.replaceAll("\\s","");}

		updatApplicationProperties(nameDataBase); 
		creatDataBase(nameDataBase);
		showDatabase();
	}

	public static void creatEntity(String  name, int index) throws IOException {
 String text = "Hello world";
		StringBuffer buffer= new StringBuffer() ;
		buffer.append ("package com.example.entites;\r\n" + 
				" \r\n" + 
				"import javax.persistence.Column;\r\n" + 
				"import javax.persistence.Entity;\r\n" + 
				"import javax.persistence.GeneratedValue;\r\n" + 
				"import javax.persistence.GenerationType;\r\n" + 
				"import javax.persistence.Id;\r\n" + 
				"\r\n" + 
				"import lombok.AllArgsConstructor;\r\n" + 
				"import lombok.Data;\r\n" + 
				"import lombok.NoArgsConstructor;\r\n" + 
				"import lombok.ToString;\r\n" + 
				"@Entity\r\n" + 
				"@Data\r\n" + 
				"@AllArgsConstructor\r\n" + 
				"@NoArgsConstructor\r\n" + 
				"@ToString\r\n" + 
				"public class Dossier {\r\n" + 
				"@Id\r\n" + 
				"@GeneratedValue(strategy = GenerationType.AUTO)\r\n" + 
				" @Column(name=\"id\")\r\n" + 
				"private Long id ;\r\n" + 
				"private String nom ;\r\n" + 
				"private String prenom ;\r\n" + 
				"private String tlphon ;\r\n");
  
		for (int i = 1; i < index+1; i++) {
			buffer.append ("private boolean ch"+i+" ;\n");
		}
		buffer.append ("}\n");

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
			//	JOptionPane.showMessageDialog(null,databaseName + " Database has been created successfully", "System Message", JOptionPane.INFORMATION_MESSAGE);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}
	public static int dropDataBase(String databaseName){
		try {
			// String databaseName = "d";
			String userName = "root";
			String password = "root";

			String url = "jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=convertToNull";
			connection = DriverManager.getConnection(url,userName, password);

			String sql = "DROP DATABASE IF  EXISTS model_" + databaseName;

			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);
			statement.close();
			//	JOptionPane.showMessageDialog(null,databaseName + " Database has been created successfully", "System Message", JOptionPane.INFORMATION_MESSAGE);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}
	public static void dropTable(String dbname) throws SQLException {
		// SQLite connection string
		String userName = "root";
		String password = "root";

		String url = "jdbc:mysql://localhost:3306/model_"+dbname;
  		//DROP TABLE IF EXISTS table_name;
		String sql1="DROP TABLE IF EXISTS  dossier ; ";

		try (Connection	connection = DriverManager.getConnection(url,userName, password);

			 Statement stmt = connection.createStatement()) {
			// Droup a new table
			stmt.execute(sql1);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	public static void createNewTable(String dbname,int index) throws SQLException {
		// SQLite connection string
		String userName = "root";
		String password = "root";

		String url = "jdbc:mysql://localhost:3306/model_"+dbname;
		// SQL statement for creating a new table
		StringBuffer bfr =new StringBuffer();
		//DROP TABLE IF EXISTS table_name;
		String sql1="DROP TABLE IF EXISTS  dossier ; ";
		bfr.append("CREATE TABLE  IF NOT EXISTS dossier (");
		bfr.append("id bigint NOT NULL AUTO_INCREMENT,");
		for (int i = 1; i <= index; i++) {
			bfr.append("ch"+i+" varchar(255) NOT NULL,");

		}
		bfr.append("PRIMARY KEY (id) );");

		String sql2= bfr.toString();


		try (Connection	connection = DriverManager.getConnection(url,userName, password);

				Statement stmt = connection.createStatement()) {
			// create a new table
			stmt.execute(sql1);
			stmt.execute(sql2);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public static ArrayList<String> showDatabase(){
		ArrayList<String> listData=new ArrayList<String>();
		// String databaseName = "d";
		String userName = "root";
		String password = "root";

		String url = "jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=convertToNull";
		try {
			connection = DriverManager.getConnection(url,userName, password);

			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SHOW DATABASES;");
			while (rs.next()) {
				String database = rs.getString(1);
				if (database.startsWith("model_")) {
					System.out.println(database + "\n");
					listData.add(database);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listData;

	}
	
	///mysql?zeroDateTimeBehavior=convertToNull
	public static String updateInitialApplicationProperties() throws IOException{
		String text;

		StringBuffer buffer= new StringBuffer() ;

		buffer.append ("spring.datasource.url=jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=convertToNull\r\n");
		buffer.append("spring.datasource.username=root\r\n" + 
				"spring.datasource.password=root\r\n" + 
				"spring.datasource.driverClassName=com.mysql.jdbc.Driver\r\n" + 
				"spring.jpa.hibernate.ddl-auto=update"
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
	
	public static String updatApplicationProperties2(String databaseName) throws IOException{

	 
	  // reading the existing properties
FileInputStream in = new FileInputStream("../testserver/src/main/resources/application.properties");
Properties props = new Properties();
props.load(in);
in.close();
// writing back the properties after updation
FileOutputStream out = new FileOutputStream("../testserver/src/main/resources/application.properties");
props.setProperty("spring.datasource.url", "jdbc::mysql:://localhost:3306/model_aaabbbccccc");
props.store(out, null);
out.close();
	  
return null;	
}

	public static String updatApplicationProperties(String databaseName) throws IOException{
		String text;

		StringBuffer buffer= new StringBuffer() ;

		buffer.append ("spring.datasource.url=jdbc:mysql://localhost:3306/model_"+databaseName+" \r\n");
		buffer.append("spring.datasource.username=root\r\n" + 
				"spring.datasource.password=root\r\n" + 
				"spring.datasource.driverClassName=com.mysql.jdbc.Driver\r\n" + 
				"spring.jpa.hibernate.ddl-auto=update"
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
