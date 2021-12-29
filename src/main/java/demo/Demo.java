package demo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;

public class Demo {

    public static void main(String[] args) {
        //读取配置文件
        IniSecurityManagerFactory ini = new IniSecurityManagerFactory("classpath:shiro-permission.ini");
        //创建并获取安全管理器
        SecurityManager instance = ini.getInstance();
        //放入工具类
        SecurityUtils.setSecurityManager(instance);
        //得到主体
        Subject subject = SecurityUtils.getSubject();
        //创建令牌进行登陆判断
        UsernamePasswordToken token=new UsernamePasswordToken(
                "zs","123"
        );

        //登陆判断
        try {
            subject.login(token);
            System.out.println("登陆成功！");
            if(subject.isPermitted("user:view")){
                System.out.println("具备查询所有的权限");
            }
          /*  //角色身份验证是基于登陆成功的
            if(subject.hasRole("emp")){
                System.out.println("普通员工");
            }if(subject.hasRole("man")){
                System.out.println("经理");
            }if(subject.hasRole("ceo")){
                System.out.println("董事长");
            }*/
        } catch (IncorrectCredentialsException e) {
            System.out.println("密码错误");
        }catch (UnknownAccountException e){
            System.out.println("账号不存在");
        }


    }
}
