public final class ApiEndPoint {
    private final static String API_KEY = "Zo1cNgGZoI7Xuao7LswpyKN7qIUnwnWe96x3hAgM"; // "DEMO_KEY"; //
    private final static String BASE_API_URI = "https://api.nasa.gov/mars-photos/api/v1/";
    private final static String BASE_API_URI_ROVERS = "rovers/";
    private final static String BASE_API_URI_MANIFEST = "manifests/";
    private final static String BASE_API_URI_PHOTOS = "/photos";
    private static final String MARS_ROVER_PHOTOS = "Mars_Rover_Photos";
    private static final String ROVER_CURIOSITY = "curiosity";
    private static final String ROVER_OPPORTUNITY = "opportunity";
    private static final String ROVER_SPIRIT = "spirit";

    private static ApiEndPoint ApiEndPoint = null;

    public static ApiEndPoint getInstance(){
        if(ApiEndPoint == null){
            ApiEndPoint = new ApiEndPoint();
        }
        return ApiEndPoint;
    }

    private ApiEndPoint(){ }

    public String getApiKey() {
        return API_KEY;
    }

    private String getBasePhotosUri() {
        return BASE_API_URI+BASE_API_URI_ROVERS;
    }

    private String getBaseManifestUri() {
        return BASE_API_URI+BASE_API_URI_MANIFEST;
    }

    public String getMarsRoverPhotos(){
        return MARS_ROVER_PHOTOS;
    }

    private String getMarsRoverPhotosUri(String rover){
        return getBasePhotosUri().concat(rover);
    }

    public String getCuriosityPhotosUri(){
        return getMarsRoverPhotosUri(ROVER_CURIOSITY+BASE_API_URI_PHOTOS);
    }

    public String getOpportunityPhotosUri(){
        return getMarsRoverPhotosUri(ROVER_OPPORTUNITY+BASE_API_URI_PHOTOS);
    }

    public String getSpiritPhotosUri(){
        return getMarsRoverPhotosUri(ROVER_SPIRIT+BASE_API_URI_PHOTOS);
    }

    private String getMarsRoverPhotosManifesUri(String rover){
        return getBaseManifestUri().concat(rover);
    }

    public String getCuriosityPhotosManifesUri(){
        return getMarsRoverPhotosManifesUri(ROVER_CURIOSITY);
    }

    public String getOpportunityPhotosManifesUri(){
        return getMarsRoverPhotosManifesUri(ROVER_OPPORTUNITY);
    }

    public String getSpiritPhotosManifesUri(){
        return getMarsRoverPhotosManifesUri(ROVER_SPIRIT);
    }
}