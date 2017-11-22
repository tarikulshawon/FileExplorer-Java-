/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileexplorer;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.Icon;

/**
 *
 * @author TIS
 */
public class TableItemClass {
    private final ImageView id;
    private final SimpleStringProperty name;
    private final SimpleStringProperty surname;
    private final SimpleIntegerProperty age;
    
    public TableItemClass(Image id,Integer age,String name,String surname){
        super();
        this.id=new ImageView(id);
        this.name=new SimpleStringProperty(name);
        this.surname=new SimpleStringProperty(surname);
        this.age=new SimpleIntegerProperty(age);     
    }
    public ImageView getId(){
        return id;
    }
    public Integer getAge(){
        return age.get();
    }
    public String getName(){
        return name.get();
    }
    public String getSurname(){
        return surname.get();
    }
}
