package com.yzl.service.common.utils;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.*;
import java.util.Map;
import java.util.Random;

/**
 * @author kai
 * @date 2023/11/18 18:29
 */

@Slf4j
public class WordPdfUtils {

    /**
     * 本地
     *构建一个pdf
     * @param templateName 模板名称
     * @param variables    模板变量
     * @throws Exception
     */
    public static void buildResource( String templateName, Map<String, Object> variables) throws Exception {
        //thymeleaf构造模板引擎;给html文件赋值
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        //默认是找classpath路径下的资源
        resolver.setPrefix("/templates/");
        resolver.setSuffix(".html");//模板文件后缀
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(resolver);
        //构造上下文
        Context context = new Context();
        context.setVariable("username",templateName);
        //设置变量
        context.setVariables(variables);
        //TODO 渲染模板,返回的生成的模板的字符串
        String example = templateEngine.process("parent", context);
        // 生成pdf文件地址
        String pdfPath ="D://"+ new Random().nextInt(30) + "-" + System.nanoTime() + ".pdf";
        File file = new File(pdfPath);
        file.createNewFile();
        FileOutputStream os = new FileOutputStream(file);
        //构建pdf的预览
        PdfRendererBuilder builder = new PdfRendererBuilder();
        //设置字体支持中文
        builder.useFont(new ClassPathResource("/fonts/simsun.ttf").getFile(), "simsun");
        //使用快速渲染器
        builder.useFastMode();

        //String s = ResourceUtils.getURL("testPDF/templates/").toString(); 获取资源路径
        String path = new ClassPathResource("/templates").getPath();//获取项目下的资源路径
        //withHtmlContent()参数一是模板，参数二模板的基础路径
        builder.withHtmlContent(example,new ClassPathResource("/templates").getPath());
        //输出pdf的结果
        builder.toStream(os);
        builder.run();
        //设置字符编码
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(os));
        writer.flush();
        writer.close();
    }

    /**
     * TODO 通过接口接口请求生成pdf模板；有pdf预览窗口
     * @param templateName
     * @param variables
     * @throws Exception
     */
    public static void requestBuildResource(HttpServletResponse response, String templateName, Map<String, Object> variables) throws Exception {
        //thymeleaf构造模板引擎;给html文件赋值
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        //默认是找classpath路径下的资源
        resolver.setPrefix("/templates/");
        resolver.setSuffix(".html");//模板文件后缀
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(resolver);
        //构造上下文(Model)
        Context context = new Context();
        //设置变量
        context.setVariables(variables);
        //TODO 渲染模板,返回的生成的模板的字符串
        String example = templateEngine.process(templateName, context);

        //TODO 生成pdf，浏览器进行展示
        response.setHeader("Content-Disposition", "filename=" + new String(("pdfFileName" + ".pdf").getBytes(), "iso8859-1"));
        //生成pdf，浏览器不进行展示，默认附件下载；原因：attachment标记以附件方式下载保存
        // response.setHeader("Content-Disposition", "attachment;fileName=" + new String(("templateName" + ".pdf").getBytes("gb2312"), "ISO8859-1"));
        OutputStream os = response.getOutputStream();
        //构建pdf的构造器
        PdfRendererBuilder builder = new PdfRendererBuilder();
        //设置字体支持中文
        //
        builder.useFont(new ClassPathResource("/fonts/simsun.ttf").getFile(), "simsun");
        builder.useFastMode();
        //withHtmlContent()参数：提供一个包含要转换为PDF的XHTML/XML的字符串
        //ResourceUtils.getURL()获取资源的地址
        builder.withHtmlContent(example, ResourceUtils.getURL("/templates/").toString());
        //输出pdf的结果
        builder.toStream(os);
        builder.run();
        //设置字符编码
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(os));
        writer.flush();
        writer.close();
    }


    /**
     * 生成本地word
     * @param variables 模板需要的数据
     * @throws Exception
     */
    public static void buildWord(Map<String, Object> variables, String pdfPath) throws Exception {
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        //默认是找classpath路径下的资源
        resolver.setPrefix("/templates/");
        resolver.setSuffix(".html");//模板文件后缀
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(resolver);
        //构造上下文
        Context context = new Context();

        context.setVariables(variables);
        // 生成word文件地址
        File file = new File(pdfPath);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream os = new FileOutputStream(file);

        try( PrintWriter writer = new PrintWriter(new OutputStreamWriter(os))) {
            //TODO 参数一是模板;参数二：上下文数据模板需要读取的数据容器;参数三writer输出流是指定输出到那个地方
            templateEngine.process("paperTemplate",context, writer);
        }catch (Exception e){
            log.error("导出word文件出错啦：{}", e.getMessage());
        }
    }

    /**
     * 通过接口生成word
     * @param response
     * @param variables
     * @throws Exception
     */
    public static void requestBuildWord(HttpServletResponse response, Map<String, Object> variables) throws Exception {

        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        //默认是找classpath路径下的资源
        resolver.setPrefix("/templates/");
        resolver.setSuffix(".html");//模板文件后缀
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(resolver);
        //构造上下文
        Context context = new Context();
        context.setVariable("templateName", "one");
        context.setVariables(variables);
        // 生成word文件地址
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/msword");
        response.setHeader("Content-Disposition", "attachment;fileName=" + new String(("test" + ".xls").getBytes("gb2312"), "ISO8859-1"));
        try( PrintWriter writer = new PrintWriter(new OutputStreamWriter(response.getOutputStream()))) {
            //TODO 参数一是模板;参数二：上下文数据模板需要读取的数据容器;参数三writer输出流是指定输出到那个地方
            templateEngine.process("parent",context, writer);
        }catch (Exception e){
            log.error("导出word文件出错啦：{}", e.getMessage());
        }
    }
}
