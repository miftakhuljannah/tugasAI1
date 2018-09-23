package com.company;

import java.util.Random;

import static java.lang.Math.*;

public class Main {

    private static double x, y;

    private static double hitung(double x, double y) {
        return -abs(sin(x)*cos(y)*exp(abs(1 - sqrt(pow(x,2)+pow(y,2))/PI)));
    }

    public static void main(String[] args) {
        x = 1;
        y = 2;
        double suhu = 1000;

        double solusi = 0;
        double delta = 100;
        double iterasi = 0;
        int kali = 0;
        double solusiSementara = hitung(x,y);


	    while (kali <= 4 && suhu > 0 ){
	        iterasi++;
	        if (delta<=pow(10,-11)){
	            kali++;
            }else{
	            kali=0;
            }
            ubahXY();
	        solusi = hitung(x,y);
	        if (solusi < solusiSementara){
	            delta = abs(solusi-solusiSementara);
	            solusiSementara = solusi;
            }
            else{
                double deltaSementara = solusi - solusiSementara;
                if (exp(-deltaSementara/suhu) > random(0,1)) {
                    delta = abs(deltaSementara);
                    solusiSementara = solusi;
                }
            }
            suhu -= 0.0000001;
        }
        System.out.println("(x,y): (" + x + "," + y + ")." + "[solusi]:" + solusi + ". [iterasi]:" + iterasi + ". [suhu]:" + suhu);
    }

    private static void ubahXY (){
        double alpha = random(-0.00001, 0.00001);
        double hasil[] = {
                hitung(x+alpha, y),
                hitung(x, y+alpha),
                hitung(x-alpha, y),
                hitung(x,y-alpha),
                hitung(x+alpha, y+alpha),
                hitung(x-alpha, y-alpha),
                hitung(x+alpha, y-alpha),
                hitung(x-alpha, y+alpha)
        };
        double min = hasil[0];
        int x = 0;
        for (int i = 0; i<8; i++){
            if (hasil[i] < min){
                min = hasil[i];
                x = i;
            }
        }
        switch (x){
            case 0:
                x+=alpha;
                break;
            case 1:
                y+=alpha;
                break;
            case 2:
                x-=alpha;
                break;
            case 3:
                y-=alpha;
                break;
            case 4:
                x+=alpha;
                y+=alpha;
                break;
            case 5:
                x-=alpha;
                y-=alpha;
                break;
            case 6:
                x+=alpha;
                y-=alpha;
                break;
            case 7:
                x-=alpha;
                y+=alpha;
                break;

        }
    }

    private static double random (double left, double right){
        return left+new Random().nextDouble() * (right-left);
    }

}
