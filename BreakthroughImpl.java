public class BreakthroughImpl implements Breakthrough {

private PieceType board[][] = new PieceType [8][8];
private PlayerType currentTurn = PlayerType.WHITE;
private PlayerType winner = null; 
  public PieceType getPieceAt( int row, int column ) 
  {
    return board[row][column];
  }
  
  public PlayerType getPlayerInTurn() 
  {
    return currentTurn;
  }

  public PlayerType getWinner() {
    return winner;
  }

  public boolean isMoveValid(int fromRow, int fromColumn,
                             int toRow, int toColumn) {
	    
	  // is it the players piece?
	  if( getPieceAt(fromRow, fromColumn) == PieceType.WHITE && currentTurn == PlayerType.WHITE 
			      || (getPieceAt(fromRow, fromColumn) ==PieceType.BLACK && currentTurn == PlayerType.BLACK ))
	  {
		  
		  
		  // is move to an open spot? 
		  	if( getPieceAt(toRow, toColumn) == PieceType.NONE )
		  	{
		  		
		  		// and in a  diagonal, forward spot 1 away
		  			
		  		if( currentTurn == farSide() )
		  			{ 
		  		
		  			  // new spots are not out of bounds
		  			  if( toRow == fromRow-1 && fromRow != 0 && toColumn > 0 && toColumn <= 7)
		  			  {
		  				
		  				  if( toColumn == fromColumn-1 || toColumn == fromColumn || toColumn == fromColumn+1 )
		  					  return true;
		  			  }
		  				
		  			}	
		  		
		  		if( currentTurn == nearSide() )
		  		{
		  			
		  			 // new spots are not out of bounds
		  			  if( toRow == fromRow+1 && fromRow != 7 && toColumn > 0 && toColumn <= 7)
		  			  { 
		  				  if( toColumn == fromColumn-1 || toColumn == fromColumn || toColumn == fromColumn+1 )
		  					  return true;
		  			  }
		  		}
		  	}
		  	else if( getPieceAt(toRow, toColumn) == PieceType.WHITE && currentTurn == PlayerType.BLACK 
				      || (getPieceAt(toRow, toColumn) == PieceType.BLACK && currentTurn == PlayerType.WHITE ))
		  			{		
		  				
		  			// can not capture pieces in directly forward
		  				if( toColumn == fromColumn )
		  				{
		  					return false; 
		  				}
		  				
		  				else if( currentTurn == farSide() )
			  			{ 
				  			  // new spots are not out of bounds
				  			  if( toRow == fromRow-1 && fromRow != 0 && toColumn > 0 && toColumn <= 7)
				  			  {
				  				  if( toColumn == fromColumn-1 || toColumn == fromColumn+1 )
				  					  return true;
				  			  }
			  			}	
		  				
		  				else if( currentTurn == nearSide() )
				  		{
		  					
				  			 // new spots are not out of bounds
				  			  if( toRow == fromRow+1 && fromRow != 7 && toColumn > 0 && toColumn <= 7)
				  			  { 
				  				  if( toColumn == fromColumn-1 || toColumn == fromColumn+1 )
				  					  return true;
				  			  }
				  		}
		  				
		  			}
	  }
	   return false; 
  }

  public void move(int fromRow, int fromColumn,
                   int toRow, int toColumn) {
	  
	 if( isMoveValid(fromRow, fromColumn, toRow, toColumn) )
	 {
			 Breakthrough.PieceType movingPiece = this.board[fromRow][fromColumn];
			 this.board[fromRow][fromColumn] = PieceType.NONE;
			 this.board[toRow][toColumn] = movingPiece; 
	 }
	 
	 // check for a winner 
	  
	  // next players turn
	  if(currentTurn == PlayerType.WHITE)
	  {
		  currentTurn = PlayerType.BLACK;
	  }
	  else 
		  currentTurn = PlayerType.WHITE; 
  }
  
  // Determine color on the near side 
  private PlayerType nearSide()
  {
	  
	 if( getPieceAt(0,0) == PieceType.WHITE )
		   return PlayerType.WHITE ;
	 else
		 return PlayerType.BLACK;
  }
  // Determine color on the far side at the beginning of the game
  private PlayerType farSide()
  {
	  if( getPieceAt(7,7) == PieceType.WHITE )
		   return PlayerType.WHITE ;
	 else
		 return PlayerType.BLACK;
  }
  
  public void putBlackPiece(int row, int column)
  {
	  board[row][column] = PieceType.BLACK;
  }
  
  public void putWhitePiece( int row, int column)
  {
	  board[row][column] = PieceType.WHITE;
  }
  
  public void putNullPiece ( int row, int column )
  {
	  board[row][column] = PieceType.NONE;
  }
  
  private PlayerType isWinner()
  {
	  // far side winner
	  PlayerType far = farSide();
	  PlayerType near = nearSide(); 
	  for(int i=0; i<7; i++)
	  {
		  if( this.board[0][i].toString() == far.toString() )
		  {
			  winner = farSide(); 
		  }
		  
		  if(this.board[7][i].toString() == near.toString() )
		  {
			  winner = nearSide(); 
		  }
	  }
	  return winner; 
  }
  
	public void startGame()
	{
		for( int i = 0; i<2; i++)
			for( int j = 0; j<8; j++)
			{
				{
					this.putWhitePiece(i, j);
					this.putBlackPiece(7-i, j);
					
				}
			}
		for( int fff = 2; fff<6; fff++ )
		{
			for( int ggg = 0; ggg < 8; ggg++)
			{
				this.putNullPiece(fff, ggg);
			}
			
		}
	
	}
  
}
