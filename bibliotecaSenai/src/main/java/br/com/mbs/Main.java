package br.com.mbs;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableFeignClients
@EnableBatchProcessing
public class Main {

	@Autowired
    private Job processJob;

	@Autowired
    private JobLauncher jobLauncher;
	
	public static void main(String[] args) {
		   SpringApplication.run(Main.class, args);

	}
	
	@Bean
    public CommandLineRunner run() {
		return (args) -> {
			
			try {
				System.out.println("EXECUTANDO BATCH INICILIZACAO SPRING BOOT"); 
				JobParameters jobParameters = new JobParametersBuilder()
							.addLong("time", System.currentTimeMillis())
			                .toJobParameters();
			        jobLauncher.run(processJob, jobParameters);
		    	   
	    	  }catch(Exception e) {	    		  
	    		  
	    	  }
			
		};
	}

}
