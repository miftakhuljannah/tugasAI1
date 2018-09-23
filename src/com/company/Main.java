package com.company;

import java.util.Random;

import static java.lang.Math.*;

public class Main {
    private static double x, y;
    private static double hitung(double x, double y) {
        return -abs(sin(x)*cos(y)*exp(abs(1 - sqrt(pow(x,2)+pow(y,2))/PI)));
    }
    public static void main(String[] args) {
        // menetapkan nilai awal dan suhu awal
        x = 4;
        y = 4;
        System.out.println("Parameter awal (x,y):(" + x + "," + y + "). ");
        double suhu = 1000;
        double solusi = 100;
        double delta = 100;
        double iterasi = 0;
        double xakhir = 0, yakhir = 0;

        int kali = 0;
        //menghitung solusi sementara dan menjadikannya best so far.
        double solusiSementara = hitung(x,y);
        //lakukan perulangan
	    while (kali <= 4 && suhu > 0 ){
	        iterasi++;
	        if (delta<=pow(10,-11)){
	            kali++;
            }else{
	            kali=0;
            }
            // Ubah XY
            ubahXY();
	        // Hitung solusi sementara
	        solusi = hitung(x,y);
	        // Jika solusi baru lebih baik dari solusi sementara
	        if (solusi < solusiSementara){
	            // Hitung delta, solusi sesementara diganti deengan yang baru.
	            delta = abs(solusi-solusiSementara);
	            solusiSementara = solusi;
	            xakhir = x;
	            yakhir = y;
            } // Jika solusi baru tidak lebih baik dari solusi sementara
            else{
                // Hitung delta
                double deltaSementara = solusi - solusiSementara;
                if (exp(-deltaSementara/suhu) > random(0,1)) {
                    // Jika rumus melebihi nilai random dari 0...1
                    // update nilai delta dan solusi sementara
                    delta = abs(deltaSementara);
                    solusiSementara = solusi;
                    xakhir = x;
                    yakhir = y;
                }
            }
            // 4. dinginkan suhu
            suhu -= 0.0000001;
        }
        // print solusi
        System.out.println("(x,y): (" + xakhir + "," + yakhir + ")." + "[solusi]:" + solusi + ". [iterasi]:" + iterasi + ". [suhu]:" + suhu);
    }

    private static void ubahXY (){
        double alpha = random(-0.00001, 0.00001);
        double hasil[] = {hitung(x+alpha, y), hitung(x, y+alpha), hitung(x-alpha, y), hitung(x,y-alpha), hitung(x+alpha, y+alpha), hitung(x-alpha, y-alpha), hitung(x+alpha, y-alpha), hitung(x-alpha, y+alpha)
        };
        double min = hasil[0];
        int j = 0;
        for (int i = 0; i<8; i++){
            if (hasil[i] < min){
                min = hasil[i];
                j = i;
            }
        }

        switch (j){
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
