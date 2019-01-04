package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public ReseauNeuron nassim;
    @FXML
private AnchorPane nassimo;
@FXML
private GridPane gridd;



public void invokeReseauNeuron() throws IOException {
    double[] aa,bb,cc;
    aa=new double[]{1,0,1,0,1};

    bb=new double[]{4, 2, 6, 8, 0};
    cc=new double[]{0, 0, 0,
            0, 0, 1,
            0, 0, 0};
    Neuron[] dd=new Neuron[2];
    Neuron[] ee=new Neuron[9];
    dd[0]=new Neuron(aa,0.5);
    aa=new double[]{0,1,0,1,0};
    dd[1]=new Neuron(aa,0.5);
    aa=new double[2];
    Arrays.fill(aa,1);
    for (int i=0; i<9; i++) ee[i]=new Neuron(aa,0.5);
    nassim = new ReseauNeuron(null,null,dd,ee);
    nassim.entrainerReseau(1,5);





}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            invokeReseauNeuron();
        } catch (IOException e) {
            e.printStackTrace();
        }
        double[] adrao={-1, -1, -1, -1, -1};
        nassim.setEntree(adrao);
        for (Node d:gridd.getChildren()
             ) {
            d.setOnMouseClicked(event -> {
                nassim.initialiser();
                Circle a=new Circle();
                    a.setFill(Color.RED);
                    a.setRadius(20);
                    gridd.add(a,gridd.getChildren().indexOf(d)%3,gridd.getChildren().indexOf(d)/3);
                    nassim.entree[gridd.getChildren().size()-11]=gridd.getChildren().indexOf(d);
                //System.out.println("voila moi: "+nassim.entree[0]+" * "+nassim.entree[1]+" * "+nassim.entree[2]+" * "+nassim.entree[3]+" * "+nassim.entree[4]);
                //System.out.println("****************" + (gridd.getChildren().indexOf(d))+"\n");
                try {
                    nassim.calculerSortie(1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Text z=new Text();
                z.setFill(Color.GREEN);
                z.setText("X");
                z.setFont(Font.font(50));
                /*try {
                    System.out.println(nassim.classe());
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
                try {
                    gridd.add(z,nassim.classe()%3,nassim.classe()/3);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    nassim.entree[gridd.getChildren().size()-11]=nassim.classe();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //System.out.println("voila machine: "+nassim.entree[0]+" * "+nassim.entree[1]+" * "+nassim.entree[2]+" * "+nassim.entree[3]+" * "+nassim.entree[4]);
                try {
                    nassim.calculerSortie(1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                nassim.initialiser();

            });
        }


    }
}
