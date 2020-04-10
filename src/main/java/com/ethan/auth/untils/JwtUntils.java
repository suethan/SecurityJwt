package com.ethan.auth.untils;

import cn.hutool.core.util.IdUtil;
import com.ethan.auth.comm.constant.Constant;
import com.ethan.auth.comm.redis.RedisCache;
import com.ethan.auth.security.LoginUser;
import eu.bitwalker.useragentutils.UserAgent;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.security.SignatureException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author: ethan.liu
 * @Date: 2020/4/3 10:38
 */
public class JwtUntils {

    /**
     * 加密方式
     */
    private static final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    /**
     * 设置过期时间
     */
    private static final int TIME = 1;


    @Autowired
    RedisCache redisCache;

    /**
     * 对密钥进行加密
     *
     * @return
     */
    private static Key getkey() {
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(Constant.SECRET);
        return new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
    }

    /**
     * 生成Token
     * <p>
     * JWT分成3部分：1.头部（header),2.载荷（payload, 类似于飞机上承载的物品)，3.签证（signature)
     * <p>
     * 加密后这3部分密文的字符位数为：
     * 1.头部（header)：36位，Base64编码
     * 2.载荷（payload)：没准，BASE64编码
     * 3.签证（signature)：43位，将header和payload拼接生成一个字符串，
     * 使用HS256算法和我们提供的密钥（secret,服务器自己提供的一个字符串），
     * 对str进行加密生成最终的JWT
     *
     * @return
     * @throws Exception
     */
    public static String encodeToken(String token) {
        // 签发时间
        Date iatDate = new Date();
        // 设置过期时间 - 设置签发时间5秒钟后为延迟时间，这里只是做测试，实际时间会比这个长很多
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE, Constant.EXPIRETIME);
        // 得到过期时间
        Date expirensDate = nowTime.getTime();
        // 组合header
        Map<String, Object> header = new HashMap<>();
        header.put("alg", "HS256");
        header.put("typ", "JWT");
        String target = Jwts.builder()
                .setExpiration(expirensDate)
                .setIssuedAt(iatDate)
                .setSubject(token)
                .signWith(SignatureAlgorithm.HS256, getkey()).compact();
        return target;
    }

    /**
     * 解密Token查看其是否合法
     *
     * @param token
     * @return
     */
    public static Claims verifyToken(String token) {
        Claims body = Jwts.parser().setSigningKey(getkey()).parseClaimsJws(token).getBody();
        return body;
    }

    public static void main(String args[]) {


    }
}
