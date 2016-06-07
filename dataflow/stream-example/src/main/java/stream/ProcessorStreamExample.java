package stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

@SpringBootApplication
@MessageEndpoint
@EnableBinding(Processor.class)
public class ProcessorStreamExample {

	@ServiceActivator(inputChannel = Processor.INPUT,
			outputChannel = Processor.OUTPUT)
	public Message<String> out(Message<String> in) {
		return MessageBuilder
				.withPayload("{" + in.getPayload() + "}")
				.copyHeadersIfAbsent(in.getHeaders())
				.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(ProcessorStreamExample.class, args);
	}
}
