package com.force4us;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ChessBoard {
	
	//private List<ChessPiece> chessPieceList = new ArrayList<ChessPiece>();
	ChessPiece selectedChessPiece;
	ChessPiece[][] chessPieces = new ChessPiece[10][9];
	private boolean isRestart = false;
	

	public boolean isRestart() {
		return isRestart;
	}

	public void setRestart(boolean isRestart) {
		this.isRestart = isRestart;
	}

	public void addChessPiece(ChessPiece chessPiece){
		int rowChessPiece = chessPiece.getGridRow();
		int colChessPiece = chessPiece.getGridCol();
		chessPieces[rowChessPiece][colChessPiece] = chessPiece;
	}
	
	public void moveGuardiansOfMatrix(int xStart, int yStart, int xEnd, int yEnd){
			
			int StartRow = (yStart-50)/50;
			int StartCol = (xStart-80)/50;
			int EndRow = (yEnd-50)/50;
			int EndCol = (xEnd-80)/50;			
			if(EndRow>=7 && EndRow<=9 && EndCol>=3 && EndCol<=5 && Math.abs(EndCol-StartCol)==1 && Math.abs(EndRow-StartRow)==1){
				chessPieces[EndRow][EndCol] = chessPieces[StartRow][StartCol];
				chessPieces[StartRow][StartCol]=null;
				chessPieces[EndRow][EndCol].setGridRow(EndRow);
				chessPieces[EndRow][EndCol].setGridCol(EndCol);
				chessPieces[EndRow][EndCol].setSelected(false);					
				selectedChessPiece = null;
				}
			else {
				return;
			}
		
		
	}
	
	public void moveMatrix(int xStart, int yStart, int xEnd, int yEnd){
		int StartRow = (yStart-50)/50;
		int StartCol = (xStart-80)/50;
		int EndRow = (yEnd-50)/50;
		int EndCol = (xEnd-80)/50;			
		if(EndRow>=0 && EndRow<=9 && EndCol>=0 && EndCol<=9 && 
				((Math.abs(EndCol-StartCol)==0 && Math.abs(EndRow-StartRow)==1)||
						(Math.abs(EndCol-StartCol)==1 && Math.abs(EndRow-StartRow)==0))){
			chessPieces[EndRow][EndCol] = chessPieces[StartRow][StartCol];
			chessPieces[StartRow][StartCol]=null;
			chessPieces[EndRow][EndCol].setGridRow(EndRow);
			chessPieces[EndRow][EndCol].setGridCol(EndCol);
			chessPieces[EndRow][EndCol].setSelected(false);					
			selectedChessPiece = null;
			}
		else {
			return;
		}
	}
	
	public void moveIntersteller(int xStart, int yStart, int xEnd, int yEnd) {
		// TODO Auto-generated method stub
		
			int StartRow = (yStart-50)/50;
			int StartCol = (xStart-80)/50;
			int EndRow = (yEnd-50)/50;
			int EndCol = (xEnd-80)/50;			
			if(EndRow>=0 && EndRow<=9 && EndCol>=0 && EndCol<=9 && 
					((Math.abs(EndCol-StartCol)==0 &&  Math.abs(EndRow-StartRow)>=0 &&  Math.abs(EndRow-StartRow)<=9) ||
					(Math.abs(EndRow-StartRow)==0 &&  Math.abs(EndCol-StartCol)>=0 &&  Math.abs(EndCol-StartCol)<=9))){
				
				chessPieces[EndRow][EndCol] = chessPieces[StartRow][StartCol];
				chessPieces[StartRow][StartCol]=null;
				chessPieces[EndRow][EndCol].setGridRow(EndRow);
				chessPieces[EndRow][EndCol].setGridCol(EndCol);
				chessPieces[EndRow][EndCol].setSelected(false);					
				selectedChessPiece = null;
				
				}
			else {
				return;
			}
		
	}
	
	public void moveHeadquater(int xStart, int yStart, int xEnd, int yEnd){
		int StartRow = (yStart-50)/50;
		int StartCol = (xStart-80)/50;
		int EndRow = (yEnd-50)/50;
		int EndCol = (xEnd-80)/50;			
		if(EndRow>=0 && EndRow<=9 && EndCol>=0 && EndCol<=9 && 
				((Math.abs(EndCol-StartCol)==0 && Math.abs(EndRow-StartRow)==1)||
						(Math.abs(EndCol-StartCol)==1 && Math.abs(EndRow-StartRow)==0))){
			chessPieces[EndRow][EndCol] = chessPieces[StartRow][StartCol];
			chessPieces[StartRow][StartCol]=null;
			chessPieces[EndRow][EndCol].setGridRow(EndRow);
			chessPieces[EndRow][EndCol].setGridCol(EndCol);
			chessPieces[EndRow][EndCol].setSelected(false);					
			selectedChessPiece = null;
			}
		else {
			return;
		}
	}
	
	public void eatGuardiansOfMatrix(int StartRow, int StartCol, int EndRow, int EndCol) {
		// TODO Auto-generated method stub
	
			if(EndRow>=7 && EndRow<=9 && EndCol>=3 && EndCol<=5 && Math.abs(EndCol-StartCol)==1 && Math.abs(EndRow-StartRow)==1){
				for(ChessPiece chessPiece : this.getChessPieceList()){
					if(chessPiece == chessPieces[EndRow][EndCol]){
						this.getChessPieceList().remove(chessPiece);
					}
				}
				chessPieces[EndRow][EndCol]=chessPieces[StartRow][StartCol];
				chessPieces[StartRow][StartCol]=null;
				chessPieces[EndRow][EndCol].setGridRow(EndRow);
				chessPieces[EndRow][EndCol].setGridCol(EndCol);
				chessPieces[EndRow][EndCol].setSelected(false);
				selectedChessPiece = null;
			}
	
	}
	
	public ChessPiece eatMatrix(int StartRow, int StartCol, int EndRow, int EndCol){
		if(EndRow>=0 && EndRow<=9 && EndCol>=0 && EndCol<=9 && 
				((Math.abs(EndCol-StartCol)==0 && Math.abs(EndRow-StartRow)==1)||
						(Math.abs(EndCol-StartCol)==1 && Math.abs(EndRow-StartRow)==0))){
			for(ChessPiece chessPiece : this.getChessPieceList()){
				if(chessPiece == chessPieces[EndRow][EndCol]){
					this.getChessPieceList().remove(chessPiece);
				}
			}
			ChessPiece chessPiece = chessPieces[EndRow][EndCol];
			chessPieces[EndRow][EndCol] = chessPieces[StartRow][StartCol];
			chessPieces[StartRow][StartCol]=null;
			chessPieces[EndRow][EndCol].setGridRow(EndRow);
			chessPieces[EndRow][EndCol].setGridCol(EndCol);
			chessPieces[EndRow][EndCol].setSelected(false);					
			selectedChessPiece = null;
			return chessPiece;
			}
		else {
			return null;
		}
	}
	
	public void eatHeadquater(int StartRow, int StartCol, int EndRow, int EndCol){
		if(EndRow>=0 && EndRow<=9 && EndCol>=0 && EndCol<=9 && 
				((Math.abs(EndCol-StartCol)==0 && Math.abs(EndRow-StartRow)==1)||
						(Math.abs(EndCol-StartCol)==1 && Math.abs(EndRow-StartRow)==0))){
			for(ChessPiece chessPiece : this.getChessPieceList()){
				if(chessPiece == chessPieces[EndRow][EndCol]){
					this.getChessPieceList().remove(chessPiece);
				}
			}
			
			chessPieces[EndRow][EndCol] = chessPieces[StartRow][StartCol];
			chessPieces[StartRow][StartCol]=null;
			chessPieces[EndRow][EndCol].setGridRow(EndRow);
			chessPieces[EndRow][EndCol].setGridCol(EndCol);
			chessPieces[EndRow][EndCol].setSelected(false);					
			selectedChessPiece = null;
			
			}
		else {
			return;
		}
	}
	
	public ChessPiece eatIntersteller(int StartRow, int StartCol, int EndRow, int EndCol){
		if(EndRow>=0 && EndRow<=9 && EndCol>=0 && EndCol<=9 && 
				((Math.abs(EndCol-StartCol)==0 &&  Math.abs(EndRow-StartRow)>=0 &&  Math.abs(EndRow-StartRow)<=9) ||
				(Math.abs(EndRow-StartRow)==0 &&  Math.abs(EndCol-StartCol)>=0 &&  Math.abs(EndCol-StartCol)<=9))){
			for(ChessPiece chessPiece : this.getChessPieceList()){
				if(chessPiece == chessPieces[EndRow][EndCol]){
					this.getChessPieceList().remove(chessPiece);
				}
			}
			ChessPiece chessPiece = chessPieces[EndRow][EndCol];
			chessPieces[EndRow][EndCol] = chessPieces[StartRow][StartCol];
			chessPieces[StartRow][StartCol]=null;
			chessPieces[EndRow][EndCol].setGridRow(EndRow);
			chessPieces[EndRow][EndCol].setGridCol(EndCol);
			chessPieces[EndRow][EndCol].setSelected(false);					
			selectedChessPiece = null;
			return chessPiece;
			}
		else {
			return null;
		}
		
	}
	
	
	public ChessPiece getChessPiece(int xBoard, int yBoard){
		for(int i = 0;i<10;i++){
			for(int j = 0;j<9;j++){
				if(chessPieces[i][j] != null){
					double distance = Math.sqrt((Math.pow(xBoard-(80+chessPieces[i][j].getGridCol()*50),2)
							+Math.pow(yBoard-(50+50*chessPieces[i][j].getGridRow()),2)));
					//System.out.println(distance);
					if(distance<25){
						return chessPieces[i][j];
					}
				}
				
			}
		}
		return null;
	}
	
	public ChessPiece getSelectedChessPiece(){
		if (isRestart) {
			selectedChessPiece = null;
			return selectedChessPiece;
		}else {
			return selectedChessPiece;
		}
	}
	
	public void setSelectedChessPiece(ChessPiece chessPiece){
		
			chessPiece.setSelected(true);
			selectedChessPiece = chessPiece;
		
		
	}
	
	
	public List<ChessPiece> getChessPieceList(){
		List<ChessPiece> chessPieceList = new ArrayList<ChessPiece>();
		for(int i = 0;i<10;i++){
			for(int j = 0;j<9;j++){
				if(chessPieces[i][j] != null){
					chessPieceList.add(chessPieces[i][j]);
				}
			}
		}
		return chessPieceList;
	}
	
	public void clearChessBoard(){
		for(int i=0;i<10;i++){
			for(int j=0;j<9;j++){
				chessPieces[i][j]=null;
			}
		}
	}
	

}
