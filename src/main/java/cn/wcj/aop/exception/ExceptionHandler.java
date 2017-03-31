package cn.wcj.aop.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

/**
 * 
 *ͳһ�쳣����,��ΪDAO-->Service--->Handler�쳣���ǲ�������׳�,������
 *�쳣AOP���������ǳ������� 
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

	//�쳣ҳ�� 
	private static final String EXCEPTION_PAGE="redirect:/common/exception.jsp"  ; //�쳣ҳ�� 
	
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		ModelAndView exceptionView=new ModelAndView(EXCEPTION_PAGE)  ;
		//=================�������֮��������δ����==================================
		System.out.println(ex);
		//=================�������֮��������δ����==================================
		return exceptionView;
	}

}
