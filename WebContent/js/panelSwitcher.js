/*changePanel
 * Used to swap divs that are of the same container type (css class)
 * panel - the id of the panel to display
 * containers - the class that is associated with the group of divs
 */
			jQuery(document).ready(function($) {				
				$('#content_2_viewUser').click(function(){
					$('.subContent2').hide();
					$('#viewUser').fadeIn();
					return false;
				});
				$('#content_2_addUser').click(function(){
					$('.subContent2').hide();
					$('#addUser').fadeIn();;
					return false;
				});
				$('#content_2_deleteUser').click(function(){
					$('.subContent2').hide();
					$('#deleteUser').fadeIn();
					return false;
				});
				$('#content_2_mailingList').click(function(){
					$('.subContent2').hide();
					$('#mailingList').fadeIn();
					return false;
				});
			});