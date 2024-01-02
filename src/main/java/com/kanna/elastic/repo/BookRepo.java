package com.kanna.elastic.repo;

import com.kanna.elastic.entity.Book;
import org.springframework.data.elasticsearch.annotations.HighlightField;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.elasticsearch.annotations.Highlight;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public interface BookRepo extends ElasticsearchRepository<Book,String> {

    List<Book> findByAuthorName(String authorName);
    Optional<Book> findByIsbn(String isbn);
    List<Book> findByTitleIgnoreCase(String title);
    @Query("{\"bool\": {\"should\": [{\"match\": {\"title\": {\"query\": \"?0\",\"fuzziness\": \"AUTO\"}}}, {\"match\": {\"authorName\": {\"query\": \"?0\",\"fuzziness\": \"AUTO\"}}}]}}")
  //below is for user interface
        //  @Highlight(fields = {@HighlightField(name = "title"), @HighlightField(name = "authorName")})
    List<Book> autocompleteSuggestionsUsingQuery(String prefix);


    public static void main(String[] args){
        String s = "dlaksdhlekji";
        s.toLowerCase();
        char[] a = s.toCharArray();
        int vowels = 0;
        int vowelss = 0;
        for(int i=0;i<a.length;i++){
            if(a[i]=='a' || a[i] =='e' || a[i] =='i' || a[i] =='o' || a[i] =='u'){
                vowels++;
            }

        }
        for(char c: a){
            if(List.of('a','e','i','o','u').contains(c)){
                vowelss++;
            }
        }
        int[] nums = {2,7,1,3,5,9,4,1};
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));

        String joke = "da67dakd878";
        char[] j =joke.toCharArray();
        int sum = 0;
        for(char jj:j){
            if(Character.isDigit(jj)){
                sum=sum+Character.getNumericValue(jj);
            }
        }
        System.out.println(sum);
        System.out.println(vowels);
        System.out.println(vowelss);
        A aa = new B();
        aa.m1();
    }
    class A{
        void m1(){
            System.out.println("super");
        }

    }
    class B extends A{
        public B(){
            super();
            System.out.println("child cons");
        }
        void m1(){
            System.out.println("child");
        }
    }
}
