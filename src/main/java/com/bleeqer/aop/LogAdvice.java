/*
 * package com.bleeqer.aop;
 * 
 * import org.aspectj.lang.annotation.Aspect; import
 * org.aspectj.lang.annotation.Before; import
 * org.springframework.stereotype.Component;
 * 
 * import lombok.extern.log4j.Log4j;
 * 
 * @Aspect // �ش� Ŭ������ ��ü�� aspect�� ������ ������ ��Ÿ��
 * 
 * @Log4j
 * 
 * @Component // spring�� bean���� �ν��ϱ� ���� public class LogAdvice {
 * 
 * // Pointcut
 * 
 * @Before( "execution(* com.bleeqer.service.SampleService*.*(..))") // *�� �������
 * ����������, Ŭ������, �޼ҵ���� �ǹ� public void logBefore() {
 * 
 * log.info("==================="); } }
 */

package com.bleeqer.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Aspect
@Log4j
@Component
public class LogAdvice {

  // �޼ҵ���� Ư���� �� Ÿ���� �����ϰ� args()���� argument ���� �����ؼ� advice�� ����
  @Before( "execution(* com.bleeqer.service.SampleService*.doAdd(String, String)) && args(str1, str2)")
  public void logBeforeWithParam(String str1, String str2) {

    log.info("str1: " + str1);
    log.info("str2: " + str2);
  }
  
  // afterthrowing ������̼��� ������ ����� ���ܸ� �߻���Ų �Ŀ� ������ pointcut�� �����ϰ� throwing���� ������ �������� ������
  @AfterThrowing(pointcut = "execution(* com.bleeqer.service.SampleService*.*(..))", throwing="exception")
  public void logException(Exception exception) {
	  
	  log.info("Exception....!!!!");
	  log.info("exception: " + exception);
  }
  
  @Around("execution(* com.bleeqer.service.SampleService*.*(..))")
  public Object logTime(ProceedingJoinPoint pjp) { // joinpoint is a point of execution of the program
	  
	  long start = System.currentTimeMillis();
	  
	  log.info("Target: " + pjp.getTarget());
	  log.info("Param: " + Arrays.toString(pjp.getArgs()));
	  
	  Object result = null;
	  
	  try {
		  result = pjp.proceed(); // executes the target
	  } catch (Throwable e) {
		  e.printStackTrace();
	  }
	  
	  long end = System.currentTimeMillis();
	  
	  log.info("TIME: " + (end - start));
	  
	  return result; // returns the result of the method directly
  }
}