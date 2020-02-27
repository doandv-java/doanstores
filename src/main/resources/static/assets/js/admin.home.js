
jQuery(document).ready( function($) {

});
function logoutConfirm() {
    jQuery('#logoutConfirm').modal('show');
    jQuery('#deleteButton').click(function () {
        //gui den api logout
        console.log("Logout");
        jQuery('#logoutConfirm').modal('hide');
    });
}
function loadImage(input) {
    if (input.files && input.files[0]) {
        let reader = new FileReader();
        reader.onload = function (e) {
            jQuery('#image_view').attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
    }
}
