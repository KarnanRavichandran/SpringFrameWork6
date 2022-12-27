AOP - > Aspect Oriented Programming

Every application has multiple layers like web,business,data layers.
Every layer have different responsibilities assigned to them, but there are few aspects which are common to every layer in our application.
Like security, performance and logging, those are called cross-cutting concerns.

Cross-cutting concerns can be implemented using AOP. Popular Frameworks: Spring AOP and AspectJ

in AOP,
    1. We define the cross cuttings concerns as aspects
    2. And define point cut, indicating where the aspect should be applied.

Logging Aspect:

Make a class with @Configuration @Aspect
- define a method for logging and add @PointCut and the code we write inside is called Advice.
- Aspect is the combination of Advice and PointCut

    @Before("execution(* com.timothy.aopdemo.*.*.*(..))")  //Intercepting method calls //packageName.*.* - specific package name OR *.*.* for all packages in the Application.
    public void logMethodCall(JoinPoint joinPoint){
        logger.info("method is called -{}",joinPoint);  //Advice

        //When the PointCut condition is true, Advice is executed.
        //Specific Execution Instance of the Advice is called Join Point. You can also get back information using joinPoint.methodName.
    }

   Log:
   2022-12-27T11:41:34.714+05:30  INFO 12844 --- [  restartedMain] c.t.a.a.LoggingAspect$$SpringCGLIB$$0    : method is called -execution(int com.timothy.aopdemo.business.Service1.calMaths())
   2022-12-27T11:41:34.721+05:30  INFO 12844 --- [  restartedMain] c.t.a.a.LoggingAspect$$SpringCGLIB$$0    : method is called -execution(int[] com.timothy.aopdemo.data.DataService1.retrieveData())

Other Annotations:

@Before - do something before a method is called.

@After - do something after a method is called. - No matter method is executed successfully or Method throws Exception.

@AfterRunning - do something only if method is successful.
@AfterThrowing - do something only if method returns an exception.



Performance Aspect:

@Around - before and after executing a method. // Time Calc.


