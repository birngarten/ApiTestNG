package tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.testng.annotations.Test;
import java.util.List;
import java.util.Objects;

import static io.restassured.RestAssured.given;

public class Trivia01 {

    String endpoint = "https://opentdb.com/api.php?amount=10&category=23&difficulty=easy&type=multiple";
    Response response;
    JsonPath json;

    /*
    "Kullanici asagidakilerini sirasiyla yapabilmelidir.
     * Status kodunun 200 oldugunu,
     * ""results"" in icinde on(10) sorunun oldugunu dogrulayiniz.
     * Response ta gelen sorularda
       * ""question"" larin null olmadigini dogrulayiniz
       * ""correct_answer"" in null olmadigini ve her bir soruda birtane ""correct_answer"" oldugunu dogrulayiniz.
       * ""incorrect_answers"" cevaplarinin her soruda toplam 3 adet oldugunu ve gelen verilen null olmadiginidogrulayiniz."
     */

    public  Response getAllData(){
        response = given().
                   when().
                       get(endpoint);
        return response;
    }

    @Test
    public void TC0101(){
        getAllData().prettyPrint();

        response.
                then().
                assertThat().
                statusCode(200);

        json = response.jsonPath();

        List<String> resultSize = json.getList("results");

        //  ""results"" in icinde on(10) sorunun oldugunu dogrulayiniz.
        Assert.assertTrue(resultSize.size()==10);

        //""question"" larin null olmadigini dogrulayiniz
        List<String> questionsList = json.getList("results.question");
        //1. YOL
        questionsList.stream().forEach(Objects::nonNull);
        //2. YOL
//        int count = 0;
//        for (String w: questionsList){
//            if(w.contains("?")){
//                count++;
//            }
//        }
//        System.out.println(count);
//        Assert.assertTrue(count==10);

        //""correct_answer"" in null olmadigini ve her bir soruda birtane ""correct_answer"" oldugunu dogrulayiniz.
        List<String> correct_answerList = json.getList("results.correct_answer");
        correct_answerList.stream().forEach(Objects::nonNull);

        //"incorrect_answers" cevaplarinin her soruda toplam 3 adet oldugunu ve gelen verilen null olmadiginidogrulayiniz

        // 1. YOL (Lambda ile)
//        List<List<String>> allIncorrectAnswers = json.getList("results.incorrect_answers");
//        allIncorrectAnswers.forEach(t -> Assert.assertEquals(t.size(), 3));
//        allIncorrectAnswers.stream().flatMap(Collection::stream).forEach(Assert::assertNotNull);

        //2. YOL
        List<List<String>> inCorrectAnswerList = json.getList("results.incorrect_answers");
        int count = 0;
        for (List<String> w : inCorrectAnswerList) {
            Assert.assertTrue(w.size()==3);

            for (String i : w) {
                if (i.isEmpty()) { // null olmadigini dogrulayiniz
                    count++;      // eger i bos olsaydi count artardi ve null olmadigi cikardi. Ama count artmadi.
                }
            }
        }
        Assert.assertTrue(count == 0);




    }
}
