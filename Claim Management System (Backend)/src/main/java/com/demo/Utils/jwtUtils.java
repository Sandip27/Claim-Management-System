package com.demo.Utils;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.demo.Exception.AccessDeniedException;
import com.demo.Model.Member;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class jwtUtils {
	private static String secret = "this_is_secret"; 
	private static long expiryDuration=60*60;
  
	public String generateJwt(Member member){
		
		long milliTime = System.currentTimeMillis();
        long expiryTime = milliTime + expiryDuration*1000;   
       
        //claims
        Date issuedAt = new Date(milliTime);
        Date expiryAt = new Date(expiryTime);
      
        Claims claims=Jwts.claims().setIssuer(Long.toString(member.getMemberId()))
              .setIssuedAt(issuedAt);              
      
        // claims.put("type", user.getUserType());
        claims.put("name", member.getMemberName());
        claims.put("email_id", member.getEmailId());
              
       //generate JWT using claims
       return Jwts.builder()
              .signWith(SignatureAlgorithm.HS512, secret)
              .setClaims(claims)
              .compact();
                 
  }
	
	
  public void verify(String authentication) throws Exception{
  	try {
  		Jwts.parser().setSigningKey(secret).parseClaimsJws(authentication);
  	}
  	catch(Exception e){
  		//throw new Exception();
  		throw new AccessDeniedException("ACCESS DENIED!");
  		
  	}
  }


}

