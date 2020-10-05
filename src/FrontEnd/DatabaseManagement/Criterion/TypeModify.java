package FrontEnd.DatabaseManagement.Criterion;

import BackEnd.CriterionType.CriterionType;
import BackEnd.CriterionType.CriterionTypeQueries;
import FrontEnd.Login;
import Interface.JavaFX;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;

import java.util.Optional;


public class TypeModify extends Pane
{
    static double scalex = Login.scalex;
    static double scaley = Login.scaley;

    private Paint grey= Paint.valueOf("E9E9E9");
    private Paint red= Paint.valueOf("F04040");
    private Paint lightBlue= Paint.valueOf("5096be");
    private Paint lightGreen= Paint.valueOf("50be96");

    public TypeModify(CriterionInterface parent, int id)
    {
        this.setPrefWidth(575*scalex);
        this.setPrefHeight(150*scaley);
        this.setStyle("-fx-background-color: #"+grey.toString().substring(2)+";");

        CriterionType criterionType= CriterionTypeQueries.getCriteriaTypeById(id);

        Label refLB=JavaFX.NewLabel("Reference",lightBlue,1,18,10,10);
        TextField refField=JavaFX.NewTextField(18,200,10,50);
        refField.setText(criterionType.getRef());
        Label libLB=JavaFX.NewLabel("Libellé",lightBlue,1,18,250,10);
        TextField libField=JavaFX.NewTextField(18,300,250,50);
        libField.setText(criterionType.getLabel());

        Button confirm=JavaFX.NewButton("Confirmer",lightGreen,18,350,110);
        Button cancel=JavaFX.NewButton("Annuler",red,18,475,110);

        getChildren().addAll(refLB,refField,libLB,libField,confirm,cancel);

        confirm.addEventFilter(MouseEvent.MOUSE_PRESSED, mouseEvent->
        {
            String ref=refField.getText();
            String label=libField.getText();

            if(!label.equals(criterionType.getLabel()) || !ref.equals(criterionType.getRef()))
            {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Attention");
                alert.setContentText("Voulez-vous enregistrer ces modifications ?");
                alert.setHeaderText(null);
                Optional<ButtonType> result = alert.showAndWait();
                if (ButtonType.OK == result.get())
                {
                    CriterionTypeQueries.update(id,ref,label);
                }
            }
            parent.refreshTable2();

            parent.resetSelection();
            this.setStyle("-fx-background-color: #f3f3f3;");
            getChildren().clear();
        });

        cancel.addEventFilter(MouseEvent.MOUSE_PRESSED, mouseEvent->
        {
            parent.resetSelection();
            getChildren().clear();
            this.setStyle("-fx-background-color: #f3f3f3;");
        });
    }

}
