/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.*;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author abhijit.saurabh
 */
public class Test_Case_SelectorController implements Initializable{

    @FXML
    private ListView<String> test_case_list;
    @FXML
    private ListView<String> selected_test_case;
    private ObservableList<String> list = FXCollections.observableArrayList();
    @FXML
    private Button create;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        ObservableList<String> names;
        ObservableList<String> files = FXCollections.observableArrayList();
        File folder = new File("C:\\Users\\abhijit.saurabh\\Documents\\Specs_Manuals");
File[] listOfFiles = folder.listFiles();

for (File file : listOfFiles) {
    if (file.isFile()) {
        
        files.add(file.getName().substring(0, file.getName().lastIndexOf('.')));

    }
}
        names = FXCollections.observableArrayList(
                "Julia", "Ian", "Sue", "Matthew", "Hannah", "Stephan", "Denise","Julia", "Ian", "Sue", "Matthew", "Hannah", "Stephan", "Denise");
       
        test_case_list.setItems(files);
    }    

    @FXML
    private void add_other_list(MouseEvent event) {
        
        
        list.add(test_case_list.getSelectionModel().getSelectedItem());
        if(!checkDuplicateUsingSet(list)){
        } else {
            list.remove(test_case_list.getSelectionModel().getSelectedItem());
        }
        selected_test_case.setItems(list);
    }

    @FXML
    private void remove_test_case(MouseEvent event) {
      list.remove(selected_test_case.getSelectionModel().getSelectedItem());
      selected_test_case.setItems(list);
    }
    
    public static boolean checkDuplicateUsingSet(ObservableList<String> input){
       Set tempSet = new HashSet();
        for (String str : input) {
            if (!tempSet.add(str)) {
                return true;
            }
        }
        return false;

    }
    @FXML
    private void SaveFile(){
   
     FileChooser fileChooser = new FileChooser();
  
              //Set extension filter
              FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
              fileChooser.getExtensionFilters().add(extFilter);
              
              //Show save file dialog
              File file = fileChooser.showSaveDialog(new Stage());
              
              if(file != null){
                  write_file(file);
              }
}
private void write_file(File file){
    
    Alert alert = new Alert(AlertType.INFORMATION);
alert.setTitle("File Status");
        try {
            FileWriter fileWriter = null;
             
            fileWriter = new FileWriter(file);
            for(String str: list) {
                
                fileWriter.write(str);
                fileWriter.write("\r\n");
                
}
            fileWriter.close();
            alert.setHeaderText(null);
alert.setContentText("File Saved Succesfully");
        } catch (IOException ex) {
            alert.setContentText("File not Saved Succesfully");
        }
alert.showAndWait();
    }

}
