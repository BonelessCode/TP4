package com.example.tp4_v2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static com.example.tp4_v2.Game.heroes;
import static com.example.tp4_v2.Game.remiseRecompenses;
import static com.example.tp4_v2.HelloController.heroRecompensesCompteur;

public class RecompensesController {

    @FXML
    private Label labelHero;

    @FXML
    private ImageView heroImage ;
    HelloController controller;

    public void setMainController(HelloController controller){
        this.controller = controller;
    }

    public void initialize(){
        heroImage.setImage(new Image(getClass().getResourceAsStream("/img/"+Game.heroes.get(heroRecompensesCompteur).getPath()+".png")));
        labelHero.setText("Hero actuel : "+heroRecompensesCompteur);
    }

    @FXML
    protected void addArmor(ActionEvent event) {
        remiseRecompenses(heroes.get(heroRecompensesCompteur),"A");
        heroRecompensesCompteur+=1;

        loadImageHeroRecompenses();


    }
    @FXML
    protected void addDamage(ActionEvent event) {
        remiseRecompenses(heroes.get(heroRecompensesCompteur),"B");
        heroRecompensesCompteur+=1;

        loadImageHeroRecompenses();


    }
    @FXML
    protected void addConsommable(ActionEvent event) {
        remiseRecompenses(heroes.get(heroRecompensesCompteur),"D");
        heroRecompensesCompteur+=1;

        loadImageHeroRecompenses();


    }
    @FXML
    protected void addConsommableEffect(ActionEvent event) {
        remiseRecompenses(heroes.get(heroRecompensesCompteur),"C");
        heroRecompensesCompteur+=1;

        loadImageHeroRecompenses();



    }
    @FXML
    protected void addFlecheouMana(ActionEvent event) {
        Hero hero = heroes.get(heroRecompensesCompteur);
        if(hero.getClass()==Hunter.class || SpellCaster.class.isAssignableFrom(hero.getClass())){
            remiseRecompenses(heroes.get(heroRecompensesCompteur),"E");
            heroRecompensesCompteur+=1;

            loadImageHeroRecompenses();
        }

    }


    private void loadImageHeroRecompenses() {
        if(heroRecompensesCompteur<heroes.size()){
            heroImage.setImage(new Image(getClass().getResourceAsStream("/img/"+Game.heroes.get(heroRecompensesCompteur).getPath()+".png")));
            labelHero.setText("Hero actuel : "+heroRecompensesCompteur);
        }
        else{
            controller.generationJeuUI();
        }
    }


}
