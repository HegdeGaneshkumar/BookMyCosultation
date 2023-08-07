package com.upgrad.notificationservice;

import com.upgrad.notificationservice.email.EmailServiceImpl;
import com.upgrad.notificationservice.model.MailEntity;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.Set;

@RequiredArgsConstructor
@SpringBootApplication
public class NotificationServiceApplication {

	private static EmailServiceImpl emailService = new EmailServiceImpl();

	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);

		Properties properties = new Properties();
		properties.setProperty("bootstrap.servers", "ec2-35-173-141-5.compute-1.amazonaws.com:9092");
		properties.setProperty("group.id", "doctor-service");
		properties.setProperty("enable.auto.commit", "true");
		properties.setProperty("auto.commit.interval.ms", "1000");
		properties.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

		org.apache.kafka.clients.consumer.KafkaConsumer<String, String> consumer = new org.apache.kafka.clients.consumer.KafkaConsumer<String, String>(properties);
		consumer.subscribe(Arrays.asList("doctor-service", "appointment-service", "user-service"));

		Set<String> subsribedTopics = consumer.subscription();

		subsribedTopics.stream().forEach(System.out::println);

		try{
			while(true){
				ConsumerRecords<String, String > records = consumer.poll(Duration.ofMillis(100));
				for(ConsumerRecord<String, String> record: records){
					System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
					System.out.println("Sending an email to "+ record.key());
					MailEntity mailEntity = new MailEntity();
					if(record.key().isBlank())
						continue;
					mailEntity.setEmailId(record.key());
					mailEntity.setSubject("Mail from BMC");
					mailEntity.setMessage(record.value());
					emailService.initVerify();
					emailService.verifyEmail(mailEntity.getEmailId());
					try {
						emailService.init();
						emailService.sendEmail(mailEntity);
						System.out.println("Email sent successfully");
					}
					catch (Exception e){
						System.out.println(e);
					}
				}
			}
		}
		finally {
			consumer.close();
		}
	}

}
