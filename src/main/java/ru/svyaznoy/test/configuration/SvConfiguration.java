package ru.svyaznoy.test.configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.sql.DataSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "ru.svyaznoy.test")
public class SvConfiguration extends WebMvcConfigurerAdapter {

	public String loadPropertiesFile(String requestParam) throws Exception {
		InputStream inputStream = null;
		ClassLoader classLoader = null;
		String parameter = null;

		try {
			Properties properties = new Properties();
			String propertiesFile = "properties/dbconfig.properties";
			inputStream = getClass().getClassLoader().getResourceAsStream(propertiesFile);

			if (inputStream != null) {
				properties.load(inputStream);
			} else {
				throw new FileNotFoundException("Property file '" + propertiesFile + "'not found in the classpath");
			}

			parameter = properties.getProperty(requestParam);
			System.out.println("/////////////////////////////////////////////");
			System.out.println(parameter);
			System.out.println("/////////////////////////////////////////////");
		} catch (Exception e) {
			System.err.println("Error! Exception:" + e);
		} finally {
			inputStream.close();
		}

		return parameter;
	}
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		registry.viewResolver(viewResolver);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("/static/");
	}

	@Bean
	public DataSource dataSource() throws Exception {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(loadPropertiesFile("sql.driver"));
		dataSource.setUrl("jdbc:mysql://localhost:3306/svyaznoy?useUnicode=yes&characterEncoding=UTF-8");
		dataSource.setUsername("root");
		dataSource.setPassword("");
		return dataSource;
	}
}