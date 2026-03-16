package quantitymeasurement;
public class QuantityMeasurementApp {

    public static class Feet{

        private final double value;

        public Feet(double value){
            this.value=value;
        }
        @Override
        public boolean equals(Object obj){
            if(obj==null) return false;
            if(obj.getClass() != Feet.class) return false;
            return Double.compare(this.value,((Feet) obj).value)==0 ? true : false;
        }



    }
    public static class Inches {
        private double value;
        public Inches(double value) {
            if (Double.isNaN(value)) {
                throw new IllegalArgumentException("Value must be numeric");
            }
            this.value = value;
        }
        public double getValue() {
            return value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (!(obj instanceof Inches))
                return false;
            Inches other = (Inches) obj;
            return Double.compare(this.value, other.value) == 0;
        }
    }

    public static void main(String[] args) {

        Feet f1= new Feet(5.2);
        Feet f2= new Feet(6.4);

        System.out.println(f1.equals(f2));

        Inches i1 = new Inches(1.0);
        Inches i2 = new Inches(1.0);
        System.out.println(i1.equals(i2));

    }
}