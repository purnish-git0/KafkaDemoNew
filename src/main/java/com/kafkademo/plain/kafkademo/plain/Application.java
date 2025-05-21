package com.kafkademo.plain.kafkademo.plain;

import com.kafkademo.plain.kafkademo.plain.executory.MyExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Application implements CommandLineRunner {

	private final MyExecutor executor;

	@Autowired
	private ApplicationContext applicationContext;

    public Application(MyExecutor executor) {
        this.executor = executor;
    }

	@Override
	public void run(String... args) throws Exception {
		this.executor.startStreamProcessing();
	}
}
