/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileexplorer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileSystemView;

/**
 * FXML Controller class
 *
 * @author TIS
 */
public class TreeViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    TreeView <String> treeView;
    @FXML
    TableView<TableItemClass> table;
    @FXML
    TableColumn<TableItemClass,Integer> id;
    @FXML
    TableColumn<TableItemClass,Integer> size;
    @FXML
    TableColumn<TableItemClass,String> name;
    @FXML
    TableColumn<TableItemClass,Integer> date;//date
    @FXML 
    Label curr_dir;
    @FXML 
    Label curr_dir_box;        
    @FXML
    FlowPane tilebox;
    @FXML
    ScrollPane scroll;
    @FXML
    Button option;
   
    //dateFormat
     SimpleDateFormat sdf = new SimpleDateFormat("MMM d,yyyy");
     //
    int checkTab=1;
    
    String some = System.getProperty("user.dir");
    File current = new File(some);
    
    
    public ObservableList<TableItemClass> list =FXCollections.observableArrayList(
    new TableItemClass(TableIcon(current),(int)(long)current.length(),System.getProperty("user.dir"),
    sdf.format(current.lastModified()))
    );
    
    ArrayList<String> clickedList;
    //
    public void addTable(Image icon ,String name,Integer size,String date){
       
        list.add(new TableItemClass(icon,size,name,date));
        
    }
    public void removeTabel(){
        list.removeAll(list);
    }
    public void removeTile(){
        tilebox.getChildren().clear();// clear all item
    }
    
    public void addRow_string(String pathName){
          
          
      //    System.out.println("*"+);
          File[] files;
          
          if("This PC".equals(pathName)){
              files = File.listRoots();
              
          }else{
                File folder = new File( pathName );     
                files = folder.listFiles();
                
            }
         
          if(  files!=null ){
          for(File path: files ) {
               
              try {
                  addTable(TableIcon(path),path.getCanonicalPath(),(int)(long)path.length(),
                          sdf.format(path.lastModified()) );//addto table
              } catch (IOException ex) {
                  Logger.getLogger(TreeViewController.class.getName()).log(Level.SEVERE, null, ex);
              }
               
              
          }
          }
      
        
    }
    
    public void addRow_s(TableItemClass root){
          
          String pathName = root.getName();
      //    System.out.println("*"+);
          File[] files;
          
          if("This PC".equals(pathName)){
              files = File.listRoots();
              
          }else{
                File folder = new File( pathName );     
                files = folder.listFiles();
                
            }
         
          if(  files!=null ){
          for(File path: files ) {
               
              try {
                  addTable(TableIcon(path),path.getCanonicalPath(),(int)(long)path.length(),
                          sdf.format(path.lastModified()) );//addto table
              } catch (IOException ex) {
                  Logger.getLogger(TreeViewController.class.getName()).log(Level.SEVERE, null, ex);
              }
               
              
          }
          }
      
        
    }
    
    public void addTile(TreeItem<String> root) throws IOException{
            
        
          ArrayList lblList=new ArrayList();//  
         // scroll.setContent(tilebox);
          scroll.setFitToWidth(true);
          
          String pathName = root.getValue();
          
          File[] files;
          
          int error=0;
          
          if("This PC".equals(pathName)){
              files = File.listRoots();
              error=1;
          }else{
                File folder = new File( pathName );     
                files = folder.listFiles();
                error=0;
            }
         
          if(  files!=null ){
          for(File path: files ) {
              
                Label lbl=new Label();
                lblList.add(lbl);//
                lbl.setMaxSize(20,20);
                lbl.setTextOverrun(OverrunStyle.CLIP);
                
                if(error==0){
                    lbl.setText(path.toPath().getFileName().toString());
                    lbl.setId(path.getCanonicalPath());
                }
                else{
                    lbl.setText(path.getCanonicalPath());
                    lbl.setId(path.getCanonicalPath());
                }
                lbl.setGraphic(new ImageView(TableIcon(path)));
                lbl.setContentDisplay(ContentDisplay.TOP);
                
                
                TileMouseClicked(lbl);
                
                
                tilebox.getChildren().add(lbl);
                tilebox.setHgap(30);
                tilebox.setVgap(30);
                
        
          }
          }
    
    }
    
    public void addTile_s(String str) throws IOException{
           
          scroll.setFitToWidth(true);
          
          String pathName = str;
          
          File[] files;
          
          int error=0;
          
          if("This PC".equals(pathName)){
              files = File.listRoots();
              error=1;
          }else{
                File folder = new File( pathName );     
                files = folder.listFiles();
                error=0;
            }
         
          if(  files!=null ){
          for(File path: files ) {
              
                Label lbl=new Label();
                
                lbl.setMaxSize(20,20);
                lbl.setTextOverrun(OverrunStyle.CLIP);
                
                if(error==0){
                    lbl.setText(path.toPath().getFileName().toString());
                    lbl.setId(path.getCanonicalPath());
                }
                else {
                    lbl.setText(path.getCanonicalPath());
                    lbl.setId(path.getCanonicalPath());
                }
                lbl.setGraphic(new ImageView(TableIcon(path)));
                lbl.setContentDisplay(ContentDisplay.TOP);
                
                
                TileMouseClicked(lbl);
                
                
                tilebox.getChildren().add(lbl);
                tilebox.setHgap(30);
                tilebox.setVgap(30);
                
                
        
          }
          }
    
    }
    
     public void addRow(TreeItem<String> root){
          
          String pathName = root.getValue();
          
          File[] files;
          
          
          if("This PC".equals(pathName)){
              files = File.listRoots();
              
          }else{
                File folder = new File( pathName );     
                files = folder.listFiles();
                
            }
         
          if(  files!=null ){
          for(File path: files ) {
               
              try {
                  addTable(TableIcon(path),path.getCanonicalPath(),(int)(long)path.length(),
                          sdf.format(path.lastModified()) );//addto table
              } catch (IOException ex) {
                  Logger.getLogger(TreeViewController.class.getName()).log(Level.SEVERE, null, ex);
              }
               
               
          }
          }
      
        
    }
    
    public void addChild(TreeItem<String> root){
          
          String pathName = root.getValue();
          
        //  System.out.println("-"+pathName);
          
          File[] files;
          
          if("This PC".equals(pathName)){
              files = File.listRoots();
          }else{
                File folder = new File( pathName );     
                files = folder.listFiles();
            }
         
          if(  files!=null ){
              //
              
          for(File path: files ) {
              
              root.getChildren().add(new TreeItem<>(path.getAbsolutePath(),TreeIcon(path)));
             // System.out.println(path);
              
          }
      
          }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        option.setOnMouseClicked((MouseEvent e) -> {
                
            
            if(checkTab==1){
                table.setVisible(true);
                scroll.setVisible(false);
                checkTab=0;
                option.setText("Table View");
            }
            else{
                table.setVisible(false);
                scroll.setVisible(true);
                checkTab=1;
                option.setText("Tile View");
            }
            
            
            
           
            
            
                        
                });
        table.setVisible(false);
        
        
        
        Image icon = new Image(getClass().getResourceAsStream("/img/win.png"));
        TreeItem<String> root =new TreeItem<>("This PC",new ImageView(icon));
       
        this.clickedList = new ArrayList<>();
        treeView.setRoot(root);
        //code for table 
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        size.setCellValueFactory(new PropertyValueFactory<>("age"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        date.setCellValueFactory(new PropertyValueFactory<>("surname"));
        table.setItems(list);
        
        
    }    
    
    
    public void handleMouseClicked(MouseEvent event) throws IOException {
    Node node = event.getPickResult().getIntersectedNode();
    // Accept clicks only on node cells, and not on empty spaces of the TreeView
        
    
    if (node instanceof Text || (node instanceof TreeCell && ((TreeCell) node).getText() != null)) {
        
        TreeItem<String> name = treeView.getSelectionModel().getSelectedItem();
        System.out.println("Node click:  ");
        
        curr_dir_box.setText(name.getValue());
        
        removeTabel();//atfirst remove 
        addRow(name);//then add 
        //for tiles View
        removeTile();
        addTile(name);        
        
        
        if( clickedList.contains(name.getValue()) ){
            return;
        }
        clickedList.add(name.getValue());
        addChild(name);
        
        
    }
    
}
    
    public void rowMouseClicked(MouseEvent event){
                
                int count=event.getClickCount();
                System.out.println(count); 
                if(count==2){
                    TableItemClass item =table.getSelectionModel().getSelectedItem();
                    
                    System.out.println("*"+table.getSelectionModel().getSelectedItem()); 
                    removeTabel();//atfirst remove
                    addRow_s(item);
                    
                    removeTile();
                    try {  
                        addTile_s(item.getName());
                    } catch (IOException ex) {
                        Logger.getLogger(TreeViewController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }    
          
    }
    
    public void TileMouseClicked(Label lbl){
            
                lbl.setOnMouseClicked((MouseEvent e) -> {
                    if(e.getClickCount()==2){
                        
                        try {
                            removeTile();
                            addTile_s(lbl.getId());
                            
                            removeTabel();//atfirst remove
                            addRow_string(lbl.getId());
                        } catch (IOException ex) {
                            Logger.getLogger(TreeViewController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        System.out.println( lbl.getText() );
                    }
                        
                        
                        
                });
            
                
          
    }
    public ImageView TreeIcon(File item){
        
        if(item.isFile() ==true)
        {
            Image icon = new Image(getClass().getResourceAsStream("/img/file2.png"));
            ImageView img=new ImageView(icon);
            return img;
        }
        else {
            Image icon = new Image(getClass().getResourceAsStream("/img/folder.png")) ;
            ImageView img=new ImageView(icon);
            return img;
        }
         
    }
    
    public Image TableIcon (File file){
        ImageIcon icon = (ImageIcon) FileSystemView.getFileSystemView().getSystemIcon(file);
        BufferedImage bufferedImage = (BufferedImage)icon.getImage();
        WritableImage fxImage = new WritableImage(icon.getIconWidth(), icon.getIconHeight());
        Image imageview = SwingFXUtils.toFXImage(bufferedImage, fxImage);
        return imageview;
    }
    
   
    
}
