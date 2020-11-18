package Client;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import beans.Bike;
import beans.Hello;

public class Test {

	public static void main(String[] args) {
		/*Resource res = new ClassPathResource("resources/spring.xml");
		BeanFactory beanFac = new XmlBeanFactory(res);*/
		// loading spring xml file into core ioc container
		// if we use core container then instantiation is not done on xml loading, but instantiation is done on first request getBean.
		// by default all the beans are singleton in spring xml file unless we write explicitly that this bean should be prototype
		// when spring bean is singleton, then only one object is created and the same object is used on multiple requests.
		/*System.out.println("first request for bean");
		Car c = (Car)beanFac.getBean("car");
		System.out.println(c.getPrice());
		System.out.println(c.getEngine().getModel());*/
		
		
		
		// J2EE container - ApplicationContext - to use j2ee container we have to use xsd instead of dtd.
		/*System.out.println("before loading xml");*/
		ClassPathXmlApplicationContext ap = new ClassPathXmlApplicationContext("resources/spring.xml");
		// If we use j2ee container, then as soon as xml is loaded, ApplicationContext will create instantiation of singleton beans and inject the dependencies
		Hello hello = ap.getBean("hello", Hello.class);
		hello.m1();
		ap.getBean("bike", Bike.class);
		ap.registerShutdownHook();

	}

}
