package sofka.demopracticarabbit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sofka.demopracticarabbit.model.Message;
import sofka.demopracticarabbit.services.RabbitMQSender;

import java.io.IOException;

@RestController
@RequestMapping(value = "/rabbitmq/")
public class Controller {
    @Autowired
    private RabbitMQSender rabbitMQSender;

    @PostMapping(value = "/producer")
    public String producer(@RequestBody Message message) throws IOException {
        rabbitMQSender.send(message);
        return "Message sent to the RabbitMQ Successfully";
    }
}
