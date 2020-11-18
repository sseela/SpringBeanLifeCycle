package beans;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class Hello implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, BeanPostProcessor, InitializingBean, DisposableBean {
	
	private BeanFactory bf;
	private String beanName;
	private final List<Object> prototypeBeans = new LinkedList<>();
	
	public void m1() {
		System.out.println("operation starts and ends");
	}

	@Override
	public void setBeanName(String beanName) {
		System.out.println("BeanNameAware - setBeanName()");
		this.beanName = beanName;
	}


	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.bf = beanFactory;
		System.out.println("BeanFactoryAware - setBeanFactory()");
	}


	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		System.out.println("ApplicationContextAware - setApplicationContext()");
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("BeanPostProcessor - postProcessBeforeInitialization");
		return bean;
	}
	
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("BeanPostProcessor - postProcessAfterInitialization");
		if(bf.isPrototype(beanName)) {
			this.prototypeBeans.add(bean);
		}
		return bean;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("InitializingBean - afterPropertiesSet()");
	}


	@Override
	public void destroy() throws Exception {
		System.out.println("DisposableBean - destroy()");
		for(Object bean: prototypeBeans) {
			if(bean instanceof DisposableBean) {
				DisposableBean disposableBean = (DisposableBean)bean;
				disposableBean.destroy();
			}
		}
	}
	
	
}
