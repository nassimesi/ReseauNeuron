package sample;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class ReseauNeuron {
    public double[] entree;
    private double[] resultatsVoulus;
    private Neuron[] hiddenNeurons;
    public Neuron[] resultNeurons;
    private double[] a;


    public ReseauNeuron(double[] entree, double[] resultatsVoulus, Neuron[] hiddenNeurons, Neuron[] resultNeurons) {
        this.entree = entree;
        this.resultatsVoulus = resultatsVoulus;
        this.hiddenNeurons = hiddenNeurons;
        this.resultNeurons = resultNeurons;
        a =new double[hiddenNeurons.length];

    }

    public void setEntree(double[] entree) {
        this.entree = entree;
    }
    public int classe() throws IOException {
        double max=resultNeurons[0].out;
        int maxIndice=0;

        for (int i=0; i<resultNeurons.length; i++){
            if (max<resultNeurons[i].out) {max=resultNeurons[i].out; maxIndice=i;}
        }
        System.out.println(maxIndice);
        return maxIndice;
    }
    public int calculerSortie(double alpha) throws IOException {
        for (int i=0; i<hiddenNeurons.length; i++){
            hiddenNeurons[i].calculerSortie(entree);
            a[i]=hiddenNeurons[i].out;
        }
        for (int i=0; i<resultNeurons.length; i++){
            resultNeurons[i].calculerSortie(a);
        }
        return classe();

    }
    public void entrainerReseau(double alpha, int tailleExemple) throws IOException {
        boolean stop=false;
        while (!stop)
        {
            System.out.print("enter training example");
        Scanner bagra = new Scanner(System.in);
        int hello=0;
        entree=new double[tailleExemple];
        resultatsVoulus= new double[resultNeurons.length];
        while (hello<tailleExemple) {entree[hello]=bagra.nextDouble(); System.out.println(entree[hello]); hello++;}
    System.out.println("enter desired result");
            hello=0;
            while (hello<9) {resultatsVoulus[hello]=bagra.nextDouble(); hello++;}
            calculerSortie(alpha);
            for (int i=0; i<resultNeurons.length; i++) {
            resultNeurons[i].d = resultNeurons[i].out * (1 - resultNeurons[i].out) * (resultatsVoulus[i] - resultNeurons[i].out);
            for (int j=1; j<resultNeurons[0].poids.length; j++){ resultNeurons[i].poids[j]+=alpha * resultNeurons[i].d* a[j-1];
                }
        }

        for (int i=0; i<hiddenNeurons.length; i++) {
            double sum = 0;
            for (int j = 0; j < resultNeurons.length; j++) sum += resultNeurons[j].d * resultNeurons[j].poids[i+1];
            hiddenNeurons[i].d = hiddenNeurons[i].out * (1 - hiddenNeurons[i].out) * sum;
            for (int j=1; j<hiddenNeurons[0].poids.length; j++) { hiddenNeurons[i].poids[j]+=alpha * hiddenNeurons[i].d * entree[j-1];
            }
        }
            calculerSortie(alpha);
            System.out.println("Do you want to stop ?");
            Scanner stoppable=new Scanner(System.in);
            if(Boolean.parseBoolean(stoppable.next())) stop=true;}

    }
    }
