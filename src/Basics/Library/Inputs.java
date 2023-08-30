package Library;

public class Inputs
{
    public static String addABook1()
    {
        String P1="{\n" +
                "\n" +
                "\"name\":\"Learn Appium Automation with Java\",\n" +
                "\"isbn\":\"farhaa2sbcd\",\n" +
                "\"aisle\":\"227\",\n" +
                "\"author\":\"John foe\"\n" +
                "}\n";
        return P1;

    }
//    Add books using parameters
    public static String addBookUsingParams(String isbn,String aisle)
    {
        String P1="{\n" +
                "\n" +
                "\"name\":\"Learn Appium Automation with Java\",\n" +
                "\"isbn\":\""+isbn+"\",\n" +
                "\"aisle\":\""+aisle+"\",\n" +
                "\"author\":\"John foe\"\n" +
                "}\n";
        return P1;

    }
}
