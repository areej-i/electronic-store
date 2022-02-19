import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ElectronicStoreApp extends Application {

    private ElectronicStore model;
    private ElectronicStoreView view;

    private Stage stage;

    @Override
    public void start(Stage stage) throws Exception {

        this.stage = stage;

        model = ElectronicStore.createStore();
        view = new ElectronicStoreView(model);

        stage.setTitle(model.getName());
        stage.setScene(new Scene(view, 800, 400));
        stage.setResizable(false);
        stage.show();
        doUpdate();

        view.getStockListView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                doUpdate();
            }
        });

        view.getCartListView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                doUpdate();
            }
        });

        view.getAddToCartButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                doAddToCart();
            }
        });

        view.getRemoveFromCartButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                doRemoveFromCart();
            }
        });

        view.getFinishSaleButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                doFinishSale();
            }
        });

        view.getResetButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                try {
                    doReset();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void doAddToCart() {
        for (int i = 0; i < model.getStockItems(false).length; i++){
            if (model.getStock()[i].toString().contains(view.getStockListView().getSelectionModel().getSelectedItem().toString())){
                model.transferToCart(i);
            }
        }
        view.getCartLabel().setText("Current Cart: " + "($" + model.getCartVal() + ")");
        doUpdate();
    }

    public void doRemoveFromCart() {
        model.transferToStock(view.getCartListView().getSelectionModel().getSelectedIndex());
        doUpdate();
    }

    public void doFinishSale() {
        for (int i = 0; i < model.MAX_PRODUCTS; i++){
            model.sellProducts(i, model.getCartCount(i));
        }
        model.tickSale();
        model.clearCart();
        view.getStockListView().getSelectionModel().select(0);
        model.getMostPopular(3);
        doUpdate();
    }

    public void doReset() throws Exception {
        start(stage);
    }

    public void doUpdate(){
        view.getCartListView().setItems(FXCollections.observableArrayList(model.getCartItems()));
        view.getStockListView().setItems(FXCollections.observableArrayList(model.getStockItems(true)));
        view.getPopularListView().setItems(FXCollections.observableArrayList(model.getMostPopular(3)));

        view.getSalesField().setText(String.valueOf(model.getSales()));
        view.getRevenueField().setText(String.valueOf(model.getRevenue()));
        view.getPerSaleField().setText(String.valueOf(model.getRevenuePerSale()));

        if (view.getCartListView().getSelectionModel().getSelectedIndex() == -1) {
            view.getRemoveFromCartButton().setDisable(true);
        }
        else {
            view.getRemoveFromCartButton().setDisable(false);
        }

        if (view.getStockListView().getSelectionModel().getSelectedIndex() == -1) {
            view.getAddToCartButton().setDisable(true);
        }
        else {
            view.getAddToCartButton().setDisable(false);
        }

        if (view.getCartListView().getItems().isEmpty()) {
            view.getFinishSaleButton().setDisable(true);
        }
        else {
            view.getFinishSaleButton().setDisable(false);
        }
    }
}
