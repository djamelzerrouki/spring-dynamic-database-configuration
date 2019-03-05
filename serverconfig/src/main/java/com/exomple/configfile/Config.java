package com.exomple.configfile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.Employe;
import com.example.model.Service;

public class Config {
	private static Connection connection;

	public static void main(String[] args) throws IOException {
		// TODO main test methode by jimmi
		configAll("djamel",6);
		configAll("a",6);
		configAll("b",6);
		configAll("c",6);
		  configAll("permis",6);


		/*
		 configAll("passeport",9);
		configAll("cartebiometrique",2);
		*/
	 System.out.println("Don!");	
	 }
	public static  void configAll(String name,int nbr) throws IOException  {
		// TODO main test methode by jimmi
		 	newMkdirss(FinalName.pathRepository,name);
		 // creation Entity Dossier
			creatEntity(name,nbr);
		// addingRepository
		addingRepository(name,"Employe");
 		addingRepository(name,"Service");
		addingRepository(name,"Historique");
		addingRepository(name,"Dossier");
		//creation de modele controller web 
		creatController(name);
		// creation new database with JDBC conecter 
		creatDataBase(name);
		// creation creat Config DB Class 
		creatConfigDBClass(name);
		// config Properties File
		configPropertiesFile(name);	

	}
	// config dosser
	public static  void configDossier(String name,int nbr) throws IOException  {
		 	creatEntity(name,nbr);
		 	addingRepository(name,"Dossier");
		 
	}
	// config DB 
	public static  void configDB(String name ) throws IOException  {
		// TODO main test methode by jimmi
		 	newMkdirss(FinalName.pathRepository,name);
		  
		// addingRepository
		addingRepository(name,"Employe");
 		addingRepository(name,"Service");
		addingRepository(name,"Historique");
 		//creation de modele controller web 
		creatController(name);
		// creation new database with JDBC conecter 
		creatDataBase(name);
		// creation creat Config DB Class 
		creatConfigDBClass(name);
		// config Properties File
		configPropertiesFile(name);	

	}
	public static  void newMkdirss(String path,String nameModel)  {
		File dir = new File (path+nameModel);
		dir.mkdirs();
	}
	public static  void addingRepository(String nameModel,String nameEntity) throws IOException {

		String text = "Hello world";

		StringBuffer buffer= new StringBuffer() ;
		buffer.append ("package com.example.repository."+nameModel+";\r\n" + 
				"\r\n" + 
				"import org.springframework.data.jpa.repository.JpaRepository;\r\n" + 
				"import org.springframework.stereotype.Repository;\r\n" + 
				"\r\n");
		if (nameEntity=="Dossier") {
			buffer.append ("import com.example.repository."+nameModel+"."+nameEntity+";\r\n" );

		}else {
				buffer.append ("import com.example.model."+nameEntity+";\r\n" );
		}
						buffer.append ("\r\n" + 
				"@Repository\r\n" + 
				"public interface Repo"+nameEntity+nameModel+" extends JpaRepository<"+nameEntity+", Long>{\r\n" + 
				"\r\n" + 
				"}\r\n");


		text=buffer.toString();
		BufferedWriter output = null;
		try {
			File file = new File(FinalName.pathRepository+nameModel+"/"+"Repo"+nameEntity+nameModel+".java");
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
	public static void creatConfigDBClass(String  name) throws IOException {
		String text = "Hello world";
		newMkdirss(FinalName.pathRepository,name);

		StringBuffer buffer= new StringBuffer() ;
		buffer.append ("package com.example.config;\r\n" + 
				"\r\n" + 
				"import java.util.HashMap;\r\n" + 
				"\r\n" + 
				"import javax.persistence.EntityManagerFactory;\r\n" + 
				"import javax.sql.DataSource;\r\n" + 
				"\r\n" + 
				"import org.springframework.beans.factory.annotation.Qualifier;\r\n" + 
				"import org.springframework.boot.context.properties.ConfigurationProperties;\r\n" + 
				"import org.springframework.boot.jdbc.DataSourceBuilder;\r\n" + 
				"import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;\r\n" + 
				"import org.springframework.context.annotation.Bean;\r\n" + 
				"import org.springframework.context.annotation.Configuration;\r\n" + 
				"import org.springframework.data.jpa.repository.config.EnableJpaRepositories;\r\n" + 
				"import org.springframework.orm.jpa.JpaTransactionManager;\r\n" + 
				"import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;\r\n" + 
				"import org.springframework.transaction.PlatformTransactionManager;\r\n" + 
				"import org.springframework.transaction.annotation.EnableTransactionManagement;\r\n" + 
				"\r\n" + 
				"@Configuration\r\n" + 
				"@EnableTransactionManagement\r\n" + 
				"@EnableJpaRepositories(entityManagerFactoryRef = \""+name+"EntityManagerFactory\", transactionManagerRef = \""+name+"TransactionManager\", basePackages = {\r\n" + 
				"		\"com.example.repository."+name+"\" })\r\n" + 
				"public class ConfDB"+name+" {\r\n" + 
				"\r\n" + 
				"	@Bean(name = \""+name+"DataSource\")\r\n" + 
				"	@ConfigurationProperties(prefix = \"spring."+name+".datasource\")\r\n" + 
				"	public DataSource dataSource() {\r\n" + 
				"		return DataSourceBuilder.create().build();\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	@Bean(name = \""+name+"EntityManagerFactory\")\r\n" + 
				"	public LocalContainerEntityManagerFactoryBean "+name+"EntityManagerFactory(EntityManagerFactoryBuilder builder,\r\n" + 
				"			@Qualifier(\""+name+"DataSource\") DataSource dataSource) {\r\n" + 
				"		HashMap<String, Object> properties = new HashMap<>();\r\n" + 
				"		properties.put(\"hibernate.hbm2ddl.auto\", \"update\");\r\n" + 
				"		properties.put(\"hibernate.dialect\", \"org.hibernate.dialect.MySQL5Dialect\");\r\n" + 
				"		return builder.dataSource(dataSource).properties(properties)\r\n" + 
				"				.packages(\"com.example.model\",\"com.example.repository."+name+"\").persistenceUnit(\""+name+"\").build();\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	@Bean(name = \""+name+"TransactionManager\")\r\n" + 
				"	public PlatformTransactionManager "+name+"TransactionManager(\r\n" + 
				"			@Qualifier(\""+name+"EntityManagerFactory\") EntityManagerFactory "+name+"EntityManagerFactory) {\r\n" + 
				"		return new JpaTransactionManager("+name+"EntityManagerFactory);\r\n" + 
				"	}\r\n" + 
				"}\r\n" 
				);


		text=buffer.toString();
		BufferedWriter output = null;
		try {
			File file = new File(FinalName.pathconfigDB+"ConfDB"+name+".java");
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

	public static void configPropertiesFile(String name) {

		try(FileWriter fw = new FileWriter(FinalName.pathApplicationProperties, true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw))
		{
			String textconfig;

			StringBuffer buffer= new StringBuffer() ;

			buffer.append ("spring."+name+".datasource.jdbcUrl=jdbc:mysql://localhost:3306/model_"+name+" \r\n");
			buffer.append("spring."+name+".datasource.username=root\r\n");  
			buffer.append("spring."+name+".datasource.password=root\r\n");   
			buffer.append("spring."+name+".datasource.driverClassName=com.mysql.jdbc.Driver\r\n");  
			buffer.append("spring."+name+".jpa.hibernate.ddl-auto=update\r\n");

			textconfig=buffer.toString();
			out.println("#"+name);
			out.println(textconfig);

			//more code
		} catch (IOException e) {
			//exception handling left as an exercise for the reader
		}
	}
	public static void creatEntity(String  name, int index) throws IOException {
		String text = "Hello world";
		StringBuffer buffer= new StringBuffer() ;
		buffer.append ("package com.example.repository."+name+";\r\n" + 
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
			File file = new File(FinalName.pathRepository+name+"/Dossier.java");
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
	//dropDataBase
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
// creatDataBase
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
	// showDatabase
	public static ArrayList<String> showDatabase(){
	ArrayList<String> listData=new ArrayList<String>();
	// String databaseName = "d";
	String userName = "root";
	String password = "root";

	String url = "jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=convertToNull" ;
	
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

	// CREATE NEW CONTROLLER WEB 
	public static void creatController(String  name) throws IOException {
		String text = "Hello world";
		StringBuffer buffer= new StringBuffer() ;
		buffer.append ("package com.example;\r\n" + 
				"\r\n" + 
				"import java.io.IOException;\r\n" + 
				"import java.sql.SQLException;\r\n" + 
				"import java.util.List;\r\n" + 
				"\r\n" + 
				"import org.springframework.beans.factory.annotation.Autowired;\r\n" + 
				"import org.springframework.stereotype.Controller;\r\n" + 
				"import org.springframework.ui.Model;\r\n" + 
				"import org.springframework.web.bind.annotation.PostMapping;\r\n" + 
				"import org.springframework.web.bind.annotation.RequestMapping;\r\n" + 
				"import org.springframework.web.bind.annotation.RequestMethod;\r\n" + 
				"import org.springframework.web.bind.annotation.RequestParam;\r\n" + 
				"\r\n" + 
				"import com.example.model.Employe;\r\n" + 
				"import com.example.model.Service;\r\n" + 
				"import com.example.repository."+name+".RepoEmploye"+name+";\r\n" + 
				"import com.example.repository."+name+".RepoService"+name+";\r\n" + 
				"import com.exomple.configfile.Config;\r\n" + 
				"@Controller \r\n" + 
				"@RequestMapping(value=\"/model_"+name+"\")\r\n" + 
				"public class ControllerRep"+name+" {\r\n" +
				"	\r\n" + 
				"	@Autowired \r\n" + 
				"	private   RepoEmploye"+name+" red ;\r\n" + 
				"	@Autowired \r\n" + 
				"	private   RepoService"+name+" rsd ;\r\n" + 
				"	 	 \r\n"
				+ "private static String namedb; \r\n" + 
				"	\r\n" + 
				"	// Modele selectioner\r\n" + 
				"	@RequestMapping(\"/model\" )\r\n" + 
				"	public String model(Model model,@RequestParam(name = \"m\") String namemodel) throws IOException, SQLException {\r\n" + 
				"		//	ArrayList <String> list=TestserverApplication.showDatabase() ;\r\n" + 
				"\r\n" + 
				"		namemodel =namemodel.replaceAll(\"\\\\s\",\"\");\r\n" + 
				"		namedb=namemodel;\r\n" + 
				" 		model.addAttribute(\"modelName\",namedb);\r\n" + 
				" \r\n" + 
				"		return \"showModel\";\r\n" + 
				"\r\n" + 
				"	}" + 
				"	//Employe\r\n" + 
				"		@RequestMapping(value=\"/Employe\" ,method=RequestMethod.GET)\r\n" + 
				"		public String formEmploye(Model model) {\r\n" + 
				"			List<Employe> list = red.findAll();\r\n" + 
				"			model.addAttribute(\"enployee\",new Employe());\r\n" + 
				"			model.addAttribute(\"enployees\",list);\r\n" + 
				"	 	return \"addenployee\";\r\n" + 
				"		}\r\n" + 
				"		//Service\r\n" + 
				"			@RequestMapping(value=\"/Service\" ,method=RequestMethod.GET)\r\n" + 
				"			public String formService(Model model) {\r\n" + 
				"				List<Service> list = rsd.findAll();\r\n" + 
				"				model.addAttribute(\"service\",new Employe());\r\n" + 
				"				model.addAttribute(\"services\",list);\r\n" + 
				"		 	return \"addservice\";\r\n" + 
				"			}\r\n" + 
				"\r\n" + 
				"@RequestMapping(value=\"/saveEnployee\" ,method=RequestMethod.POST)\r\n" + 
				"			public String saveEnployee(Employe ep) {\r\n" + 
				"				red.save(ep);\r\n" + 
				"				return \"redirect:Employe\";\r\n" + 
				"			}	\r\n" + 
				"			//saveService\r\n" + 
				"			@RequestMapping(value=\"/saveService\" ,method=RequestMethod.POST)\r\n" + 
				"			public String saveService(Service srv) {\r\n" + 
				"				rsd.save(srv);\r\n" + 
				"				return \"redirect:Service\";\r\n" + 
				"			}"
				+ "\r\n"
				+ "@RequestMapping(value=\"/bpmn\")\r\n" + 
				"	public String bpmnModele()  {\r\n" + 
				" 		return \"bpmn\";\r\n" + 
				"	}" + 
				"}\r\n" );

		text=buffer.toString();
		BufferedWriter output = null;
		try {
			File file = new File(FinalName.pathPackage+"ControllerRep"+name+".java");
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
	
}
