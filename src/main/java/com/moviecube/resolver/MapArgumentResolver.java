package com.moviecube.resolver;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.moviecube.common.CommandMap;


public class MapArgumentResolver implements HandlerMethodArgumentResolver{

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return CommandMap.class.isAssignableFrom(parameter.getParameterType());
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
								  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		
		CommandMap commandMap = new CommandMap();
		
		HttpServletRequest request = (HttpServletRequest)webRequest.getNativeRequest();
		Enumeration<?> enumeration = request.getParameterNames();	// '?name=승현'으로 url 쿼리가 오면 
																	// 'name'이라는 파라미터 이름만 뽑아서 enumeration에 저장
		
		String key = null;
		String[] values = null;
		while(enumeration.hasMoreElements()) {
			key = (String)enumeration.nextElement();		
			values = request.getParameterValues(key);
			if(values != null) {
				commandMap.put(key, (values.length > 1) ? values:values[0]);	//while문으로 key값 저장, 
			}																	//values의 길이가 1보다 크면 그냥 values를 넣고
		}																	    //		                        작으면 values배열의 0번을 넣는다.
		return commandMap;
	}
	
	

}
