package Interface;

import FrontEnd.Login;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;

import java.sql.ResultSet;
import java.util.List;

public class JavaFX
{

	static double scalex = Login.scalex;
	static double scaley = Login.scaley;

	// -------------------NewLabel--------------------------------------------------------------------------------------
	public static Label NewLabel(String text, Paint color,int style, int size, int x, int y)
	{
		Label label = new Label(text);
		FontWeight fontWeight=FontWeight.NORMAL;
		if(style==1) fontWeight=FontWeight.BOLD;
		label.setFont(Font.font("Century Gothic",fontWeight, (int) (scalex * size)));
		if(style==2) label.setStyle("-fx-font-style: italic;");
		label.setTextFill(color);
		label.setLayoutX(scalex*x);
		label.setLayoutY(scaley*y);
		return label;
	}

	public static Label NewLabel(String text, int size, int x, int y)
	{
		return NewLabel(text, Paint.valueOf("000000"), size, x, y);
	}

	public static Label NewLabel(String text, Paint color, int size, int x, int y)
	{
		return NewLabel(text, color,0,  size,  x,  y);
	}

	public static Label NewLabel(String text,int style, int size, int x, int y)
	{
		return NewLabel(text, Paint.valueOf("000000"),style, size, x, y);
	}


	// --------------------NewButton------------------------------------------------------------------------------------
	public static Button NewButton(String text,Image image,ContentDisplay contentDisplay, Paint color, int size,double x, double y,double width,double height)
	{
		Button button = new Button(text);
		if(image!=null)
		{
			ImageView imageView = new ImageView(image);
			double factor=2*size/height;
			if(contentDisplay==ContentDisplay.TOP)
				factor=(height-size*2)/height;
			double scale=width*factor;
			if(height<width)
				scale=height*factor;
			imageView.setFitWidth(scale*scalex);
			imageView.setFitHeight(scale*scaley);

			imageView.setSmooth(true);
			button.setGraphic(imageView);
		}

	    button.setContentDisplay(contentDisplay);
		button.setStyle("-fx-base: #"+color.toString().substring(2)+";");
		//button.setStyle("-fx-base: #FFFFFF00;");
		button.setFont(Font.font("Century Gothic",FontWeight.BOLD, (int) (scalex * size)));
		button.setTextFill(Color.WHITE);
		button.setLayoutX(scalex*x);
		button.setLayoutY(scaley*y);
		if(width>0 && height>0)
		{
			button.setMinSize(width*scalex, height*scaley);
			button.setMaxSize(width*scalex, height*scaley);
		}
		button.setFocusTraversable(false);
		return button;
	}

	public static Button NewButton(String test, double x, double y)
		{
			return NewButton(test, 20, x, y);
		}

	public static Button NewButton(String test, Color color, double x, double y)
	{
		return NewButton(test, color, 20, x, y);
	}

	public static Button NewButton(String text, int size,double x, double y)
	{
		//return NewButton(text,Color.rgb(80, 150, 190), size, x, y);
		return NewButton(text,Color.rgb(17, 186, 248), size, x, y);
	}

	public static Button NewButton(String text, Paint color, int size,double x, double y)
	{
		return NewButton( text,  color,  size, x,  y,0,0);
	}
	public static Button NewButton(String text, Paint color, int size,double x, double y,double width,double height)
	{
		return NewButton(text,null,ContentDisplay.LEFT, color, size,x, y,width,height);
	}

