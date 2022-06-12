package vip.ashes.blood.utils;

import cn.hutool.core.util.ReUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import vip.ashes.blood.entity.vo.MailVO;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Date;

/**
 * 邮件工具类
 *
 * @author loveliness
 */
@Component
public class MailUtils {

    @Autowired
    private static JavaMailSenderImpl mailSender;
    @Resource
    private JavaMailSenderImpl javaMailSender;

    public static MailVO sendMail(MailVO mailVo) {
        try {
            //1.检测邮件
            checkMail(mailVo);
            //2.发送邮件
            sendMimeMail(mailVo);
            //3.保存邮件
            return saveMail(mailVo);
        } catch (Exception e) {
            mailVo.setStatus("fail");
            mailVo.setError(e.getMessage());
            return mailVo;
        }
    }

    /**
     * 检测邮件信息类
     *
     * @param mailVo 邮箱实体
     */
    private static void checkMail(MailVO mailVo) {
        if (ObjectUtils.isEmpty(mailVo.getTo())) {
            throw new RuntimeException("邮件收信人不能为空");
        }
        if (ObjectUtils.isEmpty(mailVo.getSubject())) {
            throw new RuntimeException("邮件主题不能为空");
        }
        if (ObjectUtils.isEmpty(mailVo.getText())) {
            throw new RuntimeException("邮件内容不能为空");
        }
    }

    /**
     * 构建复杂邮件信息类
     *
     * @param mailVo 邮箱实体
     */
    private static void sendMimeMail(MailVO mailVo) {
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mailSender.createMimeMessage(), true);
            //true表示支持复杂类型
            //mailVo.setFrom(getMailSendFrom());//邮件发信人从配置项读取
            messageHelper.setFrom(mailVo.getFrom());
            //邮件发信人
            messageHelper.setTo(mailVo.getTo().split(","));
            //邮件收信人
            messageHelper.setSubject(mailVo.getSubject());
            //邮件主题
            messageHelper.setText(mailVo.getText(), true);
            //邮件内容
            if (!ObjectUtils.isEmpty(mailVo.getCc())) {
                //抄送
                messageHelper.setCc(mailVo.getCc().split(","));
            }
            if (!ObjectUtils.isEmpty(mailVo.getBcc())) {
                //密送
                messageHelper.setCc(mailVo.getBcc().split(","));
            }
            if (mailVo.getMultipartFiles() != null) {
                //添加邮件附件
                for (MultipartFile multipartFile : mailVo.getMultipartFiles()) {
                    messageHelper.addAttachment(multipartFile.getOriginalFilename(), multipartFile);
                }
            }
            if (ObjectUtils.isEmpty(mailVo.getSentDate())) {
                //发送时间
                mailVo.setSentDate(new Date());
                messageHelper.setSentDate(mailVo.getSentDate());
            }
            mailSender.send(messageHelper.getMimeMessage());
            //正式发送邮件
            mailVo.setStatus("ok");
        } catch (Exception e) {
            //发送失败
            throw new RuntimeException(e);
        }
    }

    /**
     * 保存邮件
     * 未使用
     *
     * @param mailVo 邮箱实体
     * @return 原样返回
     */
    private static MailVO saveMail(MailVO mailVo) {

        //将邮件保存到数据库..

        return mailVo;
    }

    /**
     * 正则表达，看是不是邮箱
     *
     * @param mailAddress
     * @return
     */
    public static boolean isEmail(String mailAddress) {
        return ReUtil.isMatch("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", mailAddress);
    }

    @PostConstruct
    public void init() {
        mailSender = javaMailSender;
    }

    /**
     * 获取邮件发信人
     * 暂时没用
     *
     * @return 返回发信人
     */
    public String getMailSendFrom() {
        return mailSender.getJavaMailProperties().getProperty("from");
    }

}
