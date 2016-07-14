package com.force4us;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class Layout extends JFrame implements MouseListener,ActionListener{
	


	private JPanel jpanelChessBoard;
	private JPanel jpControl;
	private JButton restartButton;
	private ChessBoard chessBoard = new ChessBoard();
	private boolean isInitPaint = true;
	private int count = 0;
	ChessPart currentChessPart = ChessPart.Silicono;
	
	public Layout(){
		setTitle("中国象棋");
		jpanelChessBoard = new JPanel();
		jpControl = new JPanel();
		restartButton = new JButton("Restart");
		JSplitPane jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true,jpanelChessBoard,jpControl);
		jpanelChessBoard.setBackground(Color.white);
		jpControl.add(restartButton);
		jSplitPane.setDividerLocation(540);
		this.add(jSplitPane, BorderLayout.CENTER);
		jpanelChessBoard.addMouseListener(this);
		restartButton.addActionListener(this);
		setSize(800,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	

	
	public void drawChessBoard(Graphics g){
//		if (null == g){
//			System.out.println("sadfsdf");
//		}
		Color color = g.getColor();
//		System.out.println(color);
		g.setColor(Color.black);
		for(int i=0; i<10; i++){
			g.drawLine(80,50+50*i,80+50*8,50+50*i);
		}
		for(int i=0; i<9; i++){
			g.drawLine(80+50*i,50,80+50*i,50*5);
			g.drawLine(80+50*i,50+50*5,80+50*i,50+50*9);
		}
		g.drawLine(80,50*5,80,50*6);
		g.drawLine(80+8*50,50*5,80+50*8,50*6);
		g.drawLine(80+50*3,50,80+50*5,50*3);
		g.drawLine(80+50*3,50*3,80+50*5,50);
		g.drawLine(80+50*3,50*10,80+50*5,50*8);
		g.drawLine(80+50*3,50*8,80+50*5,50*10);
		g.setColor(color);
	}

	public void drawChessPieceInit(Graphics g){
		
		Color colorOld = g.getColor();
		g.setFont(new Font("Default",Font.BOLD,30));
		chessBoard.addChessPiece(ChessPiece.getMatrix());
		chessBoard.addChessPiece(ChessPiece.getIntersteller());
		chessBoard.addChessPiece(ChessPiece.getGuardiansOfMatrix());
		chessBoard.addChessPiece(ChessPiece.getHeadquater());
		
//		for(ChessPiece chessPieces : chessBoard.getChessPieceList()){
//			System.out.println(chessPieces.getChessRole().toString()+" "+chessPieces.isSelected());
//		}
		
		for(ChessPiece chessPiece : chessBoard.getChessPieceList()){
			System.out.println(chessPiece.getChessRole()+", "+chessPiece.isSelected());
			g.setColor(chessPiece.getColor());
			g.drawString(chessPiece.getChessRole().toString().substring(0,1),
				chessPiece.getX()-10,chessPiece.getY()+10);
		}
//		g.drawString(chessBoard.getTelepoter().getChessRole().substring(0,1),chessBoard.getTelepoter().getX(),chessBoard.getTelepoter().getY());
		g.setColor(colorOld);
		isInitPaint = false;
	}
	
	public void draw(Graphics g){
		Color colorOld = g.getColor();
		g.setFont(new Font("Default",Font.BOLD,30));
		for(ChessPiece chessPiece : chessBoard.getChessPieceList()){
			g.setColor(chessPiece.getColor());
			g.drawString(chessPiece.getChessRole().toString().substring(0,1),
				chessPiece.getX()-10,chessPiece.getY()+10);
//			System.out.println(chessPiece.getGridRow()+" , "+chessPiece.getGridCol());
		}
		g.setColor(colorOld);
	}
	

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g = jpanelChessBoard.getGraphics();
		drawChessBoard(g);
		if (isInitPaint) {
			
			drawChessPieceInit(g);
//			if(chessBoard.getSelectedChessPiece()!=null){
//				chessBoard.getSelectedChessPiece().setSelected(false);
//				System.out.println(chessBoard.getSelectedChessPiece().getChessRole().toString());
//			}

		}
		else {
			draw(g);
			if(chessBoard.getSelectedChessPiece()!=null){
				System.out.println(chessBoard.getSelectedChessPiece().getChessRole().toString());
			}
		}
		
	
	}



	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		int xBoard = e.getX();
		int yBoard = e.getY();
		int row = -1;
		int col = -1;
		for(int i = 0;i<10;i++){
			for(int j = 0;j<9;j++){
				double distance = Math.sqrt((Math.pow(xBoard-(80+j*50),2)
							+Math.pow(yBoard-(50+50*i),2)));
				//System.out.println(distance);
				if(distance<25){
					row = i;
					col = j;
				}
			}
		}
		if (row == -1 ||col == -1) {
			return;
		}
		
//		System.out.println(xBoard);
		ChessPiece chessPiece = chessBoard.getChessPiece(xBoard,yBoard);
		if (chessPiece == null ) {
				if (chessBoard.getSelectedChessPiece()==null) {
					System.out.println("sdf");
					return;
				}else {
					
					ChessPiece chessPiece2 = chessBoard.getSelectedChessPiece(); 
					System.out.println("当前被选中的"+chessPiece2.getChessRole());
					switch (chessPiece2.getChessRole()) {
					case GuardiansOfMatrix:
						chessBoard.moveGuardiansOfMatrix(chessPiece2.getX(),chessPiece2.getY(),
									80+50*col,50+50*row);
						count++;
						System.out.println("after silicon move "+count);
						break;
					case intersteller:
						chessBoard.moveIntersteller(chessPiece2.getX(),chessPiece2.getY(),
								80+50*col,50+50*row);
						count++;
						System.out.println("after human move "+ count);
						break;
					case Matrix:
						chessBoard.moveMatrix(chessPiece2.getX(),chessPiece2.getY(),
								80+50*col,50+50*row);
						break;
					case headquater:
						chessBoard.moveHeadquater(chessPiece2.getX(),chessPiece2.getY(),
								80+50*col,50+50*row);
						break;
					default:
						System.out.println("选错棋子了");
						break;
					}
//					System.out.println("当前被选中的"+chessBoard.getSelectedChessPiece().getChessRole());
					repaint();
					//chessBoard.test();
					return;
				}
		}else {
			
			if(chessBoard.getSelectedChessPiece()!=null){
				if(chessPiece.getChessPart()==chessBoard.getSelectedChessPiece().getChessPart()){
					chessBoard.getSelectedChessPiece().setSelected(false);
					chessBoard.setSelectedChessPiece(chessPiece);
				}
				else {
					ChessPiece chessPiece3 = chessBoard.getSelectedChessPiece();
					chessBoard.setSelectedChessPiece(chessPiece);
					chessPiece3.setSelected(false);
					System.out.println(chessPiece.getChessRole().toString()+chessPiece3.getChessRole().toString());
					ChessPiece chessPieceFinal = null;
					switch (chessPiece3.getChessRole()) {
					case GuardiansOfMatrix:
						chessBoard.eatGuardiansOfMatrix(chessPiece3.getGridRow(),chessPiece3.getGridCol(),chessPiece.getGridRow(),chessPiece.getGridCol());
						break;
					case Matrix:
						chessPieceFinal = chessBoard.eatMatrix(chessPiece3.getGridRow(),chessPiece3.getGridCol(),chessPiece.getGridRow(),chessPiece.getGridCol());
						break;
					case intersteller:
						chessPieceFinal = chessBoard.eatIntersteller(chessPiece3.getGridRow(),
								chessPiece3.getGridCol(),chessPiece.getGridRow(),chessPiece.getGridCol());
					case headquater:
						chessBoard.eatHeadquater(chessPiece3.getGridRow(),chessPiece3.getGridCol(),chessPiece.getGridRow(),chessPiece.getGridCol());
						break;
					default:
						System.out.println("吃失败");
						break;
					}
					repaint();
					Judge(chessPieceFinal);
					return;
				}
				
			} 
			else {
				
				if(chessPiece.getChessPart() == ChessPart.Silicono & currentChessPart == ChessPart.Silicono){
					chessBoard.setSelectedChessPiece(chessPiece);
					currentChessPart = ChessPart.Human;
				}
				if(chessPiece.getChessPart() == ChessPart.Human & currentChessPart == ChessPart.Human){
					chessBoard.setSelectedChessPiece(chessPiece);
					currentChessPart = ChessPart.Silicono;
				}
				
				chessBoard.setRestart(false);
				
			}
				
		}
		
		System.out.println(chessPiece.getChessRole());
//		for(ChessPiece chessPieces : chessBoard.getChessPieceList()){
//			System.out.println(chessPieces.getChessRole().toString()+" "+chessPieces.isSelected());
//			if(chessPieces.getChessRole().equals(ChessRole.headquater)
//					&& chessPieces.getChessRole().equals(ChessRole.Matrix)){
//				return;
//			}
//			else {
//				if(chessPieces.getChessRole().equals(ChessRole.headquater)){
//					JOptionPane.showInternalMessageDialog(jpanelChessBoard,"红方胜","判断输赢",JOptionPane.INFORMATION_MESSAGE);
//				}
//			}
//		}
	}



	private void Judge(ChessPiece chessPieceFinal) {
		// TODO Auto-generated method stub
		if(chessPieceFinal.getChessRole().equals(ChessRole.headquater)){
			JOptionPane.showMessageDialog(null,"蓝方胜","判断输赢",JOptionPane.INFORMATION_MESSAGE);
		}
		if(chessPieceFinal.getChessRole().equals(ChessRole.Matrix)){
			JOptionPane.showMessageDialog(null,"红方胜","判断输赢",JOptionPane.INFORMATION_MESSAGE);
		}
	}



	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		isInitPaint=true;
		currentChessPart = ChessPart.Silicono;
		chessBoard.clearChessBoard();
		repaint();
		chessBoard.setRestart(true);

		
		
		
		
	}
	
	
}
