package roomescape.controller.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import roomescape.model.member.Member;
import roomescape.model.member.Role;
import roomescape.service.dto.MemberInfo;

public class TokenUtils {

    private static final String SECRET_KEY = "AttoAndEverInWoowacourseMadeThisApplicationSoHardAndFunny";

    public static String createToken(Member member) {
        return Jwts.builder()
                .subject(String.valueOf(member.getId()))
                .claim("name", member.getName())
                .claim("email", member.getEmail())
                .claim("role", member.getRole())
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .compact();
    }

    public static MemberInfo parseToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload();
        long memberId = Long.parseLong(claims.getSubject());
        String memberName = claims.get("name").toString();
        String memberEmail = claims.get("email").toString();
        Role memberRole = Role.asRole(claims.get("role").toString());
        return new MemberInfo(memberId, memberName, memberEmail, memberRole);
    }
}
