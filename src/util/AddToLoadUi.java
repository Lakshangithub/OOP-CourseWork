package util;

import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public interface AddToLoadUi {
    void loadUi(String location) throws IOException;


    void CloseWindowUi(AnchorPane x) throws IOException;

}
