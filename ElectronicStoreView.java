import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.control.ListView;
import javafx.scene.control.*;

import java.awt.*;

public class ElectronicStoreView extends Pane {

    private Button resetBtn;
    private Button addToBtn;
    private Button removeFromBtn;
    private Button finishBtn;

    private ListView<String> popularItemsLstVw;
    private ListView<String> stockLstVw;
    private ListView<String> cartLstVw;

    private TextField salesFld;
    private TextField revenueFld;
    private TextField perSaleFld;

    private Label summaryLbl;
    private Label popularItemsLbl;
    private Label stockLbl;
    private Label cartLbl;
    private Label numSalesLbl;
    private Label revenueLbl;
    private Label perSaleLbl;

    private ElectronicStore model;

    private String textStyle = "-fx-text-fill: #cccccc; -fx-font-weight: bold;";

    public ElectronicStoreView(ElectronicStore model) {

        /***** Set panel background *****/

        setStyle("-fx-background-color: #444444;");

        /***** Assign model *****/

        this.model = model;

        /***** Init buttons and add *****/

        resetBtn = new Button("Reset Score");
        resetBtn.relocate(10, 355);
        resetBtn.setPrefSize(150, 40);

        addToBtn = new Button("Add to Cart");
        addToBtn.relocate(250, 355);
        addToBtn.setPrefSize(125, 40);

        removeFromBtn = new Button("Remove from Cart");
        removeFromBtn.relocate(480, 355);
        removeFromBtn.setPrefSize(150, 40);
        removeFromBtn.setStyle("-fx-background-color: #FF0000;");

        finishBtn = new Button("Complete Sale");
        finishBtn.relocate(630, 355);
        finishBtn.setPrefSize(150, 40);
        finishBtn.setStyle("-fx-background-color: #00FF00;");

        getChildren().add(resetBtn);
        getChildren().add(addToBtn);
        getChildren().add(removeFromBtn);
        getChildren().add(finishBtn);

        /***** Init lists and add *****/

        popularItemsLstVw = new ListView<>();
        popularItemsLstVw.relocate(10, 200);
        popularItemsLstVw.setPrefSize(150, 150);

        stockLstVw = new ListView<>();
        stockLstVw.relocate(170, 50);
        stockLstVw.setPrefSize(300, 300);

        cartLstVw = new ListView<>();
        cartLstVw.relocate(480, 50);
        cartLstVw.setPrefSize(300, 300);

        getChildren().add(popularItemsLstVw);
        getChildren().add(stockLstVw);
        getChildren().add(cartLstVw);

        /***** Init fields and add *****/

        salesFld = new TextField();
        salesFld.relocate(70, 50);
        salesFld.setPrefSize(90, 20);

        revenueFld = new TextField();
        revenueFld.relocate(70, 80);
        revenueFld.setPrefSize(90, 20);

        perSaleFld = new TextField();
        perSaleFld.relocate(70, 110);
        perSaleFld.setPrefSize(90, 20);

        getChildren().add(salesFld);
        getChildren().add(revenueFld);
        getChildren().add(perSaleFld);

        /***** Init labels and add *****/

        summaryLbl = new Label("Store Summary: ");
        summaryLbl.relocate(50,10);
        summaryLbl.setPrefSize(100, 30);
        summaryLbl.setStyle(textStyle);

        popularItemsLbl = new Label("Most Popular Items:");
        popularItemsLbl.relocate(30, 160);
        popularItemsLbl.setPrefSize(130, 30);
        popularItemsLbl.setStyle(textStyle);

        stockLbl = new Label("Store Stock:");
        stockLbl.relocate(280, 10);
        stockLbl.setPrefSize(100, 30);
        stockLbl.setStyle(textStyle);

        cartLbl = new Label();
        cartLbl.relocate(580,10);
        cartLbl.setPrefSize(150, 30);
        cartLbl.setStyle(textStyle);

        numSalesLbl = new Label("# Sales:");
        numSalesLbl.relocate(20, 45);
        numSalesLbl.setPrefSize(100, 30);
        numSalesLbl.setStyle(textStyle);

        revenueLbl = new Label("Revenue:");
        revenueLbl.relocate(12, 75);
        revenueLbl.setPrefSize(100, 30);
        revenueLbl.setStyle(textStyle);

        perSaleLbl = new Label("$ / Sale:");
        perSaleLbl.relocate(18, 105);
        perSaleLbl.setPrefSize(100, 30);
        perSaleLbl.setStyle(textStyle);

        getChildren().add(summaryLbl);
        getChildren().add(popularItemsLbl);
        getChildren().add(stockLbl);
        getChildren().add(cartLbl);
        getChildren().add(numSalesLbl);
        getChildren().add(revenueLbl);
        getChildren().add(perSaleLbl);
    }

    /***** Getters *****/

    public Button getResetButton() {
        return resetBtn;
    }

    public Button getAddToCartButton() {
        return addToBtn;
    }

    public Button getRemoveFromCartButton() {
        return removeFromBtn;
    }

    public Button getFinishSaleButton() {
        return finishBtn;
    }

    public ListView getStockListView() {
        return stockLstVw;
    }

    public ListView getCartListView() {
        return cartLstVw;
    }

    public ListView getPopularListView() {
        return popularItemsLstVw;
    }

    public TextField getSalesField() {
        return salesFld;
    }

    public TextField getRevenueField() {
        return revenueFld;
    }

    public TextField getPerSaleField() {
        return perSaleFld;
    }

    public Label getCartLabel() {
        return cartLbl;
    }
}
