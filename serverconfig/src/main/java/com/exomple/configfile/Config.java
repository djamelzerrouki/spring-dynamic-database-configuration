package com.exomple.configfile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Config {

	public static void main(String[] args) throws IOException {
		// TODO main test methode by jimmi
		configAll("dddd",6);
		configAll("vvvb",9);
		configAll("djamel",2);
	}
	public static  void configAll(String name,int nbr) throws IOException  {
		// TODO main test methode by jimmi
		 	newMkdirss(FinalName.pathRepository,name);

		// addingRepository
		addingRepository(name,"Employe");
		addingRepository(name,"Book");
		addingRepository(name,"User");
		addingRepository(name,"Service");
		addingRepository(name,"Historique");
		addingRepository(name,"Dossier");
// creation Entity Dossier
		creatEntity(name,nbr);
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
				"\r\n" + 
				"import com.example.model."+nameEntity+";\r\n" + 
				"\r\n" + 
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
				"				.packages(\"com.example.model\").persistenceUnit(\""+name+"\").build();\r\n" + 
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

}
