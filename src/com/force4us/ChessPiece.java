package com.force4us;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class ChessPiece {
	
	private ChessPart chessPart;
	private ChessRole chessRole;
	private int x;
	private int y;
	private int gridRow;
	private int gridCol;
	private Color color;
	private boolean isSelected;
	
	public ChessPiece(int gridRow, int gridCol, ChessPart chessPart, ChessRole chessRole) {
		// TODO Auto-generated constructor stub
		this.x = 80+gridCol*50;
		this.y = 50+gridRow*50;
		this.gridRow = gridRow;
		this.gridCol = gridCol;
		this.chessPart = chessPart;
		this.chessRole = chessRole;
		if(chessPart == ChessPart.Human){
			this.color = Color.red;
		}else {
			this.color = Color.blue;
		}
		this.isSelected = false;
	}
	
	public static ChessPiece getIntersteller(){
		ChessPiece intersteller = new ChessPiece(0,0,ChessPart.Human,ChessRole.intersteller);
		return intersteller;
	}
	
	public static ChessPiece getHeadquater(){
		ChessPiece headquater = new ChessPiece(0,4,ChessPart.Human,ChessRole.headquater);
		return headquater;
	}
	
	public static ChessPiece getMatrix(){
		ChessPiece Matrix = new ChessPiece(9,4,ChessPart.Silicono,ChessRole.Matrix);
		return Matrix;
	}
	
	public static ChessPiece getGuardiansOfMatrix(){
		ChessPiece guardiansOfMatrix = new ChessPiece(9,3,ChessPart.Silicono,ChessRole.GuardiansOfMatrix);
		return guardiansOfMatrix;
	}
	
//	public static ChessPiece getTelepoter(){
//		ChessPiece Teleporter = new ChessPiece(65+50*3,515,ChessPart.Silicono,ChessRole.Teleporter);
//		return Teleporter;
//	}
	
	public ChessPart getChessPart() {
		return chessPart;
	}

	public void setChessPart(ChessPart chessPart) {
		this.chessPart = chessPart;
	}

	public ChessRole getChessRole() {
		return chessRole;
	}

	public void setChessRole(ChessRole chessRole) {
		this.chessRole = chessRole;
	}

	public int getX() {
		return x;
	}

	

	public int getY() {
		return y;
	}

	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getGridRow() {
		return gridRow;
	}

	public void setGridRow(int gridRow) {
		this.gridRow = gridRow;
		this.y = 50+gridRow*50;
	}

	public int getGridCol() {
		return gridCol;
	}

	public void setGridCol(int gridCol) {
		this.gridCol = gridCol;
		this.x = 80+gridCol*50; 
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	
	
	
	
}
