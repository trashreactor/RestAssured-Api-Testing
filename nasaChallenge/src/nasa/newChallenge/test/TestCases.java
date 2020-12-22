import java.util.ArrayList;
import java.util.HashMap;
import org.testng.annotations.*;
import org.testng.Assert;


public class TestCases {
    private ApiEndPoint api = ApiEndPoint.getInstance();
    private String sol = "1000";
    private String pages = "1";
    private String earth_date = "2015-5-30"; // 2015-5-30 == sol 1000
    private Requests requests = Requests.getInstance();
    private Helpers helper = Helpers.getInstance();

    /*  
        1- Test Case: Retrieve the first 10 Mars photos made by "Curiosity"
         on 1000 Martian sol
    */
    @Test
    public void test_1_NasaMarsCuriosityPhotosOnSol1000(){
        ArrayList<String> photos = requests.getPhotosResponseOnSol(sol, api.getApiKey(), pages, api.getCuriosityPhotosUri());
        if(photos !=null){
            Assert.assertTrue(photos.size() >= 10,"There are less than 10 or no photos in the response");           
        }else Assert.fail("Unable to compare the results, since the response is empty"); 
    }

    /*  
        2- Test Case: Retrieve the first 10 Mars photos made by "Curiosity"
         on Earth date equal to 1000 Martian sol.
    */
    @Test
    public void test_2_NasaMarsCuriosityPhotosOnEarthDateEqualToSol1000(){
        ArrayList<String> photos = requests.getPhotosResponseOnEarthDate(earth_date, api.getApiKey(), pages, api.getCuriosityPhotosUri());
        if(photos !=null){
            Assert.assertTrue(photos.size() >= 10,"There are less than 10 or no photos in the response");  
        }else Assert.fail("Unable to compare the results, since the response is empty");       
    }

    /*  
        3- Test Case: Retrieve and compare the first 10 Mars photos made by 
        "Curiosity" on 1000 sol and on Earth date equal to 1000 Martian sol.
    */
    @Test
    public void test_3_NasaMarsCuriosityComparePhotosOnEarthDateEqualToSol1000(){
        ArrayList<String> solPhotos = requests.getPhotosResponseOnSol(sol, api.getApiKey(), pages, api.getCuriosityPhotosUri());
        ArrayList<String> earthPhotos = requests.getPhotosResponseOnEarthDate(earth_date, api.getApiKey(), pages, api.getCuriosityPhotosUri());
        if(solPhotos !=null && earthPhotos != null){
            Assert.assertEquals(solPhotos,earthPhotos,"Mars photos on sol "+sol+" are not equal to Mars photos on earth date "+earth_date);    
        }else Assert.fail("Unable to compare the results, since one of the responses is empty");
     }

    /*  
        4- Test Case: Validate that the amounts of pictures that each "Curiosity" 
        camera took on 1000 Mars sol is not greater than 10 times the amount 
        taken by other cameras on the same date.
    */
    @Test
    public void test_4_NasaMarsCuriosityCompareCuriosityPhotosAreNot10TimesGreaterThanOpportunityOnSol1000(){
        ArrayList<HashMap<String, Object>> curiosityPhotos = requests.getManifestResponse(api.getApiKey(), api.getCuriosityPhotosManifesUri());
        Assert.assertTrue(curiosityPhotos.size() >= 10,"There are less than 10 or no photos in the response");
        HashMap<String, Object> curiosityData = helper.getManifestDataForSol(curiosityPhotos, sol);
        if(curiosityData != null){
            ArrayList<HashMap<String, Object>> opportunityPhotos = requests.getManifestResponse(api.getApiKey(), api.getOpportunityPhotosManifesUri());
            Assert.assertTrue(opportunityPhotos.size() >= 10,"There are less than 10 or no photos in the response");
            HashMap<String, Object> opportunityData = helper.getManifestDataForSol(opportunityPhotos, sol);
            if(opportunityData != null){
                int curiosityTotalPhotos = Integer.parseInt(curiosityData.get("total_photos").toString());
                int opportunityTotalPhotos = Integer.parseInt(opportunityData.get("total_photos").toString());
                Assert.assertTrue(opportunityTotalPhotos*10 >= curiosityTotalPhotos,"Curiosity took "+curiosityTotalPhotos+" photos and Opportunity took "+opportunityTotalPhotos+" (x10 = "+opportunityTotalPhotos*10+" photos) photos on sol " +sol);
            }else Assert.fail("No Oportunity was retrieved");
        }else Assert.fail("No Curiosity was retrieved");

     }

     @Test
    public void test_4_NasaMarsCuriosityCompareCuriosityPhotosAreNot10TimesGreaterThanSpiritOnSol1000(){
        ArrayList<HashMap<String, Object>> curiosityPhotos = requests.getManifestResponse(api.getApiKey(), api.getCuriosityPhotosManifesUri());
        Assert.assertTrue(curiosityPhotos.size() >= 10,"There are less than 10 or no photos in the response");
        HashMap<String, Object> curiosityData = helper.getManifestDataForSol(curiosityPhotos, sol);
        if(curiosityData != null){
            ArrayList<HashMap<String, Object>> SpiritPhotos = requests.getManifestResponse(api.getApiKey(), api.getSpiritPhotosManifesUri());
            Assert.assertTrue(SpiritPhotos.size() >= 10,"There are less than 10 or no photos in the response");
            HashMap<String, Object> SpiritData = helper.getManifestDataForSol(SpiritPhotos, sol);
            if(SpiritData != null){
                int curiosityTotalPhotos = Integer.parseInt(curiosityData.get("total_photos").toString());
                int SpiritTotalPhotos = Integer.parseInt(SpiritData.get("total_photos").toString());
                Assert.assertTrue(SpiritTotalPhotos*10 >= curiosityTotalPhotos,"Curiosity took "+curiosityTotalPhotos+" photos and Spirit took "+SpiritTotalPhotos+" (x10 = "+SpiritTotalPhotos*10+" photos) photos on sol " +sol);
            }else Assert.fail("No Spirit photo was retrieved");
        }else Assert.fail("No Curiosity photo was retrieved");

     }
}
