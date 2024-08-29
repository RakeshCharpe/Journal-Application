package com.hustlertechnology.JournalApplication;

import com.mongodb.client.MongoClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class JournalApplication {

	public static void main(String[] args) {
		SpringApplication.run(JournalApplication.class, args);
	}

	//Interface - PlatformTransactionalManager --> implementation - MongoTransactionManager


	//EnableTransactionManagement search  karti hai ki konsi bean hai jo platformtransactionManager implement kar rahi hai

	@Bean
	public  PlatformTransactionManager add(MongoDatabaseFactory dbFactory){
	return new MongoTransactionManager(dbFactory);
	}
}
