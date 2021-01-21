package com.stackroute.activitystream.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/*This class will contain the application-context for the application. 
 * Define the following annotations:
 * @Configuration - Annotating a class with the @Configuration indicates that the 
 *                  class can be used by the Spring IoC container as a source of 
 *                  bean definitions
 * @ComponentScan - this annotation is used to search for the Spring components amongst the application
 * @EnableTransactionManagement - Enables Spring's annotation-driven transaction management capability.
 * @EnableAspectJAutoProxy - This spring aop annotation is used to enable @AspectJ support with Java @Configuration                
 * 
 * */

@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.stackroute.activitystream" })
@PropertySource(value = { "classpath:application.properties" })
@EnableAspectJAutoProxy
public class ApplicationContextConfig {

}
