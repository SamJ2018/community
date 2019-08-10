package com.cys.community.cache;

import com.cys.community.dto.TagDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author sam
 * @since  2019-08-08-1:35 PM
 * @apiNote :
 **/
public class TagCache {


    public static List<TagDTO> get() {
        ArrayList<TagDTO> tagDTOS = new ArrayList<>();
        TagDTO program = new TagDTO();
        program.setTags(Arrays.asList("javascript", "php", "css", "html", "html5", "java", "node.js", "python", "c++", "c", "golang", "objective-c", "typescript", "shell ", "c#", "swift", "sass", "bash", "ruby", "less", "asp.net", "lua", "scala", "coffeescript", "actionscript", "rust", "erlang", "perl"));
        program.setCategoryName("开发语言");
        tagDTOS.add(program);

        TagDTO framework = new TagDTO();
        framework.setCategoryName("平台架构");
        framework.setTags(Arrays.asList("laravel", "spring", "express", "django", "flask", "yii", "ruby-on-rails", "tornado", "koa", "struts"));
        tagDTOS.add(framework);

        TagDTO server = new TagDTO();
        server.setCategoryName("服务器");
        server.setTags(Arrays.asList("linux", "nginx", "docker", "apache", "ubuntu", "centos", "缓存", "tomcat", "负载均衡", "unix", "hadoop", "windows-server"));
        tagDTOS.add(server);

        TagDTO database = new TagDTO();
        database.setCategoryName("数据库");
        database.setTags(Arrays.asList("mysql", "redis", "mongodb", "sql", "oracle", "nosql", "memcached", "sqlserver", "postgresql", "sqlite"));
        tagDTOS.add(database);

        TagDTO devtools = new TagDTO();
        devtools.setCategoryName("开发工具");
        devtools.setTags(Arrays.asList("git", "github", "visual-studio-code", "vim", "sublime-text", "xcode", "intellij-idea", "eclipse", "maven", "ide", "svn", "visual-studio", "atom", "emacs", "textmate", "hg"));
        tagDTOS.add(devtools);

        return tagDTOS;
    }

    public static String filterInvalid(String tags) {
        String[] split = StringUtils.split(tags, ",");
        List<TagDTO> tagDTOS = get();
        List<String> tagList = tagDTOS.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());
        String invalidList = Arrays.stream(split).filter(t ->StringUtils.isBlank(t) || !tagList.contains(t)).collect(Collectors.joining(","));
        System.out.println(invalidList);
        return invalidList;
    }
}
