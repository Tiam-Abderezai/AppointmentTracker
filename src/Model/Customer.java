package Model;

public class Customer {

    private int customerId;
    private int addressId;
    private int countryId;
    private int cityId;
    private String name;
    private String address;
    private String country;
    private String city;
    private String phone;

    // adress id, city id, country, 

    public Customer(int customerId, int addressId, int countryId, int cityId, String name, String address, String country, String city, String phone) {
        this.customerId = customerId;
        this.addressId = addressId;
        this.countryId = countryId;
        this.cityId = cityId;
        this.name = name;
        this.address = address;
        this.country = country;
        this.city = city;
        this.phone = phone;
    }

//    public Customer(int nameId, String name, String address, String country, String city, String phone) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
    

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getPhone() {
        return phone;
    }
}
