//Claude Sonnet 4.5
public class ThermometerAI { //I changed class name manually to prevent file conflicts
    private double temperatureCelsius;
    
    // Default constructor - sets temperature to 0 Celsius
    public ThermometerAI() {
        this.temperatureCelsius = 0.0;
    }
    
    // Set temperature using Celsius
    public void setCelsius(double celsius) {
        this.temperatureCelsius = celsius;
    }
    
    // Set temperature using Fahrenheit
    public void setFahrenheit(double fahrenheit) {
        this.temperatureCelsius = (fahrenheit - 32) * 5.0 / 9.0;
    }
    
    // Get temperature in Celsius
    public double getCelsius() {
        return temperatureCelsius;
    }
    
    // Get temperature in Fahrenheit
    public double getFahrenheit() {
        return temperatureCelsius * 1.8 + 32;
    }
    
    // Static method to get difference in Celsius (1st - 2nd)
    public static double diffCelsius(ThermometerAI t1, ThermometerAI t2) {
        return t1.getCelsius() - t2.getCelsius();
    }
    
    // Static method to get difference in Fahrenheit (1st - 2nd)
    public static double diffFahrenheit(ThermometerAI t1, ThermometerAI t2) {
        return t1.getFahrenheit() - t2.getFahrenheit();
    }
}

// ThermometerDriver.java
public class ThermometerDriver {
    public static void main(String[] args) {
        // Create first thermometer and add 10 degrees Celsius
        ThermometerAI thermo1 = new ThermometerAI();
        thermo1.setCelsius(10);
        System.out.println("Thermometer 1 temperature: " + thermo1.getFahrenheit() + " F");
        
        // Create second thermometer and set to 32 degrees Fahrenheit
        ThermometerAI thermo2 = new ThermometerAI();
        thermo2.setFahrenheit(32);
        
        // Print temperature difference in Celsius
        double diff = ThermometerAI.diffCelsius(thermo1, thermo2);
        System.out.println("Temperature difference (thermo1 - thermo2): " + diff + " C");
    }
}