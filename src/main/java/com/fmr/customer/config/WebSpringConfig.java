package com.fmr.customer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {
        "com.fmr.customer.manager",
        "com.fmr.customer.mapper"
         })
@MapperScan("com.fmr.customer.mapper")
public class WebSpringConfig extends WebMvcConfigurerAdapter{  
	@Bean(name="dataSource")
	@Primary
    public DataSource getDataSource() {
       DriverManagerDataSource dataSource = new DriverManagerDataSource();
       dataSource.setDriverClassName("com.mysql.jdbc.Driver");
       dataSource.setUrl("jdbc:mysql://localhost:3306/customer-service");
       dataSource.setUsername("root");
       dataSource.setPassword("root");
       return dataSource;
   }
	 @Bean
	    public InternalResourceViewResolver jspViewResolver() {
	        InternalResourceViewResolver bean = new InternalResourceViewResolver();
	        bean.setPrefix("/WEB-INF/");
	        bean.setSuffix(".jsp");
	        return bean;
	    }
	@Bean
	   public DataSourceTransactionManager transactionManager() {
	       return new DataSourceTransactionManager(getDataSource());
	   }
	   @Bean
	   public SqlSessionFactory getSqlSessionFactory() throws Exception {
	      SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
	      sessionFactory.setDataSource(getDataSource());
	      sessionFactory.setTypeAliasesPackage("com.fmr.customer.model");
	      SqlSessionFactory theSqlSessionFactory=sessionFactory.getObject();
	      theSqlSessionFactory.getConfiguration().setJdbcTypeForNull(JdbcType.NULL);
	      return sessionFactory.getObject();
	   }

	    
}
