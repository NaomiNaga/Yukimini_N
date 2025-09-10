# Guia JAVA + JAVAFX for Dummies

## Hierarquia de pastas

```
src
- app
  - components
    - <tituloParteDaTela>
      - <TituloParteDaTela>.fxml
      - <TituloParteDaTelaController>.java
  - icons
    - <imagem>.png
  - interfaces
    - ComponentCallback.java
  - <mainScreen>
    - <MainScreen>.fxml
    - <MainScreenController>.java
  - model
    - <ModelName>.java
  - styles
    - <styles>.css
  - Main.java
```

## Imports básicos

```java
package app<.pastasAntesDoSeuNível>;
```
        
Exemplo para TituloParteDaTelaController.java:
```java
package app.components.tituloParteDaTela;

import <necessário para não quebrar o script - o IJ vai sugerir>;

import app<.pastasAntesDoNível><.ControllerDesejado>;
```

Os imports garantem o necessário para funcionar, terminam com `;`

## Main exemplo

```java
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("mainScreen/MainScreen.fxml"));
        Parent mainRoot = mainLoader.load();

        primaryStage.setTitle("App v1.0.001");
        primaryStage.setMaximized(true);
        primaryStage.setFullScreen(false);      // garante que NÃO é fullscreen
        primaryStage.initStyle(javafx.stage.StageStyle.DECORATED); // com barra de título
        primaryStage.setScene(new Scene(mainRoot));
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
```

## Includes

Exemplo:

```xml
<fx:include fx:id="graphResults" source="../components/graphResults/GraphResults.fxml" />
```

O `fx:id` é para dar nome único ao componente na tela (para chamar no Controller).

## MainScreenController

```java
package app.mainScreen;

import <todos os components referenciados/usados>

public class MainScreenController implements Initializable {

    @FXML private <TipoDoComponente/NomeController> <nomeComponente/classeUsadaNoController>

    @FXML private void <Função>() { /* TODO: implementar depois */ }

    private ComponentCallback <callbackEspecificoEmOutroController> = new ComponentCallback() {
        @Override
        public void onClickCallback() {
            <Algo>;
            <Algo2>;
        }
    };

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        <Outro>Controller.set<CallbackEspecificoEmOutroController>(callbackEspecificoEmOutroController);
    }

}
```

## ComponentCallback

Função que me permite que um componente incluído em outro fxml seja chamado pelo principal dele.

```java
package app.interfaces;

public interface ComponentCallback {
    void onClickCallback();
}
```

## Controller incluido

```java
public class <AlgumController> {
    private ComponentCallback callback<Especifico>;

    public void set<CallbackEspecifico>(ComponentCallback callback){
        this.callback<Especifico> = callback;
    }

    @FXML
    private void initialize() {
        <btnNomeDoBotao>.setOnMouseClicked(this::on<Especifico>);
        System.out.println("<AlgumController> carregado");
    }

    private void on<Especifico>(javafx.scene.input.MouseEvent mouseEvent) {
        callback<Especifico>.onClickCallback();
        System.out.println("[ComponenteDaTela] <Botão> clicado!");
        // coloque aqui o que deve acontecer ao clicar
    }

    @FXML
    private Button <btnNomeDoBotao>;
}
```
