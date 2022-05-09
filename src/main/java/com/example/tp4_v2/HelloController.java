package com.example.tp4_v2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.tp4_v2.Game.*;
import static com.example.tp4_v2.Game.enemies;

import static java.lang.Math.max;

public class HelloController {

    @FXML
    int nombreHerosValeur = 1;

    @FXML
    private Label nombreHeros;

    Text herosSelectionneValeurText;
    Text degatsValeurText;
    Text potionsValeurText;
    Text nourritureValeurText;
    Text armureValeurText;
    Text JoueursRestantsText;
    Text JoueursRestantsValeurText;
    Text roundValeurText;
    Text tourValeurText;
    private Hero selectedHero;
    int tour = 1;
    int round = 1;
    static int heroRecompensesCompteur;
    public boolean isAttackMode;

    static Stage stage;

    GridPane root;

    final int SCREEN_SIZE = 800;
    private int maximum;



    @FXML
    protected void onPlayButtonClick(ActionEvent event) {



        generateHeroes(nombreHerosValeur);
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();

        stage = new Stage();
        generationJeuUI();
    }

    public void generationJeuUI() {

        herosSelectionneValeurText = null;
        degatsValeurText  = null;
        potionsValeurText  = null;
        nourritureValeurText  = null;
        armureValeurText  = null;
        JoueursRestantsText  = null;
        JoueursRestantsValeurText  = null;
        roundValeurText  = null;
        tourValeurText  = null;

        heroRecompensesCompteur= 0;
        tour = 1;

        generateEnemies();
        maximum = max(enemies.size(),heroes.size());

        resetHeroesLeftRound();


        ScrollPane scrollPane = new ScrollPane();

        root = new GridPane();


        genererPremiereLigneUI();
        updateAllUI();


        scrollPane.setFitToWidth(true);
        scrollPane.setContent(root);
        Scene scene = new Scene(scrollPane, SCREEN_SIZE, SCREEN_SIZE);
        stage.setTitle("RPG!");
        stage.setScene(scene);
        stage.show();
    }






    private void checkEnemiesLeft() {
        verifyHealth();
        if(enemies.isEmpty()){
            afficherRecompenses();
        }
    }



    private void checkHeroesLeft() {
        if(heroesLeftRound.isEmpty()){
            tour +=1;
            Game.randomAttack();
            verifyHealth();
            resetHeroesLeftRound();
            if(heroes.isEmpty()){
                afficherDefaite(); //TODO : et créer une variable pour la défaite dans Game pour les tests.
            }
        }
        selectedHero = null;
        updateAllUI();
    }