	public static Button Bwsh(String t, ImageView iv)
	{
		Button b1 = new Button(t, iv);
		DropShadow shadow = new DropShadow();
		b1.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>()
		{
			@Override public void handle(MouseEvent e)
			{
				b1.setEffect(shadow);
			}
		});
		b1.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>()
		{
			@Override public void handle(MouseEvent e)
			{
				b1.setEffect(null);
			}
		});
		return b1;
	}

	// ----------------------NewTextField--------------------------------------------------------------------------------
	public static TextField NewTextField(int size,int width, int x, int y)
	{
		TextField textField = new TextField();
		textField.setFont(new Font("Century Gothic",(int) (scalex *size )));
		textField.setPrefWidth(width*scalex);
		textField.setLayoutX(scalex*x);
		textField.setLayoutY(scaley*y);
		textField.setAlignment(Pos.CENTER);
		return textField;
	}

	public static PasswordField NewPasswordField(int size,int width, int x, int y)
	{
		PasswordField passwordField = new PasswordField();
		passwordField.setFont(new Font("Century Gothic", (int) (scalex * size)));
		passwordField.setPrefWidth(width*scalex);
		passwordField.setLayoutX(scalex*x);
		passwordField.setLayoutY(scaley*y);
		return passwordField;
	}

	// -------------------NewImage--------------------------------------------------------------------------------------
	public static ImageView NewImage(String path, int w, int h, int x, int y)
	{
		Image image=new Image("file:"+path);
		ImageView imageview=new ImageView();
		imageview.setImage(image);
		imageview.setFitWidth(scalex*w);
		imageview.setFitHeight(scaley*h);
		imageview.setLayoutX(scalex*x);
		imageview.setLayoutY(scaley*y);

		return imageview;
	}
	public static ImageView NewImage(String path, int x, int y)
	{
		Image image=new Image("file:"+path);
		return NewImage(path, (int)image.getWidth(),  (int)image.getHeight(), x, y);
	}

	public static ImageView NewNext()
	{
		ImageView next=JavaFX.NewImage("res/icon/Next.png",150,120,1150,615);
		Image org=next.getImage();
		ImageView hovered=JavaFX.NewImage("res/icon/NextHovered.png",120,150,0,0);
		next.addEventFilter(MouseEvent.MOUSE_ENTERED, mouseEvent -> next.setImage(hovered.getImage()));
		next.addEventFilter(MouseEvent.MOUSE_EXITED, mouseEvent ->next.setImage(org));
		return next;
	}
	public static ImageView NewPrevious()
	{
		ImageView next=JavaFX.NewImage("res/icon/Previous.png",150,120,50,615);
		Image org=next.getImage();
		ImageView hovered=JavaFX.NewImage("res/icon/PreviousHovered.png",120,150,0,0);
		next.addEventFilter(MouseEvent.MOUSE_ENTERED, mouseEvent -> next.setImage(hovered.getImage()));
		next.addEventFilter(MouseEvent.MOUSE_EXITED, mouseEvent ->next.setImage(org));
		return next;
	}
	public static ImageView NewEnd()
	{
		ImageView next=JavaFX.NewImage("res/icon/End.png",150,75,600,655);
		Image org=next.getImage();
		ImageView hovered=JavaFX.NewImage("res/icon/EndHovered.png",150,75,0,0);
		next.addEventFilter(MouseEvent.MOUSE_ENTERED, mouseEvent -> next.setImage(hovered.getImage()));
		next.addEventFilter(MouseEvent.MOUSE_EXITED, mouseEvent ->next.setImage(org));
		return next;
	}
	public static ImageView NewImageButton(String name,int w,int h,int x, int y)
	{
		ImageView button=JavaFX.NewImage("res/icon/"+name+".png",w,h,x,y);
		Image org=button.getImage();
		ImageView hovered=JavaFX.NewImage("res/icon/"+name+"Hovered.png",150,75,0,0);
		button.addEventFilter(MouseEvent.MOUSE_ENTERED, mouseEvent -> button.setImage(hovered.getImage()));
		button.addEventFilter(MouseEvent.MOUSE_EXITED, mouseEvent ->button.setImage(org));
		return button;
	}


	// --------------------NewComboBox----------------------------------------------------------------------------------
	public static ComboBox<String> NewComboBox(String[] list, int w,int h, int x, int y)
	{
		ObservableList<String> observableList=FXCollections.observableArrayList(list);
		ComboBox<String> comboBox = new ComboBox<>(observableList);

		int size=18;
		comboBox.setPrefWidth(scalex*w);
		if(h>0)
		{
			size+=(h-comboBox.getPrefHeight())/6;
			comboBox.setPrefHeight(scaley*h);
		}

		comboBox.setStyle("-fx-font: "+(int) (scalex * size)+"px \"Century Gothic\";");

		comboBox.setLayoutX(scalex*x);
		comboBox.setLayoutY(scaley*y);
		comboBox.getSelectionModel().selectFirst();
		return comboBox;
	}

	public static ComboBox<String> NewComboBox(String[] list, int w, int x, int y)
	{
		return NewComboBox(list,w,0,x,y);
	}

	public static ComboBox<String> NewComboBox(String[] list, int x, int y)
		{
			return NewComboBox(list, 350, x, y);
		}

	public static ComboBox<String> NewComboBox(List<String> list, int w,int h, int x, int y)
	{
		ObservableList<String> observableList=FXCollections.observableArrayList(list);
		ComboBox<String> comboBox = new ComboBox<>(observableList);

		int size=18;
		comboBox.setPrefWidth(scalex*w);
		if(h>0)
		{
			size+=(h-comboBox.getPrefHeight())/6;
			comboBox.setPrefHeight(scaley*h);
		}

		comboBox.setStyle("-fx-font: "+(int) (scalex * size)+"px \"Century Gothic\";");

		comboBox.setLayoutX(scalex*x);
		comboBox.setLayoutY(scaley*y);
		comboBox.getSelectionModel().selectFirst();
		return comboBox;
	}
	public static ComboBox<String> NewComboBox(List<String> list,int w, int x, int y)
	{
		return NewComboBox(list,w,0,x,y);
	}

	public static ComboBox<String> NewComboBox(List<String> list, int x, int y)
	{
		return NewComboBox(list, 350, x, y);
	}

	// --------------------NewCheckBox----------------------------------------------------------------------------------
	public static CheckBox NewCheckBox(String text,int size,int x,int y)
	{

		CheckBox checkBox = new CheckBox(text);
		checkBox.setFont(new Font("Century Gothic", (int) (scalex * size)));
		checkBox.setLayoutX(scalex*x);
		checkBox.setLayoutY(scaley*y);
		return checkBox;
	}
	public static CheckBox NewCheckBox(String text,int x,int y)
	{
		return NewCheckBox(text,17,x,y);
	}

	// --------------------NewTableView----------------------------------------------------------------------------------
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static TableView NewTableView(ResultSet rs,double x,double y,double w,double h)
	{
		ObservableList<ObservableList> data;
		TableView tableview=new TableView();
		tableview.setLayoutX(scalex*x);
		tableview.setLayoutY(scaley*y);
		tableview.setPrefWidth(scalex*(w+5));
		tableview.setPrefHeight(scaley*(h+5));
		tableview.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


		data = FXCollections.observableArrayList();
		try
		{
			for (int i = 0; i < rs.getMetaData().getColumnCount(); i++)
			{
				final int j = i;
				TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
				col.setCellValueFactory(
						new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>()
						{
							public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param)
							{
								return new SimpleStringProperty(param.getValue().get(j).toString());
							}
						});
				tableview.getColumns().addAll(col);
			}
				while (rs.next())
			{
				ObservableList<String> row = FXCollections.observableArrayList();
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++)
				{
					row.add(rs.getString(i));
				}
				data.add(row);
			}
			tableview.setItems(data);
			return tableview;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void updateTable(TableView tableview,ResultSet rs)
	{
		ObservableList<ObservableList> data = FXCollections.observableArrayList();
		try
		{
			while (rs.next())
			{
				ObservableList<String> row = FXCollections.observableArrayList();
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++)
				{
					row.add(rs.getString(i));
				}
				data.add(row);
			}
			tableview.setItems(data);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	// --------------------CloseButton----------------------------------------------------------------------------------
	public static FlowPane windowclose(Color color)
	{
		FlowPane windowclose=new FlowPane();
		HBox h = new HBox();
		Label text=new Label("Fermer");
		int font =(int)(20*scaley);
		Label cls = new Label("X");
		text.setTextFill(color);
		text.setStyle("-fx-font: "+font+" vedrana");
		cls.setTextFill(color);
		cls.setStyle("-fx-font: "+font+" verdana;-fx-font-weight: Bold; -fx-backgound-color: red;");
		h.setPadding(new Insets(3,3,3,3));
		h.getChildren().addAll(text,cls);
		h.addEventHandler(MouseEvent.MOUSE_ENTERED,
				new EventHandler<MouseEvent>() {
					@Override public void handle(MouseEvent e) {
						cls.setTextFill(Color.rgb(200,40,40));
						text.setTextFill(Color.rgb(200,40,40));
					}
				});
		h.addEventHandler(MouseEvent.MOUSE_EXITED,
				new EventHandler<MouseEvent>() {
					@Override public void handle(MouseEvent e) {
						cls.setTextFill(color);
						text.setTextFill(color);
					}
				});
		windowclose.getChildren().addAll(h);
		windowclose.setAlignment(Pos.CENTER_RIGHT);
		return windowclose;
	}

	public static void privilegeAlert()
	{
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Attention");
		alert.setContentText("Vous n'avez pas le droit d'accéder à cette option ou effectuer cette opération!");
		alert.setHeaderText(null);
		alert.showAndWait();
	}
}
