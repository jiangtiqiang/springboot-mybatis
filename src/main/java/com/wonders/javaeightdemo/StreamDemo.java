package com.wonders.javaeightdemo;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author huangwieyue
 * @date 2018-02-23 11:23
 * Stream 代替循环
 * 用Article实体类
 * http://www.importnew.com/14841.html
 */
public class StreamDemo {
    public static void main(String[] args) {
        List<Article> articles=new ArrayList<>();
        List<String> strings=new ArrayList<>();
        strings.add("Java");
        strings.add("c");
        Article article1=new Article("Java","Java",strings);
        articles.add(article1);

        for (Article articleSys : articles) {
            if (articleSys.getTags().contains("Java")) {//改成小写
                //System.out.println(articleSys.getTags());
            }
        }

         Optional<Article> optional= articles.stream().filter(article -> article.getTags().contains("Java"))
                    .findFirst();
        System.out.println(optional);
    }

    /**
     * 获取所有匹配的元素而不是仅获取第一个
     */
    //for循环方案
    public List<Article> getAllJavaArticles(List<Article> articles) {

        List<Article> result = new ArrayList<>();

        for (Article article : articles) {
            if (article.getTags().contains("Java")) {
                result.add(article);
            }
        }

        return result;
    }
    //Stream操作的方案,我们使用 collection 操作在返回流上执行少量代码而不是手动声明一个集合并显式地添加匹配的文章到集合里。
    public List<Article> getAllJavaArticlesByStream(List<Article> articles) {
        return articles.stream()
                .filter(article -> article.getTags().contains("Java"))
                .collect(Collectors.toList());
    }


    /**
     *根据作者来把所有的文章分组
     */
    //集合  这里存在问题 待测
    public Map<String, List<Article>> groupByAuthor(List<Article> articles) {

        Map<String, List<Article>> result = new HashMap<>();

        for (Article article : articles) {
            if (result.containsKey(article.getAuthor())) {
                result.get(article.getAuthor()).add(article);
            } else {
                ArrayList<Article> articles2 = new ArrayList<>();
                articles2.add(article);
                result.put(article.getAuthor(), articles2);
            }
        }

        return result;
    }
    //Stream
    public Map<String, List<Article>> groupByAuthorByStream(List<Article> articles) {
        return articles.stream()
                .collect(Collectors.groupingBy(Article::getAuthor));
    }
}
