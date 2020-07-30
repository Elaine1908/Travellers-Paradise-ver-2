package com.Elaineee.project.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet(name = "ValidateColorServlet",value = "/validateColorServlet")
public class ValidateColorServlet extends HttpServlet {
    public static final String CHECK_CODE_KEY = "CHECK_CODE_KEY";
    private static final long serialVersionUID = 1L;

    //设置验证图片宽度、高度、字符个数
    private int width = 1520;
    private int height = 400;
    private int codeCount = 4;
    //验证码字体高度
    private int fontHeight = 4;
    //验证码中的全字符基线，即验证码中的单个字符位于验证码图形左上角的（codeX，codeY）位置处
    private int codeX = 0;
    private int codeY = 0;
    //验证码由哪些字符组成
    char [] codeSequence = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz23456789".toCharArray();

    public void init(){
        fontHeight = height - 2;
        codeX = width / (codeCount + 2);
        codeY = height - 4;
    }

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //定义一个类型为BufferedImage，TYPE_INT_BGR类型的图像缓存
        BufferedImage bufferedImage = null;
        bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

        //在bufferedImage中创建一个Graphics2D图像
        Graphics2D graphics = null;
        graphics = bufferedImage.createGraphics();

        //设置一个颜色，使Graphics2D对象的后续图形使用这个颜色
        graphics.setColor(Color.WHITE);

        //填充一个指定的矩形：x-要填充矩形的x坐标；y-要填充矩形的y坐标；width-要填充矩形的宽度；height-要填充矩形的高
        graphics.fillRect(0, 0, width, height);

        //创建一个Font对象：name-字体名称；style-Font的样式常量；size-Font的点大小
        Font font = null;
        font = new Font("", Font.BOLD, fontHeight);

        //使GraphicsD对象的后续图形使用此字体
        graphics.setFont(font);
        graphics.setColor(Color.BLACK);

        //绘制指定矩形的边框，绘制出的矩形将比构件宽、高一个像素
        graphics.drawRect(0, 0, width-1, height-1);

        //随机产生干扰线，使图像中的验证码不易被其他程序探测
        Random random = null;
        random = new Random();
        graphics.setColor(Color.GREEN);
        for (int i = 0; i < 15; i++){
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(20);
            int y1 = random.nextInt(20);
            graphics.drawLine(x,y,x+x1,y+y1);
        }

        //创建randomCode对象，用于保存随机产生的验证码，以使用户登录后进行验证
        StringBuffer randomCode;
        randomCode = new StringBuffer();

        for (int i = 0; i < codeCount; i++){
            //得到随机产生的验证码数字
            String strRand = null;
            strRand = String.valueOf(codeSequence[random.nextInt(36)]);
            randomCode.append(strRand);

            //用随机产生的颜色将验证码绘制到图像中
            graphics.setColor(Color.BLUE);
            graphics.drawString(strRand,(i+1)*codeX,codeY);
        }

        //再把存放有所有随机字符的StringBuffer对应的字符串放入到HttpSession中
        request.getSession().setAttribute(CHECK_CODE_KEY, randomCode.toString());

        //禁止图像缓存
        response.setHeader("Pragma","no-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setDateHeader("Expires",0);

        //将图像输出到输出流中
        ServletOutputStream servletOutputStream = null;
        servletOutputStream = response.getOutputStream();
        ImageIO.write(bufferedImage, "jpeg", servletOutputStream);
        servletOutputStream.close();
    }

}
