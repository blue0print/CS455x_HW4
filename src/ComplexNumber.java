// Name: Ismayil Adigozalzada
// USC NetID: 3786609877
// CSCI 455 HW#4
// Summer 2021

public class ComplexNumber {
    double real;
    double imag;

    public ComplexNumber() {
        real=0;
        imag=0;
    }
    public ComplexNumber(double x, double y) {
        real=x;
        imag=y;
    }
    public ComplexNumber(ComplexNumber c) {
        real=c.real;
        imag=c.imag;
    }

    // outputs a sum, as a new complex number
    public ComplexNumber add(ComplexNumber c) {
        double tmpReal = real + c.real;
        double tmpImag = imag + c.imag;
        ComplexNumber cOut = new ComplexNumber(tmpReal,tmpImag);
        return cOut;
    }
    // outputs a squared value, as a new complex number
    public ComplexNumber squared(ComplexNumber c) {
        double tmpReal = java.lang.Math.pow(c.real,2)-java.lang.Math.pow(c.imag,2);
        double tmpImag = 2*c.real*c.imag;
        ComplexNumber cOut = new ComplexNumber(tmpReal,tmpImag);
        return cOut;
    }
    // does cout in the form of 'real + imag j'
    public void prt(ComplexNumber c) {
        System.out.println(c.real +" + " +c.imag +"j");
    }
    // outputs the magnitude of the complex number: sqrt(real*real+imag*imag)
    public double abs(ComplexNumber c) {
        return java.lang.Math.sqrt(c.real*c.real+c.imag*c.imag);
    }
}
