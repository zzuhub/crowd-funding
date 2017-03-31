package cn.wcj.aop.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

/**
 * 
 *统一异常处理,因为DAO-->Service--->Handler异常都是层层向上抛出,这样用
 *异常AOP处理起来非常轻量级 
 * 
 * 
 * @author WangCJ
 *
 *
 *
 *
 */
@Component
public class ExceptionHandler extends AbstractHandlerExceptionResolver {

	//异常页面 
	private static final String EXCEPTION_PAGE="redirect:/common/exception.jsp"  ; //异常页面 
	
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		ModelAndView exceptionView=new ModelAndView(EXCEPTION_PAGE)  ;
		//=================开发完成之后屏蔽这段代码↓==================================
		System.out.println(ex);
		//=================开发完成之后屏蔽这段代码↑==================================
		return exceptionView;
	}

}
