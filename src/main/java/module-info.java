module br.edu.pucgoias.dbfx {
    requires javafx.controls;
    requires javafx.fxml;

    //necessário adicionar para liberar as bibliotecas de acesso a dados SQL
    requires java.sql;

    opens br.edu.pucgoias.dbjfx to javafx.fxml;
    exports br.edu.pucgoias.dbjfx;
}