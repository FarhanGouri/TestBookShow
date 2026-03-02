package org.example.authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.enums.Roles;
import org.example.model.User.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component
public class CORSfilter implements Filter {

	public static final String X_CLACKS_OVERHEAD = "X-Clacks-Overhead";
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	UserSession userSession;

	public String token;
	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
						 FilterChain chain) throws IOException, ServletException {

		HttpServletResponse response = (HttpServletResponse) res;
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT,OPTIONS");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization, type");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.addIntHeader("Access-Control-Max-Age", 180);			
		
		try {
			HttpServletRequest httpRequest = (HttpServletRequest) req;

			this.token = httpRequest.getHeader(SecurityConstants.HEADER_STRING);

			String requestArray  [] = new String [] {"user-signup", "user-signin", "logout", "upload-image"};		

			String[] url = httpRequest.getRequestURI().split("\\/");

			
			if(httpRequest.getMethod().equalsIgnoreCase("OPTIONS") ){				
				chain.doFilter(req, res);
			}					
			else if(httpRequest.getHeader("Authorization") == null && Arrays.asList(requestArray).indexOf(url[url.length -1]) != -1 ) {
				
				chain.doFilter(req, res);
			}
			else {
				try {
					Claims claims = Jwts.parser().setSigningKey(JWTHelper.getPublicSigningKey())
							.parseClaimsJws(this.token.replace(SecurityConstants.TOKEN_PREFIX, "")).getBody();
					String userId = (String) claims.get("USERID");
					String role = (String) claims.get("ROLE");
					userSession.setRole(Roles.valueOf(role));
					userSession.setUserId(userId);

				} catch (Exception e) {
				}
			}
		}catch(Exception e) {			
			System.out.println(e.getMessage());
			throw new IOException(e.getMessage());			
		}		
	}
	
	@Override
	public void destroy() {}

	@Override
	public void init(FilterConfig arg0) throws ServletException {			  
	}
	
	public boolean verifieToken(String id) {
		
		Optional<User> user = null;
		long userId = Long.parseLong(id);		
		user = userRepository.findById(userId);
		
		if(user.isPresent()) {
			return true;
		}else {
			return false;
		}
	}	
}