package oop;

public class classC implements inter{

    @Override
    public void printInter() {
        System.out.println("Class C-Inter");
    }
    public classC(){
        System.out.println("Class C");
        inter.printer();
    }
}
