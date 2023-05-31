package com.prosilion.scdecisionmatrix.config;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

@Configuration
public class DatabaseConfig {
	@Bean
	public DataSource embeddedDataSource() {
		return new EmbeddedDatabaseBuilder()
				.setType(H2)
				.addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
				.build();
	}

	// TODO: add external datasource next POC
//  @Bean
//  DataSource externalDataSource() {
//    return new EmbeddedDatabaseBuilder()
//        .setType(H2)
//        .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
//        .build();
//  }

	/*************************************************
	 * keep this method handy/commented for reference/convenience testing
	 @Bean
	 public UserDetailsService inMemoryUserDetailsService() {
	 UserDetails user =
	 User.withUsername("user").password(passwordEncoder().encode("userpass")).roles("USER").build();
	 return new InMemoryUserDetailsManager(user);
	 }
	 *************************************************/
}
