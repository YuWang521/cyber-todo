package com.wy.cybertodoadmin.config.security.encoder;

import com.wy.cybertodoadmin.core.constant.CommonConstant;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.regex.Pattern;

/**
 * @author WangYu
 * @project cyber-todo
 * @description
 * @date 2023/7/13 12:36:11
 */
public class SystemPasswordEncoder extends BCryptPasswordEncoder {
    private static final Pattern BCRYPT_PATTERN = Pattern.compile("\\A\\$2(a|y|b)?\\$(\\d\\d)\\$[./0-9A-Za-z]{53}");
    private final Log logger = LogFactory.getLog(getClass());

    /**
     * 密码对比
     * @param rawPassword the raw password to encode and match
     * @param encodedPassword the encoded password from storage to compare with
     * @return
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (rawPassword == null) {
            throw new IllegalArgumentException("rawPassword cannot be null");
        }
        if (encodedPassword == null || encodedPassword.length() == 0) {
            this.logger.warn("Empty encoded password");
            return false;
        }
        // 长度正确，且包含分隔符
        if (!isHashSaltSeparator(encodedPassword)) {
            this.logger.warn("Encoded password does not look like HASH_SALT ");
            return false;
        }
        // 分割密码和盐值
        String[] split = encodedPassword.split(CommonConstant.SPLIT_EXPRESSION);
//      eg:c$2a$10$MOTzTNlt88j/4nA1cJyhueJo7aVD8qWZjMe9vpocBv2RInjHEYYhe*1689224121698
//        First part: $2a$10$MOTzTNlt88j/4nA1cJyhueJo7aVD8qWZjMe9vpocBv2RInjHEYYhe
//        Second part: 1689224121698
        if (!this.BCRYPT_PATTERN.matcher(split[0]).matches()) {
            this.logger.warn("Encoded password does not look like BCrypt");
            return false;
        }
        return BCrypt.checkpw(rawPassword.toString() + split[1], split[0]);
    }

    /**
     * // 长度正确，且包含分隔符
     * @param encodedPassword 密码集合
     * @return 是否包含分隔符
     */
    private static boolean isHashSaltSeparator(String encodedPassword) {
        return encodedPassword.length() == CommonConstant.HASH_SALT_LENGTH &&  encodedPassword.contains(CommonConstant.HASH_SALT_SEPARATOR);
    }



}
