/*changePanel
 * Used to swap divs that are of the same container type (css class)
 * panel - the id of the panel to display
 * containers - the class that is associated with the group of divs
 */

function changePanel(CollectionName,TabName)
{
	jQuery('.'+CollectionName).hide();
	jQuery('#'+TabName).fadeIn();
	return false;
}
			