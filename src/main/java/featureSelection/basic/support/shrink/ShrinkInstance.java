package featureSelection.basic.support.shrink;

/**
 * An interface for Universe instance shrinking(reducing) by removing instances.
 *
 * @param <Input>
 *     	Type of input for streamline.
 * @param <InputItem>
 *     	Type of item of input for streamline.
 * @param <Output>
 *      Type of output as the result of streamline.
 */
public interface ShrinkInstance<Input, InputItem, Output> {
	/**
	 * Execute instance removing.
	 *
	 * @param in
	 * 		Input for shrinking.
	 * @return {@link Output}.
	 * @throws Exception if exceptions occur.
	 */
	Output shrink(Input in) throws Exception;

	/**
	 * Get if the item is the kind of removable item.
	 *
	 * @param item
	 * 		Item to be checked.
	 * @return true if removable.
	 */
	boolean removAble(InputItem item);
}