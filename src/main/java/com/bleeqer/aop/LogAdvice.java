/*
 * package com.bleeqer.aop;
 * 
 * import org.aspectj.lang.annotation.Aspect; import
 * org.aspectj.lang.annotation.Before; import
 * org.springframework.stereotype.Component;
 * 
 * import lombok.extern.log4j.Log4j;
 * 
 * @Aspect // 해당 클래스의 객체는 aspect를 구현한 것임을 나타냄
 * 
 * @Log4j
 * 
 * @Component // spring의 bean으로 인식하기 위함 public class LogAdvice {
 * 
 * // Pointcut
 * 
 * @Before( "execution(* com.bleeqer.service.SampleService*.*(..))") // *는 순서대로
 * 접근제한자, 클래스명, 메소드명을 의미 public void logBefore() {
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

  // 메소드명을 특정할 때 타입을 설정하고 args()에서 argument 명을 설정해서 advice에 전달
  @Before( "execution(* com.bleeqer.service.SampleService*.doAdd(String, String)) && args(str1, str2)")
  public void logBeforeWithParam(String str1, String str2) {

    log.info("str1: " + str1);
    log.info("str2: " + str2);
  }
  
  // afterthrowing 어노테이션은 지정된 대상이 예외를 발생시킨 후에 동작함 pointcut을 결정하고 throwing에서 예외의 변수명을 설정함
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