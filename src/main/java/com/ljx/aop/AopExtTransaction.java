package com.ljx.aop;//package com.itmayiedu.aop;

import java.lang.reflect.Method;

import com.ljx.annotation.ExtTransaction;
import com.ljx.transaction.TransactionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;

//  自定义事务注解具体实现
@Aspect
@Component
public class AopExtTransaction {
	// 一个事务实例子 针对一个事务
	@Autowired
	private TransactionUtils transactionUtils;

	// 使用异常通知进行 回滚事务
	@AfterThrowing("execution(* com.ljx.service.*.*.*(..))")
	public void afterThrowing() {
		// 获取当前事务进行回滚
		// TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		transactionUtils.rollback();
	}

	// 环绕通知 在方法之前和之后处理事情
	@Around("execution(* com.ljx.service.*.*.*(..))")
	public void around(ProceedingJoinPoint pjp) throws Throwable {

		// 1.获取该方法上是否加上注解
		ExtTransaction extTransaction = getMethodExtTransaction(pjp);
		TransactionStatus transactionStatus = begin(extTransaction);
		// 2.调用目标代理对象方法
		pjp.proceed();
		// 3.判断该方法上是否就上注解
		commit(transactionStatus);
	}

	private TransactionStatus begin(ExtTransaction extTransaction) {
		if (extTransaction == null) {
			return null;
		}
		// 2.如果存在事务注解,开启事务
		return transactionUtils.begin();
	}

	private void commit(TransactionStatus transactionStatus) {
		if (transactionStatus != null) {
			// 5.如果存在注解,提交事务
			transactionUtils.commit(transactionStatus);
		}

	}

	// 获取方法上是否存在事务注解
	private ExtTransaction getMethodExtTransaction(ProceedingJoinPoint pjp)
			throws NoSuchMethodException, SecurityException {
		String methodName = pjp.getSignature().getName();
		// 获取目标对象
		Class<?> classTarget = pjp.getTarget().getClass();
		// 获取目标对象类型
		Class<?>[] par = ((MethodSignature) pjp.getSignature()).getParameterTypes();
		// 获取目标对象方法
		Method objMethod = classTarget.getMethod(methodName, par);
		ExtTransaction extTransaction = objMethod.getDeclaredAnnotation(ExtTransaction.class);
		return extTransaction;
	}

}
