package cu.cs.cpsc2150.project2;

/**
 * The Observer interface is implemented by the checkout and return Transactions in the Checkout tab
 * @author nickbarrs
 *
 */
public interface Observer {
	/**
	 * Implemented by Transactions
	 */
	public void Notify();
}
