/**
 * Player class for adventure park that simply has a balance.
 * @author Joshua Henderson
 */

public class Player {
	public int balance;

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public Player() {
		this.balance = 100;
	}
}
