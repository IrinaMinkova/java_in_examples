package com.github.vedenin.rus.spring_xml;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Пример использования DI Spring инъекции в метод-сеттер,
 * используя аннотации Spring и конфигурацию через xml файлы
 *
 * Классу Notifier нужен класс реализующий интерфейс NotificationService,
 * чтобы отправить сообщение пользователю. Получим эту зависимость через DI
 *
 * Created by vvedenin on 11/14/2015.
 */
public class XmlSetterAutowiredTest {
    public static class Notifier {
        private NotificationService service;

        @Autowired
        public void setService(NotificationService service) {
            this.service = service;
        }

        public void send(String message) {
            service.send("email: " + message);
        }
    }

    public static class EMailService implements NotificationService {
        public void send(String message) {
            System.out.println("I send " + message);
        }
    }

    public interface NotificationService {
        void send(String message);
    }

    public static void main(String[] args)  throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "fieldAutowired.xml");
        Notifier notifier =  context.getBean(Notifier.class);
        notifier.send("test email"); // Print "I send email: test email"
    }
}
