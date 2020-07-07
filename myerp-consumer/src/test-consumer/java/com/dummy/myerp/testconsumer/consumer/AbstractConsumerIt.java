package com.dummy.myerp.testconsumer.consumer;

import java.io.File;
import java.nio.file.Path;

import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringJUnitConfig(locations = "classpath:/com/dummy/myerp/consumer/applicationContext.xml")
@Testcontainers
public abstract class AbstractConsumerIt {

	protected final Path projectPath = Path.of("").toAbsolutePath();

	// @Container
	protected DockerComposeContainer<?> dockerEnvironment = new DockerComposeContainer<>(
			new File(projectPath.getParent().toFile(), "docker/dev/docker-compose.yml"))
					.withExposedService("myerp.db_1", 5432);

}
