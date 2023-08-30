package test1;


import io.restassured.path.json.JsonPath;
import org.testng.Assert;

public class ComplexJson
{
    public static void main(String[] args)
    {
        JsonPath js=new JsonPath(Payload.cJson());
       int size= js.getInt("courses.size()");
        System.out.println(size);
//        Print purchase amount
        int amount=js.getInt("dashboard.purchaseAmount");
        System.out.println(amount);
//        Title of the first course
        String firstTitle=js.getString("courses[0].title");
        System.out.println(firstTitle);
//        get all courses title and prices
        for(int i=0;i<size;i++)
        {
            String tittle=js.get("courses["+i+"].title");
            System.out.println(tittle);
            int price=js.get("courses["+i+"].price");
            System.out.println(price);
        }

//         Print no of copies sold by RPA Course
        for(int i=0;i<size;i++) {
            String tittle = js.get("courses[" + i + "].title");
            if(tittle.equalsIgnoreCase("Cypress"))
            {
               int RPACopies= js.get("courses[" + i + "].copies");
                System.out.println(RPACopies);
                break;
            }

        }
//        Verify if Sum of all Course prices matches with Purchase Amount
        int sum=0;
        for(int i=0;i<size;i++)
        {
            int price=js.get("courses["+i+"].price");
            int copies= js.get("courses[" + i + "].copies");
            sum=sum+(price*copies);

        }
        System.out.println(sum);
        Assert.assertEquals(sum,amount);
        System.out.println("price amount is correct");



    }
}
