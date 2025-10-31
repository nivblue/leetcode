package leetcode.a;

import lombok.ToString;

@ToString
public class VehicleUpdate {
    String vin;
    String manufacturer;
    int year;
    String model;
    double latitude;
    double longitude;
    // miliseconds
    long timestamp;
    int velocity;
}
