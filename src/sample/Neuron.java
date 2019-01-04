package sample;

public class Neuron {
    private double[] entree;
    public double[] poids;
    private double in=0;
    public double out=0;
    public double d=0;


    public Neuron( double[] poids, double seuil) {
        this.poids = new double[poids.length+1];
        this.poids[0]=seuil;
        for (int i=0; i<poids.length;i++) this.poids[i+1]=poids[i];

    }

    public void setPoids(double[] poids, double seuil) {
        this.poids = new double[poids.length+1];
        this.poids[0]=seuil;
        for (int i=0; i<poids.length;i++) this.poids[i+1]=poids[i];

    }

    public void calculerSortie(double[] entree){
        //System.out.println(entree.length);
        this.entree = new double[entree.length+1];
        this.entree[0]=-1;
        for (int i=0; i<entree.length;i++) this.entree[i+1]=entree[i];
        in = 0.0;
        //System.out.println("here"+ in);
        for (int i=0;i<this.entree.length;i++){
            in += this.entree[i]*this.poids[i];
        //System.out.println(this.entree[i]+" multiplied by "+this.poids[i]+" add in to "+in);
        }
        out = 1/(1+Math.exp(-1*in));
        System.out.println("and here \n "+ out);

    }
    //public boolean estActive(){return in>0;}
    /*public void entrainerNeoron(double[] entreeExemple, double resultatsVoulus, double alpha)
    {
        for (int i=0; i<entreeExemple.length; i++){
            calculerSortie(entreeExemple);
            for (int k=1; k<poids.length ;k++){
                poids[k]+=alpha*(resultatsVoulus - out)* entreeExemple[k];
            }
        }

    }
*/
}
