
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestBreakthrough {
BreakthroughImpl bt; 

	@Before
	public void setUp() throws Exception 
	{
		bt = new BreakthroughImpl(); 
	}

	@Test 
	public void testSwitchTurns()
	{
		assertEquals(bt.getPlayerInTurn(), Breakthrough.PlayerType.WHITE );
	}

	@Test 
	public void testSwitchTurnsTwice()
	{
		bt.move(1, 1, 2, 1);
		assertEquals(bt.getPlayerInTurn(), Breakthrough.PlayerType.BLACK);
	}
	
	@Test
	public void testGetPieceAt()
	{
		bt.putBlackPiece(7,7);
		assertEquals(Breakthrough.PieceType.BLACK, bt.getPieceAt(7, 7));
	}
	
	@Test 
	public void testValidMoveFromTop()
	{
		bt.move(1, 1, 2, 1);
		bt.putNullPiece(6, 6);
		bt.putBlackPiece(7,7);
		assertTrue( bt.isMoveValid(7, 7, 6, 6));
	}
	
	@Test
	public void testValidMoveFromBottom()
	{
		bt.putNullPiece(1,1);
		bt.putWhitePiece(0, 0);
		assertTrue( bt.isMoveValid(0, 0, 1, 1) );
	}
	
	@Test 
	public void testValidMovetoOccupied()
	{
		bt.putBlackPiece(1, 1);
		bt.putWhitePiece(0,0);
		assertTrue( bt.isMoveValid(0,0,1,1) );
	}
	
	@Test 
	public void testMove()
	{
		bt.putNullPiece(1, 1);
		bt.putWhitePiece(0,0);
		bt.move(0, 0, 1, 1);
		assertEquals(bt.getPieceAt(1, 1), Breakthrough.PieceType.WHITE );
	}
	
	
	@Test
	public void testStartGame() 
	{
		bt.startGame();
		
		assertEquals("White piece on (0,7)", BreakthroughImpl.PieceType.WHITE, bt.getPieceAt(0, 7));
		assertEquals("White piece on (1,3)", BreakthroughImpl.PieceType.WHITE, bt.getPieceAt(1, 3));
		assertEquals("Black piece on (6,6)", BreakthroughImpl.PieceType.BLACK, bt.getPieceAt(6, 6));
		assertEquals("Black piece on (7,1)", BreakthroughImpl.PieceType.BLACK, bt.getPieceAt(7, 1));
		assertEquals("No piece at (5,3)", BreakthroughImpl.PieceType.NONE, bt.getPieceAt(5, 3));
	}
}





