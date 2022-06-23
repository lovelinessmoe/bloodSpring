package vip.ashes.blood.service.serviceimpl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import vip.ashes.blood.constants.UserConstants;
import vip.ashes.blood.dao.UserMapper;
import vip.ashes.blood.entity.User;
import vip.ashes.blood.entity.vo.MailVO;
import vip.ashes.blood.service.UserService;
import vip.ashes.blood.utils.MailUtils;
import vip.ashes.blood.utils.Result;
import vip.ashes.blood.utils.ResultCode;

import java.util.concurrent.TimeUnit;


/**
 * @author loveliness
 */
@Service
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;
    /**
     * 密码加密
     */
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    /**
     * redis操作
     */
    private final StringRedisTemplate stringRedisTemplate;


    @Override
    public User login(User loginUser) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        return this.baseMapper.selectOne(userQueryWrapper.eq(User.COL_EMAIL, loginUser.getEmail()));
    }

    @Override
    public boolean check(String currentPassword, String password) {
        return this.bCryptPasswordEncoder.matches(currentPassword, password);
    }

    @Override
    public int register(User userRegister) {
        //不用检测数据库是否存在用户，发送邮箱验证码的时候验证过了
        userRegister.setPassword(bCryptPasswordEncoder.encode(userRegister.getPassword()));
        //2是普通用户
        userRegister.setRoleId(2);
        return userMapper.insert(userRegister);

    }

    @Override
    public boolean checkCaptcha(String captchaVerification, String code) {
        //通过随机码获取验证码
        String captchaCode = stringRedisTemplate.opsForValue().get(captchaVerification);
        //Redis中删除对应的验证码
        stringRedisTemplate.delete(captchaVerification);
        return code.equals(captchaCode);
    }

    @Override
    public Result generateCaptcha() {
        //定义图形验证码的长、宽、验证码字符数、干扰元素个数
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(150, 30, 5, 5);
        String captchaVerification = "captcha" + IdUtil.fastSimpleUUID();
        String code = captcha.getCode();
        stringRedisTemplate.opsForValue().set(captchaVerification, code);
        stringRedisTemplate.expire(captchaVerification, 60 * 5, TimeUnit.SECONDS);
        return Result.ok().data("img", captcha.getImageBase64Data())
                .data("captchaVerification", captchaVerification);
    }

    @Override
    public Result registerMail(User user) {
        if (!MailUtils.isEmail(user.getEmail())) {
            return Result.RCode(false, ResultCode.EMAIL_FORMAT_ERROR);
        }
        if (ObjectUtils.isEmpty(user.getUserName()) || "".equals(user.getUserName())) {
            return Result.RCode(false, ResultCode.USERNAME_ERROR);
        }
        //数据库是否存在用户
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq(User.COL_EMAIL, user.getEmail());
        Long userNum = userMapper.selectCount(userQueryWrapper);
        if (userNum == 1) {
            return Result.RCode(false, ResultCode.USER_ACCOUNT_EMAIL_ALREADY_EXIST);
        }

        // 生成随机数验证码
        RandomGenerator randomGenerator = new RandomGenerator(6);
        String code = randomGenerator.generate();
        // redis register+email做key 随机验证码做val
        stringRedisTemplate.opsForValue().set(UserConstants.REGISTER_MAIL + user.getEmail(), code);
        // 30min 过期时间
        stringRedisTemplate.expire(user.getEmail(), 60 * 30, TimeUnit.SECONDS);


        //发送邮件
        MailVO mailVo = new MailVO();
        mailVo.setFrom("admin@javaee.xyz");
        mailVo.setTo(user.getEmail());
        mailVo.setSubject("Blood注册验证码");
        mailVo.setText("<div style=\"margin: 0 auto;width: 500px;text-align: center;\">\n" +
                "        <h3>您好" + user.getUserName() + "，欢迎注册血站平台，您的邮箱刚刚在Blood注册，为了保护您的信息安全，我们来信进行邮箱验证，如果此操作不是由您发起的，请忽略此邮件。</h3>\n" +
                "        <h1>您的验证码为<span style=\"color: red;\">" + code + "</span></h1>\n" +
                "</div>");

        MailUtils.sendMail(mailVo);
        return Result.ok();
    }

    @Override
    public Result modifyInfMail(String userId) {
        //数据库查询用户
        User user = userMapper.selectById(userId);

        // 生成随机数验证码
        RandomGenerator randomGenerator = new RandomGenerator(6);
        String code = randomGenerator.generate();
        // redis modifyInf+email做key 随机验证码做val
        stringRedisTemplate.opsForValue().set(UserConstants.MODIFY_INFORMATION_MAIL + user.getEmail(), code);
        // 5min 过期时间
        stringRedisTemplate.expire("modifyInf" + userId, 60 * 5, TimeUnit.SECONDS);

        //发送邮件
        MailVO mailVo = new MailVO();
        mailVo.setFrom("admin@javaee.xyz");
        mailVo.setTo(user.getEmail());
        mailVo.setSubject("Blood修改信息验证码");
        mailVo.setText("<div style=\"margin: 0 auto;width: 500px;text-align: center;\">\n" +
                "        <h3>您好" + user.getUserName() + "，您的邮箱刚刚在Blood进行修改信息操作，为了保护您的信息安全，我们来信进行邮箱验证，如果此操作不是由您发起的，请忽略此邮件。</h3>\n" +
                "        <h1>您的验证码为<span style=\"color: red;\">" + code + "</span></h1>\n" +
                "</div>");

        MailUtils.sendMail(mailVo);
        return Result.ok();
    }
}

