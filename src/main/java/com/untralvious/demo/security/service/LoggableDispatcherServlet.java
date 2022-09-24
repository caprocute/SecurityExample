package com.untralvious.demo.security.service;

import com.google.gson.Gson;
import com.untralvious.demo.security.domain.SysLog;
import com.untralvious.demo.security.domain.SysUser;
import com.untralvious.demo.security.repository.SysLogRepository;
import com.untralvious.demo.security.security.SecurityUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Configuration
public class LoggableDispatcherServlet extends DispatcherServlet {
    private final Logger log = LoggerFactory.getLogger(LoggableDispatcherServlet.class);

    private final UserService userService;

    private final SysLogRepository sysLogRepository;

    public LoggableDispatcherServlet(UserService userService, SysLogRepository sysLogRepository) {
        this.userService = userService;
        this.sysLogRepository = sysLogRepository;
    }

    @Override
    protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        long starTime = System.currentTimeMillis();
        if(!(request instanceof ContentCachingRequestWrapper)){
            request = new ContentCachingRequestWrapper(request);
        }
        if(!(response instanceof ContentCachingResponseWrapper)){
            response = new ContentCachingResponseWrapper(response);
        }
//        HandlerExecutionChain handler = getHandler(request);

        try {
            super.doDispatch(request, response);
        } finally {
            log(request, response, starTime);
            updateResponse(response);
        }
    }

    private void log(HttpServletRequest requestToCache, HttpServletResponse responseToCache, Long startTime) {
        try {
            Gson gson = new Gson();
            SysLog applicationLog = new SysLog();
            applicationLog.setLogType(2);
            applicationLog.setLogContent(getRequestPayload(requestToCache));
            String sysUser = SecurityUtils.getCurrentUserLogin().get();
            if(sysUser != null && !sysUser.isEmpty() && !sysUser.equals("anonymousUser")){
                applicationLog.setUserid(userService.getUserWithLogin().getId());
            }
            applicationLog.setUsername(requestToCache.getRemoteUser());
            applicationLog.setIp(requestToCache.getRemoteAddr());
            applicationLog.setMethod(requestToCache.getMethod());
            applicationLog.setRequestUrl(requestToCache.getRequestURL().toString());
            applicationLog.setRequestParam(gson.toJson(getRequestParam(requestToCache)));
            Long duration = System.currentTimeMillis() - startTime;
            applicationLog.setCostTime(duration);
            applicationLog.setResponseCode(responseToCache.getStatus());
            applicationLog.setResponseContent(getResponsePayload(responseToCache));
            log.info(gson.toJson(applicationLog));
            sysLogRepository.saveAndFlush(applicationLog);
        } catch(Exception e){
            log.error("Logging process is not fine. Skipping!", e);
        }
    }

    private Map<String, String[]> getRequestParam(HttpServletRequest request) throws UnsupportedEncodingException {
        Map<String, String[]> queryParameters = new HashMap<>();
        String queryString = request.getQueryString();
        if (queryString != null && !queryString.isEmpty()) {
            queryString = URLDecoder.decode(queryString, StandardCharsets.UTF_8.toString());
            String[] parameters = queryString.split("&");
            for (String parameter : parameters) {
                String[] keyValuePair = parameter.split("=");
                String[] values = queryParameters.get(keyValuePair[0]);
                //length is one if no value is available.
                values = keyValuePair.length == 1 ? ArrayUtils.add(values, "") :
                    ArrayUtils.addAll(values, keyValuePair[1].split(",")); //handles CSV separated query param values.
                queryParameters.put(keyValuePair[0], values);
            }
        }
        return queryParameters;
    }



    private String getRequestPayload(HttpServletRequest request) {
        ContentCachingRequestWrapper wrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
        if (wrapper != null) {

            byte[] buf = wrapper.getContentAsByteArray();
            if (buf.length > 0) {
                int length = Math.min(buf.length, 5120);
                try {
                    return new String(buf, 0, length, wrapper.getCharacterEncoding());
                }
                catch (UnsupportedEncodingException ex) {
                    // NOOP
                }
            }
        }
        return "";
    }

    private String getResponsePayload(HttpServletResponse response) {
        ContentCachingResponseWrapper wrapper = WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
        if (wrapper != null) {

            byte[] buf = wrapper.getContentAsByteArray();
            if (buf.length > 0) {
                int length = Math.min(buf.length, 5120);
                try {
                    return new String(buf, 0, length, wrapper.getCharacterEncoding());
                }
                catch (UnsupportedEncodingException ex) {
                    // NOOP
                }
            }
        }
        return "";
    }

    private String getClientIpAddress(HttpServletRequest requestToCache) {
        return "";
    }

    private void updateResponse(HttpServletResponse response) throws IOException {
        ContentCachingResponseWrapper responseWrapper =
            WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
        responseWrapper.copyBodyToResponse();
    }

    @Bean
    public ServletRegistrationBean dispatcherRegistration() {
        return new ServletRegistrationBean(LoggableDispatcherServlet.this);
    }
}
