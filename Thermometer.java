public class Thermometer  {
    private double tempC;

    public Thermometer(){
        tempC = 0.0;
    }

    public void setCelsius (double c){
        tempC = c;
    }

    public void setFahrenheit(double f){
        tempC = (f - 32.0) * (5.0 / 9.0);
    }

    public double getCelsius(){
        return tempC;
    }

    public double getFahrenheit(){
        return (tempC * 1.8) + 32.0;
    }

    public static double diffCelsius (Thermometer t1, Thermometer t2){
        return t1.getCelsius() - t2.getCelsius();
    }

    public static double diffFahrenheit (Thermometer t1, Thermometer t2){
        return t1.getFahrenheit() - t2.getFahrenheit();
    }

    public static void main(String args[]){
        Thermometer t1 = new Thermometer();
        Thermometer t2 = new Thermometer();

        t1.setCelsius(10);
        System.out.println("Thermometer 1 in Fahrenheit: " + t1.getFahrenheit());

        t2.setFahrenheit(32);
        System.out.println("Difference between T1 and T2 in celsius:" + Thermometer.diffCelsius(t1, t2));
    }
}
