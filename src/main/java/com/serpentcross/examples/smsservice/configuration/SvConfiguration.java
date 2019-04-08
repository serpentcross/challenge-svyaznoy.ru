package com.serpentcross.examples.smsservice.configuration;

import java.io.FileNotFoundException;
import java.io.InputStream;

import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.sql.DataSource;

import com.serpentcross.examples.smsservice.constants.DataBaseProperties;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.serpentcross.examples.smsservice")
public class SvConfiguration implements WebMvcConfigurer {

	private LinkedHashMap<String, String> loadPropertiesFile() throws Exception {

		LinkedHashMap<String, String> connectionSettings = new LinkedHashMap<>();
		InputStream inputStream = null;

		try {
			Properties properties = new Properties();
			String propertiesFile = "properties/dbconfig.properties";
			inputStream = getClass().getClassLoader().getResourceAsStream(propertiesFile);

			if (inputStream != null) {
				properties.load(inputStream);
			} else {
				throw new FileNotFoundException("Property file '" + propertiesFile + "'not found in the classpath");
			}

			connectionSettings.put(DataBaseProperties.SQL_DRV, properties.getProperty(DataBaseProperties.SQL_DRV));
			connectionSettings.put(DataBaseProperties.SQL_URL, properties.getProperty(DataBaseProperties.SQL_URL));
			connectionSettings.put(DataBaseProperties.SQL_USR, properties.getProperty(DataBaseProperties.SQL_USR));
			connectionSettings.put(DataBaseProperties.SQL_PSW, properties.getProperty(DataBaseProperties.SQL_PSW));

		} catch (Exception e) {
			System.err.println("Error! Exception:" + e);
		} finally {
			Objects.requireNonNull(inputStream).close();
		}

		return connectionSettings;
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

		dataSource.setDriverClassName(loadPropertiesFile().get(DataBaseProperties.SQL_DRV));
		dataSource.setUrl(loadPropertiesFile().get(DataBaseProperties.SQL_URL));
		dataSource.setUsername(loadPropertiesFile().get(DataBaseProperties.SQL_USR));
		dataSource.setPassword(loadPropertiesFile().get(DataBaseProperties.SQL_PSW));

		return dataSource;
	}
}