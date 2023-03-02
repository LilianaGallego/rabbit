package sofka.demopracticarabbit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sofka.demopracticarabbit.model.Message;
import sofka.demopracticarabbit.services.RabbitMQSender;

import java.util.Random;

@SpringBootApplication
public class DemoPracticaRabbitApplication implements CommandLineRunner {

	@Autowired
	private RabbitMQSender rabbitMQSender;

	public static void main(String[] args) {
		SpringApplication.run(DemoPracticaRabbitApplication.class, args);
	}


	@Override
	public void run(String... args) {
		process("Sender-1");

		process("Sender-2");

		process("Sender-3");

	}

	private void process(String s) {
		new Thread(() -> {
			Random random = new Random();
			for (long i = 0; ; i++) {
				String message = "You have a new message with no " + i;
				rabbitMQSender.send(new Message(message));

				try {
					Thread.sleep(random.nextInt((15000 - 4000) + 1) + 4000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}, s).start();
	}
}
