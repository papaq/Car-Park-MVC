package model.car;

public class Car {

    private String  model;
    private String  brand;
    private int     year;

    Car(String brand, String model, int year)
    {

        this.brand  = brand;
        this.model  = model;
        this.year   = year;
    }

    public String   getBrand()  {return brand;}
    public String   getModel()  {return model;}
    public int      getYear()   {return year;}

    @Override
    public String   toString() {

        return brand.concat(" ")
                .concat(model)
                .concat(" ")
                .concat("(")
                .concat(String.valueOf(year))
                .concat(")");
    }
}
