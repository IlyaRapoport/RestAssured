package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.json.simple.parser.ParseException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class GetPostSteps {
    @Given("I perform GET {string}")
    public void iPerformGET(String url) {
        //       given().contentType(ContentType.JSON);


    }

    @And("I perform id {string} and checking if first name {string} exist")
    public void iPerformIdAndCheckingIfFirstNameExist(String id, String name) {
        Metods.GETPOST(id, name);


    }


    @And("I'm Posting: email , first_name , last_name ,gender")
    public void iMPostingEmailFirst_nameLast_nameGender(Map<String, String> person) {
        Metods.Post(person);
    }

    @And("Verify data")
    public void verifyData(Map<String, String> person) {
        Metods.GetData(person);
    }

    @And("I perform id {string} and checking if first name {string} delete")
    public void iPerformIdAndCheckingIfFirstNameDelete(String id, String name) {
        Metods.Delete(id, name);
    }


    @And("I perform id {string}")
    public void iPerformId(String id, Map<String, String> person) {
        Metods.Put(id, person);
    }

    @And("Changing data")
    public void iPerformNewId(Map<String, String> person) {
        Metods.PutNewId(person);
    }

    @And("Delete all data")
    public void iDeleteDataWithNewId() {
        Metods.deleteWithNewId();
    }

    @Then("Verify status")
    public void verifyStatus() {
        Metods.verifyStatus();
    }

    @And("Getting data")
    public void gettingData() throws IOException, ParseException {

        Metods.gettingdat();

        FileWriter file = new FileWriter("Data.json");


        file.write(Metods.DataString);
        file.flush();

        //       FileReader reader = new FileReader("newDateJsonArr.json");

//       BufferedReader br=new BufferedReader(reader);
//       String str;
//
//       while (br.readLine()!=null){
//           str=br.readLine();
//           for (int i = 0; i <Metods.keys.length ; i++) {
//
//               if(str.contains(Metods.keys[i]))           System.out.println(str);
//           }
//       }


    }


    @And("Getting new data")
    public void gettingNewData() throws IOException {
        Metods.getingNewData();
        FileWriter file = new FileWriter("newData.json");


        file.write(Metods.newDataString);
        file.flush();
    }

    @And("Delete all objects")
    public void deleteAllObjects() {
        Metods.deleteAllObjects();



    }
}
