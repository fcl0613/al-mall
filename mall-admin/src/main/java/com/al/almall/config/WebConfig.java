package com.al.almall.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //商品封面图片
//        registry.addResourceHandler("/mzmall/img/cover/**")
//                .addResourceLocations("file:" + System.getProperty("user.dir") +
//                        System.getProperty("file.separator") + "img" +
//                        System.getProperty("file.separator") + "goodscover"+
//                        System.getProperty("file.separator"));

        //商品详情图片
//        registry.addResourceHandler("/mzmall/img/showimg/**")
//                .addResourceLocations("file:" + System.getProperty("user.dir") +
//                        System.getProperty("file.separator") + "img" +
//                        System.getProperty("file.separator") + "goodsdetailimg"+
//                        System.getProperty("file.separator"));

        //用户头像
        registry.addResourceHandler("/image/avatar/**")
                .addResourceLocations("file:" + "D:\\Programming\\IdeaProject\\al-mall\\image\\avatar\\");

        // 分类icon
        registry.addResourceHandler("/image/category/**")
                .addResourceLocations("file:" + "D:\\Programming\\IdeaProject\\al-mall\\image\\category\\");

        // 商品图片
        registry.addResourceHandler("/image/goods/**")
                .addResourceLocations("file:" + "D:\\Programming\\IdeaProject\\al-mall\\image\\goods\\");
    }
}
