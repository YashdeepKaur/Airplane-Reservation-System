public class Flight {
    private String flightNumber;
    private String departureCity;
    private String arrivalCity;
    private String departureTime;
    private String price;
    private String[] mealOptions;
    private String imagePath;
    private String arrivalTime;

    public Flight(String flightNumber, String departureCity, String arrivalCity, String departureTime,String arrivalTime, String price, String[] mealOptions, String imagePath) {
        this.flightNumber = flightNumber;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
        this.mealOptions = mealOptions;
        this.imagePath = imagePath;
    }

    

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }
    public String getPrice() {
        return price;
    }

    public String[] getMealOptions() {
        return mealOptions;
    }

    public String getImagePath() {
        return imagePath;
    }

   
}