    private void afficherDefaite() {
        stage.close();

        Stage stage2 = new Stage();

        Parent root2 = null;
        try {
            root2 = new FXMLLoader(HelloApplication.class.getResource("defaite.fxml")).load();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Scene scene2 = new Scene(root2, SCREEN_SIZE, SCREEN_SIZE);
        stage2.setTitle("Défaite !");
        stage2.setScene(scene2);
        stage2.show();
    }


    private void afficherRecompenses() {


        Parent newRoot;
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("recompenses.fxml"));
            newRoot = loader.load();

            RecompensesController controller = loader.getController();
            controller.setMainController(this);





            round++;
            Scene scene3 = new Scene(newRoot, SCREEN_SIZE, SCREEN_SIZE);
            stage.setTitle("Remise des récompenses !");
            stage.setScene(scene3);
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void updateAllUI(){

        updateUI();
        updateBottomUI();
    }

    private void updateHeroesLeft() {
        Game.updateHeroesLeft(selectedHero);
        checkHeroesLeft();
    }


    private void updateUI(){



        Image herbe = new Image(getClass().getResourceAsStream("/img/herbe.jpg"),SCREEN_SIZE / 11, SCREEN_SIZE / 11, false, false);


        for (int i=0;i<11;i++){

            for(int j=1;j<maximum+1;j++){
                root.add(new ImageView(herbe),i,j);


                /*
                Génération des héros et des clicks liés aux héros
                 */
                if(heroes.size()>=j){
                    if(i==2){
                        ImageView imageHero = new ImageView(new Image(getClass().getResourceAsStream("/img/"+heroes.get(j-1).getPath()+".png"),SCREEN_SIZE / 11, SCREEN_SIZE / 11, false, false));
                        root.add(imageHero,i,j);


                        int finalJ = j;
                        imageHero.setOnMouseClicked(mouseEvent -> {
                            if(selectedHero!=null){
                                if(isAttackMode && selectedHero.getClass()==Healer.class){
                                    actionChoix(selectedHero,"Attaquer",finalJ-1);
                                    updateHeroesLeft();
                                }
                                else if (Collections.frequency(heroesLeftRound,heroes.get(finalJ-1))>0){
                                    newSelection(finalJ);
                                }
                            }

                            else if (Collections.frequency(heroesLeftRound,heroes.get(finalJ-1))>0){
                                newSelection(finalJ);
                            }
                        });
                    }

                    else if(i==1){
                        Text textPVHero = new Text(""+heroes.get(j-1).getLifePoints());
                        GridPane.setHalignment(textPVHero, HPos.CENTER);
                        textPVHero.setFont(Font.font("Impact", FontWeight.BOLD, 50));
                        root.add(textPVHero,i,j);
                    }

                    else if (i == 0) {
                        if(SpellCaster.class.isAssignableFrom(heroes.get(j-1).getClass()) || heroes.get(j-1).getClass()==Hunter.class){
                            if(SpellCaster.class.isAssignableFrom(heroes.get(j-1).getClass())){
                                SpellCaster spellCaster = (SpellCaster) heroes.get(j-1);
                                Text textManaHero = new Text(""+ spellCaster.getMana());

                                textManaHero.setFont(Font.font("Impact", FontWeight.BOLD, 50));

                                GridPane.setHalignment(textManaHero, HPos.CENTER);

                                root.add(textManaHero,i,j);
                            }
                            else{
                                Hunter hunter = (Hunter) heroes.get(j-1);
                                Text textArrowHero = new Text(""+ hunter.getArrow());
                                textArrowHero.setFont(Font.font("Impact", FontWeight.BOLD, 50));

                                GridPane.setHalignment(textArrowHero, HPos.CENTER);
                                root.add(textArrowHero,i,j);
                            }

                        }
                    }

                }







                /*
                Génération des ennemis et des clicks liés aux ennemis
                */

                if (enemies.size()>=j){
                    if(i==9){
                        ImageView imageEnemy = new ImageView(new Image(getClass().getResourceAsStream("/img/"+enemies.get(j-1).getPath()+".png"),SCREEN_SIZE / 11, SCREEN_SIZE / 11, false, false));
                        root.add(imageEnemy,i,j);

                        int finalJ1 = j;
                        imageEnemy.setOnMouseClicked(mouseEvent -> {

                            if(isAttackMode == true && selectedHero!=null){
                                System.out.println("ATTAQUE");
                                if(selectedHero.getClass()!=Healer.class){
                                    System.out.println("ATTAQUE REUSSIE");
                                    actionChoix(selectedHero,"Attaquer",finalJ1-1);
                                    updateHeroesLeft();
                                    System.out.println(enemies);
                                    checkEnemiesLeft();
                                }
                            }

                        });
                    }
                    else if(i==10){
                        Text textPVEnemy = new Text(""+enemies.get(j-1).getLifePoints());
                        GridPane.setHalignment(textPVEnemy, HPos.CENTER);
                        textPVEnemy.setFont(Font.font("Impact", FontWeight.BOLD, 50));
                        root.add(textPVEnemy,i,j);
                    }
                }
            }
        }
    }

    private void newSelection(int selectedHero) {
        this.selectedHero = heroes.get(selectedHero -1);
        // TODO : toast message "selected"
        updateBottomUI();
    }

    private void updateBottomUI(){
        List<Integer> listPositionHeroes = new ArrayList<>();

        if(herosSelectionneValeurText==null){
            int debut = max(heroes.size(), enemies.size())+1;

            Text roundText = new Text("Round : ");
            root.add(roundText,9,debut);
            GridPane.setHalignment(roundText, HPos.CENTER);

            roundValeurText = new Text(""+round);
            root.add(roundValeurText,10,debut);



            Text tourText = new Text("Tour : ");
            root.add(tourText,9,debut+1);

            tourValeurText = new Text(""+tour);
            root.add(tourValeurText,10,debut+1);




            Text herosSelectionneText = new Text("Héros : ");
            root.add(herosSelectionneText,0,debut);

            herosSelectionneValeurText = new Text(selectedHero==null ? "-" : ""+(heroes.indexOf(selectedHero)+1));
            root.add(herosSelectionneValeurText,1,debut);

            Text degatsText = new Text("Dégâts : ");

            root.add(degatsText,0,debut+1);

            degatsValeurText = new Text("-");
            root.add(degatsValeurText,1,debut+1);

            Text potionsText = new Text("Potions : ");
            root.add(potionsText,0,debut+2);

            potionsValeurText = new Text("-");
            root.add(potionsValeurText,1,debut+2);

            Text nourritureText = new Text("Nourriture : ");
            root.add(nourritureText,0,debut+3);

            nourritureValeurText = new Text("-");
            root.add(nourritureValeurText,1,debut+3);

            Text armureText = new Text("Armure : ");
            root.add(armureText,0,debut+4);

            armureValeurText = new Text("-");
            root.add(armureValeurText,1,debut+4);


            JoueursRestantsText = new Text("Joueurs \nrestants : ");
            root.add(JoueursRestantsText ,0,debut+5);

            for(Hero hero : heroesLeftRound){
                listPositionHeroes.add(heroes.indexOf(hero)+1);
            }

            JoueursRestantsValeurText = new Text(""+listPositionHeroes);
            JoueursRestantsValeurText.setFont(Font.font("Arial", FontWeight.NORMAL, 9));
            root.add(JoueursRestantsValeurText,1,debut+5);



            List<Text> textList = List.of(herosSelectionneValeurText,
                    degatsValeurText,
                    potionsValeurText,
                    nourritureValeurText,
                    armureValeurText,
                    JoueursRestantsText,
                    JoueursRestantsValeurText,
                    roundValeurText,
                    tourValeurText,
                    roundText,
                    tourText,
                    herosSelectionneText,
                    degatsText,
                    potionsText,
                    nourritureText,
                    armureText);

            for(Text x : textList){
                GridPane.setHalignment(x, HPos.CENTER);
            }

        }
        else{

            roundValeurText.setText(""+round);
            tourValeurText.setText(""+tour);

            herosSelectionneValeurText.setText(selectedHero==null ? "-" : ""+(heroes.indexOf(selectedHero)+1));
            degatsValeurText.setText(selectedHero==null ? "-" : ""+selectedHero.getWeaponDamage());
            potionsValeurText.setText(selectedHero==null ? "-" : ""+selectedHero.getPotions().size());
            nourritureValeurText.setText(selectedHero==null ? "-" : ""+selectedHero.getFood().size());
            armureValeurText.setText(selectedHero==null ? "-" : ""+selectedHero.getArmor());

            JoueursRestantsValeurText.setText(""+heroes);

            for(Hero hero : heroesLeftRound){
                listPositionHeroes.add(heroes.indexOf(hero)+1);
            }

            JoueursRestantsValeurText.setText(""+listPositionHeroes);
        }
    }

    private void genererPremiereLigneUI() {
        List<String> boutonsPremiereLigne = List.of("Attaquer","Defendre","Potion","Bouffe");
        List<String> textePremiereLigne = List.of("Mana|Flèches","PV","PV");
        List<Integer> positionTextePremiereLigne = List.of(0,1,10);

        for(int l=0;l<3;l++){
            Text textPremiereLigneObject = new Text(textePremiereLigne.get(l));
            root.add(textPremiereLigneObject,positionTextePremiereLigne.get(l),0);
            GridPane.setHalignment(textPremiereLigneObject, HPos.CENTER);
        }


        for(int p=0;p<boutonsPremiereLigne.size();p++){

            Button buttonPremiereLigneObject = new Button(boutonsPremiereLigne.get(p));
            root.add(buttonPremiereLigneObject,p+4,0);

            GridPane.setHalignment(buttonPremiereLigneObject, HPos.CENTER);

            int finalP1 = p;
            buttonPremiereLigneObject.setOnMouseClicked(mouseEvent ->{
                if(selectedHero!=null){
                    if(finalP1==0){
                        isAttackMode = true;
                    }
                    else{
                        isAttackMode = false;
                        actionChoix(selectedHero,boutonsPremiereLigne.get(finalP1));
                        updateHeroesLeft();
                        updateAllUI();
                    }
                }
            });
        }
    }

    @FXML
    protected void onAddButtonClick(){
        nombreHerosValeur++;
        nombreHeros.setText("Nombre de héros : " + nombreHerosValeur);
    }

    @FXML
    protected void onDecreaseButtonClick(){
        nombreHerosValeur = nombreHerosValeur >1 ? nombreHerosValeur -1 : 1;
        nombreHeros.setText("Nombre de héros : " + nombreHerosValeur);
    }

}