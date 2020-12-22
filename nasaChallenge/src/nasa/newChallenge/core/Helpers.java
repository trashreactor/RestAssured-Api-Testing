import java.util.HashMap;
import java.util.ArrayList;

public class Helpers {
    private static Helpers helpers = null;

    public static Helpers getInstance(){
        if(helpers == null){
            helpers = new Helpers();
        }
        return helpers;
    }
    private Helpers(){ }

    public HashMap<String, Object> getManifestDataForSol(ArrayList<HashMap<String, Object>> manifestData,String sol) {
        int i = 0;
        Boolean found = false;
        HashMap<String, Object> data = null;
        while(i<manifestData.size() & !found){
            if(manifestData.get(i).get("sol").toString().equalsIgnoreCase(sol)){
                found = true;
                data = manifestData.get(i);
            }
            i++;
        }
        return data;
    }
}
