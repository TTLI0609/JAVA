package pobj.pinboard.editor;

import java.io.File;

import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pobj.pinboard.document.Board;
import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipGroup;
import pobj.pinboard.editor.commands.CommandGroup;
import pobj.pinboard.editor.commands.CommandUngroup;
import pobj.pinboard.editor.tools.Tool;
import pobj.pinboard.editor.tools.ToolEllipse;
import pobj.pinboard.editor.tools.ToolImage;
import pobj.pinboard.editor.tools.ToolRect;
import pobj.pinboard.editor.tools.ToolSelection;

public class EditorWindow implements EditorInterface, ClipboardListener{

	private Tool tool;
	private Board board;
	private final Canvas canvas = new Canvas(800,400);
	
	private Selection selection = new Selection();
	private CommandStack stack =  new CommandStack();
	
	MenuItem editPaste;
	
	/**
	 * construit un fenetre avec le stage passé en argument
	 * @param stage
	 */
	public EditorWindow(Stage stage) {
		board = new Board();
		
		stage.setTitle("PinBoard");
		
		// separateur 
		Separator separateur = new Separator();
		// Barre de statut (etat initial)
		Label label = new Label("Choose a tool");
		
		
		/* Barre des menus */
		//File
		Menu menufile = new Menu("File");	
			//ajouter le menu deroulante de File et leur action (New et Close)
		MenuItem filenew = new MenuItem("New");
		menufile.getItems().add(filenew);
		filenew.setOnAction( (e) -> {   
			new EditorWindow(new Stage());  
		} );
		MenuItem fileclose = new MenuItem("Close");
		menufile.getItems().add(fileclose);
		fileclose.setOnAction( (e) -> {  
			Clipboard.getInstance().removeListener(this);	// on supprime le listener quand on ferme la fenetre
			stage.close();   
		});
		
		
		// Edit
		Menu menuedit = new Menu("Edit");
			//menu deroulante de Edit et leur action ( Copy, Paste, Delete)
		MenuItem editCopy=new MenuItem("Copy");
		editCopy.setOnAction( (e) -> {
			//on ajoute les clips selectionnés par Clipboard
			Clipboard.getInstance().copyToClipboard(selection.getContents());
			clipboardChanged() ;	// on a selectionné quelque chose	
			draw();
		} );
		
		editPaste=new MenuItem("Paste");
		clipboardChanged();
		editPaste.setOnAction( (e) -> {
			//on ajoute les clips selectionne dans board
			board.addClip(Clipboard.getInstance().copyFromClipboard());
			draw();
		} );	
		
		MenuItem editDelete=new MenuItem("Delete");
		editDelete.setOnAction( (e) -> {
			//on supprime les clips selectionne du board
			board.removeClip(selection.getContents());
			clipboardChanged() ;
			draw();
		} );	
		
			//TME9 : Group et Ungroup dans le menu Edit
		MenuItem editGroup=new MenuItem("Group");
		editGroup.setOnAction(
				(e)-> {
					ClipGroup gr = new ClipGroup();
					for(Clip c : selection.getContents()) 	// parcourir tout les clip selectionner
						gr.addClip(c);
					CommandGroup cgr = new CommandGroup(this, gr.getClips());
					stack.addCommand(cgr);		// ajouter dans le satck
					cgr.execute();
					//board.removeClip(gr.getClips()); // on enleve les clips qui sont séparés
					//board.addClip(gr);		// on ajoute le clipGroup regrouper
				}
		);
		MenuItem editUngroup=new MenuItem("Ungroup");
		editUngroup.setOnAction(
				(e)-> {
					if( selection.getContents().get(0) instanceof ClipGroup) {	// si l'element selectionner est un ClipGroup 
						CommandUngroup cugr = new CommandUngroup(this, (ClipGroup)selection.getContents().get(0));
						stack.addCommand(cugr);
						cugr.execute();
						//board.removeClip( selection.getContents().get(0));// enleve le clip groupé
						//board.addClip( ((ClipGroup)selection.getContents().get(0)).getClips()  );	// ajoute les clips separer (donc argument cest une liste de vlip)
					}
				}
		);
		
			// ajout de undo et redo
		MenuItem editUndo = new MenuItem("Undo");
		editUndo.setOnAction(
				(e) -> {
					stack.undo();
					draw();
		});
		
		MenuItem editRedo = new MenuItem("Redo");
		editRedo.setOnAction(
				(e) -> {
					stack.redo();
					draw();
		});
		
		menuedit.getItems().addAll(editCopy, editPaste, editDelete, editGroup, editUngroup,editUndo, editRedo);
		
		
		// Tools
		Menu menutools = new Menu("Tools");
			//ajout du menu deroulante de Tools et leur action (Rectangle, Ellipse, Image, Select)
		MenuItem toolsRect=new MenuItem("Rectangle");
		toolsRect.setOnAction( (e) -> {
			tool=new ToolRect();
			label.setText(tool.getName(this));
		} );
		
		MenuItem toolsEllipse=new MenuItem("Ellipse");
		toolsEllipse.setOnAction( (e) -> {
			tool=new ToolEllipse();
			label.setText(tool.getName(this));
		} );
		MenuItem toolsImage=new MenuItem("Image");
		toolsImage.setOnAction( (e) -> {
			FileChooser fileChooser = new FileChooser();
			File file = fileChooser.showOpenDialog(stage);
			tool = new ToolImage( file );
			label.setText(tool.getName(this));
		} );
		MenuItem toolsSelct = new MenuItem("Selct");
		toolsSelct.setOnAction( (e) -> {
			tool=new ToolSelection();
			label.setText(tool.getName(this));
		} );
		menutools.getItems().addAll(toolsRect, toolsEllipse, toolsImage, toolsSelct);
		
		
			//le menu barre
		MenuBar menubar = new MenuBar(menufile, menuedit, menutools);
		
		
		
		/* Barre de boutons */
		Button box = new Button("Box");
		Button ellipse = new Button("Eclipse");
		Button img = new Button("Img...");
		Button select = new Button("Select");
			//color palet
		Separator verticalSep = new Separator(Orientation.VERTICAL);
		ColorPicker ColorChoose = new ColorPicker(Color.BLACK);
		ColorChoose.setOnAction((e) ->{
			Color choice = ColorChoose.getValue();
			board.setColor(choice);
		});
		
		ToolBar toolbar = new ToolBar(box , ellipse, img, select, verticalSep, ColorChoose );

		box.setOnAction(	// on choisi rectangle dans la barre de boutons
				(e) -> {
					tool = new ToolRect();
					label.setText(tool.getName(this));
				});
		ellipse.setOnAction(	//on choisi ellipse dans la barre de boutons
				(e) ->{ 
					tool = new ToolEllipse();
					label.setText(tool.getName(this));
				});
		img.setOnAction(		// on choisi img... dans la barre de boutons
				(e) -> {
					FileChooser fileChooser = new FileChooser();
					File file = fileChooser.showOpenDialog(stage);
					tool = new ToolImage( file );
					label.setText(tool.getName(this));
				});
		select.setOnAction(		// on choisi select dans la barre de boutons
				(e) -> {
					tool = new ToolSelection();
					label.setText(tool.getName(this));
				});
		

		EventHandler<MouseEvent> press = new EventHandler<MouseEvent>() {
	    	@Override
	    	public void handle(MouseEvent e) {
	  		   tool.press(EditorWindow.this, e);
	  	     }
	    };
		EventHandler<MouseEvent> drag = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				tool.drag(EditorWindow.this, e);
	            draw();
			}
		};
	    EventHandler<MouseEvent> released = new EventHandler<MouseEvent>() {
	           @Override
	           public void handle(MouseEvent e) {
	        		   tool.release(EditorWindow.this, e);
		        	   board.draw(canvas.getGraphicsContext2D());
	           }
	    };
	    canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED,drag);
	    canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, press);
	    canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, released);
	    
	    
	    
	    
	    
		//canvas = Board
		//Canvas canvas = new Canvas(800, 500);
		
	
		

		
		
		//VBox qui contient tout
		VBox vbox = new VBox();
		vbox.getChildren().addAll(menubar, toolbar, canvas, separateur, label);
		
		stage.setScene(new javafx.scene.Scene(vbox));
		stage.show();
		
		//pour que initialement Paste est en gris car on a rien selectionner
		Clipboard.getInstance().addListener(this);
		Clipboard.getInstance().Changed();
		
	}	
	
	
	
	
	public Tool getTool(){
		return tool;
	}
	@Override
	public Board getBoard() {
		return board;
	}
	@Override
	public Selection getSelection() {
		return selection;
	}
	@Override
	public CommandStack getUndoStack() {
		return stack;
	}
	
	/**
	 * mettre a jour le canvas
	 */
	public void draw(){
		GraphicsContext gc = canvas.getGraphicsContext2D();
		board.draw(gc);	//dessiner le board
		tool.drawFeedback(this, gc);
		
	}
	
	@Override
	public void clipboardChanged() {
		if(Clipboard.getInstance().isEmpty()){		//si cest vide, donc on a rien selectionné donc on désactive paste
			editPaste.setDisable(true);
		}
		else{
			editPaste.setDisable(false);
		}
	}
	
	
}
